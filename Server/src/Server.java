import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {

	private static ServerSocket serverSocket;
	private static int port = 3322;
	private ServerProtocol protocol;
	private Socket client;

	public Server()
	{
		protocol = new ServerProtocol();
		setUpServer();
		acceptConnections();
	}



	public static void main(String[] args)
	{
		System.out.println(Runtime.getRuntime().availableProcessors());
		Server s = new Server();
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
				//Fix this
			}catch(IOException e){
				e.printStackTrace();
				//And this
			}
		}
	}
	
	public ServerProtocol getServerProtocol()
	{
		return protocol;
	}
}


