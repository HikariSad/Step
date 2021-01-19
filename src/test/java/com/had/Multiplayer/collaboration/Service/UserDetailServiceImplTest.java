package com.had.Multiplayer.collaboration.Service;

import com.had.Multiplayer.collaboration.bean.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@ExtendWith(MockitoExtension.class)
class UserDetailServiceImplTest{
    @Mock
    UserService userService;
    @InjectMocks
    UserDetailServiceImpl userDetailService;

    @Test
    public void throwUsernameNotFoundException() {
        Mockito.when(userService.getUserByUserName("myUser")).thenReturn(null);
        Assertions.assertThrows(UsernameNotFoundException.class,()->
                userDetailService.loadUserByUsername("myUser"));
    }

    @Test
    public void loadUserByUsername() {
        Mockito.when(userService.getUserByUserName("myUser")).
                thenReturn(new User(1,"myUser","123"));
        UserDetails user = userDetailService.loadUserByUsername("myUser");
        Assertions.assertEquals("myUser",user.getUsername());
    }
}