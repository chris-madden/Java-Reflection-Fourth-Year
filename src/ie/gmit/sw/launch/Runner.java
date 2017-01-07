package ie.gmit.sw.launch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import ie.gmit.sw.controller.JarSet;
import ie.gmit.sw.controller.Measurement;
import ie.gmit.sw.controller.Result;
import ie.gmit.sw.controller.ClassHandler;
import ie.gmit.sw.model.DatabaseRecord;
import ie.gmit.sw.view.UserView;

public class Runner 
{
	
   private Class c;
   private static boolean start = false;
	
   public static void main(String args[])throws FileNotFoundException, IOException, ClassNotFoundException 
   {
	   
	   if (args.length == 0) {
           System.out.println("Please specify a class name.");
           System.exit(1);
       }
	   
	   // ========================
	   // 1. Load Classes from jar
	   // ========================
	   
	   ClassHandler ch = new ClassHandler();
	   
	   JarSet set = new JarSet();
	   
	   try {
		   
		   // Read classes from jar file
		   set = ch.getClassesFromJar(args[0]);
		   
	   } catch (FileNotFoundException e1) {
		
			e1.printStackTrace();
		
	   } catch (IOException e1) {
		
			e1.printStackTrace();
		
	   }// End try catch
	   
	   // ===========================
	   // 2. Build the user interface
	   // ===========================
	   
	   // Pass set of classes to UserView
	   UserView ui = new UserView(set);
	   
	   // builds the outer shell 
	   ui.buildInterfaceShell();
	  
	   // when run button is pressed the table fields are populated
	   //ui.runButtonPress();
	   
	  /* //  ===
	   //  GUI
	   //  ===
	   
	   JFrame frame = new JFrame();
	   
	   // Create panel for buttons and set colour
	   JPanel jpanelButtons = new JPanel();
	   jpanelButtons.setBackground(Color.blue);
	   jpanelButtons.setLayout(new BoxLayout(jpanelButtons, BoxLayout.Y_AXIS));
	   
	   // Create panel for text area
	   JPanel jpanelTextArea = new JPanel();
	   jpanelTextArea.setLayout(new BoxLayout(jpanelTextArea, BoxLayout.Y_AXIS));
	   
	   // Create panel for table
	   JPanel jpanelTable = new JPanel();
	   jpanelTable.setLayout(new BoxLayout(jpanelTable, BoxLayout.Y_AXIS));
	   
	   // Create text area 
	   JTextArea textArea = new JTextArea(10, 10);
	   textArea.setEditable(false);  
	   //textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	   jpanelTextArea.add(textArea);
	   
	   // Create button and add to panel
	   JButton button = new JButton("Run");
	   jpanelButtons.add(button);
	   
	   // Add panel to frame
	   frame.getContentPane().add(BorderLayout.NORTH, jpanelButtons);
	   frame.getContentPane().add(BorderLayout.WEST, jpanelTextArea);
	   
	   
	   frame.setSize(1000, 500);
	   frame.setVisible(true);*/
	   
	  /* // Add action listener to button
	   button.addActionListener(new ActionListener() {

			// When button is clicked
			public void actionPerformed(ActionEvent e)
			{
				
				start = runJar();
				
				System.out.println("Moved and still working");
				
				//  ===============================
			   //  Start application functionality
			   //  ===============================
			  
				   ClassHandler ch = new ClassHandler();
				   
				   JarSet set = new JarSet();
				   
				   // Get classes from jar
				   // set will contain a list of classes that were in the jar
				   try {
					   
					set = ch.getClassesFromJar(args[0]);
					
				} catch (FileNotFoundException e1) {
					
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				} catch (IOException e1) {
					
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
				   
				   System.out.println("Set is: " + set);
				
				   // =======================================================================
				   // Print out Efferent couplings, Afferent couplings and instability result
				   // =======================================================================
				   Measurement measure = new Measurement(set);
				   measure.getCouplings();
				   measure.calculateInstabilities();
				   
				   List<Result> result = measure.getResult();
				   
				   // Initialise table array
				   Object[][] tableData = new Object[result.size()][columnNames.length]; 
				   
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
						
						//System.out.println("==> CLASS: " + in.getCl().getSimpleName() + " --> Ce=" + in.getCe() + ", Ca="
								//+ in.getCa() + ", I=" + in.getI());
						
						System.out.printf("| %14s|  %4.2f|  %4.2f|  %4.3f|\n", instabilityResult.getCl().getSimpleName(), instabilityResult.getCe(), instabilityResult.getCa(), instabilityResult.getI());

						// Print out class names to GUI
						textArea.append(instabilityResult.getCl().getSimpleName() + "\n");
						
				   }// End outer for
				
				   // ======================
				   // Create table with data
				   // ======================
				   JTable jtable = new JTable(tableData, columnNames){
					   
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
				   JScrollPane scrollPane = new JScrollPane(jtable);
				   jpanelTable.add(scrollPane);
				   
				   frame.getContentPane().add(BorderLayout.CENTER, jpanelTable);
				
			}
		    
       }); // End button.addActionListener
*/	   
	  /* // open the database file if it exists. 
	   // If not then create a file called database
	   ObjectContainer db = Db4oEmbedded.openFile("db");
	   
	   // Create new record
	   DatabaseRecord dbRecord = new DatabaseRecord("Test_2", 15, 5, 5, 5);
	   
	   // Save to database
	   db.store(dbRecord);
	   
	   // Retrieve record from database
	   ObjectSet<DatabaseRecord> record = db.query(DatabaseRecord.class);
	   
	   for(DatabaseRecord item : record)
	   {
			System.out.println("Database records => \nJar Name: " + item.getNameOfJar() + ",\nNumber of classes "
					 + item.getNumOfClasses() + ", \n Num. of Stable: " + item.getFullStability() + ",\nNum. fo Instalbe"
					 + item.getFullUnstability() + ",\nNum. of Others: " + item.getInbetweenStabilty());
		}*/
	   
   }// End main

