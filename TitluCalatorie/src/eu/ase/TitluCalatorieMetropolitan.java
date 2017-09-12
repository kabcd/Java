package eu.ase;

import java.io.Serializable;

public class TitluCalatorieMetropolitan extends TitluCalatorie implements Comparable<TitluCalatorieMetropolitan>,Serializable {
	private String denumireOperatorMetropolitan;
	
	public TitluCalatorieMetropolitan(int id, String denumire, float idLinie,
			int idZona, String denumireOperatorMetropolitan) throws Exception{
		super(id, denumire, idLinie, idZona);
		if(idLinie<0)
			throw new Exception("Id linie nu poate fi negativ!");
		this.denumireOperatorMetropolitan = denumireOperatorMetropolitan;
	}

	public String getDenumireOperatorMetropolitan() {
		return denumireOperatorMetropolitan;
	}

	@Override
	public String getZona() {
		return this.getIdLinie()+" "+this.getDenumireOperatorMetropolitan();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		TitluCalatorieMetropolitan other=(TitluCalatorieMetropolitan)super.clone();
		return other;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof TitluCalatorieMetropolitan))
			return false;
		TitluCalatorieMetropolitan other=(TitluCalatorieMetropolitan)obj;
		return this.getDenumireOperatorMetropolitan().equalsIgnoreCase(other.getDenumireOperatorMetropolitan());
	}

	@Override
	public int compareTo(TitluCalatorieMetropolitan other) {
		return Integer.compare(this.getId(),other.getId());
	}

	@Override
	public String toString() {
		return "Id: "+ this.getId()+" Denumire: "+this.getDenumire();
	}
}
