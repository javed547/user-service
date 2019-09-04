package com.javed.aws.applications.constant;

public interface QueryConstant {

    public static final String SQL_INSERT_USER = "insert into users values(?,?,?,?,?,?,?)";
    public static final String SQL_SELECT_USER = "select * from users where username=? and password=?";

}
