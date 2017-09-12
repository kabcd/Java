package eu.ase;

import java.io.Serializable;

public class PasagerVip extends Pasager implements Cloneable, Comparable<PasagerVip>, Serializable {
	private String nrCardVip;

	public PasagerVip(String nume, float numarLoc, float varsta,
			int idRezervare, String nrCardVip) throws Exception{
		super(nume, numarLoc, varsta, idRezervare);
		if(numarLoc<0)
			throw new Exception("Numar loc nu poate fi negativ");
		this.nrCardVip = nrCardVip;
	}

	public String getNrCardVip() {
		return nrCardVip;
	}

	public void setNrCardVip(String nrCardVip) {
		this.nrCardVip = nrCardVip;
	}

	@Override
	public String getRezervare() {
		return this.getIdRezervare()+" "+this.getNrCardVip();
	}

	

	@Override
	public String toString() {
		return "NumarLoc=" + getNumarLoc() ;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		PasagerVip other=(PasagerVip)super.clone();
		other.nrCardVip=this.nrCardVip;
		return other;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof PasagerVip))
			return false;
		PasagerVip other=(PasagerVip)obj;
		return this.nrCardVip.equals(other.nrCardVip);
	}
	
	@Override
	public int compareTo(PasagerVip other) {
		return Integer.compare(this.getIdRezervare(), other.getIdRezervare());
	}
	
}
