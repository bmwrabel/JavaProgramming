import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class GraphComponent extends JComponent {
	
	private View view;
	private Model model;
	
	// graph's components
    private int padding = 25;
    private int numberOfTicks = 10;
    private Color pointColour = new Color(255, 100, 100, 255); // set dot colour
	private ArrayList<Point> listOfPoints = new ArrayList<Point>(); // array list that is used to plot dots 
		
	
    // constructor 
	public GraphComponent(Model m) {	
    	model = m;
    }

	// painting graph
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
			
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// clear the array list that is used for displaying details of selected trade
		if(model.getSelectedTradeList().size() > model.getBondTradeList().size()) {
			model.getSelectedTradeList().clear();
		}
		
		// draw graph components only when the list has data in it
		if(model.getBondTradeList().size() > 0) {
			
			g2.setColor(Color.BLACK); // axis colour
			// draw x-axis
			g2.drawLine(2 * padding, getHeight() - padding, getWidth() - 30, getHeight() - padding);
			// draw y-axis
			g2.drawLine(2 * padding, getHeight() - padding, 2 * padding, padding);
    		
				
			// draw bond trade points 
			g2.setColor(pointColour); // set point colour
			
			listOfPoints = bondTradeDots(view.getComboBoxX().getSelectedIndex(), view.getComboBoxY().getSelectedIndex());
   
	        	for (int i = 0; i < listOfPoints.size() ; i++) {	
	        		int x = listOfPoints.get(i).x;
	        		int y = listOfPoints.get(i).y;
	        		g2.fillOval(x, y, 5, 5);
	        	}	      
	        	
	        	
	        // tick marks and labels x-axis
	        g2.setColor(Color.BLACK); // tick mark colour
      			for (int i = 0; i < numberOfTicks; i++) {
      				String xLabel;
      				// draw tick marks
      				int x1 = i * (getWidth() - 20) / numberOfTicks + 50; 
      				int x2 = x1;
      				int y1 = getHeight() - padding;
      				int y2 = getHeight() - 20;
      				g2.drawLine(x1, y1, x2, y2);
      				
      				// draw labels
      				int xLab = (int) ((model.getMinValue(view.getComboBoxX().getSelectedIndex()) + (model.getMaxValue(view.getComboBoxX().getSelectedIndex()) - model.getMinValue(view.getComboBoxX().getSelectedIndex())) * ((i * 1.0) / (numberOfTicks - 1))) * 100);
      				
      					//if yield is selected display labels with two decimal places
      					if(view.getComboBoxX().getSelectedIndex() == 0) {
      						xLabel = xLab / 100.0 + "";	
      					}
      					else {
      						xLabel = xLab / 100 + "";
      					}
      					
      				FontMetrics metrics = g2.getFontMetrics();
      				int labelWidth = metrics.stringWidth(xLabel);
      				g2.drawString(xLabel, x1 - labelWidth/2, y1 + metrics.getHeight() + 3);
      			}   
      
      			// tick marks and labels y-axis
      			for (int i = 0; i < numberOfTicks + 1; i++) {
      				String yLabel;
      				// draw tick marks
      				int x1 = 20 + padding;
      				int x2 = 2 * padding;
      				int y1 = getHeight() - (i * (getHeight() - 2 * padding) / numberOfTicks + padding);
      				int y2 = y1;
      				g2.drawLine(x1, y1, x2, y2);
    	  
      				// draw labels
      				int yLab = (int) ((model.getMinValue(view.getComboBoxY().getSelectedIndex()) + (model.getMaxValue(view.getComboBoxY().getSelectedIndex()) - model.getMinValue(view.getComboBoxY().getSelectedIndex())) * ((i * 1.0) / numberOfTicks)) * 100);
      				
  						//if yield is selected display labels with two decimal places
      					if(view.getComboBoxY().getSelectedIndex() == 0) {
      						yLabel = yLab / 100.0 + "";
      					}
      					else {
      						yLabel = yLab / 100 + "";
      					}
      	
      				FontMetrics metrics = g2.getFontMetrics();
      				int labelWidth = metrics.stringWidth(yLabel);
      				g2.drawString(yLabel, 75 - labelWidth - 35, y1 + (metrics.getHeight() / 2) - 3);
      			}
			}
		listOfPoints.clear();
		repaint();
	}
	
	// method that returns an array list with x and y coordinates. the array list is used to plot points. scaling is done here too.
	public ArrayList<Point> bondTradeDots (int comboBoxSelectionX, int comboBoxSelectionY) {
		
		int xCoordinate = 0, yCoordinate = 0;
		
    	for (int i = 0; i < model.getBondTradeList().size() ; i++) {	
    		if(comboBoxSelectionX == 0) {
    			xCoordinate = (int) (((model.getBondTradeList().get(i).getYield() - model.getMinValue(comboBoxSelectionX)) * (getWidth() - 80) - 2 * padding) / (model.getMaxValue(comboBoxSelectionX) - model.getMinValue(comboBoxSelectionX))) + 2 * padding;
    		}
    		else if(comboBoxSelectionX == 1) {
    			xCoordinate = (int) (((model.getBondTradeList().get(i).getDaysToMaturity() - model.getMinValue(comboBoxSelectionX)) * (getWidth() - 80) - 2 * padding) / (model.getMaxValue(comboBoxSelectionX) - model.getMinValue(comboBoxSelectionX))) + 2 * padding;
    		}
    		else if(comboBoxSelectionX == 2) {
    			xCoordinate = (int) (((model.getBondTradeList().get(i).getAmount() - model.getMinValue(comboBoxSelectionX)) * (getWidth() - 80) - 2 * padding) / (model.getMaxValue(comboBoxSelectionX) - model.getMinValue(comboBoxSelectionX))) + 2 * padding;
    		}
    		
    		if(comboBoxSelectionY == 0) {
    			yCoordinate = (int) (((model.getBondTradeList().get(i).getYield() - model.getMaxValue(comboBoxSelectionY)) * ((getHeight() - 1) - 2 * padding)) / (model.getMinValue(comboBoxSelectionY) - model.getMaxValue(comboBoxSelectionY))) + padding;
    		}
    		else if(comboBoxSelectionY == 1) {
    			yCoordinate = (int) (((model.getBondTradeList().get(i).getDaysToMaturity() - model.getMaxValue(comboBoxSelectionY)) * ((getHeight() - 1) - 2 * padding)) / (model.getMinValue(comboBoxSelectionY) - model.getMaxValue(comboBoxSelectionY))) + padding;
    		}
    		else if(comboBoxSelectionY == 2) {
    			yCoordinate = (int) (((model.getBondTradeList().get(i).getAmount() - model.getMaxValue(comboBoxSelectionY)) * ((getHeight() - 1) - 2 * padding)) / (model.getMinValue(comboBoxSelectionY) - model.getMaxValue(comboBoxSelectionY))) + padding;
    		}
    		listOfPoints.add(new Point(xCoordinate, yCoordinate)); // populating the array used for plotting points with coordinates
    		model.getSelectedTradeList().add(new Ellipse2D.Double(xCoordinate, yCoordinate, 5, 5)); // populating the array list of ellipse2d objects with coordinates
    	}
    	return listOfPoints;
    }
}