package ie.gmit.sw.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Loadable 
{
	
	public JarSet load(String nameOfJar) throws FileNotFoundException, IOException;

}
