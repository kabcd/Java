package eu.ase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConexiuneBD {
	public static void main(String[] args) {
		Connection con=null;
		try{
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:BDAvioane.db");
			
			Statement st = con.createStatement();
			st.executeUpdate("drop table AvionCargo");
			st.executeUpdate("drop table AvionPasageri");
			st.executeUpdate("CREATE TABLE AvionPasageri(serie text, tonaj float, marca text primary key not null, capacitate float, cnpPasageri text)");
			st.executeUpdate("CREATE TABLE AvionCargo(serie text, tonaj float, marca text primary key not null, capacitate float, serieMarfuri text)");	
			st.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(con!=null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}
