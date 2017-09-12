package eu.ase;

import java.util.Vector;

public class ProgMain {
	public static void main(String[] args) {
		try{
			Vector<String> serieMarfuri=new Vector<>();
			serieMarfuri.add("serie01");serieMarfuri.add("serie02");serieMarfuri.add("serie03");
			AvionCargo ac=new AvionCargo("S0001", 50, "AvionC01", 5000, serieMarfuri);
			
			System.out.println(ac);
			
			Vector<String> cnpPasageri=new Vector<>();
			cnpPasageri.add("cnp01");cnpPasageri.add("cnp02");
			AvionPasageri ap=new AvionPasageri("S001", 46, "AvionP01", 100, cnpPasageri);
			
			System.out.println(ap);
			
			OperatiiInOutFisier of=new OperatiiInOutFisier();
			of.citesteObiectDinFisierText("AvionPasageri01.txt");
			of.scrieObiectInFisierText("AvionPasageri.txt");
			
			OperatiiInOutFisier of2=new OperatiiInOutFisier();
			of2.citesteObiectDinFisierText("AvionCargo01.txt");
			of2.scrieObiectInFisierText("AvionCargo.txt");
			
			afisareTabel("AvionCargo01.txt");
			afisareTabel("AvionPasageri01.txt");
			
			transmiteTcp("AvionCargo01.txt");
			transmiteTcp("AvionPasageri01.txt");
			
			Client c=new Client();
			c.transmite(ac);
			c.transmite(ap);
		}catch(Exception e){
			e.printStackTrace();
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

	public static void afisareTabel(String numeFisier){
		OperatiiInOutFisier of=new OperatiiInOutFisier();
		of.citesteObiectDinFisierText(numeFisier);
		String [] columns={"Serie","Tonaj","Marca","CapacitateTransportKg|NrLocuri","SerieMarfuri|CnpPasageri"};
		int nrLinii=of.getListaVehicule().size()+1;
		int nrColoane=columns.length;
		
		Object [][]tabel=new Object[nrLinii][nrColoane];
		for(int j=0;j<nrColoane;j++)
			tabel[0][j]=columns[j];
		
		int i=1;
		while(i<nrLinii){
			for(Object o:of.getListaVehicule()){
				if(o instanceof AvionCargo){
					AvionCargo ac=(AvionCargo)o;
					tabel[i][0]=ac.getSerie();
					tabel[i][1]=ac.getTonaj();
					tabel[i][2]=ac.getMarca();
					tabel[i][3]=ac.getCapacitate();
					tabel[i][4]=ac.getSerieMarfuri();
					i++;
				}
				else if(o instanceof AvionPasageri){
					AvionPasageri ap=(AvionPasageri)o;
					tabel[i][0]=ap.getSerie();
					tabel[i][1]=ap.getTonaj();
					tabel[i][2]=ap.getMarca();
					tabel[i][3]=ap.getCapacitate();
					tabel[i][4]=ap.getCnpPasageri();
					i++;
				}
			}
		}
		for(int k=0;k<nrLinii;k++){
			System.out.printf("%-10s", tabel[k][0]);
			System.out.printf("%-10s", tabel[k][1]);
			System.out.printf("%-10s", tabel[k][2]);
			System.out.printf("%-35s", tabel[k][3]);
			System.out.printf("%-30s", tabel[k][4]);
			System.out.println();
		}
	}
}
