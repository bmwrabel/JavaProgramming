
public class BondTrade {

	private double yield;
	private int daysToMaturity;
	private int amount;
	
	public BondTrade (double y, int d, int a) {
		this.yield = y;
		this.daysToMaturity = d;
		this.amount = a;
	}
	
	public double getYield() {
		return yield;
	}
	
	public int getDaysToMaturity() {
		return daysToMaturity;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public String toString() {
		return "yield: " + yield + "; maturity: " + daysToMaturity + "; value: " + amount + " CHF(000)";
	}
}
