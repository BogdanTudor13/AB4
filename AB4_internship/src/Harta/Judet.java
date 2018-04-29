package Harta;

import java.util.ArrayList;

public class Judet {

	String numeJudet;
	
	ArrayList<Oras> orase;
	
	public Judet(String nume){
		numeJudet = nume;
		orase = new ArrayList<Oras>();
	}
	
	
	public boolean equals(String nume){
		if (numeJudet.equals(nume))
			return true;
		return false;
	}

	public ArrayList<Oras> getOrase() {
		// TODO Auto-generated method stub
		return orase;
	}

	public void print() {
		// TODO Auto-generated method stub
		System.out.println(numeJudet);
	}
	public boolean existaOras(String s){
		
		for (Oras it : orase){
			if (it.equals(s))
				return true;
		}
		
		return false;
	}
}
