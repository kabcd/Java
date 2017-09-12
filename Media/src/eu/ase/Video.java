package eu.ase;

import java.io.Serializable;

public class Video extends Media implements Cloneable, Serializable{
	private int rezolutie;
	
	public Video(String denumire, float lungime, int semnaturaUnica,
			int rezolutie) throws Exception {
		super(denumire, lungime, semnaturaUnica);
		if(denumire==null)
			throw new Exception("Valoare denumire nu poate fi nula!");
		this.rezolutie = rezolutie;
	}

	public int getRezolutie() {
		return rezolutie;
	}

	@Override
	public void returneazaPlayer() {
		System.out.println("Player video -> Fisier: "+this.getDenumire()+" | Lungime: "+this.getLungime()+" sec | Rezolutie: "+this.getRezolutie()+" p.");
		
	}

	@Override
	protected Object clone(){
		Video other=null;
		try{
			other=new Video(this.getDenumire(), this.getLungime(), this.getSemnaturaUnica(), this.getRezolutie());
		}catch(Exception e){
			e.printStackTrace();
		}
		return other;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Video))
			return false;
		Video other=(Video)obj;
		return this.getRezolutie()==other.getRezolutie();
	}
	
}
