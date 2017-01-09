package ie.gmit.sw.controller;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 *   This class contains a LinkedList of type Class. The LinkedList methods are delegated.  
 * 
 */
public class ClassSet
{
	
	List<Class<?>> list = new LinkedList<Class<?>>();

	/**
	 * This method is used to find the number of classes in the list
	 * 
	 * @return The size of the list is returned as an integer
	 */
	public int size()
	{
		return list.size();
	}

	/**
	 * This method is used to check is the list is empty
	 * 
	 * @return A boolean returns true is list is empty
	 */
	public boolean isEmpty()
	{
		return list.isEmpty();
	}

	/**
	 * 
	 * @param cls A type Class is passed into the method 
	 * @return A boolean returns true if the class is contained within the list
	 */
	public boolean contains(Class<?> cls) 
	{
		return list.contains(cls);
	}

	/**
	 * This method is used to add a class to the list
	 * 
	 * @param e A type class is passed into the method
	 * @return A boolean returns true if class is added to the list
	 */
	public boolean add(Class<?> e) 
	{
		return list.add(e);
	}

	/**
	 * This method returns a class at a given index
	 * 
	 * @param index This is an integer which will signal which element of the list should be returned
	 * @return A class from the list is returned 
	 */
	public Class<?> get(int index)
	{
		return list.get(index);
	}
	
}// End class JarSet
