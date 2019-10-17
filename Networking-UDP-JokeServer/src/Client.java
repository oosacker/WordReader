import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {

	DatagramSocket socket; 
	InetAddress ip;  
    byte[] sendBuffer = null; 
    byte[] receiveBuffer = new byte[256];
    String sendString, receiveString;
    DatagramPacket mySendPacket, myReceivePacket;
    int port = 3000;
    String inp;

    public Client() throws Exception {
    	socket = new DatagramSocket(); 
    	ip = InetAddress.getLocalHost();  

		long start = System.nanoTime();
		
		System.out.println("Client running..."); 
		System.out.println("~~~~~~~~~~~~~~~~~");

    	Scanner sc = new Scanner(System.in);
    	
    	
    	myReceivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length); 

    	
    	do{ 

    		sendString = sc.nextLine(); 

    		sendBuffer = sendString.getBytes(); 
    		//mySendPacket.setData(sendBuffer);
    		mySendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, ip, port); 
    		
    		socket.send(mySendPacket); 
    		System.out.println("Sent: " + sendString); 

    		
    		socket.receive(myReceivePacket);
    		receiveString = new String(myReceivePacket.getData(), 0, myReceivePacket.getLength());
    		System.out.println("Received: " + receiveString); 

    		//receiveBuffer = new byte[256]; 
    		//sendBuffer = new byte[256]; 

    	}while (!sendString.equals("bye")); 

    	socket.close();
    	
    	long time = System.nanoTime() - start;
		System.out.println("~~~~~~~~~~~~~~~~~");
		System.out.println("Time (ns): " +time);
		System.out.println("Client End"); 

	}

	public static void main(String[] args) throws Exception {
		new Client();
	}
	

}
