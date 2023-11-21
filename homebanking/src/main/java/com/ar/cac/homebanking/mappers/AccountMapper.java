package com.ar.cac.homebanking.mappers;

import com.ar.cac.homebanking.models.Account;
import com.ar.cac.homebanking.models.dtos.AccountDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountMapper {

    public static Account dtoToAccount(AccountDTO dto){
        Account account = new Account();
        account.setTipeAccount(dto.getTipeAccount());
        account.setCbu(dto.getCbu());
        account.setAlias(dto.getAlias());
        account.setAmount(dto.getAmount());
        account.setUserAccount(dto.getUserAccaount());
        return account;

    }

    public static AccountDTO accountToDto(Account account){
        AccountDTO dto = new AccountDTO();
        dto.setAccount_id(account.getAccount_id());
        dto.setTipeAccount(account.getTipeAccount());
        dto.setCbu(account.getCbu());
        dto.setAlias(account.getAlias());
        dto.setAmount(account.getAmount());
        dto.setUserAccaount(account.getUserAccount());
        return dto;

    }

}