   public Runner(Class c){
      super();
      this.c = c;

     /* printConstructors();
      printFields();
      printMethods();
      createArray();*/
   }

/*   public void printConstructors(){
      Constructor ctorlist[] = c.getDeclaredConstructors();
      System.out.println("--------------" + ctorlist.length + " Constructors --------------");
      for (int i = 0; i < ctorlist.length; i++) {
         Constructor ct = ctorlist[i];
         System.out.println("\tname  = " + ct.getName());
         System.out.println("\tdecl class = " + ct.getDeclaringClass());

         Class pvec[] = ct.getParameterTypes();
         for (int j = 0; j < pvec.length; j++){
            System.out.println("\tparam #" + j + " " + pvec[j]);
         }

         Class evec[] = ct.getExceptionTypes();
         for (int j = 0; j < evec.length; j++){
            System.out.println("\texc #" + j + " " + evec[j]);
         }
         System.out.println("\t-----");
      }
   }

   public void printFields(){
      Field fieldlist[] = c.getDeclaredFields();
      for (int i = 0; i < fieldlist.length; i++) {
         Field fld = fieldlist[i];
         System.out.println("\tname = " + fld.getName());
         System.out.println("\tdecl class = " + fld.getDeclaringClass());
         System.out.println("\ttype = " + fld.getType());
         int mod = fld.getModifiers();
         System.out.println("\tmodifiers = " + Modifier.toString(mod));
         System.out.println("-----");
      }
   }

   public void printMethods(){
      Method methlist[] = c.getDeclaredMethods();
      System.out.println("--------------" + methlist.length + " Methods --------------");
      for (int i = 0; i < methlist.length;i++) {
      	Method m = methlist[i];
      	System.out.println("\tname = " + m.getName());
      	System.out.println("\tdecl class = " + m.getDeclaringClass());
      	Class pvec[] = m.getParameterTypes();
      	for (int j = 0; j < pvec.length; j++){
         		System.out.println("\tparam #" + j + " " + pvec[j]);
    	}
      	Class evec[] = m.getExceptionTypes();
      	for (int j = 0; j < evec.length; j++){
         		System.out.println("\texc #" + j + " " + evec[j]);
      	}
      	System.out.println("\treturn type = " + m.getReturnType());
      	System.out.println("\t-----");
      }
   }

   public void createArray(){
      try {
         Class cls = Class.forName("java.lang.String");
         Object arr = Array.newInstance(cls, 10);
         Array.set(arr, 5, "Msc OO");
         String s = (String)Array.get(arr, 5);
         System.out.println(s);
      }catch (Throwable e) {
         System.err.println(e);
      }
   }*/
   
   /*for (int i = 0; i < set.size(); i++) 
   {
   	
   	System.out.println("Class" + set.get(i).getSimpleName());
   	
   	Efferent ce = new Efferent(set.get(i), set);
   	
   	for(int j = 0; j < ce.getJarSetDependencies().size(); j++)
   	{
   		
   		System.out.println("\t\t\t\tShow me bitch " + ce.getJarSetDependencies().get(j).getSimpleName());
   		
   	}
   	
   	System.out.println("Number of dependencies is " + ce.getJarSetDependencies().size() );
		
	}*/
   
   public static boolean runJar()
   {
	   
	   return true;
	   
   }
   
}// End class Runner
