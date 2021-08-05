package com.workhuman.interview.finance.account.dao;

import com.workhuman.interview.finance.account.model.Account;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountDAO extends PagingAndSortingRepository<Account, Long> {

    List<Account> findByAccountName(@Param("accountName") String accountName);

}
