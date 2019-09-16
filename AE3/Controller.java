import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class Controller {

	private Model model;
	private View view;
	
	// constructor
	public Controller (Model m, View v) {
		model = m;
		view  = v;
		view.graphMouseListener(new detailsOfBondTrade());
		view.buttonActionListener(new buttonAction());
	}	
	
	// method that allows user to use JFileChooser to select a CSV file
	public void openFileChooser() {
		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		    
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV", "csv"); // file filter applied
		chooser.setFileFilter(filter);
		    
		int returnVal = chooser.showOpenDialog(view.getMainWindow());
		
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    		
		    	File selectedFile = chooser.getSelectedFile();
		    	
		    	displayFileName(selectedFile.getName()); // put selected file's name into the fileNameTextField
				model.clearList(model.getBondTradeList()); // clear bondTrade array list when a file is loaded

		    	try {
					addToArrayList(selectedFile.getPath()); // passing file path of selected file to a method that will scan through it and populate the bond trade array list.
				} 
		    	catch (FileNotFoundException e) {
					e.printStackTrace();
				}
		    }
	}
	
	// read selected file and add lines into array list of bond trade objects
	public void addToArrayList (String selectedFilePath) throws FileNotFoundException 
	{
		FileReader fr = null;
		
		try {
			fr = new FileReader(selectedFilePath); // create file reader
			Scanner scanningSelectedFile = new Scanner(fr); // wrap scanner around selected file
			
			String line = scanningSelectedFile.nextLine();
			String[] tokens = line.split(",");
			
				// first line of selected file is read into combo boxes
				for(int i = 0; i < 3; i++) {
					view.getComboBoxX().addItem(tokens[i]);
					view.getComboBoxY().addItem(tokens[i]);
				}
				
				// read from line 2 and populate the bondTradeList array list
				while(scanningSelectedFile.hasNextLine()) {
					line = scanningSelectedFile.nextLine();
					tokens = line.split(",");
					
					// parsing string to double and it so that an instance of the bond trade object can be created
					double yield = Double.parseDouble(tokens[0]);
					int daysToMaturity = Integer.parseInt(tokens[1]);
					int amount = Integer.parseInt(tokens[2]);
			
					// create a bond trade object
					BondTrade bondTradeObject = new BondTrade(yield, daysToMaturity, amount); 
					// add bond trade objects into the bondTradeList arrayList
					model.getBondTradeList().add(bondTradeObject);
				}
			scanningSelectedFile.close(); // close the scanner
		}
		catch(FileNotFoundException exception) {
			exception.printStackTrace();
		}
		finally {
			// close the file, if it's open
			if(fr!=null) {
				try {
					fr.close();
				} 
				catch (IOException exception) {
					exception.printStackTrace();
				}
			}	
		}
	}
	
	public void closeProgram() {
		System.exit(0);
	}
	
	//method to remove items from combo boxes
	public void removeItemsFromComboBox() {
		
		// check whether combo boxes have more than 3 items each, if true, remove items
		if(view.getComboBoxX().getItemCount() > 3 && view.getComboBoxY().getItemCount() > 3) {
			for(int i = 2; i >= 0; i--) {
				view.getComboBoxX().removeItemAt(i);
				view.getComboBoxY().removeItemAt(i);
			}
		}
	}
	
	// method for changing the content of the fileNameTextField field
	public void displayFileName(String name) {
		view.getFileNameTextField().setText(name);
	}
	
	// inner class with the actionPerformed method for open and quit buttons
	class buttonAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == view.getOpenButton()) {
				openFileChooser(); // open file chooser
				removeItemsFromComboBox(); // remove items from the combo boxes if necessary
			}
			else if(event.getSource() == view.getQuitButton()) {
				closeProgram(); // close program
			}
		}
	}
	
	// if users click on a dot they'll be able to see details 
	class detailsOfBondTrade implements MouseListener {
		@Override
		public void mousePressed(MouseEvent event) {
			for(int i = 0; i < model.getSelectedTradeList().size(); i++) {
				if(model.getSelectedTradeList().get(i).contains(event.getPoint())) { // checks whether coordinates of a click are in the array list
					view.getdetailsOfSelectedTradeTextField().setText(model.getBondTradeList().get(i).toString()); // if true display details inside the text field
					model.getSelectedTradeList().clear();
			}
		}
	}
	
	// methods not used
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	}
}