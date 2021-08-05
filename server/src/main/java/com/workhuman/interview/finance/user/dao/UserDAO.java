package com.workhuman.interview.finance.user.dao;

import com.workhuman.interview.finance.user.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDAO extends PagingAndSortingRepository<User, Long> {

    List<User> findByEmail(@Param("email") String email);
}
