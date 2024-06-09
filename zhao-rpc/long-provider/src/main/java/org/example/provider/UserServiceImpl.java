package org.example.provider;

import org.example.UserService.UserService;
import org.example.domain.User;

public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println(user.getName()+"= "+user.getAge());
        return user;
    }
}
