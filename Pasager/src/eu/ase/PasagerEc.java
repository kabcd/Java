package eu.ase;

import java.io.Serializable;

public class PasagerEc extends Pasager implements Cloneable, Comparable<PasagerEc>, Serializable{
	private String nrCardEc;
	
	public PasagerEc(String nume, float numarLoc, float varsta,
			int idRezervare, String nrCardEc) throws Exception{
		super(nume, numarLoc, varsta, idRezervare);
		if(numarLoc<0)
			throw new Exception("Numar loc nu poate fi negativ");
		this.nrCardEc = nrCardEc;
	}

	public String getNrCardEc() {
		return nrCardEc;
	}

	public void setNrCardEc(String nrCardEc) {
		this.nrCardEc = nrCardEc;
	}
	
	@Override
	public String getRezervare() {
		return this.getIdRezervare()+" "+this.getNrCardEc();
	}

	

	@Override
	public String toString() {
		return "NumarLoc=" + getNumarLoc() ;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		PasagerEc other=(PasagerEc)super.clone();
		other.nrCardEc=this.nrCardEc;
		return other;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof PasagerEc))
			return false;
		PasagerEc other=(PasagerEc)obj;
		return this.nrCardEc.equals(other.nrCardEc);
	}

	@Override
	public int compareTo(PasagerEc other) {
		return Integer.compare(this.getIdRezervare(), other.getIdRezervare());
	}
	
	
	
}
