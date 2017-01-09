package ie.gmit.sw.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Label;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.db4o.ObjectSet;

import ie.gmit.sw.controller.ClassSet;
import ie.gmit.sw.controller.DatabaseOperations;
import ie.gmit.sw.controller.Measurement;
import ie.gmit.sw.controller.Result;
import ie.gmit.sw.model.DatabaseRecord;

public class UserView
{
	
	private String[] columnNames = {"Class Name","Efferent","Afferent", "Instability"};
	private String[] dbColumnNames = {"Jar Name", "No. of Classes", "No. of Stable", "No. of Unstable", "No. of Inbetween"};
	private Object[][] tableData;
	private List<Result> result;
	private DatabaseOperations dop;
	
	private JFrame frame;
	private JPanel jpanelButtons, jpanelTextArea, jpanelTable;
	private JTextArea textArea;
	private JButton runButton, loadDbButton, saveDbButton;
	private JTable jtable, databaseTable;
	private JScrollPane scrollPane, scrollPaneDB;
	private DefaultTableModel model, dbModel; 
	private Label jarLabel, dbLabel;
	
	private ClassSet ClassSet;
	
	public UserView(ClassSet ClassSetClasses, DatabaseOperations dop) 
	{
		
		// Initialize UI ClassSet
		this.ClassSet = ClassSetClasses;
		
		// Initialize opened database 
		this.dop = dop;
		
		// will return list of results of dependencies for each class in jar file
		result = getResultList(ClassSet);
		
		// Populate table array
		populateTableArray();
		
	}
	
	public void buildInterfaceShell()
	{
		
		// =======================
		// Create frame with title
		// =======================
		
		frame = new JFrame("Dependency Checker");
		
		// ========================
	    // Create panel for buttons 
		// ========================
		
	    jpanelButtons = new JPanel();
	    jpanelButtons.setLayout(new BoxLayout(jpanelButtons, BoxLayout.X_AXIS));
	   
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
	    /*textArea = new JTextArea(10, 10);
	    textArea.setEditable(false);  
	    jpanelTextArea.add(textArea);*/
	   
	    // ==================================
	    // Create run button and add to panel
	    // =================================
	    runButton = new JButton("Run");
		jpanelButtons.add(runButton);
		
		// ======================================
	    // Create load DB button and add to panel
	    // ======================================
	    loadDbButton = new JButton("Load DB");
		jpanelButtons.add(loadDbButton);
		
		// ======================================
	    // Create save DB button and add to panel
	    // ======================================
	    saveDbButton = new JButton("Save to DB");
		jpanelButtons.add(saveDbButton);
		
		// ============================
		// Create jar information Label
		// ============================
		
		jarLabel = new Label();
		jarLabel.setText("Jar Information");
		jarLabel.setAlignment(Label.CENTER);
		jarLabel.setBackground(Color.gray);
		jarLabel.setForeground(Color.white);
		jpanelTable.add(jarLabel);
		
		// =============
		// Create JTable
		// =============
		model = new DefaultTableModel(columnNames, 0);
		
		jtable = new JTable(model){
			   
			private static final long serialVersionUID = 3344805599300020343L;

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
		
		// ===============
		// Create DB Label
		// ===============
		
		dbLabel = new Label();
		dbLabel.setText("Database Information");
		dbLabel.setAlignment(Label.CENTER);
		dbLabel.setBackground(Color.gray);
		dbLabel.setForeground(Color.white);
		jpanelTable.add(dbLabel);
		
		// ==============
		// Create dbTable
		// ==============
		
		dbModel = new DefaultTableModel(dbColumnNames, 0);
		databaseTable = new JTable(dbModel);
		jpanelTable.add(databaseTable);
		
		// ================================
		// Add ScrollPane to database table
		// ================================
		
		scrollPaneDB = new JScrollPane(databaseTable);
		jpanelTable.add(scrollPaneDB);
		
		// ===================
		// Add panels to frame
		// ===================
		
	    frame.getContentPane().add(BorderLayout.NORTH, jpanelButtons);
	    //frame.getContentPane().add(BorderLayout.WEST, jpanelTextArea);
	    frame.getContentPane().add(BorderLayout.CENTER, jpanelTable);
	    
	    // =============================
	    // Set frame size and visibility
	    // =============================
	    
	    frame.setSize(1000, 750);
	    frame.setVisible(true);
		
	}// End method buildInterface
	
	// Populate the JTable when clicking run button
	public void runButtonPress()
	{
		
		// When run button is pressed
		runButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e)
			{
				
				// Create object array with the length of 4
				Object[] row = new Object[columnNames.length];
				
				// Loop through table data array
				for(int i = 0; i < tableData.length; i++)
				{
					
					for(int j = 0; j < columnNames.length; j++)
					{
						
						// Store a row from table data into a single dimension array
						row[j] = tableData[i][j];
						
					}// End inner for
					
					// Add row to table
					model.addRow(row);
					
				}// End outer array
				
			}// End method actionPerformed
			
		});// End addActionListener
		
	}// End method runButton
	
	// Populate database table
	public void loadDbButton()
	{
		
		loadDbButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				ObjectSet<DatabaseRecord> record = dop.retrieveAll();
				
				// Create object array with the length of 4
				Object[] row = new Object[dbColumnNames.length];
				
				for(DatabaseRecord item : record)
				{
					
					Object tableItem0 = item.getNameOfJar();
					row[0] = tableItem0;
					Object tableItem1 = item.getNumOfClasses();
					row[1] = tableItem1;
					Object tableItem2 = item.getFullStability();
					row[2] = tableItem2;
					Object tableItem3 = item.getFullUnstability();
					row[3] = tableItem3;
					Object tableItem4 = item.getInbetweenStabilty();
					row[4] = tableItem4;
					
					dbModel.addRow(row);
				
				}// End inner for
				
			}// End method actionPerformed
			
		});// End loadDbButton.addActionListener
		
	}// End method loadDbButton()
	
	// ==============
	// Helper methods
	// ==============
	
	// Calculates dependencies within the ClassSet and returns a list of results
	private List<Result> getResultList(ClassSet js)
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
				
			}// End inner for 
		   
		}// End outer for
		
	}// End method populateTableArray
	
}// End class UserView
