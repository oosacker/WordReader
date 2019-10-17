import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {

	DatagramSocket socket;
	InetAddress ip;
	byte[] receiveBuffer, sendBuffer;
	DatagramPacket myReceivePacket, mySendPacket;
	String received, send;
	int serverPort = 3000;

	
	public Server() throws Exception {
		
		ip = InetAddress.getLocalHost();  
		socket = new DatagramSocket(serverPort);  
		receiveBuffer = new byte[256]; 
		sendBuffer = new byte[256]; 
		myReceivePacket = null;
		mySendPacket = null; 
		
		String myJoke;
		int random;

		System.out.println("Server running...");
		System.out.println("~~~~~~~~~~~~~~~~~");

		
		do {
			myReceivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length); 	 
			socket.receive(myReceivePacket);
			received = data(receiveBuffer).toString();
			System.out.println("Received: " + received); 	

			random = (int)(Math.random() * jokesArray.size());
			myJoke = jokesArray.get(random);
			sendBuffer = myJoke.getBytes();


			myReceivePacket.setData(sendBuffer);	//change the data in packet

			System.out.println("Sent: " +myJoke); 
			
			socket.send(myReceivePacket); 

			receiveBuffer = new byte[256]; 
			sendBuffer = new byte[256]; 

		}while(!received.equals("bye"));

		socket.close();
		System.out.println("Server finish"); 

	}

	
	
	static ArrayList<String> jokesArray;
	
	public static void readline() {
		
		jokesArray = new ArrayList<String>();
		
		String fileName = "jokes.txt";

	
		try {
			
			Scanner scanner = new Scanner(new File(fileName));

			
			while (scanner.hasNextLine()) {
				
				// FILE CANNOT CONTAIN QUOTE MARKS!!!!!
				String funnyJoke = scanner.nextLine();
				//System.out.println(funnyJoke);
				
				jokesArray.add(funnyJoke);

			}
			scanner.close();
			System.out.println("Joke number: " + jokesArray.size()); 	
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		readline();
		new Server();
		
		//readline();

	}

	public static StringBuilder data(byte[] a) { 
		if (a == null) 
			return null; 
		StringBuilder ret = new StringBuilder(); 
		int i = 0; 
		while (a[i] != 0) { 
			ret.append((char) a[i]); 
			i++; 
		} 
		return ret; 
	}

}
