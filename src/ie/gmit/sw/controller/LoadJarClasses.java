package ie.gmit.sw.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import ie.gmit.sw.launch.Runner;

/*
 * 
 *  This particular concrete class is part of the strategy pattern and is used to load classes 
 *  from a jar file
 * 
 */

public class LoadJarClasses implements Loadable 
{
	
	private ClassSet list;
	private File jar;

	public ClassSet load(String nameOfJar) throws FileNotFoundException, IOException 
	{
		
		// List with classes from Jar file
		   this.list = new ClassSet();
		  
		   // New file object
		   jar = new File(nameOfJar);
		   
		   // Pass in file
		   JarInputStream in  = new JarInputStream(new FileInputStream(jar)); 
		   
		   // Get instance of SingletonRecord
		   SingletonRecord sRecord = SingletonRecord.getInstance();
		   
		   // Set name of jar
		   sRecord.setNameOfJar(jar.getName());
			
		   JarEntry next = in.getNextJarEntry(); 
		   
		   while(next != null)
		   {
			   
			   if (next.getName().endsWith(".class")) 
				{
					
					String name = next.getName().replaceAll("/", "\\.");
					
					name = name.replaceAll(".class", "");
					
					if (!name.contains("$")) 
						name.substring(0, name.length() - ".class".length()); 
					
					Class<?> queryClass;
					 
					try {
						
						queryClass = Class.forName(name);
						
						list.add(queryClass);
						 
						new Runner(queryClass);
						
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					
					// Store class name in a data structure
					//System.out.println(name);
					
				}// End if 
				
				next = in.getNextJarEntry();
			  
		   }
		
		   in.close();
		   
		   return list;
		
	}// End load

}// End LoadJarClasses
