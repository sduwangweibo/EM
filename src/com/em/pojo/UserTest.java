package com.em.pojo;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class UserTest {
	private int userId; // userID

	private Map<UserTest, Long> friends = new HashMap<UserTest, Long>(); // 用一个map存储user的朋友列表。
																	// key是朋友的列表，value是建立联系的时间。

	public UserTest(int userId) { // 构造函数，new一个对象的时候表明user的id.
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Map<UserTest, Long> getFriends() {
		return friends;
	}

	public void setFriends(Map<UserTest, Long> friends) {
		this.friends = friends;
	}

	public static void main(String[] args) {
		User[] users = new User[5]; // 把用户都存在一个数组中。
		Story[] storys=new Story[5];
		users[1] = new User(1); // 用户User(1)代表 id = 1 //1号位置代表的是用户1.
		users[2] = new  User(2);
		users[3] = new  User(3);
		users[4] = new  User(4);
		
		storys[1] =new Story(1);
         if(users[0]==null){
        	 System.out.println("users[0]没有初始化 ，果然是null");
         }
		// 1关注了2 、3
		users[1].getFriends().put(users[2], 20150210L);
		users[1].getFriends().put(users[3], 20150310L);
		//输出1的好友列表以及时间：
		UserTest u =new UserTest(0);
		u.getUserFriends(users[1]);//取得user1的好友。。。
		
		
		// 2关注了1，3
		users[2].getFriends().put(users[1], 20150210L);
		users[2].getFriends().put(users[3], 20150310L);
		u.getUserFriends(users[2]);
		// System.out.println(users[1].getFriends());
		// 判断B是否是A的好友(可以单独写个方法)
		// user[1].getFriends().containsKey(user[2]);
		
		//给故事1投票的用户有2，3
		storys[1].getUsers().put(users[2], 201111001L);
		storys[1].getUsers().put(users[3], 201111001L);
        u.getVoteUser(storys[1]);
	}

	/**
	 * 得到user的所有好友、以及时间。，方法二：
	 */
	public void getUserFriends(User  user) {
		Set entries = user.getFriends().entrySet();
		if (entries != null) {
			Iterator iterator = entries.iterator();
			while (iterator.hasNext()) {
				Map.Entry entry = (Entry) iterator.next();
				User key = (User) entry.getKey();
				int userId = key.getUserId();
				Object value = entry.getValue();

				System.out.println("user["+user.getUserId()+"]的 朋友id: " + userId
						+ " ,建立联系的时间: " + value);
			}
		}

	}
	/**
	 * 得到给故事投票的用户有哪些。
	 * @param story
	 */
		public void getVoteUser(Story story) {
			int count = 0;
			Set entries = story.getUsers().entrySet();
			System.out.println("故事" + story.getStoryId() + " 的投票用户以及时间：");
			if (entries != null) {
				Iterator iterator = entries.iterator();
				while (iterator.hasNext()) {
					Map.Entry entry = (Entry) iterator.next(); // 每一条entry是投票用户id,以及时间。。
					User key = (User) entry.getKey();
					int userId = key.getUserId(); // 投票用户的id/
					Object value = entry.getValue(); // 投票时间
					System.out.println(userId + " ,投票时间: " + value);
					count++;
				}

			}
			System.out.println("一共有" + count + "个用户为故事投票");
		}

}
