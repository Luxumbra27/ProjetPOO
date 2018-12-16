package org.projetpoo.client.users;

import java.io.Serializable;

public class UserInformation implements Serializable {

	private String nickname ;
	
	public UserInformation (String nickname) {

		this.nickname = nickname;

	}
	
	public void setNickname (String nickname) {

		this.nickname = nickname;

	}
	
	public String getNickname () {

		return this.nickname ;

	}
	
}
