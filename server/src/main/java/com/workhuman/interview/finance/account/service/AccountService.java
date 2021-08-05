package com.workhuman.interview.finance.account.service;

import com.workhuman.interview.common.service.BaseService;
import com.workhuman.interview.finance.account.dto.AccountDTO;

import java.util.List;

public interface AccountService extends BaseService<AccountDTO> {

    List<AccountDTO> getAccountByName(String name);
}
