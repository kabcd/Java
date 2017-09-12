package eu.ase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class ProgMainXML {
	public static void main(String[] args) {
		Car c=new Car(12, "Logan", 500);
		Car c2=new Car(15,"Audi",800);
		Car c3=new Car(18,"Dacia",900);
		Car c4=new Car(19,"Cielo",600);
		
		List<Car> lista=new ArrayList<Car>();
		lista.add(c);
		lista.add(c2);
		lista.add(c3);
		lista.add(c4);
		
		for(Car i:lista)
			System.out.println(i);
		
		ParcAuto pa=new ParcAuto(lista,"Bucuresti", "Baneasa Shopping City");
		try{
			JAXBContext context=JAXBContext.newInstance(ParcAuto.class);
			Marshaller m=context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(pa, System.out);
			FileWriter file=new FileWriter(new File("output.txt"));
			m.marshal(pa, file);
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			JAXBContext context=JAXBContext.newInstance(ParcAuto.class);
			Unmarshaller um=context.createUnmarshaller();
	
			BufferedReader file=new BufferedReader(new FileReader(new File("output.txt")));
			ParcAuto paXml=(ParcAuto)um.unmarshal(file);
			System.out.println(paXml.getDenumire());
			System.out.println(paXml.getLocatie());
			for(Car i: paXml.getlistaMasini())
				System.out.println(i);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
