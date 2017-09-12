package eu.ase;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="store")
public class StoreVehiculCargo {
	@XmlElementWrapper(name="listaVehiculCargo")
	@XmlElement(name="vehiculCargo")
	public ArrayList<VehiculCargo> listaVC;
	public StoreVehiculCargo(){
		
	}
	public StoreVehiculCargo(ArrayList<VehiculCargo> listaVC) {
		this.listaVC = listaVC;
	}
	
}
