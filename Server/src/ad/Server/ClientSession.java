package ad.Server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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


//Handles the connection between the server and the specific client
public class ClientSession implements Runnable {

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	private int timeOut = 30000; //Timeout limit in milliseconds
	private long lastCheck = System.currentTimeMillis();
	
	private Server server;
	

	public ClientSession(Server server, Socket client)
	{
		socket = client;
		this.server = server;
		try {
			
			in = new ObjectInputStream(client.getInputStream());
			out = new ObjectOutputStream(client.getOutputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted())
		{
			
			Object o = null;
			
			//Read from the client
			try {
				o = in.readObject();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				Thread.currentThread().interrupt();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			//Generate answer and send it
			if(o != null) {
				server.getServerProtocol().process(server, this, o);
			}
			
		
			
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
		}
		
	}
	
	
	public void writeToClient(Object o) 
	{
		
		try {
			out.writeObject(o);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void checkTimeOut()
	{
		if(System.currentTimeMillis() >= lastCheck + timeOut)
		{

		}
		
	}

	public Socket getSocket()
	{
		return socket;
	}
}
