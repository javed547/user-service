package com.javed.aws.applications.dao;

import com.javed.aws.applications.model.Login;
import com.javed.aws.applications.model.User;

public interface UserDao {

    void register(User user);

    User validateUser(Login login);
}
