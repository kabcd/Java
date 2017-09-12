package eu.ase;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class ExportXML {
	public static void main(String[] args) {
		try{
			Class.forName("org.sqlite.JDBC");
			Connection con=DriverManager.getConnection("jdbc:sqlite:BDTren.db");
			
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from TrenCargo");
			ArrayList<TrenCargo> listaTC=new ArrayList<>();
			while(rs.next()){
				String serie=rs.getString("serie");
				float tonaj=rs.getFloat("tonaj");
				String marca=rs.getString("marca");
				float capacitate=rs.getFloat("capacitate");
				String str=rs.getString("serieMarfuri");
				Vector<String> lista=new Vector<String>(); lista.add(str);
				TrenCargo tc=new TrenCargo(serie, tonaj, marca, capacitate, lista);
				listaTC.add(tc);
			}
			writeXMLTrenCargo(listaTC);
			
			rs=st.executeQuery("select * from TrenPasageri");
			ArrayList<TrenPasageri> listaTP=new ArrayList<>();
			while(rs.next()){
				String serie=rs.getString("serie");
				float tonaj=rs.getFloat("tonaj");
				String marca=rs.getString("marca");
				float capacitate=rs.getFloat("capacitate");
				String str=rs.getString("cnpPasageri");
				Vector<String> lista=new Vector<String>(); lista.add(str);
				TrenPasageri tp=new TrenPasageri(serie, tonaj, marca, capacitate, lista);
				listaTP.add(tp);
			}
			writeXMLTrenPasageri(listaTP);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void writeXMLTrenCargo(ArrayList<TrenCargo> listaTC){
		StoreTrenCargo stc=new StoreTrenCargo(listaTC);
		try{
			JAXBContext context=JAXBContext.newInstance(StoreTrenCargo.class);
			Marshaller m=context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			PrintWriter file=new PrintWriter(new FileWriter(new File("xmlTrenCargo.txt")));
			m.marshal(stc, file);
			file.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void writeXMLTrenPasageri(ArrayList<TrenPasageri> listaTP){
		StoreTrenPasageri stp=new StoreTrenPasageri(listaTP);
		try{
			JAXBContext context=JAXBContext.newInstance(StoreTrenPasageri.class);
			Marshaller m=context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			PrintWriter file=new PrintWriter(new FileWriter(new File("xmlTrenPasageri.txt")));
			m.marshal(stp, file);
			file.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
