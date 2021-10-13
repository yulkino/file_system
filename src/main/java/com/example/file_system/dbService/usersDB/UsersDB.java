package com.example.file_system.dbService.usersDB;

import com.example.file_system.accounts.UserProfile;
import com.example.file_system.dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

public class UsersDB {
    private final Executor executor;

    public UsersDB(Connection connection) throws SQLException {
        this.executor = new Executor(connection);
        //createTable();
    }

    public UserProfile getUser(String nickName) throws SQLException {
        return executor.execQuery("select * from users where user_name = '" + nickName + "'", result -> {
            if(result.next()) {
                return new UserProfile(result.getString(1), result.getString(2), result.getString(3));
            }
            else{
                return null;
            }
        });
    }

    public void insertUser(UserProfile userProfile) throws SQLException {
        executor.execUpdate("insert into users (user_name, user_password, user_email) values ('" + userProfile.getNickName()
                + "', '" + userProfile.getPassword() +"', '"+ userProfile.getEmail() +"')");
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users (user_name varchar(256), user_password varchar(256)," +
                " user_email varchar(256), primary key (user_name))");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table users");
    }
}
