
//A string with the formation
//"SERVICEID:ACTIONID:OPTIONAL:OPTIONAL:OPTIONAL...."
//is passed to this protocol
//The protocol then parses the strings and 

public class ServerProtocol {
	public final int BAD_REQUEST = 900;
	
	
	//SERVICE_ID:
	public final int CHAT = 0;
	public final int ACCOUNT = 1;
	//etc...
	
	//ACTION_ID:
	
	



	public ServerProtocol()
	{

	}

	public void process(Server s, ClientSession cs, String msg)
	{
		
		
		//Hämta ut gruppnamn, kommando, sträng etc.
		
		//Get the SERVICEID from the string:
		String array[] = msg.split(":", 2);
		int serviceId = Integer.parseInt(array[0]);
		
		switch(serviceId)
		{
		case CHAT:
			//bla bla
			break;
		case ACCOUNT: 
			//bla bla
			break;
		}
	}
}
