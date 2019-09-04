package com.javed.aws.applications.service;

import com.javed.aws.applications.dao.UserDao;
import com.javed.aws.applications.model.Login;
import com.javed.aws.applications.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    public void register(User user) {
        userDao.register(user);
    }

    public User validateUser(Login login) {
        return userDao.validateUser(login);
    }
}
