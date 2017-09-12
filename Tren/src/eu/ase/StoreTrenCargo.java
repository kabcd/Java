package eu.ase;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="store")
public class StoreTrenCargo {
	
	@XmlElementWrapper(name="listaTrenCargo")
	@XmlElement(name="trenCargo")
	private ArrayList<TrenCargo> listaTC;
	
	public StoreTrenCargo() {
		
	}

	public StoreTrenCargo(ArrayList<TrenCargo> listaTC) {
		this.listaTC = listaTC;
	}

	public ArrayList<TrenCargo> getlistaTC() {
		return listaTC;
	}

	public void setListaTC(ArrayList<TrenCargo> listaTC) {
		this.listaTC = listaTC;
	}
	
}
