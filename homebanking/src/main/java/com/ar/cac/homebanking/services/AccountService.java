package com.ar.cac.homebanking.services;

import com.ar.cac.homebanking.mappers.AccountMapper;
import com.ar.cac.homebanking.models.Account;
import com.ar.cac.homebanking.models.dtos.AccountDTO;
import com.ar.cac.homebanking.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<AccountDTO> accountsDTOS = accounts.stream()
                .map(AccountMapper::accountToDto)
                .collect(Collectors.toList());
        return accountsDTOS;
    }

    public AccountDTO newBankAccount(AccountDTO accountDTO){
        Account bankAccount = repository.save(AccountMapper.dtoToAccount(accountDTO)),
        return AccountMapper.accountToDto(bankAccount);
    }
}
