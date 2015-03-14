package com.em.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.em.pojo.UserTest;

public class testReadFile {
	static UserTest[] users = new UserTest[336225];// 初始化user类型的数组，每个数组中存储的对象为user对象，

	// 让数组的下表就是user的id,根据日志，userID最大为336200
	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件 ，并且同时把用户对象都创建出来
	 */
	public static void readFileByLines(String fileName) {
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
					users[userId] = new UserTest(userId);
				}
				if (users[friendId] == null) { // 同样，也初始话friendId用户，因为他也user
					users[friendId] = new UserTest(friendId);
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
	 * 得到user的所有好友、以及时间。，方法二：
	 */
	public void getUserFriends(UserTest user) {
		int count = 0;
		Set entries = user.getFriends().entrySet();
		System.out.println("用户" + user.getUserId() + " 的朋友以及创建时间：");
		if (entries != null) {
			Iterator iterator = entries.iterator();
			while (iterator.hasNext()) {
				Map.Entry entry = (Entry) iterator.next();
				UserTest key = (UserTest) entry.getKey();
				int friendId = key.getUserId();
				Object value = entry.getValue();
				System.out.println(friendId + " ,建立联系的时间: " + value);
				count++;
			}
		}
		System.out.println("一共有关注了" + count + "个好友");

	}

	public static void main(String[] args) {
		// User[] users =new User[336200]; //初始化user类型的数组，每个数组中存储的对象为user对象，
		// 让数组的下表就是user的id,根据日志，userID最大为336200
		String fileName = "D:/Program Files (x86)/workspace/EM/src/digg_friends.txt";
		testReadFile readFile = new testReadFile();
		readFile.readFileByLines(fileName); // 读文件并且把用户创建
	
		// user id ,friend id
		readFile.getUserFriends(users[5]); // 查看336216的好友

	}
}
