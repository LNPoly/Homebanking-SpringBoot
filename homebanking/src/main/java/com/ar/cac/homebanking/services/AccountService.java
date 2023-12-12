package com.ar.cac.homebanking.services;

import com.ar.cac.homebanking.exceptions.AccountNotFoundException;
import com.ar.cac.homebanking.exceptions.UserNotExistsException;
import com.ar.cac.homebanking.mappers.AccountMapper;
import com.ar.cac.homebanking.models.Account;
import com.ar.cac.homebanking.models.User;
import com.ar.cac.homebanking.models.dtos.AccountDTO;
import com.ar.cac.homebanking.models.enums.AccountType;
import com.ar.cac.homebanking.models.enums.WordsForAlias;
import com.ar.cac.homebanking.repositories.AccountRepository;
import com.ar.cac.homebanking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        dto.setCbu(generateCBU());
        dto.setAlias(generateAlias());
        dto.setAmount(BigDecimal.ZERO);

        Account newAccount = AccountMapper.dtoToAccount(dto);
        newAccount.setUserAccount(user);

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
            return "Account with id:" + accountId + " has been deleted.";
        } else {
            throw  new UserNotExistsException("The account chosen to delete doesn't exists.");
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

    public String generateAlias() {
        Random random = new Random();
        String alias;

        do {
            // Obtengo 3 palabras aleatorias del Enum "Palabra"
            WordsForAlias word1 = getRandomWord();
            WordsForAlias word2 = getRandomWord();
            WordsForAlias word3 = getRandomWord();

            // Concateno las 3 palabras con puntos para formar el alias
            alias = word1.name().toLowerCase() + "." + word2.name().toLowerCase() + "." + word3.name().toLowerCase();

            // Verifico que no exista ese alias en la BBDD
        } while (aliasExists(alias));
        return alias;
    }

    private WordsForAlias getRandomWord() {
        // Obtiene desde el Enum una palabra aleatoria entre 0 y el tamaño del enum (Palabras.values().length)
        return WordsForAlias.values()[new Random().nextInt(WordsForAlias.values().length)];
    }

    public boolean aliasExists(String alias) {
        // Metodo para vericicar si en la BBDD existe una cuenta con ese alias
        Account userExists = accountRepository.findByAlias(alias);
        if(userExists != null){
            return true;
        } else {
            return false;
        }
    }

    public  String generateCBU() {
        Random random = new Random();
        String cbu;
        do {
            cbu = "021";
            // Concateno el numero 021 (N° de entidad inventado), con 19 digitos mas aleatorios dentro del rango [0-9]
            for (int i = 0; i < 19; i++) {
                cbu += random.nextInt(10);
            }
        } while (cbuExists(cbu));
        return cbu;
    }

    public boolean cbuExists(String cbu) {
        // Metodo para vericicar si en la BBDD existe una cuenta con ese cbu
        Account userExists = accountRepository.findByCbu(cbu);
        if(userExists != null){
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
            throw  new AccountNotFoundException("The amount to be deposited is invalid.");
        }
    }

    public AccountDTO extractAccount(AccountDTO dto) {

        Account entity = accountRepository.findById(dto.getAccountId()).get();


        if(entity.getAmount().subtract(dto.getAmount()).compareTo(BigDecimal.ZERO)>0) {
            entity.setAmount(entity.getAmount().subtract(dto.getAmount()));
            Account account = accountRepository.save(entity);

            return AccountMapper.accountToDto(account);
        } else {
                throw  new AccountNotFoundException("Insufficient funds.");
        }
    }
}
