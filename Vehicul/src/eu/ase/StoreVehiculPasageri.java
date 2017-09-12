package eu.ase;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="store")
public class StoreVehiculPasageri {
	@XmlElementWrapper(name="listaVehiculPasageri")
	@XmlElement(name="vehiculPasageri")
	public ArrayList<VehiculPasageri> listaVP;

	public StoreVehiculPasageri(){
		
	}
	public StoreVehiculPasageri(ArrayList<VehiculPasageri> listaVP) {
		this.listaVP = listaVP;
	}
	
}
