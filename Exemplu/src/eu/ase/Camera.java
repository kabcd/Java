package eu.ase;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyAnnotation{
}

public class Camera {
	private String nume;
	private int nrPaturi;
	private int nrFerestre;
	private float pret;
	
	public Camera(){
		
	}
	
	public Camera(String nume, float pret) {
		this.nume = nume;
		this.pret = pret;
	}

	public Camera(String nume, int nrPaturi, int nrFerestre, float pret) {
		this.nume = nume;
		this.nrPaturi = nrPaturi;
		this.nrFerestre = nrFerestre;
		this.pret = pret;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public int getNrPaturi() {
		return nrPaturi;
	}

	public void setNrPaturi(int nrPaturi) {
		this.nrPaturi = nrPaturi;
	}

	public int getNrFerestre() {
		return nrFerestre;
	}

	public void setNrFerestre(int nrFerestre) {
		this.nrFerestre = nrFerestre;
	}

	public float getPret() {
		return pret;
	}

	public void setPret(float pret) {
		this.pret = pret;
	}

	@Override
	public String toString() {
		return "Camera: nume=" + nume + ", nrPaturi=" + nrPaturi + ", nrFerestre=" + nrFerestre + ", pret=" + pret;
	}
	@MyAnnotation
	public static void m1(){}
	@MyAnnotation
	public static void m2(){}
}
