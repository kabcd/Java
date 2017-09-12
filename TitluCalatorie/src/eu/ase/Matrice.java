package eu.ase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public class Matrice implements Comparable<Matrice>, Serializable {
	private int nrLinii;
	private int nrColoane;
	private Object[][] mat;
	
	public Matrice() {
	}
	public Matrice(int nrLinii, int nrColoane, Object[][] mat) {
		this.nrLinii = nrLinii;
		this.nrColoane = nrColoane;
		this.mat = new Object[nrLinii][nrColoane];
		for (int i = 0; i < this.nrLinii; i++) {
			for (int j = 0; j < this.nrColoane; j++) {
				this.mat[i][j]=mat[i][j];
	        }
		}
	}
	public int getNrLinii() {
		return nrLinii;
	}
	public int getNrColoane() {
		return nrColoane;
	}
	public Object[][] getMat() {
		return mat;
	}
	public void setNrLinii(int nrLinii) {
		this.nrLinii = nrLinii;
	}
	public void setNrColoane(int nrColoane) {
		this.nrColoane = nrColoane;
	}
	public void setMat(Object[][] mat) {
		this.mat = mat;
	}
	
	public Object getMinim(){
		Object minim=null;
		for (int i = 0; i < this.nrLinii; i++) {
			for (int j = 0; j < this.nrColoane; j++) {
				if(this.mat[i][j]!=null){
					minim=this.mat[i][j];
					i=this.nrLinii;
					j=this.nrColoane;
				}
			}
		}
		for (int i = 0; i < this.nrLinii; i++) {
			for (int j = 0; j < this.nrColoane; j++) {
				if( this.mat[i][j]!=null){
					if(this.mat[i][j].hashCode()<minim.hashCode())
						minim=this.mat[i][j];
				}
	        }
		}
		return minim;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Matrice other=(Matrice)super.clone();
		Object[][] m = new Object[other.nrLinii][other.nrColoane];
		for (int i = 0; i < this.getNrLinii(); i++) {
            for (int j = 0; j < this.getNrColoane(); j++) {
            	m[i][j]=this.mat[i][j];
            }
        }
		other.mat=m;
		return other;
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Matrice))
            return false;
        Matrice other = (Matrice) obj;
        for (int i = 0; i < this.getNrLinii(); i++) {
            for (int j = 0; j < this.getNrColoane(); j++) {
                if (this.mat[i][j]!=other.mat[i][j]) 
                    return false;
            }
        }
        return this.getNrLinii() == other.getNrLinii() && this.getNrColoane()== other.getNrColoane();
	}

	@Override
	public int compareTo(Matrice o) {
		if(this.mat.hashCode()==o.mat.hashCode())
			return 0;
		else if(this.mat.hashCode()>o.mat.hashCode())
			return 1;
		else
			return -1;
	}
	public void citesteFisier(String numeFisier){
		BufferedReader in=null;
		try{
			in=new BufferedReader(new FileReader(new File(numeFisier)));
			String line=null;
			while((line=in.readLine())!=null){
				StringTokenizer columns=new StringTokenizer(line,"#");
				while (columns.hasMoreElements()) {
					String id=columns.nextElement().toString();
					String denumire=columns.nextElement().toString();
					String idLinie=columns.nextElement().toString();
					String idZona=columns.nextElement().toString();
					String denumireOperatorUrbanSauMetropolitan=columns.nextElement().toString();
					if(numeFisier.equals("TitluCalatorieUrban.txt"))
					{
						TitluCalatorieUrban aux=new TitluCalatorieUrban(Integer.parseInt(id), denumire, Float.parseFloat(idLinie), Integer.parseInt(idZona), denumireOperatorUrbanSauMetropolitan);
						this.mat[Integer.parseInt(idZona)][Integer.parseInt(idLinie)]=aux;
					}
					else if(numeFisier.equals("TitluCalatorieMetropolitan.txt"))
					{
						TitluCalatorieMetropolitan aux=new TitluCalatorieMetropolitan(Integer.parseInt(id), denumire, Float.parseFloat(idLinie), Integer.parseInt(idZona), denumireOperatorUrbanSauMetropolitan);
						this.getMat()[Integer.parseInt(idZona)][Integer.parseInt(idLinie)]=(Object)aux;
					}
				}
			}
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void displayMatrice(){
		for (int i = 0; i < this.getNrLinii(); i++) {
			for (int j = 0; j < this.getNrColoane(); j++) {
				System.out.printf("%-30s  ",this.getMat()[i][j]);
	        }
			System.out.println();
		}
	}
	
	public void sortMatrice() 
	{
		ArrayList<TitluCalatorie> lista=new ArrayList<>();
		for(int i = 0; i < this.nrLinii; i++) {
	   		for(int j = 0; j < this.nrColoane; j++){
	   			if(this.mat[i][j] instanceof TitluCalatorie)
	   				lista.add((TitluCalatorie)this.mat[i][j]);
	    	}
		}
		Collections.sort(lista, (a, b) -> Integer.compare(a.getId(),b.getId()));
		int k=0;
		for(int i = 0; i < this.nrLinii; i++) {
			for(int j = 0; j < this.nrColoane; j++){
				if(this.mat[i][j]!=null){
					this.mat[i][j]=lista.get(k);
					k++;
				}
			}
		}
	}
	public void transpuneInVector(){
		Vector<TitluCalatorie> aux = new Vector<>();
    	for(int i = 0; i < this.nrLinii; i++) {
    		for(int j = 0; j < this.nrColoane; j++){
		    		aux.add((TitluCalatorie)this.mat[i][j]);
		   	}
		}
    	for(TitluCalatorie i: aux){
    		System.out.println(i);
    	}
	}
}
