package com.workhuman.interview.finance.user.mapper;

import com.workhuman.interview.finance.user.dto.UserDTO;
import com.workhuman.interview.finance.user.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);

    List<UserDTO> userListToUserDTOList(List<User> users);

    List<User> userDTOListToUserList(List<UserDTO> users);
}
