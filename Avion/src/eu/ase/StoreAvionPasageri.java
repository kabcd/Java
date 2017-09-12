package eu.ase;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="store")
public class StoreAvionPasageri {
	@XmlElementWrapper(name="listaAvionPasageri")
	@XmlElement(name="avionPasageri")
		private ArrayList<AvionPasageri> listaAP;
		public StoreAvionPasageri(){
			
		}
		public StoreAvionPasageri(ArrayList<AvionPasageri> listaAP) {
			this.listaAP = listaAP;
		}
		public ArrayList<AvionPasageri> getlistaAP() {
			return listaAP;
		}
		public void setListaAC(ArrayList<AvionPasageri> listaAP) {
			this.listaAP = listaAP;
		}
		
}
