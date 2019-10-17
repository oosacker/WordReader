import java.io.*;
import java.security.*;
import javax.crypto.Cipher;


public class Main {
	
	Cipher cipher;
	ObjectInputStream objectin;
	ObjectOutputStream objout;
	

	public void generateKeys(int bits) throws Exception {

		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		keyPairGen.initialize(bits);
		KeyPair pair = keyPairGen.generateKeyPair();
		
		PrivateKey pri = pair.getPrivate();
		PublicKey pub = pair.getPublic();
		
		//System.out.println(pri);
		//System.out.println(pub);
		
        objout = new ObjectOutputStream(new FileOutputStream("privatekey"));
        objout.writeObject(pri);
        objout.close();
        
        objout = new ObjectOutputStream(new FileOutputStream("publickey"));
        objout.writeObject(pub);
        objout.close();
        
	}
	
	public KeyPair readKeys() throws Exception{
		
		// create an ObjectInputStream for the file we created before
        objectin = new ObjectInputStream(new FileInputStream("privatekey"));
        PrivateKey pri = (PrivateKey) objectin.readObject();
        objectin.close();
        
        objectin = new ObjectInputStream(new FileInputStream("publickey"));
        PublicKey pub = (PublicKey) objectin.readObject();
        objectin.close();
        
        KeyPair newPair = new KeyPair(pub, pri);

        return newPair;
        
	}
	
	public byte[] encryptMessage(String plaintextMsg, Key key) throws Exception {
		
		byte[] byteMsg = plaintextMsg.getBytes();
		cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		return cipher.doFinal(byteMsg);
		
	}
	
	public byte[] decryptMessage(byte[] encodedMsg, Key key) throws Exception {
		
		cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, key);
		
		return cipher.doFinal(encodedMsg);
		
	}
	
	public Main() throws Exception {
		
		generateKeys(1024);	// generate original RSA key pair
		KeyPair mypair  = readKeys();	// read the key pair from file
		
		String msg = "This is the message that I will encode!!!";
		System.out.println("Original: " + msg);
		
		byte[] coded = encryptMessage(msg, mypair.getPublic());
		System.out.println("Encoded: " + new String(coded, 0, coded.length));
		
		byte[] decoded = decryptMessage(coded, mypair.getPrivate());
		System.out.println("Decoded: " + new String(decoded, 0, decoded.length));
		
	}
	
	public static void main(String[] args) throws Exception {
		new Main();
	}

}
