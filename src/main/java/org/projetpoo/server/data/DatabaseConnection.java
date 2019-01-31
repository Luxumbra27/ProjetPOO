package org.projetpoo.server.data;


import org.projetpoo.client.users.RemoteUser;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseConnection {

    private Connection _connection;

    public DatabaseConnection() {

        try {
            _connection = DriverManager.getConnection("jdbc:sqlite:" + (new File("resources/db.sqlite")).getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ArrayList<RemoteUser> getConnectedUsers() {

        ArrayList<RemoteUser> ret = new ArrayList<>();
        try {
            Statement statement = _connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Users where state = 1;");

            while (result.next()) {
                ret.add(new RemoteUser(result.getString("name"), result.getString("ip"), result.getInt("port")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }


    public void addUser(RemoteUser user) {
        try {
            Statement statement = _connection.createStatement();
            statement.executeUpdate("INSERT INTO Users VALUES ('" + user.getUsername() + "', '"
                    + user.getHostname() + "', "
                    + user.getPort() + ", 1);"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public boolean getUserStatusByNickName(String nickname) throws Exception {
        Statement statement = _connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT name, state FROM Users where name = '" + nickname + "';");
        boolean ret = result.next();
        if (ret) {
            return (result.getInt("state") == 1);
        } else {
            throw new Exception("User not found.");
        }
    }


    public void setDisconnected(RemoteUser user) {
        try {
            Statement statement = _connection.createStatement();
            statement.executeUpdate("UPDATE Users SET state = 0 WHERE name = '" + user.getUsername() + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setConnected(RemoteUser user) {
        try {
            Statement statement = _connection.createStatement();
            statement.executeUpdate("UPDATE Users SET state = 1, port = " + user.getPort() + ", ip = '" + user.getHostname() + "' WHERE name = '" + user.getUsername() + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnectAll() {
        try {
            Statement statement = _connection.createStatement();
            statement.executeUpdate("UPDATE Users SET state = 0;");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
