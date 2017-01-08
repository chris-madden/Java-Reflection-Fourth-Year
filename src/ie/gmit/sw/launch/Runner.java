package ie.gmit.sw.launch;

import java.io.FileNotFoundException;
import java.io.IOException;
import ie.gmit.sw.controller.ClassSet;
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
	   // 2. Build the user interface
	   // ===========================
	   
	   // Pass set of classes to UserView
	   UserView ui = new UserView(setOfClasses);
	   
	   // builds the outer shell 
	   ui.buildInterfaceShell();
	  
	   // when run button is pressed the table fields are populated
	   ui.runButtonPress();
	   
	   // ====================
	   // Simple Database Code
	   // ====================
   
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
   
}// End class Runner
