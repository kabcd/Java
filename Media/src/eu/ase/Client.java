package eu.ase;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	public void transmite(Object obj) {
		Socket client=null;
		ObjectOutputStream out=null;
		ObjectInputStream in=null;
		try{
			client=new Socket(InetAddress.getLocalHost(),8000);
			out=new ObjectOutputStream(client.getOutputStream());
			out.writeObject(obj);
			
			in=new ObjectInputStream(client.getInputStream());
			obj=in.readObject();
			if(obj instanceof StreamAudio){
				StreamAudio response=(StreamAudio)obj;
				System.out.println("Server response to client: "+response);	
			}
			else if(obj instanceof StreamVideo){
				StreamVideo response=(StreamVideo)obj;
				System.out.println("Server response to client: "+response);
			}
			in.close();out.close();client.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}	
