import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;



//Handles the connection between the server and the specific client
public class ClientSession implements Runnable {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	
	
	private int timeOut = 30000; //Timeout limit in milliseconds
	private long lastCheck = System.currentTimeMillis();
	
	private Server server;
	

	public ClientSession(Server server, Socket client)
	{
		socket = client;
		this.server = server;
		try {
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//create I/O reader etc.
		
		while(true)
		{
			
			//Do work here
			String readLine = null;
			try {
				readLine = in.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			server.getServerProtocol().process(server, this, readLine);
			
			if(readLine != null) {
				System.out.println("Message: " + readLine);
				out.println("hej");
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void checkTimeOut()
	{
		if(System.currentTimeMillis() >= lastCheck + timeOut)
		{
			//
		}
		
	}
	
}
