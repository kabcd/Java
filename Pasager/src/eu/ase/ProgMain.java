package eu.ase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ProgMain {
	public static void main(String[] args) {
		try{
			PasagerEc pe=new PasagerEc("PE01", 18, 35, 115, "CardEc01");
			System.out.println(pe);
		
			PasagerVip pv=new PasagerVip("PV01", 45, 25, 85, "CardVip01");
			System.out.println(pv);
			
			
			Object [][] matrix={ {14,54,48},{5,8,9},{78,45,13}};
			Matrice m=new Matrice(3,3,matrix);
			m.displayMatrice();
			System.out.println(m.getElementMinim());
			
			matrix=new Object[2][2];
			matrix[0][0]="Maria"; matrix[1][0]="Oana";matrix[1][1]="Andrei";
			m=new Matrice(2,2,matrix);
			m.displayMatrice();
			System.out.println(m.getElementMinim());
			
			System.out.println("-----------Matrice Pasageri----------");
			Matrice m2=new Matrice();
			m2.setNrLinii(4);
			m2.setNrColoane(8);
			matrix=new Object[4][8];
			m2.setMasiv(matrix);
			m2.citesteDinFisier("PasagerEc.txt");
			m2.citesteDinFisier("PasagerVip.txt");
			m2.displayMatrice();
			System.out.println("--------Serializare-------");
			try{
				ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(new File("file.txt")));
				out.writeObject(m2);
				out.close();
				
				ObjectInputStream in=new ObjectInputStream(new FileInputStream(new File("file.txt")));
				Matrice obj=(Matrice)in.readObject();
				in.close();
				obj.displayMatrice();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			System.out.println("---------Vector----------");
			m2.transpuneMatrice();
			
			System.out.println("----------Matrice sortata--------");
			m2.sortMatrice();
			m2.displayMatrice();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
