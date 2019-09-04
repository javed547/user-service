package com.javed.aws.applications.dao;

import com.javed.aws.applications.constant.QueryConstant;
import com.javed.aws.applications.model.Login;
import com.javed.aws.applications.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void register(User user) {
        jdbcTemplate.update(QueryConstant.SQL_INSERT_USER,
                new Object[]{user.getPhone(), user.getUsername(), user.getPassword(),
                        user.getFirstname(), user.getLastname(), user.getEmail(), user.getAddress()});
        System.out.println(user.toString());
    }

    public User validateUser(Login login) {
        List<User> userList = jdbcTemplate.query(QueryConstant.SQL_SELECT_USER,
                new Object[]{login.getUsername(), login.getPassword()}, new BeanPropertyRowMapper<User>(User.class));
        return userList.size() > 0 ? userList.get(0) : null;
    }
}
