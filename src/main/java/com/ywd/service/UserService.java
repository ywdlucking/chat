package com.ywd.service;

import com.ywd.model.User;

import java.util.List;

/**
 * Created by admin on 2017/6/30.
 */
public interface UserService {

    boolean addUser(User user);

    boolean deleteUser(Long id);

    User findById(Long id);

    User findByName(String name);

    List<User> listAll();
}
