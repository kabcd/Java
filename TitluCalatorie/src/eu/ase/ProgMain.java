package eu.ase;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ProgMain {
	public static void main(String[] args) {
		try{
			TitluCalatorieMetropolitan tcm=new TitluCalatorieMetropolitan(1, "TitluM01", 2, 3, "OperatorM");
			TitluCalatorieUrban tcu=new TitluCalatorieUrban(2, "TitluU01", 1, 2, "OperatorU");
			System.out.println(tcm.getZona());
			System.out.println(tcu.getZona());
			
			String [][] matr=new String[2][2];
			matr[0][0]="Maria";
			matr[1][1]="Bianca";
			Matrice m1=new Matrice(2,2,matr);
			m1.displayMatrice();
			System.out.println(m1.getMinim());
			
			Integer [][]matr1={ {23,12,55},{78,9,1},{10,52,73}};
			Matrice  m2=new Matrice(3,3,matr1);
			m2.displayMatrice();
			System.out.println(m2.getMinim());
			
			Object [][] tren =new Object[4][4];
			Matrice m=new Matrice(4,4,tren);
			m.citesteFisier("TitluCalatorieUrban.txt");
			m.citesteFisier("TitluCalatorieMetropolitan.txt");
			
			System.out.println("-------Tren---------");
			m.displayMatrice();
			System.out.println(m.getMinim());
			
			System.out.println("---------Matrice sortata-----------");
			m.sortMatrice();
			m.displayMatrice();
			System.out.println("----------Matrice in Vector------------");
			m.transpuneInVector();
			
			try{
				ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("file2.txt"));
				out.writeObject(m);
				out.close();
				
				System.out.println("----------Citire Matrice Object------------");
				ObjectInputStream in=new ObjectInputStream(new FileInputStream("file2.txt"));
				Matrice x=(Matrice)in.readObject();
				x.displayMatrice();
				
				in.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
