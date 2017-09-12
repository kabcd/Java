package eu.ase;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProgMain {
	public static void main(String[] args) {
		try{
			Audio a=new Audio("Audio1", 100, 1, 1000);
			a.returneazaPlayer();
			
			Audio a2=new Audio("Audio2", 200, 1, 2000);
			Audio a3=new Audio("Audio3", 300, 1, 3000);
			ArrayList<Audio> lista=new ArrayList<>();
			lista.add(a); lista.add(a2);lista.add(a3);
			StoreAudio strA=new StoreAudio(lista);
			
			JsonXmlReadWite.writeJson(strA);
			JsonXmlReadWite.readJson();
			
			JsonXmlReadWite.wrietXML(strA);
			JsonXmlReadWite.readXML();
			
			
			Video v=new Video("Video1", 200, 2, 1500);
			v.returneazaPlayer();
			
			Vector<Integer> strSA=new Vector<>();
			StreamAudio sa=new StreamAudio("Audio2", 300, 3, 1100, strSA);
			
			Vector<Integer> strSV=new Vector<>();
			StreamVideo sv=new StreamVideo("Video2", 400, 4, 1200, strSV);
			
			sa.citesteStreamFisier("Audio.txt");
			sv.citesteStreamFisier("Video.txt");
			
			System.out.println(sa.toString());
			System.out.println(sv.toString());
			
			System.err.println("---------Scriere/Citire Fisier------------");
			scrieObiectInFisier(sa);
			citesteObiectDinFisier("scrie.txt");
			scrieObiectInFisier(sv);
			citesteObiectDinFisier("scrie.txt");
			
			sa.returneazaPlayer();
			sv.returneazaPlayer();
			
			Client c=new Client();
			c.transmite(sv);
			ExecutorService executor = Executors.newFixedThreadPool(2);
			executor.execute(sa);
			executor.execute(sv);
			executor.shutdown();

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void scrieObiectInFisier(Object obj){
		ObjectOutputStream out=null;
		try{
			out=new ObjectOutputStream(new FileOutputStream("scrie.txt"));
			out.writeObject(obj);
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void citesteObiectDinFisier(String numeFisier){
		ObjectInputStream in=null;
		try{
			in=new ObjectInputStream(new FileInputStream(numeFisier));
			Object obj=in.readObject();
			if(obj instanceof StreamAudio){
				StreamAudio sa=(StreamAudio)obj;
				System.out.println(sa);
			}
			else if(obj instanceof StreamVideo){
				StreamVideo sv=(StreamVideo)obj;
				System.out.println(sv);
			}
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
