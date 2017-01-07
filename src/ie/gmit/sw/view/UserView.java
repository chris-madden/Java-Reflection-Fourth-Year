package ie.gmit.sw.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

import ie.gmit.sw.controller.JarSet;
import ie.gmit.sw.controller.Measurement;
import ie.gmit.sw.controller.Result;

public class UserView
{
	
	// Column names for jtable
	private String[] columnNames = {"Class Name","Efferent","Afferent", "Instability"};
	private Object[][] tableData;
	private List<Result> result;
	
	private JFrame frame;
	private JPanel jpanelButtons, jpanelTextArea, jpanelTable;
	private JTextArea textArea;
	private JButton runButton;
	private JTable jtable;
	private JScrollPane scrollPane;
	
	private JarSet jarSet;
	
	public UserView(JarSet jarSetClasses) 
	{
		
		// Initialize UI JarSet
		this.jarSet = jarSetClasses;
		
		// will return list of results of dependencies for each class in jar file
		result = getResultList(jarSet);
		
		// Populate table array
		populateTableArray();
		
	}
	
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
		
		// =============
		// Create jtable
		// =============
		
		jtable = new JTable(tableData, columnNames){
			   
		     public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
		    	
		        Component comp = super.prepareRenderer(renderer, row, col);
		        
		        Object value = getModel().getValueAt(row, col);
		   
		        // Add color to cells depending on their stability
	            if (value.equals(Double.valueOf(1)) && col == 3)
	            {
	                comp.setBackground(Color.red);
	                
	            }
	            else if(value.equals(Double.valueOf(0)) && col == 3)
	            {
	                comp.setBackground(Color.green);
	                
	            } else 
	            {
	                comp.setBackground(Color.white);
	            }
	            
		        return comp;
		        
		    }// End prepareRenderer
		};
		jpanelTable.add(jtable);
		
		// =======================
		// Add ScrollPane to table
		// =======================
		
		scrollPane = new JScrollPane(jtable);
		jpanelTable.add(scrollPane);
		
		// ===================
		// Add panels to frame
		// ===================
		
	    frame.getContentPane().add(BorderLayout.NORTH, jpanelButtons);
	    frame.getContentPane().add(BorderLayout.WEST, jpanelTextArea);
	    frame.getContentPane().add(BorderLayout.CENTER, jpanelTable);
	    
	    // =============================
	    // Set frame size and visibility
	    // =============================
	    
	    frame.setSize(1000, 500);
	    frame.setVisible(true);
		
	}// End method buildInterface
	
	/*public void runButtonPress()
	{
		
		// When run button is pressed
		runButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Run button pressed");
				
				jtable = new JTable(tableData, columnNames){
					   
				     public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				    	
				        Component comp = super.prepareRenderer(renderer, row, col);
				        
				        Object value = getModel().getValueAt(row, col);
				   
				        // Add colour to cells depending on their stability
			            if (value.equals(Double.valueOf(1)) && col == 3)
			            {
			                comp.setBackground(Color.red);
			                
			            }
			            else if(value.equals(Double.valueOf(0)) && col == 3)
			            {
			                comp.setBackground(Color.green);
			                
			            } else 
			            {
			                comp.setBackground(Color.white);
			            }
			            
				        return comp;
				        
				    }// End prepareRenderer
				};
				
			   jpanelTable.add(jtable);
			   
			   // ScrollPane
			   scrollPane = new JScrollPane(jtable);
			   jpanelTable.add(scrollPane);
			   
			   frame.getContentPane().add(BorderLayout.CENTER, jpanelTable);
			 
			 }// End actionPerformed
			
		});// End addActionListener
		
	}// End method runButton
*/	
	// Calculates dependencies within the jarset and returns a list of results
	private List<Result> getResultList(JarSet js)
	{
		
		Measurement measure = new Measurement(js);
	    measure.getCouplings();
	    measure.calculateInstabilities();
	    
	    List<Result> result = measure.getResult();
		
		return result;
		
	}// End method getResultList
	
	private void populateTableArray()
	{
		
		 // Initialize table array
	    tableData = new Object[result.size()][columnNames.length]; 
	   
	    for(int i = 0; i < result.size(); i++)
	    {
	    	
			Result instabilityResult = result.get(i);
			
			// Populate table array
			for(int j = 0; j < 4; j++)
			{
				
				switch(j)
				{
				
					case 0: tableData[i][j] = instabilityResult.getCl().getSimpleName();
					break;
					
					case 1: tableData[i][j] = instabilityResult.getCe();
					break;
					
					case 2: tableData[i][j] = instabilityResult.getCa();
					break;
					
					case 3: tableData[i][j] = instabilityResult.getI();
					break;
					
					default:
				
				}// End switch 
				
				System.out.println(tableData[i][j]);
				
			}// End inner for 
		   
		}// End outer for
		
	}// End method populateTableArray
	
}// End class UserView
