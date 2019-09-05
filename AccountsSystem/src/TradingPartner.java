import java.util.ArrayList;

public class TradingPartner implements Contact{

	private String name;
	private int phone;
	private ArrayList<Transaction> purchases = new ArrayList<>();	//purchases made from this partner
	private ArrayList<Transaction> sales = new ArrayList<>();		//sales made to this partner
	
	public TradingPartner(String name, int phone) {
		this.name = name;
		this.phone = phone;
		this.purchases = new ArrayList<Transaction>();
		this.sales = new ArrayList<Transaction>();
	}
	
	public ArrayList<Transaction> getSales(){
		return this.sales;
	}
	
	public ArrayList<Transaction> getPurchases(){
		return this.purchases;
	}
	
	public void makeSale(String product, double price) {
		Transaction t = new Transaction(product, price);
		this.sales.add(t);
	}
	
	public void makePurchase(String product, double price) {
		Transaction t = new Transaction(product, price);
		this.purchases.add(t);
	}
	
	public String getInfo() {
		String temp = 
				"Partner Name: " + this.name + "\n" + 
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
