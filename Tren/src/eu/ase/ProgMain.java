package eu.ase;

import java.util.Vector;

public class ProgMain {
	public static void main(String[] args) {
		try{
			Vector<String> serieMarfuri=new Vector<>();
			serieMarfuri.add("serie01");serieMarfuri.add("serie02");serieMarfuri.add("serie03");
			TrenCargo tc=new TrenCargo("SC01", 150,"TrenC01",5500, serieMarfuri);
			System.out.println(tc);
			
			Vector<String> cnpPasageri=new Vector<>();
			cnpPasageri.add("cnp01");cnpPasageri.add("cnp02");
			TrenPasageri tp=new TrenPasageri("SP01", 56, "TrenP01", 100, cnpPasageri);
			System.out.println(tp);
			
			OperatiiInOutFisier of=new OperatiiInOutFisier();
			of.citesteObiectDinFisierText("TrenCargo01.txt");
			of.scrieObiectInFisierText("TrenCargo.txt");
			
			OperatiiInOutFisier of2=new OperatiiInOutFisier();
			of2.citesteObiectDinFisierText("TrenPasageri01.txt");
			of2.scrieObiectInFisierText("TrenPasageri.txt");
			
			afisareTabel("TrenCargo01.txt");
			afisareTabel("TrenPasageri01.txt");
			
			Client c=new Client();
			c.transmite(tc);
			c.transmite(tp);
			
			transmiteTcp("TrenCargo01.txt");
			transmiteTcp("TrenPasageri01.txt");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void afisareTabel(String numeFisier){
		OperatiiInOutFisier of=new OperatiiInOutFisier();
		of.citesteObiectDinFisierText(numeFisier);
		String[] columns ={"Serie","Tonaj","Marca","NrLocuri|CapacitateTransportKg","CnpPasageri|SerieMarfuri"};
		int nrLinii=of.getListaTrenuri().size()+1;
		int nrColoane=columns.length;
		
		Object[][] tabel=new Object[nrLinii][nrColoane];
		
		for(int j=0;j<nrColoane;j++)
				tabel[0][j]=columns[j];
		
		int k=1;
		while(k<nrLinii){
			for(Object o : of.getListaTrenuri())
			{
				if(o instanceof TrenCargo)
				{
					TrenCargo tc = (TrenCargo)o;
					tabel[k][0]=tc.getSerie();
					tabel[k][1]=tc.getTonaj();
					tabel[k][2]=tc.getMarca();
					tabel[k][3]=tc.getCapacitate();
					tabel[k][4]=tc.getSerieMarfuri();
					k++;
				}
				if(o instanceof TrenPasageri)
				{
					TrenPasageri tp = (TrenPasageri)o;
					tabel[k][0]=tp.getSerie();
					tabel[k][1]=tp.getTonaj();
					tabel[k][2]=tp.getMarca();
					tabel[k][3]=tp.getCapacitate();
					tabel[k][4]=tp.getCnpPasageri();
					k++;
				}
			}
		}
		for(int i=0;i<nrLinii;i++){	
			System.out.printf("%-10s",tabel[i][0]);
			System.out.printf("%-10s",tabel[i][1]);
			System.out.printf("%-15s",tabel[i][2]);
			System.out.printf("%-35s",tabel[i][3]);
			System.out.printf("%s", tabel[i][4]);
			System.out.println();
		}
	}
	
	public static void transmiteTcp(String numeFisier){
		OperatiiInOutFisier of=new OperatiiInOutFisier();
		of.citesteObiectDinFisierText(numeFisier);
		Client c=new Client();
		for(Object o:of.getListaTrenuri()){
			try{
				c.transmite(o);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
