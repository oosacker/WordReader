
public class Person implements Contact{

	private String company;
	private String name;	// mistake: can be confused with business name in the other classes
	private int age;
	private int phone;
	
	public Person(String name, String company, int age, int phone) {
		this.company = company;
		this.name = name;
		this.age = age;
		this.phone = phone;
	}
	
	public String getInfo() {
		String temp = 
				"Name: "+ this.name + "\n" +
				"Age: "+ this.age + "\n" + 
				"Company: " + this.company + "\n" + 
				"Phone: " + this.phone + "\n" + 
				"---------------------";
		return temp;
	}
	
	public String getCompany() {
		return this.company;
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getAge() {
		return this.age;
		
	}
	
	public int getPhone() {
		return this.phone;
	}
	
	public void setCompany(String newCompany) {
		this.company = newCompany;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public void setAge(int newAge) {
		this.age = newAge;
	}
	
	public void setPhone(int newPhone) {
		this.phone = newPhone;
	}
	
}
