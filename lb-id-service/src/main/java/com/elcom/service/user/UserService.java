package com.elcom.service.user;

import com.elcom.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll(Integer currentPage, Integer rowPerPage, String sort);

    User findById(Integer id);

    void save(User user);

    void remove(User user);

    boolean insertTest();
}
