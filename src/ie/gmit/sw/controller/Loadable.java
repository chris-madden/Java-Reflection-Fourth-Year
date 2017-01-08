package ie.gmit.sw.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * 
 *  Using the strategy pattern, classes can be read from alternative sources by creating a new concrete class  
 *  for that particular source, E.G Ordinary zip archive
 * 
 */

public interface Loadable 
{
	
	// Returns ClassSet which is a list of Classes
	public ClassSet load(String nameOfJar) throws FileNotFoundException, IOException;

}
