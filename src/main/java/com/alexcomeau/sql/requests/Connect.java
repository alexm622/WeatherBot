package com.alexcomeau.sql.requests;

import com.alexcomeau.sql.LoginDetails;
import com.alexcomeau.utils.ReadToken;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    private static Connection connect = null;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        LoginDetails details = ReadToken.getSql();

        String connection = "jdbc:mysql://" + details.getIp() + "/discord?"
                + "user=" + details.getUsername()
                + "&password=" + details.getPassword();

        connect = DriverManager.getConnection(connection);
        return connect;
    }
}
