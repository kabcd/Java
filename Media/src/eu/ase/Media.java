package eu.ase;

import java.io.Serializable;

public abstract class Media implements Serializable{
	private String denumire;
	private float lungime;
	private final int semnaturaUnica;
	
	public Media(String denumire, float lungime, int semnaturaUnica) {
		this.denumire = denumire;
		this.lungime = lungime;
		this.semnaturaUnica = semnaturaUnica;
	}
	
	public String getDenumire() {
		return denumire;
	}

	public float getLungime() {
		return lungime;
	}

	public int getSemnaturaUnica() {
		return semnaturaUnica;
	}

	public abstract void returneazaPlayer();
	
}
