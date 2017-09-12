package eu.ase;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace="eu.ase")
public class ParcAuto {
	@XmlElementWrapper(name="listaMasini")
	@XmlElement(name="masina")
	
	private List<Car> listaMasini;
	private String locatie;
	private String denumire;
	public ParcAuto(){
		
	}

	public ParcAuto(List<Car> listaMasini, String locatie, String denumire) {
		this.listaMasini = listaMasini;
		this.locatie = locatie;
		this.denumire = denumire;
	}


	public void setListaMasini(List<Car> listaMasini) {
		this.listaMasini = listaMasini;
	}
	public List<Car> getlistaMasini() {
		return listaMasini;
	}

	public String getLocatie() {
		return locatie;
	}

	public void setLocatie(String locatie) {
		this.locatie = locatie;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}
}
