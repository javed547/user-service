package com.javed.aws.applications.service;

import com.javed.aws.applications.model.Login;
import com.javed.aws.applications.model.User;

public interface UserService {

    void register(User user);

    User validateUser(Login login);

}
