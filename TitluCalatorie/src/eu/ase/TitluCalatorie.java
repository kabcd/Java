package eu.ase;

import java.io.Serializable;

public abstract class TitluCalatorie implements Serializable {
	private int id;
	private String denumire;
	private float idLinie;
	private final int idZona;

	public TitluCalatorie(int id, String denumire, float idLinie, int idZona) {
		this.id = id;
		this.denumire = denumire;
		this.idLinie = idLinie;
		this.idZona = idZona;
	}
	public int getId() {
		return id;
	}
	public String getDenumire() {
		return denumire;
	}
	public float getIdLinie() {
		return idLinie;
	}
	public int getIdZona() {
		return idZona;
	}
	public abstract String getZona();
	
}
