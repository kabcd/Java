package eu.ase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConexiuneBD {
	public static void main(String[] args) {
		try{
			Class.forName("org.sqlite.JDBC");
			Connection con=DriverManager.getConnection("jdbc:sqlite:BdVehicule.db");
			
			Statement st=con.createStatement();
			st.executeUpdate("drop table VehiculCargo");
			st.executeUpdate("drop table VehiculPasageri");
			st.executeUpdate("create table VehiculCargo(serie text, tonaj real, marcaC text primary key, capacitate real, serieMarfuri text);");
			st.executeUpdate("create table VehiculPasageri(serie text, tonaj real, marcaP text primary key, capacitate real, cnpPasageri text);");
			st.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
