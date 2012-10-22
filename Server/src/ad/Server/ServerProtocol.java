package ad.Server;
import ad.model.protocol.ChatMessage;
import ad.model.protocol.Group;
import ad.model.protocol.User;

/**
 * A protocol that processes the information received from the client
 * and generates the response for the client.
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


public class ServerProtocol {

	
	public void process(Server s, ClientSession cs, Object o)
	{
		
		
		//Send an instance of User if you want to log in or create a new account
		if(o instanceof User)
		{
			User user = (User)o;
			
			int action = user.getAccountAction();
			
			if(action == User.LOG_IN)
			{
				User result = logInUser(user);
				if(result != null)
				{
					//Successfully logged in
					user.setIsLoggedIn(true);
					System.out.println("User " + user.getUserName() + " logged in.");
					//Connect this user with a clientsession and store it in the server
					s.connectUser(user.getUserId(), cs);
					//Return the logged in user to the client
					cs.writeToClient(result);
					
				}
				else
				{
					//Failed to log in, return error message
					cs.writeToClient("Couldn't log in");
				}
			}
			else if(action == User.CREATE_ACCOUNT)
			{
				User newUser = createUser(user);
				//return the new user, will be null if it couldn't be created,
				//else it will be the new user
				if(newUser != null)
				{
					//Add the user to the server
					s.addUser(newUser);
					cs.writeToClient(newUser);
				}
				else
				{
					//Failed to create account
					cs.writeToClient("Couldn't create account");
				}
				
			}
			else if(action == User.DELETE_ACCOUNT)
			{
				
			}
			else if(action == User.EDIT_ACCOUNT)
			{
				
			}
		
		}
		
		
		if(o instanceof Group)
		{
			Group group = (Group)o;
			int action = group.getGroupAction();
			
			if(action == Group.CREATE_GROUP)
			{
				Group newGroup = createGroup(group);
				//return the new group, will be null if it couldn't be created,
				//else it will be the new group'
				if(newGroup != null)
				{
					cs.writeToClient(newGroup);
				}
				else
				{
					cs.writeToClient("Couldn't create group");
				}
				
			}
			else if(action == Group.DELETE_GROUP)
			{
				
			}
		}
		
		if(o instanceof ChatMessage)
		{
			ChatMessage cm = (ChatMessage)o;
			Group to = cm.getGroup();
			
			//Iterate through all users in the receiver-group
			for(User u : to.getUsers())
			{
				//Iterate through every user's clientsessions
				for(ClientSession c : s.getUsersClientSessions(u.getUserId()))
				{
					//Write the ChatMessage to the client
					c.writeToClient(cm);
				}
			}
		}
		
		System.out.println(o.getClass());
	}
	
	public User logInUser(User u)
	{
		//Authentication etc..
		//Check if the username and password matches
		User compareUser, newUser = null;
		
		compareUser = DbHandler.getUserByName(u.getUserName());
		
		//User doesn't exist!
		if(compareUser == null)
			return newUser;
		//Password doesn't match!
		else if(!u.getPassword().equals(compareUser.getPassword()))
			return newUser;
		
		//User exists and the password is correct
		return compareUser;
	}
	
	public User createUser(User u)
	{
		User newUser = null;
		//If you get a user from the database with the requested username,
		//the user already exists. Therefore, can't create a user with that name.
		if(DbHandler.getUserByName(u.getUserName()) != null)
			//Return a user that is null if the user couldn't be created!
			return newUser;
		
		//Create user
		newUser = DbHandler.addUser(u);
		//Return the new user
		return newUser;
	}
	
	
	public Group createGroup(Group g)
	{
		Group newGroup = null;
		//If you get a group from the database with the requested groupname,
		//the group already exists. Therefore, can't create a group with that name.
		if(DbHandler.getGroupByName(g.getGroupName()) != null)
			return newGroup;
		
		newGroup = DbHandler.addGroup(g);
		return newGroup;
	}
}
