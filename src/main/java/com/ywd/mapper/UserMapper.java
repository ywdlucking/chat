package com.ywd.mapper;

import com.ywd.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by admin on 2017/6/30.
 */
@Component
public interface UserMapper {

    int insert(User user);

    int delete(Long id);

    User findById(Long id);

    User findByName(String name);

    List<User> listAll();
}
