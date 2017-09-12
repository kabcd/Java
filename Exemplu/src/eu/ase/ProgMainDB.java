package eu.ase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProgMainDB {
	public static void main(String[] args) {
		Connection con=null;
		try{
			Class.forName("org.sqlite.JDBC");
			
			con=DriverManager.getConnection("jdbc:sqlite:test.db");
			
			con.setAutoCommit(false);
			
			//dropTable(con);
			createTable(con);
			insertTable(con);
			selectTable(con);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(con!=null)
				try{
					con.close();
				}catch (Exception e2) {
					e2.printStackTrace();
				}
		}
	}
	public static void createTable(Connection con) throws Exception
	{
		Statement st=null;
		st=con.createStatement();
		String sql="create table masini( id int primary key not null, marca text not null, pret real);";
		st.executeUpdate(sql);
		st.close();
		con.commit();
	}
	public static void dropTable(Connection con) throws Exception
	{
		Statement st=null;
		st=con.createStatement();
		String sql="drop table masini;";
		st.executeUpdate(sql);
		st.close();
		con.commit();
	}
	public static void insertTable(Connection con) throws Exception
	{
		Statement st=null;
		st=con.createStatement();
		String sql="insert into masini values(25,'Skod',900);";
		st.executeUpdate(sql);
		st.close();
		con.commit();
	}
	public static void selectTable(Connection con) throws Exception
	{
		Statement st=null;
		st=con.createStatement();
		String sql="select * from masini;";
		ResultSet rs=st.executeQuery(sql);
		while(rs.next())
		{
			int id=rs.getInt("id");
			String marca=rs.getString("marca");
			float pret=rs.getFloat("pret");
			System.out.println("\nCar: id=" + id + ", marca=" + marca + ", pret=" + pret);
			
		}
		st.close();
		rs.close();
	}
}
