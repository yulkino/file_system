package com.example.file_system.dbService;

import com.example.file_system.accounts.UserProfile;
import com.example.file_system.dbService.usersDB.UsersDB;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {

    private final Connection connection;

    public DBService() {
        this.connection = getMysqlConnection();
    }

    public UserProfile getUser(String nickName) throws DBException {
        try {
            return (new UsersDB(connection).getUser(nickName));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void addUser(UserProfile userProfile) throws DBException {
        try {
            connection.setAutoCommit(false);
            UsersDB dbOperation = new UsersDB(connection);
            dbOperation.createTable();
            dbOperation.insertUser(userProfile);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver)Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            String url = "jdbc:mysql://localhost:3306/filesystem?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT";

            return DriverManager.getConnection(url, "root", "admin");
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
