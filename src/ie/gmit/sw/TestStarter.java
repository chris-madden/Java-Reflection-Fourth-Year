package ie.gmit.sw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class TestStarter {

	public static void main(String[] args) throws FileNotFoundException, IOException 
	{
		
		// Need validation in case file does not exist
		// Put into a method as a loader
				
		JarInputStream in  = new JarInputStream(new FileInputStream(new File("testJar.jar"))); 
	
		JarEntry next = in.getNextJarEntry(); 
		
		while (next != null) 
		{ 
			
			if (next.getName().endsWith(".class")) 
			{
				
				String name = next.getName().replaceAll("/", "\\.");
				
				name = name.replaceAll(".class", "");
				
				if (!name.contains("$")) 
					name.substring(0, name.length() - ".class".length()); 
				
				// Store class name in a data structure
				System.out.println(name);
				
			}// End if 
			
			next = in.getNextJarEntry();
		
		}// End while 
		
		try 
		{
			// Need to be able to loop through the classes 
			// Therefore need to extract package name as class names are retrieved above
			Class cls = Class.forName("ie.gmit.sw.ClassD");
			
			Package pack = cls.getPackage(); //Get the package 
			boolean iface = cls.isInterface(); //Is it an interface? 
			Class[] interfaces = cls.getInterfaces(); //Get the set of interfaces it implements
			Constructor[] cons = cls.getConstructors(); //Get the set of constructors 
			Class[] params = cons[0].getParameterTypes(); //Get the parameters *****  Might cause an issue later  *****
			
			System.out.println(pack.getName());
			System.out.println("********** " + cls.getName() + " **********");
			System.out.println("********** " + cons[0] + " **********");
			System.out.println("********** " + params.length + " **********");

			
		} 
		catch (ClassNotFoundException e)
		{
			
			e.printStackTrace();
		} 
		
	}// End main

}// End 
