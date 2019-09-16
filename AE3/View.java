import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class View extends JFrame {
	
	private Model model;
	private GraphComponent graph;
	
	// main window and swing components
	private JFrame mainWindow;
	private JPanel northPanel, southPanel;
	private JTextField fileNameTextField, detailsOfSelectedTradeTextField;
	private JButton openButton, quitButton;
	private static JComboBox<String> comboBoxX, comboBoxY;
	
	// constructor
	public View (Model m, GraphComponent g) {
		model = m;
	    graph = g;
	    
		// create main window
		mainWindow = new JFrame("UBS Scatter Plot"); // create frame with a name
		mainWindow.getContentPane(); // create container in the main window
		mainWindow.setLayout(new BorderLayout()); // main window's layout manager
		
		// add panels into the pane container of the main window
		mainWindow.add(northPanel(), BorderLayout.NORTH);
		mainWindow.add(graph, BorderLayout.CENTER);
		mainWindow.add(southPanel(), BorderLayout.SOUTH);
		
		mainWindow.setSize(625, 648); // set size of the main window
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setVisible(true); // make the main window visible
	}	
	
	public Component northPanel() {
		// create JPanel
		northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout()); // panel layout 
		northPanel.setPreferredSize(new Dimension(0,40)); // panel dimensions
		northPanel.setBackground(Color.LIGHT_GRAY); // panel colour
		
		// create swing components
		openButton = new JButton("Open");
		quitButton = new JButton("Quit");
		
		fileNameTextField = new JTextField(30);
		fileNameTextField.setText("<Name of file>");
		fileNameTextField.setHorizontalAlignment(JTextField.CENTER);
		fileNameTextField.setEditable(false);
		fileNameTextField.setBackground(Color.WHITE);
		
		// add swing components into the panel
		northPanel.add(openButton);
		northPanel.add(fileNameTextField);
		northPanel.add(quitButton);
		
		return northPanel;
	}

	public Component southPanel() {
		// create JPanel
		southPanel = new JPanel();
		southPanel.setBackground(Color.LIGHT_GRAY); // panel dimensions
		southPanel.setLayout(new FlowLayout()); // panel layout
		southPanel.setPreferredSize(new Dimension(0,70)); // panel colour 
		
		// create swing components
		detailsOfSelectedTradeTextField = new JTextField(50);
		detailsOfSelectedTradeTextField.setText("Click a point for more info");
		detailsOfSelectedTradeTextField.setHorizontalAlignment(JTextField.CENTER);
		detailsOfSelectedTradeTextField.setEditable(false);
		detailsOfSelectedTradeTextField.setBackground(Color.WHITE);
		comboBoxX = new JComboBox<String>();
		comboBoxY = new JComboBox<String>();

		// add components into the panel
		southPanel.add(detailsOfSelectedTradeTextField);
		southPanel.add(comboBoxX);
		southPanel.add(comboBoxY);
		
		return southPanel;
	}

	public JTextField getFileNameTextField() {
		return fileNameTextField;
	}

	public JTextField getdetailsOfSelectedTradeTextField() {
		return detailsOfSelectedTradeTextField;
	}

	public JButton getOpenButton() {
		return openButton;
	}

	public void setOpenButton(JButton openButton) {
		this.openButton = openButton;
	}

	public JButton getQuitButton() {
		return quitButton;
	}

	public void setQuitButton(JButton quitButton) {
		this.quitButton = quitButton;
	}
	
	public JFrame getMainWindow() {
		return mainWindow;
	}

	public static JComboBox<String> getComboBoxY() {
		return comboBoxY;
	}

	public static JComboBox<String> getComboBoxX() {
		return comboBoxX;
	}
	
	// clicks and action listeners below
	public void graphMouseListener(MouseListener bondTradeDetails) {
		graph.addMouseListener(bondTradeDetails);
	}
	
	public void buttonActionListener(ActionListener event) {
		openButton.addActionListener(event);
		quitButton.addActionListener(event);
	}
}