package Harta;

import java.util.ArrayList;

public class Tara {

	String numeTara;
	ArrayList<Judet> judete;
	
	public Tara(String nume){
		this.numeTara = nume;
		judete = new ArrayList<Judet>();
	}
	
	public boolean equals(String nume){
		if (numeTara.equals(nume))
			return true;
		return false;
	}

	public void print() {
		System.out.println(numeTara);
		
	}
	
	public int gasesteJudet(String s){
		int index = 0;
		for (Judet it : judete){
			if (it.equals(s))
				return index;
			index++;
		}
		
		return -1;
	}
	
	public ArrayList <Judet> getJudete(){
		return judete;
	}
}
