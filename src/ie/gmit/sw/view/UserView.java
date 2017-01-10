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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import ie.gmit.sw.controller.ClassSet;
import ie.gmit.sw.controller.DatabaseOperations;
import ie.gmit.sw.controller.Measurement;
import ie.gmit.sw.controller.Result;
import ie.gmit.sw.controller.SingletonRecord;
import ie.gmit.sw.model.DatabaseRecord;

/**
 * 
 * This class builds the user interface and handles user interaction.
 *
 */
public class UserView
{
	
	private String[] columnNames = {"Class Name","Efferent","Afferent", "Instability"};
	private String[] dbColumnNames = {"Jar Name", "No. of Classes", "No. of Stable", "No. of Unstable", "No. of Inbetween"};
	private Object[][] tableData;
	private List<Result> result;
	private DatabaseOperations dop;
	private int runButtonClick, saveButtonClick;
	
	// ========================
	// User interface variables
	// ========================
	private JFrame frame;
	private JPanel jpanelButtons, jpanelTextArea, jpanelTable;
	private JButton runButton, loadDbButton, saveDbButton, deleteAllButton, clearDbTableButton;
	private JTable jtable, databaseTable;
	private JScrollPane scrollPane, scrollPaneDB;
	private DefaultTableModel model, dbModel; 
	private Label jarLabel, dbLabel, infoLabel;
	
	private ClassSet ClassSet;
	
	/**
	 * Constructor initializes a ClassSet type, initializes the database connection 
	 * and populates the table array
	 * 
	 * @param ClassSetClasses A list of CLasses
	 * @param dop A database connection
	 */
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
	
	/**
	 * Method to build user interface
	 */
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
		
		// ======================================
	    // Create save DB button and add to panel
	    // ======================================
	    deleteAllButton = new JButton("Delete all");
		jpanelButtons.add(deleteAllButton);
		
		// ======================================
	    // Create save DB button and add to panel
	    // ======================================
		clearDbTableButton = new JButton("Clear DB Table");
		jpanelButtons.add(clearDbTableButton);
		
		// ============================
		// Create jar information Label
		// ============================
		infoLabel = new Label();
		infoLabel.setBackground(Color.WHITE);
		infoLabel.setAlignment(Label.CENTER);
		infoLabel.setFont(new Font("Ubuntu Mono", Font.BOLD, 18));
		jpanelButtons.add(infoLabel);
		
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
	
	//  ==============
	//  Button Methods
	//  ==============
	
	// Populate the JTable when clicking run button
	/**
	 * Method handles user interaction with the run button 
	 */
	public void runButtonPress()
	{
		
		// When run button is pressed
		runButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e)
			{
				
				if(runButtonClick == 0)
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
					
				}// End if
				
				runButtonClick++;
				
				// Feedback for user
				infoLabel.setText("Jar Results");
				
			}// End method actionPerformed
			
		});// End addActionListener
		
	}// End method runButton
	
	// Populate database table
	/**
	 * Method handles user interaction with the load database button 
	 */
	public void loadDbButton()
	{
		
		loadDbButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				ObjectSet<DatabaseRecord> record = dop.retrieveAll();
				
				// Create object array with the length of 4
				Object[] row = new Object[dbColumnNames.length];
				
				// Populate database table
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
				
				// Feedback for user
				infoLabel.setText("Database records retrieved");
				
			}// End method actionPerformed
			
		});// End loadDbButton.addActionListener
		
	}// End method loadDbButton()
	
	/**
	 * Method handles user interaction with the save to database button 
	 */
	public void saveDbButtonPress()
	{
		
		saveDbButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) 
			{
			
				// If the program has run the jar file 
				// Can only save record once
				if(runButtonClick == 1 && saveButtonClick < 1)
				{
					
					saveToDb();
					
				}
				
				saveButtonClick++;
				
				// Feedback for user
				infoLabel.setText("New jar results saved to database");
				
			}// End method actionPerformed
			
		});// End saveDbButton.addActionListener
		
	}// End method saveDbButtonPress
	
	/**
	 * Method handles user interaction with the delete database records button
	 */
	public void deleteButtonPress()
	{
		
		deleteAllButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				// Get all records from database
				ObjectSet<DatabaseRecord> record = dop.retrieveAll();
				
				// Retrieve database connection
				ObjectContainer ob = dop.getDb();
				
				for(DatabaseRecord item : record)
				{
					
					ob.delete(item);
					
				}// End for
				
				// Feedback for user
				infoLabel.setText("All records in database deleted");
				
			}// End actionPerformed
			
		});// End deleteAllButton.addActionListener
		
	}// End method deleteButtonPress
	
	/**
	 * Method handles user interaction with the clear database table button
	 */
	public void clearDbTable()
	{
		
		clearDbTableButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) 
			{
				
				// Get the number of rows currently in the table
				int numOfrows = dbModel.getRowCount();
			
				// If there are rows in the table
				if(numOfrows > 0)
				{
					
					dbModel.removeRow(0);
					
				}// End if
				
				// Feedback for user
				infoLabel.setText("1 row removed from database table");
					
			}// End method actionPerformed
			
		});// End clearDbTableButton.addActionListener
		
	}// End method clearDbTable
	
	// ==============
	// Helper methods
	// ==============
	
	// Calculates dependencies within the ClassSet and returns a list of results
	/**
	 * Method gets the results of class couplings and instability score
	 * 
	 * @param classSet Pass in a ClassSet
	 * @return Returns a list of Results
	 */
	private List<Result> getResultList(ClassSet classSet)
	{
		
		Measurement measure = new Measurement(classSet);
	    measure.getCouplings();
	    measure.calculateInstabilities();
	    
	    List<Result> result = measure.getResult();
		
		return result;
		
	}// End method getResultList
	
	/**
	 * Method populates the array for the JTable and collects data needed for
	 * database records
	 */
	private void populateTableArray()
	{
		
		int numOfClasses = 0;
		int numOfFullStability = 0;
		int numOfUnstable = 0;
		int numOfInbetween = 0;
		
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
							numOfClasses++;
					break;
					
					case 1: tableData[i][j] = instabilityResult.getCe();
					break;
					
					case 2: tableData[i][j] = instabilityResult.getCa();
					break;
					
					case 3: tableData[i][j] = instabilityResult.getI();
					
							if(instabilityResult.getI() == 0)
							{
								
								numOfFullStability++;
								
							}
							else if(instabilityResult.getI() == 1)
							{
								
								numOfUnstable++;
								
							}
							else
							{
								
								numOfInbetween++;
								
							}
					break;
					
					default:
				
				}// End switch 
				
				
			}// End inner for 
		   
		}// End outer for
	    
	    
	    // Set data for database record
	    SingletonRecord sRecord = SingletonRecord.getInstance();
	    sRecord.setNumOfClasses(numOfClasses);
	    sRecord.setFullStability(numOfFullStability);
	    sRecord.setFullUnstability(numOfUnstable);
	    sRecord.setInbetweenStabilty(numOfInbetween);
		
	}// End method populateTableArray
	
	/**
	 * Method stores records to database
	 */
	private void saveToDb()
	{
		
		// Get SingletonRecord instance
		SingletonRecord sRecord = SingletonRecord.getInstance();
		
		// Initialize database record with meta data of jar file
		DatabaseRecord record = sRecord.initializeRecord();
		
		// Retrieve database connection
		ObjectContainer ob = dop.getDb();
				 
		//Save to database
		ob.store(record);
		
	}// End method saveToDb
	
}// End class UserView
