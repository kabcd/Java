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
		
	public OperatiiInOutFisier(){
		this.numeFisier="Vehicule.txt";
		this.listaVehicule=new ArrayList<>();
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
				String aux=st.nextToken();
				st=new StringTokenizer(aux,",");
				Vector<String> lista=new Vector<>();
				while(st.hasMoreTokens()){
					lista.add(st.nextToken());
				}
				if(numeFisier.equals("VehiculCargo01.txt")){
					VehiculCargo vc=new VehiculCargo(serie, Float.parseFloat(tonaj), marca, Float.parseFloat(capacitate), lista);
					this.listaVehicule.add(vc);
				}
				else if(numeFisier.equals("VehiculPasageri01.txt")){
					VehiculPasageri vp=new VehiculPasageri(serie, Float.parseFloat(tonaj), marca, Float.parseFloat(capacitate), lista);
					this.listaVehicule.add(vp);
				}
			}
			in.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void scrieObiectInFisierText(String numeFisier) {
		PrintWriter out=null;
		try{
			out=new PrintWriter(new FileWriter(new File(numeFisier)));
			for(Object o:this.listaVehicule){
				if(o instanceof VehiculCargo){
					VehiculCargo vc=(VehiculCargo)o;
					out.print(vc.getSerie()+"#");
					out.print(vc.getTonaj()+"#");
					out.print(vc.getMarca()+"#");
					out.print(vc.getCapacitateTransportKg()+"#");
					for(int i=0;i<vc.getSerieMarfuri().size()-1;i++)
						out.print(vc.getSerieMarfuri().get(i)+",");
					out.print(vc.getSerieMarfuri().get(vc.getSerieMarfuri().size()-1));
					out.println();
				}
				else if(o instanceof VehiculPasageri){
					VehiculPasageri vp=(VehiculPasageri)o;
					out.print(vp.getSerie()+"#");
					out.print(vp.getTonaj()+"#");
					out.print(vp.getMarca()+"#");
					out.print(vp.getNrLocuri()+"#");
					for(int i=0;i<vp.getCnpPasageri().size()-1;i++)
						out.print(vp.getCnpPasageri().get(i)+",");
					out.print(vp.getCnpPasageri().get(vp.getCnpPasageri().size()-1));
					out.println();
				}
			}
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		OperatiiInOutFisier other=(OperatiiInOutFisier)super.clone();
		other.numeFisier=this.numeFisier;
		other.listaVehicule = new ArrayList<Object>();
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
	public int compareTo(OperatiiInOutFisier o) {
		File f1=new File(this.numeFisier);
		File f2=new File(o.numeFisier);
		return Long.compare(f1.length(), f2.length());
		
	}
}
