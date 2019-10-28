package com.javed.aws.applications.constant;

public interface QueryConstant {

    String SQL_INSERT_USER = "insert into users values(?,?,?,?,?,?,?)";
    String SQL_SELECT_USER = "select * from users where username=? and password=?";

}
