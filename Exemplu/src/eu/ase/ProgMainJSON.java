package eu.ase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class ProgMainJSON {
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
			JSONObject obj=new JSONObject();
			obj.put("Denumire", pa.getDenumire());
			obj.put("Masina",c.getId());
			obj.accumulate("Masina", c.getMarca());
			obj.append("Masina", c.getPret() );
			obj.put("Locatie",pa.getLocatie());
			System.out.println(obj);
			FileWriter file=new FileWriter(new File("output2.txt"));
			file.write(obj.toString());
			file.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			BufferedReader file=new BufferedReader(new FileReader(new File("output2.txt")));
			String line;
			StringBuilder sb=new StringBuilder();
			while((line=file.readLine())!=null)
				sb.append(line);
			String json=sb.toString();
			JSONObject obj=new JSONObject(json);
			System.out.println(obj);
			
			file.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
