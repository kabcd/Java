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

public class OperatiiInOutFisier implements Cloneable, OperatiiFisier, Comparable<OperatiiInOutFisier> {
	private String numeFisier;
	private List<Object> listaTrenuri;

	public OperatiiInOutFisier() {
		this.numeFisier="Trenuri.txt";
		this.listaTrenuri=new ArrayList<>();
	}

	public OperatiiInOutFisier(String numeFisier, List<Object> listaTrenuri) {
		this.numeFisier = numeFisier;
		this.listaTrenuri=listaTrenuri;
	}

	public String getNumeFisier() {
		return numeFisier;
	}

	public void setNumeFisier(String numeFisier) {
		this.numeFisier = numeFisier;
	}

	public List<Object> getListaTrenuri() {
		return listaTrenuri;
	}

	public void setListaTrenuri(List<Object> listaTrenuri) {
		this.listaTrenuri = listaTrenuri;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		OperatiiInOutFisier other=(OperatiiInOutFisier)super.clone();
		other.listaTrenuri=new ArrayList<>();
		for(Object o:this.listaTrenuri)
			other.listaTrenuri.add(o);
		return other;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof OperatiiInOutFisier))
			return false;
		OperatiiInOutFisier other=(OperatiiInOutFisier)obj;
		return this.listaTrenuri.equals(other.listaTrenuri) && this.numeFisier.equals(other.numeFisier);
	}

	@Override
	public int compareTo(OperatiiInOutFisier other) {
		File f1=new File(this.numeFisier);
		File f2=new File(other.numeFisier);
		return Long.compare(f1.length(),f2.length());
	}

	@Override
	public void scrieObiectInFisierText(String numeFisier) {
		PrintWriter out=null;
		try{
			out=new PrintWriter(new FileWriter(new File(numeFisier)));
			for(Object o:this.listaTrenuri){
				if(o instanceof TrenCargo){
					TrenCargo tc=(TrenCargo)o;
					out.print(tc.getSerie()+"#");
					out.print(tc.getTonaj()+"#");
					out.print(tc.getMarca()+"#");
					out.print(tc.getCapacitateTransportKg()+"#");
					for(int i=0;i<tc.getSerieMarfuri().size()-1;i++)
						out.print(tc.getSerieMarfuri().get(i)+", ");
					out.print(tc.getSerieMarfuri().get(tc.getSerieMarfuri().size()-1));
					out.println();
				}
				else if(o instanceof TrenPasageri){
					TrenPasageri tp=(TrenPasageri)o;
					out.print(tp.getSerie()+"#");
					out.print(tp.getTonaj()+"#");
					out.print(tp.getMarca()+"#");
					out.print(tp.getNrLocuri()+"#");
					for(int i=0;i<tp.getCnpPasageri().size()-1;i++)
						out.print(tp.getCnpPasageri().get(i)+", ");
					out.print(tp.getCnpPasageri().get(tp.getCnpPasageri().size()-1));
					out.println();
				}
			}
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
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
				Vector<String> serieMarfuriSAUcnpPasageri=new Vector<>();
				StringTokenizer str=new StringTokenizer(st.nextToken(),",");
				while(str.hasMoreTokens()){
					serieMarfuriSAUcnpPasageri.add(str.nextToken());
				}
				if(numeFisier.equals("TrenCargo01.txt")){
					TrenCargo tc=new TrenCargo(serie, Float.parseFloat(tonaj), marca, Float.parseFloat(capacitate), serieMarfuriSAUcnpPasageri);
					this.listaTrenuri.add(tc);
				}
				else if(numeFisier.equals("TrenPasageri01.txt")){
					TrenPasageri tp=new TrenPasageri(serie, Float.parseFloat(tonaj), marca, Float.parseFloat(capacitate), serieMarfuriSAUcnpPasageri);
					this.listaTrenuri.add(tp);
				}
				
			}
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
