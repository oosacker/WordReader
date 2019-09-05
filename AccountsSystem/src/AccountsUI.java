import java.util.ArrayList;
import java.util.List;

import ecs100.UI;

public class AccountsUI {

	// You will need to create the "Contact" type!
	private List<Contact> contacts = new ArrayList<>();
	//private Contact tempContact; 

	//
	public AccountsUI() {
		UI.initialise();
		UI.setWindowSize(1200, 900);
		UI.addButton("Add individual contact", this::addPerson);
		UI.addButton("Add business supplier contact", this::addSupplier);
		UI.addButton("Add business client contact", this::addBusinessClient);
		UI.addButton("Add trading partner contact", this::addTradingPartner);
		UI.addButton("Remove a contact", this::removeContact);
		UI.addButton("List contacts", this::listContacts);
		UI.addButton("Find contact by name", this::findContact);
		UI.addButton("Record purchase from supplier", this::recordPurchase);
		UI.addButton("Record sale to client", this::recordSale);
		UI.addButton("Report sale/purchase balance for contact", this::reportBalance);
		UI.addButton("Compute total profit", this::computeProfit);
		UI.addButton("List all transactions with a contact", this::listTransactions);
		// Your code here?
	}
	
	private void addPerson() {
		String name = UI.askString("Name: ");
		String company = UI.askString("Company: ");
		int age = UI.askInt("Age: ");
		int phone = UI.askInt("Phone: ");
		
		Contact person = (Contact) new Person(name, company, age, phone);
		contacts.add(person);
		
		/** 
		 * I foolishly said name/company for person, for others names means company name,
		 * too much work to fix
		 * */
		UI.println("Added person: " + person.getName());	
		
	}
	
	public void removeContact() {
		
		if(contacts.isEmpty()) {
			UI.println("Contact list is empty");
			return;
		}
		
		String name = UI.askString("Name: ");
		Contact c = (Contact) searchContactList(name);
		
		if(c != null) {
			contacts.remove(c);
			UI.println("Contact removed");
		}
		else {
			UI.println("Contact not found");
		}
	}
	
	private void addSupplier() {
		// Add a supplier to the list of contacts
		String name = UI.askString("Name: ");
		int phone = UI.askInt("Phone: ");
		
		Contact supplier = (Contact) new Supplier(name, phone);
		contacts.add(supplier);
		
		UI.println("Added supplier: " + supplier.getName());
		
	}
	
	private void addBusinessClient() {
		// Add a business client to the list of contacts
		String name = UI.askString("Name: ");
		int phone = UI.askInt("Phone: ");
		
		Contact bc = (Contact) new BusinessClient(name, phone);
		contacts.add(bc);
		
		UI.println("Added business client: " + bc.getName());
	}
	
	private void addTradingPartner() {
		// Add someone who is both a buyer and a seller to the list of contacts
		String name = UI.askString("Name: ");
		int phone = UI.askInt("Phone: ");
		
		Contact tp = (Contact) new TradingPartner(name, phone);
		contacts.add(tp);
		
		UI.println("Added trading partner: " + tp.getName());
	}
	
	
	private void listContacts() {
		// List all contacts in the system
		
		if(contacts.isEmpty()) {
			UI.println("Contact list is empty");
			return;
		}
		
		for(Contact c : contacts) {
			
			// print them out
			UI.println(c.getInfo());
			
		}
	}
	
	private void findContact() {
		// Find one of the contacts by name and report on them
		if(contacts.isEmpty()) {
			UI.println("Contact list is empty");
			return;
		}
		
		String name = UI.askString("Name: ");
		
		Contact c = (Contact) searchContactList(name);
		
		UI.println(c.getInfo());

	}
	
