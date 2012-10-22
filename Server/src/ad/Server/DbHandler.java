package ad.Server;

import ad.model.protocol.Group;
import ad.model.protocol.User;

public class DbHandler {

	public DbHandler()
	{
	
	}
	


	public static User getUserByName(String userName) {
		// TODO Auto-generated method stub
		if(userName.equals("test"))
		{
			User usr = new User();
			Group grp = new Group(2);
			
			usr.setUserId(1);
			usr.setUserName("test");
			usr.setPassword("hemligt");
			usr.addGroup(grp);
			grp.addMember(usr);
		
			return usr;
			
		}
		return null;
	}
	
	public static User getUserByEmail(String eMail) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static User addUser(User u)
	{
		return null;
	}



	public static Object getGroupByName(String groupName) {
		// TODO Auto-generated method stub
		return null;
	}



	public static Group addGroup(Group g) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
