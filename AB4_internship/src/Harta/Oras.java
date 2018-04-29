package Harta;

public class Oras {

	String numeOras;

	
	public Oras(String nume){
		numeOras = nume;
	}

	public void print() {
		System.out.println(numeOras);
		
	}
	
	
	public String getNume(){
		return numeOras;
	}
	public boolean equals(String S){
		if (numeOras.equals(S)){
			return true;
		}
		return false;
	}
}
