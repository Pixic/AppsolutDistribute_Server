package ad.model.protocol;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * 
 * @author Anton Kostet
 * 
 *         Copyright [2012] [Anton Kostet]
 * 
 *         Licensed under the Apache License, Version 2.0 (the "License"); you
 *         may not use this file except in compliance with the License. You may
 *         obtain a copy of the License at
 * 
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 *         Unless required by applicable law or agreed to in writing, software
 *         distributed under the License is distributed on an "AS IS" BASIS,
 *         WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *         implied. See the License for the specific language governing
 *         permissions and limitations under the License.
 * 
 */

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -190306870708472700L;
	public static int CREATE_ACCOUNT = 0;
	public static int LOG_IN = 1;
	public static int DELETE_ACCOUNT = 2;
	public static int EDIT_ACCOUNT = 3;

	private int userId;
	private String userName, password, firstName, surname, emailAddress;
	// private NåntingAnnat password;

	private ArrayList<Group> groups;
	private boolean isLoggedIn;
	private int accountAction;

	public User() {
		isLoggedIn = false;
		groups = new ArrayList<Group>();
	}

	/*
	 * Get methods
	 */
	public int getUserId() {
		return userId;
	}

	public String getFirstName()
	{
		return firstName;
	}
	
	public String getSurname()
	{
		return surname;
	}	
	public ArrayList<Group> getGroups() {
		return groups;
	}

	public boolean getIsLoggedIn() {
		return isLoggedIn;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public int getAccountAction() {
		return accountAction;
	}

	public String getPassword() {
		return password;
	}

	/*
	 * Set methods
	 */
	public void setUserId(int id) {
		this.userId = id;
	}

	public void setIsLoggedIn(boolean b) {
		isLoggedIn = b;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setEmailAddress(String email) {
		this.emailAddress = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setFirstName(String s)
	{
		this.firstName = s;
	}
	
	public void setSurname(String s)
	{
		this.surname = s;
	}

	public void setAccountAction(int i) {
		accountAction = i;
	}
	/*
	 * Other methods
	 */
	public void addGroup(Group group) {
		groups.add(group);
	}

	public void removeGroup(Group group) {
		groups.remove(group);
	}

}
