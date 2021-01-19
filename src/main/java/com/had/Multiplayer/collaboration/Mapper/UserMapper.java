package com.had.Multiplayer.collaboration.Mapper;

import com.had.Multiplayer.collaboration.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getUserList();

    void saveUser(User user);

    boolean isRegisterd(String username);

    User getUserByUserName(String username);

    User getUserById(Integer id);
}
