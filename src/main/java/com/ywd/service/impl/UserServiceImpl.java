package com.ywd.service.impl;

import com.ywd.mapper.UserMapper;
import com.ywd.model.User;
import com.ywd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by admin on 2017/6/30.
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean addUser(User user) {
        return userMapper.insert(user) > 0;
    }

    @Override
    public boolean deleteUser(Long id) {
        return userMapper.delete(id) > 0;
    }

    @Override
    public User findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public List<User> listAll() {
        return userMapper.listAll();
    }
}
