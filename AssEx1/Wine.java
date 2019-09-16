public class Wine {
	private String wineName;
	private int wineQuantity;
	private double winePrice;
	
	public Wine (String wn, int wq, double wp) {
		this.wineName = wn;
		this.wineQuantity = wq;
		this.winePrice = wp;
	}
	
	public String getWineName() {
		return wineName;
	}
	
	public int getwineQuantity() {
		return wineQuantity;
	}
	
	public double getWinePrice() {
		return winePrice;
	}
	
	//CALCULATING THE TOTAL COST OF WINES CUSTOMERS HAS ORDERED. THIS IS USED WHEN WRITING TO THE 'STATEMENT.TXT' FILE.
	public double totalCost(double priceOfWine, int quantityOfWine) {
		double totalCostOfOrder = priceOfWine * quantityOfWine;
		return totalCostOfOrder;
	}
}