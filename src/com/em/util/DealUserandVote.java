package com.em.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.em.pojo.Story;
import com.em.pojo.User;
import com.em.pojo.UserTest;

public class DealUserandVote {
	static User[] users = new User[336225];// 初始化user类型的数组，每个数组中存储的对象为user对象，
	static Story[] storys = new Story[3554];

	// 让数组的下表就是user的id,根据日志，userID最大为336200
	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件 ，并且同时把用户对象都创建出来,并且存好友关系。
	 */
	public static void dealUser(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;

			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				// System.out.println("line " + line + ": " + tempString);
				// 处理每一行。。。
				String[] s = tempString.split(","); // 以“，”切分。
				System.out.println("line " + line + ": " + s[0] + " " + s[1]
						+ " 用户id:" + s[2] + " 关注的用户id:" + s[3]);
				int userId = Integer.parseInt(s[2]); // 一条记录中用户的id
				int friendId = Integer.parseInt(s[3]); // 一条记录中朋友的id
				long time = Long.parseLong(s[1]); // 建立联系的时间
				if (users[userId] == null) { // 如果userId的用户没有初始化 ，那么初始化他。
					users[userId] = new User(userId);
					
				}
				if (users[friendId] == null) { // 同样，也初始话friendId用户，因为他也user
					users[friendId] = new User(friendId);
				}
				users[userId].getFriends().put(users[friendId], time); // 每读一行就
				// 把关注的朋友的id,以及时间都存起来。

				line++;

			}
			System.out.println("初始化完毕，也把好友都存储完毕。。。");
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 处理用户的投票。。按照每一个故事的投票情况存储，投票用户以及时间。
	 * 
	 * @param fileName
	 */
	public static void dealVote(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;

			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				// System.out.println("line " + line + ": " + tempString);
				// 处理每一行。。。
				String[] s = tempString.split(","); // 以“，”切分。
				// System.out.println("line " + line + ":  时间： " + s[0] + "  用户"
				// + s[1]
				// + "  投票故事：" + s[2]);
				int userId = Integer.parseInt(s[1]); // 投票用户的Id
				int storyId = Integer.parseInt(s[2]); // 故事id
				long time = Long.parseLong(s[0]); // 投票时间
		//		System.out.println("故事" + storyId + "创建对象。。。。");

				if (storys[storyId] == null) { // 如果storyId的用户没有初始化 ，那么初始化他。
					storys[storyId] = new Story(storyId);
					System.out.println("故事" + storyId + "创建对象。。。。");
}
				if(users[userId]==null){       //判断一用户有没有被创建，如果没有被创建，那么创建。
					users[userId] = new User(userId);
					System.out.println("用户"+userId+"果然没有创建，新创建之。。");
				}
				storys[storyId].getUsers().put(users[userId], time); // 每读一行就
				// 把投票朋友的id,以及时间都存起来。
				line++;
			}
			System.out.println("初始化完毕，也把投票都存储完毕。。。");
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 得到user的所有好友、以及时间。，方法二：
	 */
	public void getUserFriends(User user) {
		int count = 0;
		Set entries = user.getFriends().entrySet();
		System.out.println("用户" + user.getUserId() + " 的朋友以及创建时间：");
		if (entries != null) {
			Iterator iterator = entries.iterator();
			while (iterator.hasNext()) {
				Map.Entry entry = (Entry) iterator.next();
				User key = (User) entry.getKey();
				int friendId = key.getUserId();
				Object value = entry.getValue();
				System.out.println(friendId + " ,建立联系的时间: " + value);
				count++;
			}

		}
		System.out.println("一共有关注了" + count + "个好友");

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
				System.out.println("投票id"+userId + " ,投票时间: " + value);
				count++;
			}

		}
		System.out.println("一共有" + count + "个用户为故事投票");
	}

	public static void main(String[] args) {
		DealUserandVote deal = new DealUserandVote();
		String userFile = "D:/Program Files (x86)/workspace/EM/src/digg_friends.txt";
		String voteFile = "D:/Program Files (x86)/workspace/EM/src/digg_votes.txt";
		deal.dealUser(userFile); // 处理用户，建立用户对象，保存好友以及时间。
		deal.dealVote(voteFile); // 处理对故事的投票，保存对每个故事投票的用户以及时间。
		 deal.getVoteUser(storys[1]); //得到对故事1 的投票用户以及时间
		deal.getUserFriends(users[10]); //得到用户10关注的好友以及时间。19个

	}

}
