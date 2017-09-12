package eu.ase;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Vector;

public class StreamAudio extends Audio implements Streamable, Serializable, Runnable{
	private Vector<Integer> stream;
	
	public StreamAudio(String denumire, float lungime, int semnaturaUnica,
			int calitate, Vector<Integer> stream) throws Exception {
		super(denumire, lungime, semnaturaUnica, calitate);
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
				Thread.sleep(500);
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println(i+"A~");
		}
	}

	@Override
	public void run(){
		returneazaPlayer();
	}

	@Override
	public String toString() {
		return "StreamAudio [stream=" + stream + ",\n Calitate="
				+ getCalitate() + ", Denumire=" + getDenumire()
				+ ", Lungime=" + getLungime() + ", SemnaturaUnica="
				+ getSemnaturaUnica() + "]";
	}
	

}
