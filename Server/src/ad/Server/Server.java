package ad.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import ad.model.protocol.*;

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

public class Server {

	private static ServerSocket serverSocket;
	private static int port = 3322;
	private ServerProtocol protocol;
	private Socket client;
	
	
	//A hashmap over logged in users, where the integer is the UserId
	private HashMap <Integer,ArrayList<ClientSession>> loggedInUsers;
	private ArrayList<User> users;

	public Server()
	{
		loggedInUsers = new HashMap<Integer,ArrayList<ClientSession>>();
		users = new ArrayList<User>();
		protocol = new ServerProtocol();
		setUpServer();
		acceptConnections();
	}



	public static void main(String[] args)
	{
		new Server();
	}

	public void setUpServer()
	{
		//Set up the server:
		try{
			System.out.println("Waiting for client connection");
			serverSocket = new ServerSocket(port);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void acceptConnections()
	{
		while(true)
		{
			client = null;
			//Accept the connection from a new client
			try{
				client = serverSocket.accept();
				System.out.println(client.getInetAddress() + " connected");
				//Create a new ClientConnection in a new thread and start it
				new Thread(new ClientSession(this, client)).start();
			}catch(SocketException e){
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public ServerProtocol getServerProtocol()
	{
		return protocol;
	}
	
	public void connectUser(int user, ClientSession clientSession)
	{
		ArrayList<ClientSession> clientConnections;
		clientConnections = loggedInUsers.get(user);
		
		//Initialize the arraylist of clientsessions if it isn't done yet
		if(clientConnections == null)
			clientConnections = new ArrayList<ClientSession>();
		
		clientConnections.add(clientSession);
		loggedInUsers.put(user, clientConnections);
	}
	
	public ArrayList<ClientSession> getUsersClientSessions(int u)
	{
		return loggedInUsers.get(u);
	}
	
	
	//Add a user to the server
	public void addUser(User u)
	{
		users.add(u);
		System.out.println("Added user " + u.getUserName());
	}
	
}


