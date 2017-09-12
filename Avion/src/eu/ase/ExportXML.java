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
			 Connection con=DriverManager.getConnection("jdbc:sqlite:BDAvioane.db");
			 Statement st=con.createStatement();
			 ResultSet rs=st.executeQuery("select * from AvionCargo");
			 ArrayList<AvionCargo> listaAC=new ArrayList<AvionCargo>();
			 while(rs.next()){
				 String marca=rs.getString("marca");
				 float tonaj=rs.getFloat("tonaj");
				 String serie=rs.getString("serie");
				 float capacitate=rs.getFloat("capacitate");
				 String serieMarfuri=rs.getString("serieMarfuri");
				 Vector<String> vec=new Vector<String>();
				 vec.add(serieMarfuri);
				 AvionCargo ac=new AvionCargo(serie, tonaj, marca, capacitate, vec);
				 listaAC.add(ac);
			 }
			 writeXmlTrenCargo(listaAC);
			 rs=st.executeQuery("select * from AvionPasageri");
			 ArrayList<AvionPasageri> listaAP=new ArrayList<AvionPasageri>();
			 while(rs.next()){
				 String marca=rs.getString("marca");
				 float tonaj=rs.getFloat("tonaj");
				 String serie=rs.getString("serie");
				 float capacitate=rs.getFloat("capacitate");
				 String cnpPasageri=rs.getString("cnpPasageri");
				 Vector<String> vec=new Vector<String>();
				 vec.add(cnpPasageri);
				 AvionPasageri ap=new AvionPasageri(serie, tonaj, marca, capacitate, vec);
				 listaAP.add(ap);
			 }
			 writeXmlTrenPasageri(listaAP);
			 st.close();rs.close();con.close();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
			
	}
	 public static void writeXmlTrenCargo(ArrayList<AvionCargo> lista){
		 StoreAvionCargo sac=new StoreAvionCargo(lista);
		 try{
			 JAXBContext context=JAXBContext.newInstance(StoreAvionCargo.class);
			 Marshaller m=context.createMarshaller();
			 m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			 m.marshal(sac, new PrintWriter(new FileWriter(new File("xmlAvionCargo.txt"))));
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	 public static void writeXmlTrenPasageri(ArrayList<AvionPasageri> lista){
		 StoreAvionPasageri sap=new StoreAvionPasageri(lista);
		 try{
			 JAXBContext context=JAXBContext.newInstance(StoreAvionPasageri.class);
			 Marshaller m=context.createMarshaller();
			 m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			 m.marshal(sap, new PrintWriter(new FileWriter(new File("xmlAvionPasageri.txt"))));
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
}
