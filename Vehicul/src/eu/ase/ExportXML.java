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
			Connection con=DriverManager.getConnection("jdbc:sqlite:BdVehicule.db");
			
			ArrayList<VehiculCargo> listaVC=new ArrayList<>();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from VehiculCargo");
			while(rs.next()){
				String serie=rs.getString("serie");
				float tonaj=rs.getFloat("tonaj");
				String marca=rs.getString("marcaC");
				float capacitate=rs.getFloat("capacitate");
				String aux=rs.getString("serieMarfuri");
				Vector<String> serieMarfuri=new Vector<>(); serieMarfuri.add(aux);
				VehiculCargo vc=new VehiculCargo(serie, tonaj, marca, capacitate, serieMarfuri);
				listaVC.add(vc);
			}
			
			ArrayList<VehiculPasageri> listaVP=new ArrayList<>();
			rs=st.executeQuery("select * from VehiculPasageri");
			while(rs.next()){
				String serie=rs.getString("serie");
				float tonaj=rs.getFloat("tonaj");
				String marca=rs.getString("marcaP");
				float capacitate=rs.getFloat("capacitate");
				String aux=rs.getString("cnpPasageri");
				Vector<String> cnpPasageri=new Vector<>(); cnpPasageri.add(aux);
				VehiculPasageri vp=new VehiculPasageri(serie, tonaj, marca, capacitate, cnpPasageri);
				listaVP.add(vp);
			}
			writeXMLVehiculCargo(listaVC);
			writeXMLVehiculPasageri(listaVP);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void writeXMLVehiculCargo(ArrayList<VehiculCargo> listaVC){
		StoreVehiculCargo svc=new StoreVehiculCargo(listaVC);
		try{
			JAXBContext context=JAXBContext.newInstance(StoreVehiculCargo.class);
			Marshaller m=context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(svc, new PrintWriter(new FileWriter(new File("xmlVehiculCargo.txt"))));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void writeXMLVehiculPasageri(ArrayList<VehiculPasageri> listaVP){
		StoreVehiculPasageri svp=new StoreVehiculPasageri(listaVP);
		try{
			JAXBContext context=JAXBContext.newInstance(StoreVehiculPasageri.class);
			Marshaller m=context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(svp, new PrintWriter(new FileWriter(new File("xmlVehiculPasageri.txt"))));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
