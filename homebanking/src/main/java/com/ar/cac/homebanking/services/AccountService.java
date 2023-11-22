package com.ar.cac.homebanking.services;

import com.ar.cac.homebanking.exceptions.UserNotExistsException;
import com.ar.cac.homebanking.mappers.AccountMapper;
import com.ar.cac.homebanking.mappers.UserMapper;
import com.ar.cac.homebanking.models.Account;
import com.ar.cac.homebanking.models.User;
import com.ar.cac.homebanking.models.dtos.AccountDTO;
import com.ar.cac.homebanking.models.dtos.UserDTO;
import com.ar.cac.homebanking.models.enums.AccountType;
import com.ar.cac.homebanking.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    private final AccountRepository repository;
    public AccountService(AccountRepository repository){
        this.repository=repository;
    }
    public List<AccountDTO> getAccounts(){
        List<Account> accounts = repository.findAll();
        return accounts.stream()
                .map(AccountMapper::accountToDto)
                .collect(Collectors.toList());
    }
    public AccountDTO createAccount(AccountDTO dto){
        dto.setTypeAccount(AccountType.PESOS_SAVING_BANK);
        dto.setAmount(BigDecimal.ZERO);
        Account newAccount = repository.save(AccountMapper.dtoToAccount(dto));
        return AccountMapper.accountToDto(newAccount);
    }
    public AccountDTO getAccountById(Long accountId){
        Account entity = repository.findById(accountId).get();
        return AccountMapper.accountToDto(entity);
    }
    public String deleteAccount(Long accountId){
        if(repository.existsById(accountId)) {
            repository.deleteById(accountId);
            return "Cuenta con id:" + accountId + "ha sido eliminada.";
        } else {
            throw  new UserNotExistsException("la cuenta elegida para eliminar, no existe.");
        }
    }

    public AccountDTO updateAccount(Long id, AccountDTO dto) {
        if (repository.existsById(id)){
            Account accountToModify = repository.findById(id).get();

            if (dto.getTypeAccount() != null){
                accountToModify.setTypeAccount(dto.getTypeAccount());
            }

            if (dto.getAlias() != null){
                accountToModify.setAlias(dto.getAlias());
            }

            if (dto.getCbu() != null){
                accountToModify.setCbu(dto.getCbu());
            }

            if (dto.getAmount() != null){
                accountToModify.setAmount(dto.getAmount());
            }

            Account accountModified = repository.save(accountToModify);

            return AccountMapper.accountToDto(accountModified);
        }
        return null;
    }
}
