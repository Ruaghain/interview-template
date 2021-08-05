package com.workhuman.interview.finance.account.service;

import com.workhuman.interview.common.exception.FinanceException;
import com.workhuman.interview.finance.account.dao.AccountDAO;
import com.workhuman.interview.finance.account.dto.AccountDTO;
import com.workhuman.interview.finance.account.mapper.AccountMapper;
import com.workhuman.interview.finance.account.model.Account;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDAO accountDAO;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountServiceImpl(AccountDAO accountDAO, AccountMapper accountMapper) {
        this.accountDAO = accountDAO;
        this.accountMapper = accountMapper;
    }

    @Override
    public List<AccountDTO> getEntities() {
        return accountMapper.accountListToAccountDTOList((List<Account>) accountDAO.findAll());
    }

    @Override
    public AccountDTO getEntity(Long id) {
        Optional<Account> result = accountDAO.findById(id);
        return result.map(accountMapper::accountToAccountDTO).orElse(null);
    }

    @Override
    public AccountDTO updateEntity(Long id, AccountDTO entity) {
        Optional<Account> account = accountDAO.findById(id);
        if (account.isPresent()) {
            Account retrievedAccount = account.get();
            BeanUtils.copyProperties(entity, retrievedAccount);
            return accountMapper.accountToAccountDTO(accountDAO.save(retrievedAccount));
        } else {
            throw new FinanceException("Error updating account");
        }
    }

    @Override
    public AccountDTO addEntity(AccountDTO entity) {
        try {
            Account createdAccount = accountMapper.accountDTOToAccount(entity);
            createdAccount.setCreatedDate(new Date());
            createdAccount.setUpdatedDate(new Date());
            return accountMapper.accountToAccountDTO(accountDAO.save(createdAccount));
        } catch (Exception e) {
            throw new FinanceException(e);
        }
    }

    @Override
    public List<AccountDTO> getAccountByName(String name) {
        return accountMapper.accountListToAccountDTOList(accountDAO.findByAccountName(name));
    }
}
