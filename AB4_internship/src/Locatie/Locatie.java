package Locatie;

import java.util.ArrayList;

import Harta.Oras;

public class Locatie {

	String numeLocatie;
	Oras oras;
	float pretMediu;
	ArrayList<String> activitati;
	int dA, mA, yA;
	int dB, mB, yB;
	
	
	public Locatie(String numeLocatie, Oras oras, float pretMediu, ArrayList<String> activitati, int dA, int mA,
			int yA, int dB, int mB, int yB){
		this.numeLocatie= numeLocatie;
		this.oras=oras;
		this.pretMediu= pretMediu;
		this.activitati= activitati;
		this.dA = dA;
		this.mA = mA;
		this.yA = yA;
		this.dB = dB;
		this.mB = mB;
		this.yB = yB;
	}
	
	public Locatie() {
		// TODO Auto-generated constructor stub
	}

	public void printLocatie(){
		System.out.print(numeLocatie + "\nPret mediu pe zi: " + pretMediu + "\nActivitati posibilie: ");
		for(String it : activitati){
			System.out.print(it + " ");
		}
		System.out.println("\nPerioada de cazare este de pe\n" + dA + "/"+mA+"/"+ yA + "\npana pe: " +
		dB + "/" + mB + "/" + yB);
		oras.print();
	}
	
	public String getNume(){
		return numeLocatie;
	}
	public boolean equals(String s){
		if (numeLocatie.equals(s)){
			return true;
		}
		else
			return false;
	}
	public String getOras(){
		return oras.getNume();
	}
	public float getPret(){
		return pretMediu;
	}

	public int getdA() {
		return dA;
	}

	public void setdA(int dA) {
		this.dA = dA;
	}

	public int getmA() {
		return mA;
	}

	public void setmA(int mA) {
		this.mA = mA;
	}

	public int getyA() {
		return yA;
	}

	public void setyA(int yA) {
		this.yA = yA;
	}

	public int getdB() {
		return dB;
	}

	public void setdB(int dB) {
		this.dB = dB;
	}

	public int getmB() {
		return mB;
	}

	public void setmB(int mB) {
		this.mB = mB;
	}

	public int getyB() {
		return yB;
	}

	public void setyB(int yB) {
		this.yB = yB;
	}
	
	
}
