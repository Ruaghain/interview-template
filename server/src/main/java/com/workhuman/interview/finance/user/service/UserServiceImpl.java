package com.workhuman.interview.finance.user.service;

import com.workhuman.interview.common.exception.FinanceException;
import com.workhuman.interview.finance.user.dao.UserDAO;
import com.workhuman.interview.finance.user.dto.UserDTO;
import com.workhuman.interview.finance.user.mapper.UserMapper;
import com.workhuman.interview.finance.user.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, UserMapper userMapper) {
        this.userDAO = userDAO;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> getEntities() {
        return userMapper.userListToUserDTOList((List<User>) userDAO.findAll());
    }

    @Override
    public UserDTO getEntity(Long id) {
        Optional<User> result = userDAO.findById(id);
        return result.map(userMapper::userToUserDTO).orElse(null);
    }

    @Override
    public UserDTO updateEntity(Long id, UserDTO entity) {
        Optional<User> user = userDAO.findById(id);
        if (user.isPresent()) {
            User retrievedUser = user.get();
            BeanUtils.copyProperties(entity, retrievedUser);
            return userMapper.userToUserDTO(userDAO.save(retrievedUser));
        } else {
            throw new FinanceException("Error updating user");
        }
    }

    @Override
    public List<UserDTO> getUserByEmail(String name) {
        return userMapper.userListToUserDTOList(userDAO.findByEmail(name));
    }

    @Override
    public UserDTO addEntity(UserDTO entity) {
        try {
            User createdUser = userMapper.userDTOToUser(entity);
            createdUser.setCreatedDate(new Date());
            createdUser.setUpdatedDate(new Date());
            return userMapper.userToUserDTO(userDAO.save(createdUser));
        } catch (Exception e) {
            throw new FinanceException(e);
        }
    }
}
