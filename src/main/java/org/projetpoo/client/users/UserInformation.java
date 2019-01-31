package org.projetpoo.client.users;

import java.io.Serializable;

public class UserInformation implements Serializable {

    private String _username;

    UserInformation(String username) {

        this._username = username;

    }

    public String getUsername() {

        return this._username;

    }

    void setUsername(String username) {

        this._username = username;

    }

}
