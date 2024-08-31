package filemaker;

import java.time.LocalDate;

import filemakerInterfaces.GamerBirthdateGenerator;
import filemakerInterfaces.GamerNameGenerator;

public class Gamer {
	private String name;
	private LocalDate birthdate;
	public Gamer(GamerNameGenerator name,  GamerBirthdateGenerator birthdate) {
		super();
		this.name = name.generateGamerName();
		this.birthdate = birthdate.generateBirthdate();
	}

	public Gamer() {
		
	}

	public String getName() {
		return name;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}
	 @Override
	    public String toString() {
	        return "Gamer{" +
	                "name=" + name +
	                ", birthdate=" + birthdate +
	                
	                '}';
	    }
	
}
