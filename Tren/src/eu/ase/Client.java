package eu.ase;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	public void transmite(Object o){
		ObjectOutputStream out=null;
		ObjectInputStream in=null;
		Socket client=null;
		try{
			client=new Socket(InetAddress.getLocalHost(), 8000);
			out=new ObjectOutputStream(client.getOutputStream());
			in=new ObjectInputStream(client.getInputStream());
			
			out.writeObject(o);
			
			Object obj=in.readObject();
			if(obj instanceof TrenCargo){
				TrenCargo response=(TrenCargo)obj;
				System.out.println("Server response to client: "+response);
			}
			else if(obj instanceof TrenPasageri){
				TrenPasageri response=(TrenPasageri)obj;
				System.out.println("Server response to client: "+response);
			}
			
			out.close(); in.close(); client.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
