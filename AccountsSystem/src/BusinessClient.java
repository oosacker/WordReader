import java.util.ArrayList;

public class BusinessClient implements Contact{

	private String name;
	private int phone;
	private ArrayList<Transaction> sales = new ArrayList<>();	//sales made to this client
	
	public BusinessClient(String name, int phone) {
		this.name = name;
		this.phone = phone;
		this.sales = new ArrayList<Transaction>();
	}
	
	
	public void makeSale(String product, double price) {
		Transaction t = new Transaction(product, price);
		this.sales.add(t);
	}
	
	public ArrayList<Transaction> getSales(){
		return this.sales;
	}
	
	public String getInfo() {
		String temp = 
				"Client Name: " + this.name + "\n" + 
				"Phone: " + this.phone + "\n" + 
				"---------------------";
		return temp;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getPhone() {
		return this.phone;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}

	public void setPhone(int newPhone) {
		this.phone = newPhone;
	}

}
