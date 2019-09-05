import java.util.ArrayList;

public class Supplier implements Contact{

	private String name;
	private int phone;
	private ArrayList<Transaction> purchases = new ArrayList<>();	//purchases made from this supplier
	
	public Supplier(String name, int phone) {
		this.name = name;
		this.phone = phone;
		this.purchases = new ArrayList<Transaction>();
	}
	
	public void makePurchase(String product, double price) {
		Transaction t = new Transaction(product, price);
		this.purchases.add(t);
	}
	
	public ArrayList<Transaction> getPurchases(){
		return this.purchases;
	}

	public String getInfo() {
		String temp = 
				"Supplier Name: " + this.name + "\n" + 
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
