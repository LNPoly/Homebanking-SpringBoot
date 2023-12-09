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
import com.ar.cac.homebanking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private  AccountRepository accountRepository;
    @Autowired
    private  UserRepository userRepository;

    public List<AccountDTO> getAccounts(){
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(AccountMapper::accountToDto)
                .collect(Collectors.toList());
    }
    public AccountDTO createAccount(AccountDTO dto){

        User user = userRepository.findById(dto.getUserAccount().getId()).orElse(null);

        dto.setTypeAccount(createTypeAccount());
        dto.setCbu(generarCBU());
        dto.setAlias(generarAlias());
        dto.setAmount(BigDecimal.ZERO);

        Account newAccount = AccountMapper.dtoToAccount(dto);
        newAccount.setUserAccount(user);
        newAccount.setTransfersList(new ArrayList<>());

        newAccount = accountRepository.save(newAccount);
        return AccountMapper.accountToDto(newAccount);
    }
    public AccountDTO getAccountById(Long accountId){
        Account entity = accountRepository.findById(accountId).get();
        return AccountMapper.accountToDto(entity);
    }
    public String deleteAccount(Long accountId){
        if(accountRepository.existsById(accountId)) {
            accountRepository.deleteById(accountId);
            return "Cuenta con id:" + accountId + " ha sido eliminada.";
        } else {
            throw  new UserNotExistsException("la cuenta elegida para eliminar, no existe.");
        }
    }

    public AccountDTO updateAccount(Long id, AccountDTO dto) {
        if (accountRepository.existsById(id)){
            Account accountToModify = accountRepository.findById(id).get();

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

            Account accountModified = accountRepository.save(accountToModify);

            return AccountMapper.accountToDto(accountModified);
        }
        return null;
    }

    public AccountType createTypeAccount(){
        //Obtiene el indice del tipo de cuenta en forma aleatoria a partir del Enum AccountType.
        return AccountType.values()[new Random().nextInt(AccountType.values().length)];
    }

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
        Account usuarioExistente = accountRepository.findByAlias(alias);
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
        Account usuarioExistente = accountRepository.findByCbu(cbu);
        if(usuarioExistente != null){
            return true;
        } else {
            return false;
        }
    }

    public AccountDTO depositAccount(AccountDTO dto) {

        Account entity = accountRepository.findById(dto.getAccountId()).get();

        if(dto.getAmount().compareTo(BigDecimal.ZERO)>0) {
            entity.setAmount(entity.getAmount().add(dto.getAmount()));

            Account account = accountRepository.save(entity);

            return AccountMapper.accountToDto(account);
        } else {
            throw  new AccountNotFoundException("Amount to be deposited invalid.");
        }
    }

    public AccountDTO extractAccount(AccountDTO dto) {

        Account entity = accountRepository.findById(dto.getAccountId()).get();


        if(entity.getAmount().subtract(dto.getAmount()).compareTo(BigDecimal.ZERO)>0) {
            entity.setAmount(entity.getAmount().subtract(dto.getAmount()));
            Account account = accountRepository.save(entity);

            return AccountMapper.accountToDto(account);
        } else {
                throw  new AccountNotFoundException("Amount to be drawn insufficient");
        }
    }
}
