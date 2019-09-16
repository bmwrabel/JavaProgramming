public class CustomerAccount {
	private String name;
	private double balance; 
	
	public CustomerAccount (String n, double b) {
		this.name = n;
		this.balance = b;
	}
	
	public String getName() {
		return name;
	}
	
	public double getBalance() {
		return balance;
	}
	// CALCULATING CUSTOMER'S BALANCE AFTER THE ORDER
	public void changeBalance(double price, int wineQuantity) {
		if (wineQuantity > 0) {
			balance += (price * wineQuantity);
		}
		if (wineQuantity <= 0) {
			balance += (price * wineQuantity * 8 / 10); // IF CUSTOMER RETURNS WINE, HE/SHE GETS ONLY 80%
		}
	}
}
