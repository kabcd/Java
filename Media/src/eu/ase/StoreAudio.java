package eu.ase;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="store")
public class StoreAudio {
	@XmlElementWrapper(name="listaAudio")
	@XmlElement(name="audio")
	private ArrayList<Audio> listaAudio;

	public StoreAudio() {
	}
	public StoreAudio(ArrayList<Audio> listaAudio) {
		this.listaAudio = listaAudio;
	}
	public ArrayList<Audio> getlistaAudio() {
		return listaAudio;
	}

	public void setListaAudio(ArrayList<Audio> listaAudio) {
		this.listaAudio = listaAudio;
	}
	
}
