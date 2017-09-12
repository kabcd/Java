package eu.ase;

import java.io.Serializable;
import java.util.Vector;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name="trenCargo")
public class TrenCargo extends Tren implements Cloneable, Serializable{
	private float capacitateTransportKg;
	private Vector<String> serieMarfuri;
	
	public TrenCargo(){
		super("Anonim",0,"Anonim");
		this.capacitateTransportKg=0;
		this.serieMarfuri=new Vector<>();
	}
	public TrenCargo(String serie, float tonaj, String marca,
			float capacitateTransportKg, Vector<String> serieMarfuri) throws Exception {
		super(serie, tonaj, marca);
		if(tonaj<0)
			throw new Exception("Tonajul nu poate fi negativ!");
		this.capacitateTransportKg = capacitateTransportKg;
		this.serieMarfuri = serieMarfuri;
	}

	public float getCapacitateTransportKg() {
		return capacitateTransportKg;
	}
	public void setCapacitateTransportKg(float capacitateTransportKg) {
		this.capacitateTransportKg = capacitateTransportKg;
	}
	public Vector<String> getSerieMarfuri() {
		return serieMarfuri;
	}
	public void setSerieMarfuri(Vector<String> serieMarfuri) {
		this.serieMarfuri = serieMarfuri;
	}
	@Override
	public float getCapacitate() {
		return this.capacitateTransportKg;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		TrenCargo other=(TrenCargo)super.clone();
		other.serieMarfuri=new Vector<>();
		for(String str:this.serieMarfuri)
			other.serieMarfuri.add(str);
		return other;
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof TrenCargo))
			return false;
		TrenCargo other=(TrenCargo)obj;
		return this.capacitateTransportKg==other.capacitateTransportKg && this.serieMarfuri.equals(other.serieMarfuri);
	}
	@Override
	public String toString() {
		return "TrenCargo [capacitateTransportKg=" + capacitateTransportKg
				+ ", serieMarfuri=" + serieMarfuri + ", Serie="
				+ getSerie() + ", Tonaj=" + getTonaj() + ", Marca="
				+ getMarca() + "]";
	}

	
}
