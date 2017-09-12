package eu.ase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.json.JSONObject;

public class JsonXmlReadWite {
	public static void wrietXML(StoreAudio obj){
		try{
			JAXBContext context=JAXBContext.newInstance(StoreAudio.class);
			Marshaller m=context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			PrintWriter file=new PrintWriter(new FileWriter(new File("xmlFile.txt")),true);
			m.marshal(obj, file);
			file.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void readXML(){
		System.out.println("--------Citire din XML------------");
		try{
			JAXBContext context=JAXBContext.newInstance(StoreAudio.class);
			Unmarshaller m=context.createUnmarshaller();
			BufferedReader file=new BufferedReader(new FileReader(new File("xmlFile.txt")));
			StoreAudio strA=(StoreAudio)m.unmarshal(file);
			for(Audio i:strA.getlistaAudio())
				i.returneazaPlayer();
			file.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void writeJson(StoreAudio strA){
		try{
			JSONObject obj=new JSONObject();
			PrintWriter file=new PrintWriter(new FileWriter("jsonFile.txt",true));
			for(Audio a:strA.getlistaAudio()){
				obj.put("denumire", a.getDenumire());
				obj.put("lungime", a.getLungime());
				obj.put("calitate", a.getCalitate());
				file.println(obj.toString());
			}
				file.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void readJson(){
		try{
			BufferedReader file=new BufferedReader(new FileReader(new File("jsonFile.txt")));
			StringBuilder sb=new StringBuilder();
			String line=null;
			while((line=file.readLine())!=null){
				sb.append(line);
			}
			String strJson=sb.toString();
			String []sir=strJson.split("\n");
			for(int i=0;i<sir.length;i++){
				JSONObject obj=new JSONObject(sir[i]);
				System.out.println(obj);
			}
			file.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
