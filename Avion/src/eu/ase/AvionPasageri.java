package eu.ase;

import java.io.Serializable;
import java.util.Vector;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="avionPasageri")
public class AvionPasageri extends Avion implements Cloneable, Serializable {
	private float nrLocuri;
	private Vector<String> cnpPasageri;
	
	public AvionPasageri() {
		super("Anonim",0,"Anonim");
		this.nrLocuri=0;
		this.cnpPasageri=new Vector<String>();
	}
	
	public AvionPasageri(String serie, float tonaj, String marca,
			float nrLocuri, Vector<String> cnpPasageri) throws Exception{
		super(serie, tonaj, marca);
		if(tonaj<0)
			throw new Exception("Toanjul nu poate fi negativ!");
		this.nrLocuri = nrLocuri;
		this.cnpPasageri = cnpPasageri;
	}

	public float getNrLocuri() {
		return nrLocuri;
	}

	public void setNrLocuri(float nrLocuri) {
		this.nrLocuri = nrLocuri;
	}

	public Vector<String> getCnpPasageri() {
		return cnpPasageri;
	}

	public void setCnpPasageri(Vector<String> cnpPasageri) {
		this.cnpPasageri = cnpPasageri;
	}

	@Override
	public float getCapacitate() {
		return this.nrLocuri;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		AvionPasageri other=(AvionPasageri)super.clone();
		other.cnpPasageri=new Vector<String>();
		for(String s:this.cnpPasageri)
			other.cnpPasageri.add(s);
		return other;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof AvionPasageri))
			return false;
		AvionPasageri other=(AvionPasageri)obj;
		return this.nrLocuri==other.nrLocuri && this.cnpPasageri.equals(other.cnpPasageri);
	}

	@Override
	public String toString() {
		return "AvionPasageri [nrLocuri=" + nrLocuri + ", cnpPasageri="
				+ cnpPasageri + ", Serie=" + getSerie() + ", Tonaj="
				+ getTonaj() + ", Marca=" + getMarca() + "]";
	}
	
}
