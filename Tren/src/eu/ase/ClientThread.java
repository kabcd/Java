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
			Connection con=DriverManager.getConnection("jdbc:sqlite:BDTren.db");
			Statement st=con.createStatement();
			
			ObjectOutputStream out=null;
			ObjectInputStream in=null;
			try{
				in=new ObjectInputStream(client.getInputStream());
				out=new ObjectOutputStream(client.getOutputStream());
				
				Object obj=in.readObject();
				if(obj instanceof TrenCargo){
					TrenCargo request=(TrenCargo)obj;
					System.out.println("Server response to client: "+request);
					st.executeUpdate("insert into TrenCargo values('"+request.getSerie()+"', "+request.getTonaj()+", '"+request.getMarca()+"', "+request.getCapacitate()+",'"+request.getSerieMarfuri()+"')");
					st.close();
				}
				else if(obj instanceof TrenPasageri){
					TrenPasageri request=(TrenPasageri)obj;
					System.out.println("Server response to client: "+request);
					st.executeUpdate("insert into TrenPasageri values('"+request.getSerie()+"', "+request.getTonaj()+", '"+request.getMarca()+"', "+request.getCapacitate()+",'"+request.getCnpPasageri()+"')");
					st.close();
				}
				
				out.writeObject(obj);
				in.close(); out.close(); client.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}