package com.ar.cac.homebanking.controllers;

import com.ar.cac.homebanking.services.AccountService;
import com.ar.cac.homebanking.models.dtos.AccountDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/accounts")
public class AccountController {

    private final AccountService service;
    public AccountController(AccountService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAccounts(){
        List<AccountDTO> lista = service.getAccounts();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAccountById(id));
    }


    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO account){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createAccount(account));
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable Long id, @RequestBody AccountDTO account){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateAccount(id, account));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteAccount(id));
    }

    @PutMapping("/deposit/{id}")
    public ResponseEntity<AccountDTO> depositAccount(@PathVariable Long id, @RequestBody AccountDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.depositAccount(id,dto));
    }

    @PutMapping("/extract/{id}")
    public ResponseEntity<AccountDTO> extractAccount(@PathVariable Long id, @RequestBody AccountDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.extractAccount(id,dto));
    }

}