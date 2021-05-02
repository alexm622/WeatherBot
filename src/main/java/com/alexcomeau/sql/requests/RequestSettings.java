package com.alexcomeau.sql.requests;

import com.alexcomeau.sql.Server;
import com.alexcomeau.sql.ServerSettings;

import java.sql.*;

public class RequestSettings {
    public static Server getServer(String server) throws SQLException, ClassNotFoundException {
        Connection con = Connect.getConnection();

        PreparedStatement prep = con.prepareStatement("select * from server where server=?");
        prep.setString(1, server);
        ResultSet results = prep.executeQuery();

        Server serverStruct = new Server();

        while(results.next()){
            serverStruct.setServer(results.getString("server"));
            serverStruct.setHasSettings(results.getBoolean("hasSettings"));
        }
        if(serverStruct.equals(new Server())){
            prep = con.prepareStatement("insert into servers values (?, 1)");
            prep.setString(1, server);
            prep.executeUpdate();
            serverStruct.setServer(server);
            serverStruct.setHasSettings(true);
        }

        con.close();
        return serverStruct;
    }

    public static ServerSettings getSettings(String server) throws SQLException, ClassNotFoundException {

        Connection con = Connect.getConnection();

        PreparedStatement prep = con.prepareStatement("select * from serverSettings where server=?");
        prep.setString(1, server);

        ResultSet resultSet = prep.executeQuery();

        ServerSettings settings = new ServerSettings();

        while(resultSet.next()){
            settings.setServer(resultSet.getString("server"));
            settings.setAdmin(resultSet.getString("adminRole"));
            settings.setPrefix(resultSet.getString("prefix"));
            settings.setChannel(resultSet.getString("channel"));
        }
        if(settings.equals(new ServerSettings())){
            prep = con.prepareStatement("insert into serverSettings (server, prefix, adminRole, channel) values (?, \"&\", 0, NULL");
            prep.setString(1, server);
            prep.executeUpdate();

            settings.setServer(server);
            settings.setAdmin("0");
            settings.setPrefix("&");
            settings.setChannel(null);
        }

        return settings;
    }
}
