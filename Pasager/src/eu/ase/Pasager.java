package eu.ase;

import java.io.Serializable;

public abstract class Pasager implements Serializable{
	private String nume;
	private float numarLoc;
	private float varsta;
	private final int idRezervare;
	public Pasager(String nume, float numarLoc, float varsta, int idRezervare) {
		this.nume = nume;
		this.numarLoc = numarLoc;
		this.varsta = varsta;
		this.idRezervare = idRezervare;
	}
	public String getNume() {
		return nume;
	}
	public float getNumarLoc() {
		return numarLoc;
	}
	public float getVarsta() {
		return varsta;
	}
	public int getIdRezervare() {
		return idRezervare;
	}

	public abstract String getRezervare();
}
