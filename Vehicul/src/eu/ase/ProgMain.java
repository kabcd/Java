package eu.ase;

import java.util.Vector;

public class ProgMain {
	public static void main(String[] args) {
		try{
			Vector<String> lista=new Vector<>();
			lista.add("serie01");lista.add("serie02");lista.add("serie03"); 
			VehiculCargo vc=new VehiculCargo("SVC001",55, "Boeing8",4500,lista);
			System.out.println(vc);

			lista=new Vector<>();
			lista.add("cnp01");lista.add("cnp02");
			VehiculPasageri vp=new VehiculPasageri("SVP001", 26, "Boeing175", 80, lista);
			System.out.println(vp);
			
			OperatiiInOutFisier of=new OperatiiInOutFisier();
			of.citesteObiectDinFisierText("VehiculCargo01.txt");
			of.citesteObiectDinFisierText("VehiculPasageri01.txt");
			of.scrieObiectInFisierText("Vehicule.txt");
			
			//Client c=new Client();
			//c.transmite(vp);
			
			afisareTabel("VehiculPasageri01.txt");
			afisareTabel("VehiculCargo01.txt");
			
			transmiteTcp("VehiculPasageri01.txt");
			transmiteTcp("VehiculCargo01.txt");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void afisareTabel(String numeFisier){
		OperatiiInOutFisier of=new OperatiiInOutFisier();
		of.citesteObiectDinFisierText(numeFisier);
		
		String [] columns={"Serie","Tonaj","Marca","NrLocuri|CapacitateTransportKg","CnpPasageri|SerieMarfuri"};
		int nrLinii=of.getListaVehicule().size()+1;
		int nrColoane=columns.length;
		
		Object [][]tabel=new Object[nrLinii][nrColoane];
		for(int i=0;i<nrColoane;i++)
			tabel[0][i]=columns[i];
		
		int k=1;
		while(k<nrLinii){
			for(Object o:of.getListaVehicule()){
				if(o instanceof VehiculCargo){
					VehiculCargo vc=(VehiculCargo)o;
					tabel[k][0]=vc.getSerie();
					tabel[k][1]=vc.getTonaj();
					tabel[k][2]=vc.getMarca();
					tabel[k][3]=vc.getCapacitateTransportKg();
					tabel[k][4]=vc.getSerieMarfuri();
					k++;
				}
				else if(o instanceof VehiculPasageri){
					VehiculPasageri vp=(VehiculPasageri)o;
					tabel[k][0]=vp.getSerie();
					tabel[k][1]=vp.getTonaj();
					tabel[k][2]=vp.getMarca();
					tabel[k][3]=vp.getNrLocuri();
					tabel[k][4]=vp.getCnpPasageri();
					k++;
				}
			}
		}
		
		for(int i=0;i<nrLinii;i++){
			System.out.printf("%-10s",tabel[i][0]);
			System.out.printf("%-10s",tabel[i][1]);
			System.out.printf("%-15s",tabel[i][2]);
			System.out.printf("%-35s",tabel[i][3]);
			System.out.printf("%-30s",tabel[i][4]);
			System.out.println();
		}
	}
	public static void transmiteTcp(String numeFisier){
		OperatiiInOutFisier of=new OperatiiInOutFisier();
		of.citesteObiectDinFisierText(numeFisier);
		
		Client c=new Client();
		for(Object o:of.getListaVehicule()){
			try{
				c.transmite(o);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
}
