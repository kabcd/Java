package eu.ase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ProgMainJCF {
	public static void main(String[] args) {
		Car c=new Car(12, "Logan", 500);
		Car c2=new Car(15,"Audi",800);
		Person p=new Person(123,"Maria", 85);
		Person p2=new Person(125,"Andrei",55);
		
		List<Car> lista=new ArrayList<Car>();
		lista.add(c);
		lista.add(c2);
		
		for(Car i:lista)
			System.out.println(i);
		Iterator<Car> it=lista.iterator();
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
		
		Map<Person, Car> hashmap=new TreeMap<Person, Car>();
		hashmap.put(p, c);
		hashmap.put(p2, c2);
		for(Person i:hashmap.keySet())
		{
			System.out.println("Key[ "+i+" ] " + "Value[ "+ hashmap.get(i)+" ]");
		}
		
		Set<Person> treeset=new LinkedHashSet<Person>();
		treeset.add(p);
		treeset.add(p2);
		for(Person i:treeset)
			System.out.println(i);
	}		
}
