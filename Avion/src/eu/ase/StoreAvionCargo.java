package eu.ase;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="store")
public class StoreAvionCargo {
	@XmlElementWrapper(name="listaAvionCargo")
	@XmlElement(name="avionCargo")
	private ArrayList<AvionCargo> listaAC;
	public StoreAvionCargo(){
		
	}
	public StoreAvionCargo(ArrayList<AvionCargo> listaAC) {
		this.listaAC = listaAC;
	}
	public ArrayList<AvionCargo> getlistaAC() {
		return listaAC;
	}
	public void setListaAC(ArrayList<AvionCargo> listaAC) {
		this.listaAC = listaAC;
	}
	
}
