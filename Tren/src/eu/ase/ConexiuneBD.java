package eu.ase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConexiuneBD {
	public static void main(String[] args) {
		Connection con=null;
		try{
			Class.forName("org.sqlite.JDBC");
			con=DriverManager.getConnection("jdbc:sqlite:BDTren.db");
			
			Statement st=con.createStatement();
			st.executeUpdate("drop table TrenCargo");
			st.executeUpdate("drop table TrenPasageri");
			st.executeUpdate("create table TrenCargo(serie text, tonaj real, marca text not null primary key, capacitate text, serieMarfuri text)");
			st.executeUpdate("create table TrenPasageri(serie text, tonaj real, marca text not null primary key, capacitate text, cnpPasageri text)");
			
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
