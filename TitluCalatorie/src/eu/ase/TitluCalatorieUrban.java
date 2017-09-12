package eu.ase;

import java.io.Serializable;

public class TitluCalatorieUrban  extends TitluCalatorie implements Comparable<TitluCalatorieUrban>, Serializable{
	private String denumireOperatorUrban;
	
	public TitluCalatorieUrban(int id, String denumire, float idLinie,
			int idZona, String denumireOperatorUrban) throws Exception {
		super(id, denumire, idLinie, idZona);
		if(idLinie<0)
			throw new Exception("Id linie nu poate fi negativ!");
		this.denumireOperatorUrban = denumireOperatorUrban;
	}

	public String getDenumireOperatorUrban() {
		return denumireOperatorUrban;
	}

	@Override
	public String getZona() {
		return this.getId()+" "+this.getDenumireOperatorUrban();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		TitluCalatorieUrban other=(TitluCalatorieUrban)super.clone();
		return other;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof TitluCalatorieUrban))
			return false;
		TitluCalatorieUrban other=(TitluCalatorieUrban)obj;
		return this.getDenumireOperatorUrban().equalsIgnoreCase(other.getDenumireOperatorUrban());
	}

	@Override
	public int compareTo(TitluCalatorieUrban other) {
		return Integer.compare(this.getId(),other.getId());
	}
	
	@Override
	public String toString() {
		return "Id: "+ this.getId()+" Denumire: "+this.getDenumire();
	}
	
	

}
