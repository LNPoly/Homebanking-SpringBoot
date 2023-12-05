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
        dto.setUserAccount(UserMapper.userToDto(account.getUserAccount()));
        dto.setTransfersList(account.getTransfersList());

        return dto;
    }
    public static Account dtoToAccount(AccountDTO dto){
        Account account = new Account();
        account.setAccountId(dto.getAccountId());
        account.setTypeAccount(dto.getTypeAccount());
        account.setCbu(dto.getCbu());
        account.setAlias(dto.getAlias());
        account.setAmount(dto.getAmount());
        account.setUserAccount(UserMapper.dtoToUser(dto.getUserAccount()));
        return account;
    }

}
