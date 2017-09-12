package eu.ase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public class OperatiiInOutFisier implements OperatiiFisier, Cloneable, Comparable<OperatiiInOutFisier> {
	
	private String numeFisier;
	private List<Object> listaVehicule;
	
	public OperatiiInOutFisier() {
		this.numeFisier="Anonim";
		this.listaVehicule=new ArrayList<Object>();
	}
	
	public OperatiiInOutFisier(String numeFisier, List<Object> listaVehicule) {
		this.numeFisier = numeFisier;
		this.listaVehicule = listaVehicule;
	}
	
	public String getNumeFisier() {
		return numeFisier;
	}

	public void setNumeFisier(String numeFisier) {
		this.numeFisier = numeFisier;
	}

	public List<Object> getListaVehicule() {
		return listaVehicule;
	}

	public void setListaVehicule(List<Object> listaVehicule) {
		this.listaVehicule = listaVehicule;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		OperatiiInOutFisier other=(OperatiiInOutFisier)super.clone();
		other.numeFisier=this.numeFisier;
		other.listaVehicule=new ArrayList<Object>();
		for(Object o:this.listaVehicule)
			other.listaVehicule.add(o);
		return other;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof OperatiiInOutFisier))
			return false;
		OperatiiInOutFisier other=(OperatiiInOutFisier)obj;
		return this.numeFisier.equals(other.numeFisier) && this.listaVehicule.equals(other.listaVehicule);
	}

	@Override
	public void citesteObiectDinFisierText(String numeFisier) {
		BufferedReader in=null;
		try{
			in=new BufferedReader(new FileReader(new File(numeFisier)));
			String line=null;
			while((line=in.readLine())!=null){
				StringTokenizer st=new StringTokenizer(line,"#");
				String serie=st.nextToken();
				String tonaj=st.nextToken();
				String marca=st.nextToken();
				String capacitate=st.nextToken();
				Vector<String> lista=new Vector<String>();
				st=new StringTokenizer(st.nextToken(),",");
				while(st.hasMoreTokens()){
					lista.add(st.nextToken());
				}
				if(numeFisier.equals("AvionPasageri01.txt")){
					AvionPasageri ap=new AvionPasageri(serie, Float.parseFloat(tonaj), marca, Float.parseFloat(capacitate), lista);
					this.listaVehicule.add(ap);
				}
				else if(numeFisier.equals("AvionCargo01.txt")){
					AvionCargo ac=new AvionCargo(serie, Float.parseFloat(tonaj), marca, Float.parseFloat(capacitate), lista);
					this.listaVehicule.add(ac);
				}
			}
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void scrieObiectInFisierText(String file) {
		PrintWriter out=null;
		try{
			out=new PrintWriter(new FileWriter(new File(file)));
			for(Object o:this.listaVehicule){
				if(o instanceof AvionCargo){
					AvionCargo ac=(AvionCargo)o;
					out.print(ac.getSerie()+"#");
					out.print(ac.getTonaj()+"#");
					out.print(ac.getMarca()+"#");
					out.print(ac.getCapacitate()+"#");
					for(int i=0;i<ac.getSerieMarfuri().size()-1;i++)
						out.print(ac.getSerieMarfuri().get(i)+",");
					out.print(ac.getSerieMarfuri().get(ac.getSerieMarfuri().size()-1));
					out.println();
				}
				else if(o instanceof AvionPasageri){
					AvionPasageri ap=(AvionPasageri)o;
					out.print(ap.getSerie()+"#");
					out.print(ap.getTonaj()+"#");
					out.print(ap.getMarca()+"#");
					out.print(ap.getCapacitate()+"#");
					for(int i=0;i<ap.getCnpPasageri().size()-1;i++)
						out.print(ap.getCnpPasageri().get(i)+",");
					out.print(ap.getCnpPasageri().get(ap.getCnpPasageri().size()-1));
					out.println();
				}
			}
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public int compareTo(OperatiiInOutFisier o) {
		File f1=new File(this.numeFisier);
		File f2=new File(o.numeFisier);
		return Long.compare(f1.length(), f2.length());
	}
	
}
