package eu.ase;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="store")
public class StoreTrenPasageri {
	
	@XmlElementWrapper(name="listaTrenPasageri")
	@XmlElement(name="trenPasageri")
	private ArrayList<TrenPasageri> listaTP;
	
	public StoreTrenPasageri() {
		
	}

	public StoreTrenPasageri(ArrayList<TrenPasageri> listaTP) {
		this.listaTP = listaTP;
	}

	public ArrayList<TrenPasageri> getlistaTP() {
		return listaTP;
	}

	public void setListaTP(ArrayList<TrenPasageri> listaTP) {
		this.listaTP = listaTP;
	}
	
}
