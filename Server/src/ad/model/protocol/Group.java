package ad.model.protocol;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * 
 * 
 * @author Anton Kostet
 * 
 * Copyright [2012] [Anton Kostet]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

public class Group implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9082898976713737105L;
	public static int CREATE_GROUP = 0;
	public static int DELETE_GROUP = 1;
	
	private int groupId;
	private String groupName;
	private ArrayList<User> users;
	private int groupAction;
	
	public Group(int groupId)
	{
		this.groupId = groupId;
		users = new ArrayList<User>();
	}
	
	/*
	 * Get methods
	 */
	public int getGroupId()
	{
		return groupId;
	}
	public ArrayList<User> getUsers()
	{
		return users;
	}
	public int getGroupAction()
	{
		return groupAction;
	}
	public String getGroupName()
	{
		return groupName;
	}
	/*
	 * Set methods
	 */
	public void setGroupId(int id)
	{
		this.groupId = id;
	}
	/*
	 * Other methods
	 */
	public void addMember(User u)
	{
		users.add(u);
	}
	public void setGroupAction(int i)
	{
		this.groupAction = i;
	}
	public void setGroupName(String s)
	{
		this.groupName = s;
	}
}
