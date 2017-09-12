package eu.ase;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ClientThread extends Thread {
	private Socket client;

	public ClientThread(Socket client) {
		this.client = client;
	}
	
	@Override
	public void run(){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection con=DriverManager.getConnection("jdbc:sqlite:BdVehicule.db");
			
			Statement st=con.createStatement();
			ObjectOutputStream out=null;
			ObjectInputStream in=null;
			try{
				in=new ObjectInputStream(client.getInputStream());
				out=new ObjectOutputStream(client.getOutputStream());
				
				Object o=in.readObject();
				if(o instanceof VehiculCargo){
					VehiculCargo request=(VehiculCargo)o;
					System.out.println("Client request to server: "+request);
					st.executeUpdate("insert into VehiculCargo values('"+request.getSerie()+"', "+request.getTonaj()+", '"+request.getMarca()+"', "+request.getCapacitate()+",'"+request.getSerieMarfuri()+"')");
					st.close();
				}
				else if(o instanceof VehiculPasageri){
					VehiculPasageri request=(VehiculPasageri)o;
					System.out.println("Client request to server: "+request);
					st.executeUpdate("insert into VehiculPasageri values('"+request.getSerie()+"', "+request.getTonaj()+", '"+request.getMarca()+"', "+request.getCapacitate()+",'"+request.getCnpPasageri()+"')");
					st.close();
				}
				
				out.writeObject(o);
				in.close(); out.close(); client.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	

}
