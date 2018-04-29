package Executable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import Harta.Harta;
import Harta.Judet;
import Harta.Oras;
import Harta.Tara;
import Locatie.Locatie;

public class Source {

	private static Scanner scan;
	static ArrayList<Locatie> locatii = new ArrayList<Locatie>();
	public static void openFile(String s){
		
		try{
			scan = new Scanner (new File(s));
		}
		catch(IOException e){
			System.out.println("Nu exista acest fisier");
		}
	}
	public static void closeFile(){
		scan.close();
	}
	
	
	public static void citesteLocatii(){
		
		while (scan.hasNext()){
			ArrayList<String> activitati = new ArrayList<String>();
			String numeLocatie = scan.next();
			String numeOras = scan.next();
			float pretMediu = scan.nextFloat();
			int nrActivitati = scan.nextInt();
			for (int i = 0; i < nrActivitati; i ++){
				activitati.add(scan.next());
			}
			int dA = scan.nextInt();
			int mA = scan.nextInt();
			int yA = scan.nextInt();
			int dB = scan.nextInt();
			int mB = scan.nextInt();
			int yB = scan.nextInt();
			Oras o = new Oras (numeOras);
			Locatie l1 = new Locatie (numeLocatie, o,pretMediu, activitati,dA,mA,yA,dB,mB,yB);
			locatii.add(l1);
		}
	}
	
	public static Harta citesteTari(){
		
		Harta h;
		h = Harta.getInstance();
		ArrayList <Tara> tari = new ArrayList <Tara>();
		
		while (scan.hasNext()){
			String s = scan.next();
			Tara t = new Tara (s);
			tari.add(t);
		}
		h.setTari(tari);
		
		return h;
	}
	
	public static Harta citesteOrase(String fileName){
		
		openFile(fileName);
		
		Harta h;
		h  = Harta.getInstance();
		
		while(scan.hasNext()){
			
			String tara = scan.next();
			String judet = scan.next();
			String oras = scan.next();
			
			// cautam sa vedem daca exista tara
			int indexT = h.gasesteTara(tara); 
			
			//daca tara nu exista
			if(indexT == -1){   
				//facem obiecte noi
				Tara t = new Tara(tara);
				Judet j = new Judet(judet);
				Oras o = new Oras(oras);
				
				 //adaugam tara care nu exista pana atunci
				h.getTari().add(t);
				//updatam indexul tarii recent adaugate
				indexT = h.gasesteTara(tara); 
				//adaugam judetul nou pe tara nou adaugata la indexT
				h.getTari().get(indexT).getJudete().add(j); 
				//retinem indexul judetului
				int indexJ = h.getTari().get(indexT).gasesteJudet(judet); 
				//adaugam oras nou 
				h.getTari().get(indexT).getJudete().get(indexJ).getOrase().add(o); 
			}
			//daca tara exista deja
			else{ 
				//cautam indexul judetului
				int indexJ = h.getTari().get(indexT).gasesteJudet(judet); 
				//daca judetul nu exista
				if(indexJ == -1){ 
					// creem obiect de tip judet
					Judet j = new Judet(judet); 
					//adaugam judetul
					h.getTari().get(indexT).getJudete().add(j); 
					// retinem indexul judetului nou facut
					indexJ = h.getTari().get(indexT).gasesteJudet(judet); 
					//creem si adaugam orasul O
					Oras o = new Oras(oras);
					h.getTari().get(indexT).getJudete().get(indexJ).getOrase().add(o);
				}
				//daca judetul exista atunci doar adaugam orasul
				else{ 
					Oras o = new Oras(oras);
					h.getTari().get(indexT).getJudete().get(indexJ).getOrase().add(o);
				}
			}
			
		}
		closeFile();
		return h;
				
	}
	
	
	public static void testFile(String fileName){
		
		String s = null;
		while (scan.hasNext()){
			s = scan.next();
			System.out.println(s);
		}
	}
	
	public static Harta citesteJudete(){
		Harta h;
		h = Harta.getInstance();
		
		while(scan.hasNext()){
			String tara = scan.next();
			String judet = scan.next();
			int index = h.gasesteTara(tara);
			if (index == -1){
				Tara t = new Tara(tara);
				Judet j = new Judet(judet);
				h.getTari().add(t);
				index = h.gasesteTara(tara);
				h.getTari().get(index).getJudete().add(j);
			}
		}
		
		return h;
	}
	
