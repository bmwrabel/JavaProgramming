import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Model implements Comparator<BondTrade> {
	
	private ArrayList<BondTrade> bondTradeList = new ArrayList<BondTrade>(); // array list of bond trade objects in which values read from a CSV file are stored
	private ArrayList<Ellipse2D> selectedTradeList = new ArrayList<Ellipse2D>(); // array list of ellipse2d objects used for obtaining details of a selected bond trade

	
	public Model() {
		// constructor
	}
	
	// method for getting maximum value in the bondTradeList array list.
	public double getMaxValue (int comboBoxSelection) {
		
		double maxValue = 0;
		BondTrade elementMaxYield, elementMaxMaturity, elementMaxAmount;

			if(comboBoxSelection == 0) {
				elementMaxYield = Collections.max(bondTradeList, Comparator.comparingDouble(BondTrade::getYield));
				maxValue = elementMaxYield.getYield();
			}
			else if(comboBoxSelection == 1) {
				elementMaxMaturity = Collections.max(bondTradeList, Comparator.comparingInt(BondTrade::getDaysToMaturity));
				maxValue = elementMaxMaturity.getDaysToMaturity();
			}
			else if (comboBoxSelection == 2) {
				elementMaxAmount = Collections.max(bondTradeList, Comparator.comparingInt(BondTrade::getAmount));
				maxValue = elementMaxAmount.getAmount();
			}
		return maxValue;
	}

	// method for getting minimum value in the bondTradeList array list.
	public double getMinValue (int comboBoxSelection) {
		
		double minValue = 0;
		BondTrade elementMinYield, elementMinMaturity, elementMinAmount;

			if(comboBoxSelection == 0) {
				elementMinYield = Collections.min(bondTradeList, Comparator.comparingDouble(BondTrade::getYield));
				minValue = elementMinYield.getYield();
			}
			else if(comboBoxSelection == 1) {
				elementMinMaturity = Collections.min(bondTradeList, Comparator.comparingInt(BondTrade::getDaysToMaturity));
				minValue = elementMinMaturity.getDaysToMaturity();
			}	
			else if (comboBoxSelection == 2) {
				elementMinAmount = Collections.min(bondTradeList, Comparator.comparingInt(BondTrade::getAmount));
				minValue = elementMinAmount.getAmount();
			}
		return minValue;
	}
	// method for clearing bond trade list
	public void clearList(ArrayList list) {
		list.clear();
	}
	
	public ArrayList<Ellipse2D> getSelectedTradeList() {
		return selectedTradeList;
	}

	public void setSelectedTradeList(ArrayList<Ellipse2D> selectedTradeList) {
		this.selectedTradeList = selectedTradeList;
	}

	public ArrayList<BondTrade> getBondTradeList() {
		return bondTradeList;
	}
	
	public void setBondTradeList(ArrayList<BondTrade> bondTradeList) {
		this.bondTradeList = bondTradeList;
	}

	@Override
	public int compare(BondTrade o1, BondTrade o2) {
		// TODO Auto-generated method stub
		return 0;
	}
}