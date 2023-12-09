package com.ar.cac.homebanking.services;

import com.ar.cac.homebanking.exceptions.AccountNotFoundException;
import com.ar.cac.homebanking.exceptions.InsufficientFoundsException;
import com.ar.cac.homebanking.exceptions.TransferNotFoundException;
import com.ar.cac.homebanking.mappers.TransferMapper;
import com.ar.cac.homebanking.models.Account;
import com.ar.cac.homebanking.models.Transfer;
import com.ar.cac.homebanking.models.dtos.TransferDTO;
import com.ar.cac.homebanking.repositories.AccountRepository;
import com.ar.cac.homebanking.repositories.TransferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService {

    private final TransferRepository repository;

    private final AccountRepository accountRepository;

    public TransferService(TransferRepository repository, AccountRepository accountRepository){
        this.repository = repository;
        this.accountRepository = accountRepository;
    }

    public List<TransferDTO> getTransfers(){
        List<Transfer> transfers = repository.findAll();
        return transfers.stream()
                .map(TransferMapper::transferToDto)
                .collect(Collectors.toList());
    }

    public TransferDTO getTransferById(Long id){
        Transfer transfer = repository.findById(id).orElseThrow(() ->
                new TransferNotFoundException("Transfer not found with id: " + id));
        return TransferMapper.transferToDto(transfer);
    }

    public List<TransferDTO> getTransfersExecutedByAccount(Long originAccount){
        List<Transfer> transfers = repository.findByOriginAccount(originAccount);
        return transfers.stream()
                .map(TransferMapper::transferToDto)
                .collect(Collectors.toList());
    }

    public List<TransferDTO> getTransfersTakenByAccount(Long targetAccount){
        List<Transfer> transfers = repository.findByTargetAccount(targetAccount);
        return transfers.stream()
                .map(TransferMapper::transferToDto)
                .collect(Collectors.toList());
    }

    public TransferDTO updateTransfer(Long id, TransferDTO transferDto){
        Transfer transfer = repository.findById(id).orElseThrow(() -> new TransferNotFoundException("Transfer not found with id: " + id));
        Transfer updatedTransfer = TransferMapper.dtoToTransfer(transferDto);
        updatedTransfer.setTransferId(transfer.getTransferId());
        return TransferMapper.transferToDto(repository.save(updatedTransfer));
    }

    public String deleteTransfer(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return "Se ha eliminado la transferencia";
        } else {
            return "No se ha eliminado la transferencia";
        }
    }

    @Transactional
    public TransferDTO performTransfer(TransferDTO dto) {
        // Comprobar si las cuentas de origen y destino existen
        Account originAccount = accountRepository.findById(dto.getOriginAccount())
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + dto.getOriginAccount()));
        Account destinationAccount = accountRepository.findById(dto.getTargetAccount())
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + dto.getTargetAccount()));

        // Comprobar si la cuenta de origen tiene fondos suficientes
        if (originAccount.getAmount().compareTo(dto.getTransferAmount()) < 0) {
            throw new InsufficientFoundsException("Insufficient funds in the account with id: " + dto.getOriginAccount());
        }

        // Realizar la transferencia
        originAccount.setAmount(originAccount.getAmount().subtract(dto.getTransferAmount()));
        destinationAccount.setAmount(destinationAccount.getAmount().add(dto.getTransferAmount()));

        // Guardar las cuentas actualizadas
        accountRepository.save(originAccount);
        accountRepository.save(destinationAccount);

        // Crear la transferencia y guardarla en la base de datos
        Transfer transfer = new Transfer();
        // Creamos un objeto del tipo Date para obtener la fecha actual
        LocalDate date = LocalDate.now();
        // Seteamos el objeto fecha actual en el transferDto
        transfer.setDate(date);
        transfer.setOriginAccount(originAccount.getAccountId());
        transfer.setTargetAccount(destinationAccount.getAccountId());
        transfer.setTransferAmount(dto.getTransferAmount());
        transfer = repository.save(transfer);

        // Devolver el DTO de la transferencia realizada
        return TransferMapper.transferToDto(transfer);
    }


}
