package com.workhuman.interview.finance.user.service;

import com.workhuman.interview.common.service.BaseService;
import com.workhuman.interview.finance.user.dto.UserDTO;
import com.workhuman.interview.finance.user.model.User;

import java.util.List;

public interface UserService extends BaseService<UserDTO> {
    List<UserDTO> getUserByEmail(String name);
}
