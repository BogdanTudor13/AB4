package Harta;

import java.util.ArrayList;

public class Harta {

	private static Harta firstInstance = null;
	ArrayList<Tara> tari;
	//Harta e singleton sa retinem datele intr-o singura harta
	private Harta(){
		tari = new ArrayList<Tara>();
	}
	
	public static Harta getInstance(){
		if(firstInstance == null){
			firstInstance = new Harta();
		}
		return firstInstance;
	}
	
	public ArrayList<Tara> getTari(){
		return tari;
	}
	public void setTari( ArrayList<Tara> tari ){
		this.tari = tari;
	}
	
	public void print(){
		for (Tara i : tari){
			i.print();
		}
	}
	
	public int gasesteTara(String s){
		int index = 0;
		for (Tara it : tari){
			if (it.equals(s))
				return index;
			index++;
		}
		
		return -1;
	}
	
	
	public void printTaraDinPozitiaX(int index){
		System.out.println(tari.get(index));
	}
	
	public void printTaraX(String s){
		for(Tara i : tari){
			if (i.equals(s)){
				i.print();
			}
		}
	}
}
