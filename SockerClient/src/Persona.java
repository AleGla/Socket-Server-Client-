
public class Persona {

	private String name;
	
	private String lastName;
	
	private String age;
	
	private String nationality;

	public Persona(String name, String lastName, String age, String nationality) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.nationality = nationality;
	}
	
	public Persona() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	
	
}
