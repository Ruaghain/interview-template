package com.workhuman.interview.finance.account.mapper;

import com.workhuman.interview.finance.account.dto.AccountDTO;
import com.workhuman.interview.finance.account.model.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDTO accountToAccountDTO(Account account);

    Account accountDTOToAccount(AccountDTO accountDTO);

    List<AccountDTO> accountListToAccountDTOList(List<Account> accounts);

    List<Account> accountDTOListToAccountList(List<AccountDTO> accounts);
}