	public static void top5(){
		Harta h;
		h = Harta.getInstance();
		ArrayList<Oras> orase = new ArrayList<Oras>();
		ArrayList<Locatie> topLocatii = new ArrayList<Locatie>();
		Scanner scan = new Scanner(System.in);
		System.out.println("Selectati modul de cautare: t - Tara; j - Judet; o - Oras");
		String c = scan.next();
		switch (c){
		case "t": {
			//daca vom cauta dupa tara vom face o parcurgere totala a ArrayListurilor aferente, Judet si Orase
			int indexJ = 0;
			System.out.println("Introduceti tara dupa care facem cautarea: ");
			String tara = scan.next();
			int indexT = h.gasesteTara(tara);
			if(indexT == -1){
				System.out.println("Nu exista tara");
				scan.close();
				return;
			}
			for(@SuppressWarnings("unused") Judet it: h.getTari().get(indexT).getJudete()){
				for (Oras j : h.getTari().get(indexT).getJudete().get(indexJ).getOrase()){
					orase.add(j);
				}
				indexJ++;
			}
			//apoi verificam sa existe orasele in locatii
			for(Locatie it: locatii){
				String orasCautat  = it.getOras();
				for (Oras o: orase){
					if (o.getNume().equals(orasCautat)){
						topLocatii.add(it);
						break;
					}
				}
			}
			Collections.sort(topLocatii, new Comparator<Locatie>(){
				public int compare(Locatie l1, Locatie l2){
					return Float.valueOf(l1.getPret()).compareTo(l2.getPret());
				}
			}
			);
			for(int i = 0 ; i < 5; i ++){
				topLocatii.get(i).printLocatie();
			}
			scan.close();
			return;
		}
		case "j":{
			//avand judetul tot ce facem este sa iteram prin orase
			System.out.println("Introduceti judetul dupa care facem cautarea : ");
			String judet = scan.next();
			System.out.println("Introduceti tara dupa care facem cautarea: ");
			String tara = scan.next();
			int indexT = h.gasesteTara(tara);
			if (indexT == -1){
				System.out.println("Nu exista tara");
				scan.close();
				return;
			}
			int indexJ = h.getTari().get(indexT).gasesteJudet(judet);
			if(indexJ == -1 ){
				System.out.println("Nu exista judetul " + judet + " in " + tara);
				scan.close();
				return;
			}
			orase = h.getTari().get(indexT).getJudete().get(indexJ).getOrase();
			//Dupa sa verificam daca se potrivesc datele
			for(Locatie it: locatii){
				String orasCautat  = it.getOras();
				for (Oras o: orase){
					if (o.getNume().equals(orasCautat)){
						topLocatii.add(it);
						break;
					}
				}
			}
			//sortam vectorul topLocatii crescator dupa pret
			Collections.sort(topLocatii, new Comparator<Locatie>(){
				public int compare(Locatie l1, Locatie l2){
					return Float.valueOf(l1.getPret()).compareTo(l2.getPret());
				}
			}
			);
			for(int i = 0 ; i < 5; i ++){
				topLocatii.get(i).printLocatie();
			}
			scan.close();
			return;
		}
		case "o":{
			//Dandu-se deja orasul e mai usor ca timp de executie
			System.out.println("Introduceti orasul cautat: ");
			String oras = scan.next();
			System.out.println("Introduceti judetul: ");
			String judet = scan.next();
			System.out.println("Introuceti tara: ");
			String tara = scan.next();
			//verificam daca exista tara
			int indexT = h.gasesteTara(tara);
			if (indexT == -1){
				System.out.println("Nu exista tara");
				scan.close();
				return;
			}
			//verificam daca exista judetul
			int indexJ = h.getTari().get(indexT).gasesteJudet(judet);
			if(indexJ == -1 ){
				System.out.println("Nu exista judetul " + judet + " in " + tara);
				scan.close();
				return;
			}
			//verificam daca exista orasul
			if( h.getTari().get(indexT).getJudete().get(indexJ).existaOras(oras) == false){
				System.out.println("Nu exista orasul " + oras + " in judetul " + judet + " din " + tara);
				scan.close();
				return;
			}
			//orase = h.getTari().get(indexT).getJudete().get(indexJ).getOrase();
			//acelasi lucru ca anterior
			for(Locatie it: locatii){
				String orasCautat  = oras;
				if (it.getOras().equals(orasCautat)==true){
					topLocatii.add(it);
				}
			}
			//sortam vectorul topLocatii crescator dupa pret
			Collections.sort(topLocatii, new Comparator<Locatie>(){
				public int compare(Locatie l1, Locatie l2){
					return Float.valueOf(l1.getPret()).compareTo(l2.getPret());
				}
			}
			);
			for(int i = 0 ; i < 5; i ++){
				topLocatii.get(i).printLocatie();
			}
			scan.close();
			return;
		}
		default : {
			System.out.println("Nu exista comanda. Rulati si incercati din nou!");
			scan.close();
			return;
		}
	}
	
}
	
