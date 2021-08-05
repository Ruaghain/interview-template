package com.workhuman.interview.finance.account.controller;

import com.workhuman.interview.finance.account.dto.AccountDTO;
import com.workhuman.interview.finance.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<AccountDTO> getAllAccounts() {
        return this.accountService.getEntities();
    }

    @GetMapping(path = "/{id}")
    public AccountDTO getAccount(@PathVariable Long id) {
        return this.accountService.getEntity(id);
    }

    @GetMapping(path = "/query")
    public List<AccountDTO> getAccountByName(@RequestParam("name") String name) {
        return this.accountService.getAccountByName(name);
    }

    @PutMapping(path = "/{id}")
    public AccountDTO updateAccount(@PathVariable Long id, @RequestBody AccountDTO account) {
        return this.accountService.updateEntity(id, account);
    }

    @PostMapping
    public AccountDTO addAccount(AccountDTO account) {
        return this.accountService.addEntity(account);
    }
}
