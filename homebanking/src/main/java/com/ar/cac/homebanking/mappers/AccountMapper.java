package com.ar.cac.homebanking.mappers;

import com.ar.cac.homebanking.models.Account;
import com.ar.cac.homebanking.models.dtos.AccountDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountMapper {

    public static AccountDTO accountToDto(Account account){
        AccountDTO dto = new AccountDTO();
        dto.setAccountId(account.getAccountId());
        dto.setTypeAccount(account.getTypeAccount());
        dto.setCbu(account.getCbu());
        dto.setAlias(account.getAlias());
        dto.setAmount(account.getAmount());
        //Cuando haya las relaciones corregir eso
        //dto.setUserAccount(account.getUserAccount());
        return dto;
    }
    public static Account dtoToAccount(AccountDTO dto){
        Account account = new Account();
        account.setTypeAccount(dto.getTypeAccount());
        account.setCbu(dto.getCbu());
        account.setAlias(dto.getAlias());
        account.setAmount(dto.getAmount());
        //Cuando haya las relaciones corregir eso
        //account.setUserAccount(dto.getUserAccount());
        return account;
    }

}