	public static void cautaLocatia(){
		//parcurgem ArrayList-ul sa vedem daca exista Locatia pe Harta
		Scanner scan = new Scanner (System.in);
		System.out.println("Introduceti locatia: \nLocatie Tara Judet Oras");
		String locatie = scan.next();
		String tara = scan.next();
		String judet = scan.next();
		String oras = scan.next();
		Harta h;
		h = Harta.getInstance();
		int indexT = h.gasesteTara(tara);
		if (indexT == -1){
			System.out.println("Tara nu exista!");
			scan.close();
			return;
		}
		else{
			int indexJ = h.getTari().get(indexT).gasesteJudet(judet);
			if ( indexJ == -1 ){
				System.out.println("Judetul nu exista!");
				scan.close();
				return;
			}
			else{
				boolean existaOras = h.getTari().get(indexT).getJudete().get(indexJ).existaOras(oras);
				if(existaOras == false){
					System.out.println("Orasul nu exista!");
				}
				//daca totul e okay pana aici vom cauta locatia efectiv in ArrayList-ul de locatii
				else{
					int okay = 0;
					for(Locatie it : locatii){
						if (it.getNume().equals(locatie)){
							it.printLocatie();
							System.out.println(tara+ " " + judet);
							okay = 1;
							}
						}
					if (okay == 0){
						System.out.println("Nu s-a gasit locatia");
					}
					}
				}
			}
		scan.close();
}
	
	public static void celMaiIeftin(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Introduceti datele de cautare \nDe pe Z/L/AAAA");
		int dA = scan.nextInt();
		int mA = scan.nextInt();
		int yA = scan.nextInt();
		System.out.println("Pana pe: Z/L/AAAA");
		int dB = scan.nextInt();
		int mB = scan.nextInt();
		int yB = scan.nextInt();
		float minPret = 100000;
		int okay = 0;
		Locatie aux = new Locatie();
		
		//cautam in vectorul de locatii fiecare locatie care se potriveste cerintelor
		//dupa care vom compara pretul acesteia cu cel mai mic penru a decide dacaa o punem sau nu
		
		for (Locatie it : locatii){
			//Locatie aux = new Locatie();
			
			if ( (dA >= it.getdA() && mA >= it.getmA()) && yA >= it.getyA()){
				if( (dB <= it.getdB() && mB <= it.getmB()) && yB <= it.getyB() ){
					if ( it.getPret() < minPret){
						aux = it;
						minPret = it.getPret();
						okay = 1;
					}
				}
			}
		}
		//caz in care nu exista Locatie ce ne satisface cerintele
		if (okay == 0){
			System.out.println("Nu am gasit activitati disponibile in perioada solicitata");
		}
		else{
			System.out.println("Locul cel mai rentabil unde sa mergeti este:\n");
			aux.printLocatie();
		}
		scan.close();
	}
	
	public static void main(String[] args){
		// ne creem harta
		@SuppressWarnings("unused")
		Harta h;
		h = Harta.getInstance();
		//numele fisierelor din care luam date despre harta si locatii
		String inputOrase = "src/inputOrase";
		String inputLocatii = "src/inputLocatii";
		
		/* inputul datelor organizat in structura
		 * TARA
		 * JUDET
		 * ORAS*/
		h = citesteOrase(inputOrase);
		closeFile();
		openFile(inputLocatii);
		citesteLocatii();
		closeFile();
		
		//Alegem ce program sa rulam
		
		System.out.println("Ce optiune alegem ( 1 / 2 / 3 )");
		Scanner scan = new Scanner(System.in);
		String c = scan.next();
		switch(c){
		case "1": cautaLocatia();
		break;
		case "2": top5();
		break;
		case "3": celMaiIeftin();
		break;
		
		default: System.out.println("Tasta gresita! Rulati din nou si mai incercati o data!");
		break;
		}
		scan.close();

	}
}
