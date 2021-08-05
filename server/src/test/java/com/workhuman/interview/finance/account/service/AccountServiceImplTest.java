package com.workhuman.interview.finance.account.service;

import com.workhuman.interview.common.exception.FinanceException;
import com.workhuman.interview.finance.account.dao.AccountDAO;
import com.workhuman.interview.finance.account.dto.AccountDTO;
import com.workhuman.interview.finance.account.mapper.AccountMapper;
import com.workhuman.interview.finance.account.model.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    AccountDAO accountDAO;
    @Spy
    AccountMapper mapper = Mappers.getMapper(AccountMapper.class);
    
    @InjectMocks
    AccountServiceImpl subject;

    @Test
    @DisplayName("When requesting all accounts expect all of them to be returned.")
    void getAllAccounts() {
        List<Account> accountList = mapper.accountDTOListToAccountList(getAccounts());
        when(accountDAO.findAll()).thenReturn(accountList);
        List<AccountDTO> result = subject.getEntities();
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Given a valid id when requesting an account expect it to be correctly returned")
    void getAccount() {
        AccountDTO newAccount = createAccount("Investment", "23334", 12345.65); 
        Account account = mapper.accountDTOToAccount(newAccount);
        when(accountDAO.findById(anyLong())).thenReturn(java.util.Optional.of(account));
        AccountDTO result = subject.getEntity(1L);
        assertEquals(newAccount.getAccountName(), result.getAccountName());
        assertEquals(newAccount.getAccountNumber(), result.getAccountNumber());
        assertEquals(newAccount.getAccountBalance(), result.getAccountBalance());
    }

    @Test
    @DisplayName("Given an invalid id when requesting an account expect null to be returned")
    void getInvalidAccount() {
        when(accountDAO.findById(anyLong())).thenReturn(Optional.empty());
        AccountDTO result = subject.getEntity(1L);
        assertNull(result);
    }

    @Test
    @DisplayName("Given a valid account name when requesting accounts expect them to be returned")
    void getAccountByName() {
        List<Account> accountList = mapper.accountDTOListToAccountList(getAccounts());
        when(accountDAO.findByAccountName("Current")).thenReturn(accountList);
        List<AccountDTO> result = subject.getAccountByName("Current");
        assertEquals(result.size(), 2);
        assertEquals("Current", result.get(0).getAccountName());
    }

    @Test
    @DisplayName("Given an existing account when updating expect it to be successfully updated")
    void updateEntity() {
        AccountDTO existingAccount = createAccount("Savings", "123567", 43.65);
        Account account = mapper.accountDTOToAccount(existingAccount);
        when(accountDAO.findById(anyLong())).thenReturn(Optional.of(account));
        when(accountDAO.save(any())).thenReturn(account);
        
        AccountDTO result = subject.updateEntity(anyLong(), existingAccount);
        assertEquals(existingAccount.getAccountName(), result.getAccountName());
        assertEquals(existingAccount.getAccountNumber(), result.getAccountNumber());
        assertEquals(existingAccount.getAccountBalance(), result.getAccountBalance());
    }

    @Test
    @DisplayName("Given a non-existing account when updating expect exception to be returned")
    void updateInvalidEntity() {
        AccountDTO existingAccount = createAccount("Savings", "123567", 43.65);
        when(accountDAO.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(FinanceException.class, () -> {
            subject.updateEntity(anyLong(), existingAccount);
        });
        assertEquals("Error updating account", exception.getMessage());
    }

    @Test
    @DisplayName("Given valid account details when creating an account expect saved account to be returned")
    void addEntity() {
        AccountDTO newAccount = createAccount("Current", "222222", 765.87);
        Account account = mapper.accountDTOToAccount(newAccount);
        when(accountDAO.save(any())).thenReturn(account);
        
        AccountDTO result = subject.addEntity(newAccount);
        assertEquals(newAccount.getAccountName(), result.getAccountName());
        assertEquals(newAccount.getAccountNumber(), result.getAccountNumber());
        assertEquals(newAccount.getAccountBalance(), result.getAccountBalance());
    }
 
    @Test
    @DisplayName("Given saving error when creating an account expect exception to be thrown")
    void addErrorEntity() {
        AccountDTO newAccount = createAccount("Savings", "3333333", 4765.87);
        when(accountDAO.save(any())).thenThrow(new RuntimeException("Unknown error saving account"));
        Exception exception = assertThrows(FinanceException.class, () -> {
            subject.addEntity(newAccount);
        });
        assertEquals("java.lang.RuntimeException: Unknown error saving account", exception.getMessage());
    }
 
    private List<AccountDTO> getAccounts() {
        List<AccountDTO> accountList = new ArrayList<>();
        accountList.add(createAccount("Current", "12345", 123.54));
        accountList.add(createAccount("Savings", "23456", 3456.67));

        return accountList;
    }

    private AccountDTO createAccount(String name, String number, Double balance) {
        AccountDTO account = new AccountDTO();

        account.setAccountName(name);
        account.setAccountNumber(number);
        account.setAccountBalance(balance);

        return account;
    }
}
