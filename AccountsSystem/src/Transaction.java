
public class Transaction {
	
	//to create a transaction for supplier and customer
	
	private String product;
	private double price;
	
	public Transaction(String product, double price) {
		this.product = product;
		this.price = price;
	}
	
	public String getProduct() {
		return this.product;
	}
	
	public double getPrice() {
		return this.price;
	}

}
