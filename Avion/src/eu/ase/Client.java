package eu.ase;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	public void transmite(Object o){
		Socket client=null;
		ObjectOutputStream out=null;
		ObjectInputStream in=null;
		try{
			client=new Socket(InetAddress.getLocalHost(),8000);
			out=new ObjectOutputStream(client.getOutputStream());
			in=new ObjectInputStream(client.getInputStream());
			out.writeObject(o);
			
			Object obj=in.readObject();
			if(o instanceof AvionCargo){
				AvionCargo response=(AvionCargo)obj;
				System.out.println("Server response to client: "+response);
			}
			else if(o instanceof AvionPasageri){
				AvionPasageri response=(AvionPasageri)obj;
				System.out.println("Server response to client: "+response);
			}
			out.close();
			in.close();
			client.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
