package com.workhuman.interview.common.service;

import com.workhuman.interview.common.exception.FinanceException;

import java.util.List;

public interface BaseService<T> {

    List<T> getEntities();

    T getEntity(Long id);

    T updateEntity(Long id, T entity) throws FinanceException;

    T addEntity(T entity) throws FinanceException;
}
