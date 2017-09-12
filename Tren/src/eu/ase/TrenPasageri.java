package eu.ase;

import java.io.Serializable;
import java.util.Vector;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name="trenPasageri")
public class TrenPasageri extends Tren implements Cloneable, Serializable{
	private float nrLocuri;
	private Vector<String> cnpPasageri;
	
	public TrenPasageri(){
		super("Anonim",0,"Anonim");
		this.nrLocuri=0;
		this.cnpPasageri=new Vector<>();
	}
	public TrenPasageri(String serie, float tonaj, String marca,
			float nrLocuri, Vector<String> cnpPasageri) throws Exception{
		super(serie, tonaj, marca);
		if(tonaj<0)
			throw new Exception("Tonajul nu poate fi negativ!");
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
		TrenPasageri other=(TrenPasageri)super.clone();
		other.cnpPasageri=new Vector<>();
		for(String str:this.cnpPasageri)
			other.cnpPasageri.add(str);
		return other;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof TrenPasageri))
			return false;
		TrenPasageri other=(TrenPasageri)obj;
		return this.nrLocuri==other.nrLocuri && this.cnpPasageri.equals(other.cnpPasageri);
	}
	@Override
	public String toString() {
		return "TrenPasageri [nrLocuri=" + nrLocuri + ", cnpPasageri="
				+ cnpPasageri + ", Serie=" + getSerie() + ", Tonaj="
				+ getTonaj() + ", Marca=" + getMarca() + "]";
	}

	
}
