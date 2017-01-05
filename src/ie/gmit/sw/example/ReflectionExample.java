package ie.gmit.sw.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

import ie.gmit.sw.jarcontainer.ClassHandler;
import ie.gmit.sw.jarcontainer.Efferent;
import ie.gmit.sw.jarcontainer.JarSet;
import ie.gmit.sw.jarcontainer.Measurement;
import ie.gmit.sw.jarcontainer.Result;

public class ReflectionExample 
{
	
   private Class c;
	
   public static void main(String args[])throws FileNotFoundException, IOException, ClassNotFoundException 
   {
	   
	   if (args.length == 0) {
           System.out.println("Please specify a class name.");
           System.exit(1);
       }
	   
	   //  ===
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
	   
	   //  Create table
	   JTable jtable = new JTable(10, 4);
	   jpanelTable.add(jtable);
	   
	   
	   // Create text area 
	   JTextArea textArea = new JTextArea(10, 10);
	   textArea.setEditable(false);  
	   textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	   jpanelTextArea.add(textArea);
	   
	   // Create button and add to panel
	   JButton button = new JButton("Run");
	   jpanelButtons.add(button);
	   
	   // Add panel to frame
	   frame.getContentPane().add(BorderLayout.NORTH, jpanelButtons);
	   frame.getContentPane().add(BorderLayout.WEST, jpanelTextArea);
	   frame.getContentPane().add(BorderLayout.CENTER, jpanelTable);
	   
	   frame.setSize(500, 500);
	   frame.setVisible(true);
	   
	   //  ===============================
	   //  Start application functionality
	   //  ===============================
	   
	   ClassHandler ch = new ClassHandler();
	   
	   JarSet set = new JarSet();
	   
	   // Get classes from jar
	   set = ch.getClasses(args[0]);
	   
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
	   
	   // =======================================================================
	   // Print out Efferent couplings, Afferent couplings and instability result
	   // =======================================================================
	   Measurement measure = new Measurement(set);
	   measure.getCouplings();
	   measure.calculateInstabilities();
	   
	   List<Result> result = measure.getResult();
	   
	   for(int i = 0; i < result.size(); i++)
	   {
			Result instabilityResult = result.get(i);
			
			//System.out.println("==> CLASS: " + in.getCl().getSimpleName() + " --> Ce=" + in.getCe() + ", Ca="
					//+ in.getCa() + ", I=" + in.getI());
			System.out.printf("| %14s|  %4.2f|  %4.2f|  %4.3f|\n", instabilityResult.getCl().getSimpleName(), instabilityResult.getCe(), instabilityResult.getCa(), instabilityResult.getI());

			// Print out class names to GUI
			textArea.append(instabilityResult.getCl().getSimpleName() + "\n");
	   }

   }

   public ReflectionExample(Class c){
      super();
      this.c = c;

     /* printConstructors();
      printFields();
      printMethods();
      createArray();*/
   }

   public void printConstructors(){
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
   }
}
