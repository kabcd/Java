package eu.ase;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="masina")
@XmlType(propOrder={"id","marca","pret"})
public class Car {
	private int id;
	private String marca;
	private float pret;
	public Car(int id, String marca, float pret) {
		this.id = id;
		this.marca = marca;
		this.pret = pret;
	}
	public Car(){
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public float getPret() {
		return pret;
	}
	public void setPret(float pret) {
		this.pret = pret;
	}
	@Override
	public String toString() {
		return "Car: id=" + id + ", marca=" + marca + ", pret=" + pret;
	}
	
	
}
