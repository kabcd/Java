package eu.ase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class creareConexiune {
	public static void main(String[] args) {
		Connection con=null;
		try{
			Class.forName("org.sqlite.JDBC");
			con=DriverManager.getConnection("jdbc:sqlite:BazaDeDate");
			con.setAutoCommit(false);
			
			Statement st=con.createStatement();
			st.executeUpdate("create table StreamAudio(denumire text primary key not null, lungime real,semnaturaUnica int, calitate int);");
			
			st.executeUpdate("insert into StreamAudio values('Audio1',100,1,500);");
			st.executeUpdate("insert into StreamAudio values('Audio2',200,2,600);");
			st.close();
			con.commit();
			
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from StreamAudio");
			while(rs.next()){
				String denumire=rs.getString("denumire");
				float lungime=rs.getFloat("lungime");
				int semnaturaUnica=rs.getInt("semnaturaUnica");
				int calitate=rs.getInt("calitate");
				StreamAudio sa=new StreamAudio(denumire, lungime, semnaturaUnica, calitate, null);
				System.out.println(sa.toString());
			}
			stmt.close();
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(con!=null){
				try{
					con.close();
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
	}
}
