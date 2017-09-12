package eu.ase;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Vector;

public class StreamVideo extends Video implements Streamable, Serializable, Runnable {
	private Vector<Integer> stream;
	
	public StreamVideo(String denumire, float lungime, int semnaturaUnica,
			int rezolutie, Vector<Integer> stream) throws Exception {
		super(denumire, lungime, semnaturaUnica, rezolutie);
		this.stream = stream;
	}

	public void setStream(Vector<Integer> stream) {
		this.stream = stream;
	}

	@Override
	public void citesteStreamFisier(String numeFisier) {
		FileInputStream in=null;
		try{
			in=new FileInputStream(numeFisier);
			int c;
			while((c=in.read())!=-1)
				this.stream.add(c);
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void returneazaPlayer() {
		super.returneazaPlayer();
		for(Integer i:this.stream){
			try{
				Thread.sleep(750);
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println(i+"V~");
		}
	}

	@Override
	public String toString() {
		return "StreamVideo [stream=" + stream + ",\n Rezolutie="
				+ getRezolutie() + ", Denumire=" + getDenumire()
				+ ", Lungime=" + getLungime() + ", SemnaturaUnica="
				+ getSemnaturaUnica() + "]";
	}
	
	@Override
	public void run(){
		returneazaPlayer();
	}
}