	private void recordPurchase() {
		// Record a purchase from a supplier
		if(contacts.isEmpty()) {
			UI.println("Contact list is empty");
			return;
		}
		
		String name = UI.askString("Supplier/trading partner: ");
		Contact c = (Contact) searchContactList(name);
		
		String product = UI.askString("Purchased: ");
		double price = UI.askInt("Price: ");
		
		if ( c instanceof Supplier ) {
			
			((Supplier) c).makePurchase(product, price);
			
		}
		
		else if ( c instanceof TradingPartner ) {
			
			((TradingPartner) c).makePurchase(product, price);
			
		}
		
		else {
			
			UI.println("Error: contact is neither a supplier nor trading partner");
			
		}

	}
	
	private void recordSale() {
		// Record a sale to a customer
		if(contacts.isEmpty()) {
			UI.println("Contact list is empty");
			return;
		}
		
		String name = UI.askString("Client/trading partner: ");

		Contact c = (Contact) searchContactList(name);
		
		String product = UI.askString("Purchased: ");
		double price = UI.askInt("Price: ");
		
		if ( c instanceof BusinessClient ) {
			
			((BusinessClient) c).makeSale(product, price);
			
		}
		
		else if ( c instanceof TradingPartner ) {
			
			((TradingPartner) c).makeSale(product, price);
			
		}
		
		else {
			
			UI.println("Error: contact is neither a client nor trading partner");
			
		}
		
	}
	
	
	private void reportBalance() {
		// Report how much has been paid by/to a contact
		if(contacts.isEmpty()) {
			UI.println("Contact list is empty");
			return;
		}
		
		String name = UI.askString("Name: ");
		
		ArrayList<Transaction> sales;
		ArrayList<Transaction> purchases;
		
		double totalPurchases = 0;
		double totalSales = 0;
		
		Contact c = (Contact) searchContactList(name);
		if(c == null) {
			return;
		}
		
		if ( c instanceof Supplier ) {
			
			purchases = ((Supplier) c).getPurchases();
			
			//calculate the total purchases 
			for (Transaction t : purchases) {
				totalPurchases = totalPurchases + t.getPrice();
			}
			UI.println("Total sales: $" + totalPurchases);
			
		}				
		
		else if ( c instanceof BusinessClient ) {
			
			sales = ((BusinessClient) c).getSales();
			
			//calculate the total sales 
			for (Transaction t : sales) {
				totalSales = totalSales + t.getPrice();
			}
			UI.println("Total sales: $" + totalSales);
			
		}
		
		else if ( c instanceof TradingPartner ) {
			
			//calculate the total sales
			sales = ((TradingPartner) c).getSales();
			for (Transaction t : sales) {
				totalSales = totalSales + t.getPrice();
			}
			UI.println("Total sales: $" + totalSales);

			//calculate the total sales 
			purchases = ((TradingPartner) c).getPurchases();
			for (Transaction t : purchases) {
				totalPurchases = totalPurchases + t.getPrice();
			}
			UI.println("Total purchases: $" + totalPurchases);
			
		}
		
		else {
			UI.println("Error: contact is not a supplier, client nor trading partner");
		}
		
	}
	
	private void computeProfit() {
		// Compute the total profit of the business (sales - purchases)
		// Your code here...
	
		ArrayList<Transaction> sales;
		ArrayList<Transaction> purchases;
	
		double totalPurchases = 0;
		double totalSales = 0;
		
		if(contacts.isEmpty()) {
			UI.println("Contact list is empty");
			return;
		}
		
		for(Contact c : contacts) {
			
			if ( c instanceof Supplier ) {
				
				//calculate the total purchases 
				purchases = ((Supplier) c).getPurchases();
				
				for (Transaction t : purchases) {
					totalPurchases = totalPurchases + t.getPrice();
				}
			}		
		
			else if ( c instanceof BusinessClient ) {
				
				//calculate the total sales 
				sales = ((BusinessClient) c).getSales();
				
				for (Transaction t : sales) {
					totalSales = totalSales + t.getPrice();
				}
			}
		
			else if ( c instanceof TradingPartner ) {
			
				//calculate the total sales
				sales = ((TradingPartner) c).getSales();
				
				for (Transaction t : sales) {
					totalSales = totalSales + t.getPrice();
				}

				//calculate the total sales 
				purchases = ((TradingPartner) c).getPurchases();
				
				for (Transaction t : purchases) {
					totalPurchases = totalPurchases + t.getPrice();
				}
		
			}
		
		}
		UI.println("Total sales: $" +totalSales);
		UI.println("Total purchases: $" +totalPurchases);
		UI.println("Total profit: $" + (totalSales-totalPurchases));
		
	}
	
