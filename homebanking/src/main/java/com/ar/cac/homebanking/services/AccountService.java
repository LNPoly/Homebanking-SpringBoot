package com.ar.cac.homebanking.services;

import com.ar.cac.homebanking.exceptions.AccountNotFoundException;
import com.ar.cac.homebanking.exceptions.UserNotExistsException;
import com.ar.cac.homebanking.mappers.AccountMapper;
import com.ar.cac.homebanking.mappers.UserMapper;
import com.ar.cac.homebanking.models.Account;
import com.ar.cac.homebanking.models.User;
import com.ar.cac.homebanking.models.dtos.AccountDTO;
import com.ar.cac.homebanking.models.dtos.UserDTO;
import com.ar.cac.homebanking.models.enums.AccountType;
import com.ar.cac.homebanking.models.enums.Palabras;
import com.ar.cac.homebanking.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
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
        // AGREGAMOS EL CBU
        dto.setCbu(generarCBU());
        // AGREGAMOS EL ALIAS
        dto.setAlias(generarAlias());
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


    // Metodo para generar el Alias
    public String generarAlias() {
        Random random = new Random();
        String alias;

        do {
            // Obtengo 3 palabras aleatorias del Enum "Palabra"
            Palabras palabra1 = obtenerPalabraAleatoria();
            Palabras palabra2 = obtenerPalabraAleatoria();
            Palabras palabra3 = obtenerPalabraAleatoria();

            // Concateno las 3 palabras con puntos para formar el alias
            alias = palabra1.name().toLowerCase() + "." + palabra2.name().toLowerCase() + "." + palabra3.name().toLowerCase();

            // Verifico que no exista ese alias en la BBDD
        } while (aliasExiste(alias));

        return alias;
    }

    private Palabras obtenerPalabraAleatoria() {
        // Obtiene desde el Enum una palabra aleatoria entre 0 y el tamaño del enum (Palabras.values().length)
        return Palabras.values()[new Random().nextInt(Palabras.values().length)];
    }

    public boolean aliasExiste(String alias) {
        // Metodo para vericicar si en la BBDD existe una cuenta con ese alias
        Account usuarioExistente = repository.findByAlias(alias);
        if(usuarioExistente != null){
            return true;
        } else {
            return false;
        }
    }

    public  String generarCBU() {
        Random random = new Random();
        String cbu;
        do {
            cbu = "021";
            // Concateno el numero 021 (N° de entidad inventado), con 19 digitos mas aleatorios dentro del rango [0-9]
            for (int i = 0; i < 19; i++) {
                cbu += random.nextInt(10);
            }
        } while (cbuExiste(cbu));
        return cbu;
    }

    public boolean cbuExiste(String cbu) {
        // Metodo para vericicar si en la BBDD existe una cuenta con ese cbu
        Account usuarioExistente = repository.findByCbu(cbu);
        if(usuarioExistente != null){
            return true;
        } else {
            return false;
        }
    }

    public AccountDTO depositAccount(Long id, AccountDTO dto) {

        Account entity = repository.findById(id).get();

        if(dto.getAmount().compareTo(BigDecimal.ZERO)>0) {
            entity.setAmount(entity.getAmount().add(dto.getAmount()));
            Account account = repository.save(entity);

            return AccountMapper.accountToDto(account);
        } else {
            throw  new AccountNotFoundException("Amount to be deposited invalid.");
        }
    }

    public AccountDTO extractAccount(Long id, AccountDTO dto) {

        Account entity = repository.findById(id).get();


        if(entity.getAmount().subtract(dto.getAmount()).compareTo(BigDecimal.ZERO)>0) {
            entity.setAmount(entity.getAmount().subtract(dto.getAmount()));
            Account account = repository.save(entity);

            return AccountMapper.accountToDto(account);
        } else {
                throw  new AccountNotFoundException("Amount to be drawn insufficient");
        }
    }
}
