package com.example.thi.service;

import java.util.List;

public interface IService <T> {
    List<T> getAll();
    void addNew(T t);
    void delete(T t);
    void update(T t);
    T getById(int id);
}