	private void listTransactions() {
		
		ArrayList<Transaction> sales;
		ArrayList<Transaction> purchases;
		
		if(contacts.isEmpty()) {
			UI.println("Contact list is empty");
			return;
		}
		
		String name = UI.askString("Supplier/client/trading partner: ");
		Contact c = (Contact) searchContactList(name);
		
		if ( c instanceof Supplier ) {
			
			purchases = ((Supplier) c).getPurchases();
			for (Transaction t : purchases) {
				UI.println("Supplier: " + c.getName());
				UI.println("Purchased product: " + t.getProduct());
				UI.println("Price: $" + t.getPrice());
				UI.println("---------------------");
			}

		}		
	
		else if ( c instanceof BusinessClient ) {

			sales = ((BusinessClient) c).getSales();
			
			for (Transaction t : sales) {
				UI.println("Business client: " + c.getName());
				UI.println("Sold product: " + t.getProduct());
				UI.println("Price: $" + t.getPrice());
				UI.println("---------------------");
			}	

		}
	
		else if ( c instanceof TradingPartner ) {

			sales = ((TradingPartner) c).getSales();
			
			for (Transaction t : sales) {
				UI.println("Trading partner: " + c.getName());
				UI.println("Sold product: " + t.getProduct());
				UI.println("Price: $" + t.getPrice());
				UI.println("---------------------");
			}

			purchases = ((TradingPartner) c).getPurchases();
			
			for (Transaction t : purchases) {
				UI.println("Trading partner: " + c.getName());
				UI.println("Purchased product: " + t.getProduct());
				UI.println("Price: $" + t.getPrice());
				UI.println("---------------------");
			}		
	
		}
		
		/*
		for(Contact c : contacts) {
			
			if ( c instanceof Supplier ) {
				
				//calculate the total purchases 
				purchases = ((Supplier) c).getPurchases();
				
				for (Transaction t : purchases) {
					UI.println("Supplier: " + c.getName());
					UI.println("Purchased product: " + t.getProduct());
					UI.println("Price: $" + t.getPrice());
					UI.println("---------------------");
				}
			}		
		
			else if ( c instanceof BusinessClient ) {
				
				//calculate the total sales 
				sales = ((BusinessClient) c).getSales();
				
				for (Transaction t : sales) {
					UI.println("Business client: " + c.getName());
					UI.println("Sold product: " + t.getProduct());
					UI.println("Price: $" + t.getPrice());
					UI.println("---------------------");
				}
			}
		
			else if ( c instanceof TradingPartner ) {
			
				//calculate the total sales
				sales = ((TradingPartner) c).getSales();
				
				for (Transaction t : sales) {
					UI.println("Trading partner: " + c.getName());
					UI.println("Sold product: " + t.getProduct());
					UI.println("Price: $" + t.getPrice());
					UI.println("---------------------");
				}

				//calculate the total sales 
				purchases = ((TradingPartner) c).getPurchases();
				
				for (Transaction t : purchases) {
					UI.println("Trading partner: " + c.getName());
					UI.println("Purchased product: " + t.getProduct());
					UI.println("Price: $" + t.getPrice());
					UI.println("---------------------");
				}
		
			}
		
		}
		*/
	}	
	
	/**
	 * Find a contact inside the ArrayList by name and return it
	 */
	private Contact searchContactList(String name) {

		for(Contact c : contacts) {
		
			if(c.getName().equals(name)) {
				// must stop searching once you find it!!!!!
				return c;
			}
			
		} 
		return null;
	}
	
	/**
	 * 
	 * @param args
	 */
	//private ArrayList<Transaction> getTransactions(){
		
	//}
	
	
	public static void main(String[] args) {
		new AccountsUI();
	}

}
