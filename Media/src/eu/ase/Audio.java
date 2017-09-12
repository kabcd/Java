package eu.ase;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlRootElement(name="audio")
public class Audio extends Media implements Cloneable, Serializable{
	private int calitate;
	
	public Audio() throws Exception{
		super("Anonim",0,0);
		this.calitate=0;
	}

	public Audio(String denumire, float lungime, int semnaturaUnica,
			int calitate) throws Exception {
		super(denumire, lungime, semnaturaUnica);
		if(denumire==null)
			throw new Exception("Valoare denumire nu poate fi nula!");
		this.calitate = calitate;
	}
	
	public int getCalitate() {
		return calitate;
	}
	
	public void setCalitate(int calitate) {
		this.calitate = calitate;
	}

	@Override
	public void returneazaPlayer() {
		System.out.println("Player audio -> Fisier: "+this.getDenumire()+" | Lungime: "+this.getLungime()+" sec | Calitate: "+this.getCalitate()+" MHz.");
		
	}

	@Override
	protected Object clone(){
		Audio other=null;
		try{
			other=new Audio(this.getDenumire(), this.getLungime(), this.getSemnaturaUnica(), this.getCalitate());
		}catch(Exception e){
			e.printStackTrace();
		}
		return other;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Audio))
			return false;
		Audio other=(Audio)obj;
		return this.getCalitate()==other.getCalitate();
	}
	
}
