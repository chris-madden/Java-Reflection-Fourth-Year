package ie.gmit.sw.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class UserView
{
	
	// Column names for jtable
	private String[] columnNames = {"Class Name","Efferent","Afferent", "Instability"};
	
	private JFrame frame;
	private JPanel jpanelButtons, jpanelTextArea, jpanelTable;
	private JTextArea textArea;
	private JButton runButton;
	
	public void buildInterfaceShell()
	{
		
		// =======================
		// Create frame with title
		// =======================
		
		frame = new JFrame("Dependency Checker");
		
		// ======================================
	    // Create panel for buttons and set color
		// ======================================
		
	    jpanelButtons = new JPanel();
	    jpanelButtons.setBackground(Color.blue);
	    jpanelButtons.setLayout(new BoxLayout(jpanelButtons, BoxLayout.Y_AXIS));
	   
	    // ==========================
	 	// Create panel for text area
	    // ==========================
	    
		jpanelTextArea = new JPanel();
		jpanelTextArea.setLayout(new BoxLayout(jpanelTextArea, BoxLayout.Y_AXIS));
		
		// ======================
		// Create panel for table
		// ======================
		
		jpanelTable = new JPanel();
		jpanelTable.setLayout(new BoxLayout(jpanelTable, BoxLayout.Y_AXIS));
		
		// ================
		// Create text area
		// ================
	    textArea = new JTextArea(10, 10);
	    textArea.setEditable(false);  
	    jpanelTextArea.add(textArea);
	   
	    // ==============================
	    // Create button and add to panel
	    // ==============================
	    runButton = new JButton("Run");
		jpanelButtons.add(runButton);
		
		// ===================
		// Add panels to frame
		// ===================
		
	    frame.getContentPane().add(BorderLayout.NORTH, jpanelButtons);
	    frame.getContentPane().add(BorderLayout.WEST, jpanelTextArea);
	   
	    // =============================
	    // Set frame size and visibility
	    // =============================
	    
	    frame.setSize(1000, 500);
	    frame.setVisible(true);
		
	}// End method buildInterface
	
	public void runButton()
	{
		
		// When run button is pressed
		runButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Run button pressed");
				
			}
		});
		
	}// End method runButton

}// End class UserView
