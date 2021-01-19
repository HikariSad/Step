package com.had.Multiplayer.collaboration.Service;

import com.had.Multiplayer.collaboration.Controller.AuthController;
import com.had.Multiplayer.collaboration.Mapper.UserMapper;
import com.had.Multiplayer.collaboration.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author had
 * @Date 2021/1/13 15:30
 * @Version 1.0
 **/
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getUserList() {
        return userMapper.getUserList();
    }


    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    public boolean isRegisted(String username) {
        return userMapper.isRegisterd(username);
    }

    public User getUserByUserName(String username) {
        return userMapper.getUserByUserName(username);
    }

    public User getUserById(Integer userId) {
        return userMapper.getUserById(userId);
    }

    public Optional<User> getCurrentUser() {
        String threadUserName = AuthController.getThreadUserName();
        return Optional.ofNullable(userMapper.getUserByUserName(AuthController.getThreadUserName() == null ? null : threadUserName));
    }
}
