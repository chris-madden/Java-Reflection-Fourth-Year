package ie.gmit.sw.launch;

import java.io.FileNotFoundException;
import java.io.IOException;

import ie.gmit.sw.controller.ClassSet;
import ie.gmit.sw.controller.DatabaseOperations;
import ie.gmit.sw.controller.LoadJarClasses;
import ie.gmit.sw.controller.Loadable;
import ie.gmit.sw.view.UserView;

public class Runner 
{
	
   private Class<?> c;
   
   public Runner(Class<?> c)
   {
	   
       this.c = c;

   }

   public static void main(String args[])throws ClassNotFoundException 
   {
	   
	   if (args.length == 0) {
           System.out.println("Please specify a class name.");
           System.exit(1);
       }
	   
	   // ========================
	   // 1. Load Classes from jar
	   // ========================
	   
	   // Program to an abstraction
	   Loadable loadClasses = new LoadJarClasses();
	   
	   ClassSet setOfClasses = new ClassSet();
	   
	   try {
		     
		   // Read classes from jar file
		   setOfClasses = loadClasses.load(args[0]);
		   
	   } catch (FileNotFoundException e) {
		
			e.printStackTrace();
		
	   } catch (IOException e) {
		
			e.printStackTrace();
		
	   }// End try catch
	   
	   // ===========================
	   // 2. Open database connection
	   // ===========================
	   
	   // open the database file if it exists. 
	   // If not then create a file called database
	   
	   DatabaseOperations dop = new DatabaseOperations();
	   dop.openDB();
	   
	   // ===========================
	   // 3. Build the user interface
	   // ===========================
	   
	   // Pass set of classes to UserView and the object for the database
	   UserView ui = new UserView(setOfClasses, dop);
	   
	   // builds the outer shell 
	   ui.buildInterfaceShell();
	   
	   // ========================================
	   // 4. Allow user to interact with interface
	   // ========================================
	  
	   // when run button is pressed the table fields are populated
	   ui.runButtonPress();
	   
	   // When button is pressed all records are loaded
	   ui.loadDbButton();
	   
	   // When button is pressed save jar details to database
	   ui.saveDbButtonPress();
	   
	   // When button is pressed delete all from database
	   ui.deleteButtonPress();
	   
	   // When button is pressed data in database table is removed
	   ui.clearDbTable();
	   
   }// End main
   
}// End class Runner
