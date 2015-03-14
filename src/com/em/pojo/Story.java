package com.em.pojo;

import java.util.HashMap;
import java.util.Map;

public class Story {
private int storyId; //故事的id
private Map<User,Long> users =new HashMap<User,Long>(); //给这个故事投票的用户，以及时间。

public  Story(int storyId) { // 构造函数，new一个对象的时候表明user的id.
	this.storyId = storyId;
}

public int getStoryId() {
	return storyId;
}

public void setStoryId(int storyId) {
	this.storyId = storyId;
}

public Map<User, Long> getUsers() {
	return users;
}

public void setUsers(Map<User, Long> users) {
	this.users = users;
}

}
