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

public class Matrice implements Comparable<Matrice>, Serializable{
	private int nrLinii;
	private int nrColoane;
	private Object[][] masiv;
	
	public Matrice() {
	}

	public Matrice(int nrLinii, int nrColoane, Object[][] masiv) {
		this.nrLinii = nrLinii;
		this.nrColoane = nrColoane;
		this.masiv = new Object[nrLinii][nrColoane];
		for(int i=0;i<nrLinii;i++){
			for(int j=0;j<nrColoane;j++){
				this.masiv[i][j]=masiv[i][j];
			}
		}
	}

	public int getNrLinii() {
		return nrLinii;
	}

	public void setNrLinii(int nrLinii) {
		this.nrLinii = nrLinii;
	}

	public int getNrColoane() {
		return nrColoane;
	}

	public void setNrColoane(int nrColoane) {
		this.nrColoane = nrColoane;
	}

	public Object[][] getMasiv() {
		return masiv;
	}

	public void setMasiv(Object[][] masiv) {
		this.masiv = masiv;
	}

	public Object getElementMinim(){
		Object minim=this.masiv[0][0];
		for(int i=0;i<this.nrLinii;i++){
			for(int j=0;j<this.nrColoane;j++){
				if(this.masiv[i][j]!=null && this.masiv[i][j].hashCode()<minim.hashCode())
					minim=this.masiv[i][j];
			}
		}
		return minim;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Matrice other=(Matrice)super.clone();
		other.masiv = new Object[this.nrLinii][this.nrColoane];
		for(int i=0;i<this.nrLinii;i++){
			for(int j=0;j<this.nrColoane;j++){
				other.masiv[i][j]=this.masiv[i][j];
			}
		}
		return other;
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Matrice))
			return false;
		Matrice other=(Matrice)obj;
		for(int i=0;i<nrLinii;i++){
			for(int j=0;j<nrColoane;j++){
				if(this.masiv[i][j]!=other.masiv[i][j])
					return false;
			}
		}
		return this.nrLinii==other.nrLinii && this.nrColoane==other.nrColoane;
	}

	@Override
	public int compareTo(Matrice other) {
		int result=Integer.compare(this.nrColoane, other.nrColoane);
		if(result==0)
			result=Integer.compare(this.nrLinii, this.nrLinii);
		return Integer.compare(this.masiv.hashCode(),other.masiv.hashCode());
	}
	
	public void displayMatrice(){
		for(int i=0;i<nrLinii;i++){
			for(int j=0;j<nrColoane;j++){
				System.out.printf("%-15s",this.masiv[i][j]);
			}
			System.out.println();
		}
	}
	public void sortMatrice(){
		List<Pasager> lista=new ArrayList<>();
		for(int i=0;i<nrLinii;i++){
			for(int j=0;j<nrColoane;j++){
				if(this.masiv[i][j]!=null)
					lista.add((Pasager)this.masiv[i][j]);
			}
		}
		Collections.sort(lista,(a,b)->Integer.compare(a.getIdRezervare(), b.getIdRezervare()));
		int k=0;
		for(int i=0;i<nrLinii;i++){
			for(int j=0;j<nrColoane;j++){
				if(this.masiv[i][j]!=null){
					this.masiv[i][j]=lista.get(k);
					k++;
				}
			}	
		}
	}
	public void transpuneMatrice(){
		Vector<Object> vector=new Vector<>();
		for(int i=0;i<nrLinii;i++){
			for(int j=0;j<nrColoane;j++){
				vector.add(this.masiv[i][j]);
			}
		}
		for(Object o:vector)
			System.out.println(o);
	}
	public void citesteDinFisier(String numeFisier){
		BufferedReader in=null;
		try{
			in=new BufferedReader(new FileReader(new File(numeFisier)));
			String line=null;
			while((line=in.readLine())!=null){
				StringTokenizer st=new StringTokenizer(line,"#");
				String nume=st.nextToken();
				String numarLoc=st.nextToken();
				String varsta=st.nextToken();
				String idRezervare=st.nextToken();
				String nrCard=st.nextToken();
				if(numeFisier.equals("PasagerEc.txt")){
					PasagerEc pe=new PasagerEc(nume, Float.parseFloat(numarLoc), Float.parseFloat(varsta), Integer.parseInt(idRezervare),nrCard);
					int k=0;
					for(int i=0;i<nrLinii;i++){
						for(int j=0;j<nrColoane;j++){
							k++;
							if(k==pe.getNumarLoc())
								this.masiv[i][j]=pe;
						}
					}
				}
				else if(numeFisier.equals("PasagerVip.txt")){
					PasagerVip pv=new PasagerVip(nume, Float.parseFloat(numarLoc), Float.parseFloat(varsta), Integer.parseInt(idRezervare),nrCard);
					int k=0;
					for(int i=0;i<nrLinii;i++){
						for(int j=0;j<nrColoane;j++){
							k++;
							if(k==pv.getNumarLoc())
								this.masiv[i][j]=pv;
						}
					}
				}
			}
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
