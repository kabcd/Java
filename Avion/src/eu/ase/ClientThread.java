package eu.ase;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ClientThread extends Thread{
	private Socket client;

	public ClientThread(Socket client) {
		this.client = client;
	}
	@Override
	public void run(){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection con=DriverManager.getConnection("jdbc:sqlite:BDAvioane.db");
			Statement st=con.createStatement();
			
			ObjectOutputStream out=null;
			ObjectInputStream in=null;
			try{
				in=new ObjectInputStream(client.getInputStream());
				out=new ObjectOutputStream(client.getOutputStream());
				
				Object obj=in.readObject();
				if(obj instanceof AvionCargo){
					AvionCargo request=(AvionCargo)obj;
					System.out.println("Client request to server: "+request);
	        		st.executeUpdate("INSERT INTO AvionCargo VALUES('"+request.getSerie()+"', "+request.getTonaj()+", '"+request.getMarca()+"', "+request.getCapacitate()+",'"+request.getSerieMarfuri()+"')");
				}
				else if(obj instanceof AvionPasageri){
					AvionPasageri request=(AvionPasageri)obj;
					System.out.println("Client request to server: "+request);
					st.executeUpdate("INSERT INTO AvionPasageri VALUES('"+request.getSerie()+"', "+request.getTonaj()+", '"+request.getMarca()+"', "+request.getCapacitate()+",'"+request.getCnpPasageri()+"')");
				}
	
				out.writeObject(obj);
				
				st.close();
				in.close(); out.close(); client.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
