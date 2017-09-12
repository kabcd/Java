package eu.ase;

public class Person  implements Comparable<Person>{
	private int cnp;
	private String nume;
	private float greutate;
	public Person(){
		
	}
	public Person(int cnp, String nume, float greutate) {
		this.cnp = cnp;
		this.nume = nume;
		this.greutate = greutate;
	}
	@Override
	public String toString() {
		return "Person: cnp=" + cnp + ", nume=" + nume + ", greutate=" + greutate;
	}
	
	public int getCnp() {
		return cnp;
	}
	public void setCnp(int cnp) {
		this.cnp = cnp;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public float getGreutate() {
		return greutate;
	}
	public void setGreutate(float greutate) {
		this.greutate = greutate;
	}
	@Override
	public int compareTo(Person o) {
		return this.getNume().compareTo(o.getNume());		
	}
	
}
