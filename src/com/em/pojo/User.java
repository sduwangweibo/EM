package com.em.pojo;

import java.util.HashMap;
import java.util.Map;

public class User {
	private int userId; // userID
	private Map<User, Long> friends = new HashMap<User, Long>(); // 用一个map存储user的朋友列表。

	// key是朋友的列表，value是建立联系的时间。

	public User(int userId) { // 构造函数，new一个对象的时候表明user的id.
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Map<User, Long> getFriends() {
		return friends;
	}

	public void setFriends(Map<User, Long> friends) {
		this.friends = friends;
	}

}
