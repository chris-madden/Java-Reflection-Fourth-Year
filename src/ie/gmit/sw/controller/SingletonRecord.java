package ie.gmit.sw.controller;

import ie.gmit.sw.model.DatabaseRecord;

/*
 * 
 *  Using the Singleton pattern to keep track of the information needed to store data
 *  about the jar file. It's used because the information needed is generated in two different parts
 *  of the code and this pattern allowed the information to be stored when required without having to
 *  alter code.
 * 
 */

/**
 * 
 * This class is used to collect information needed to store a record into the database
 *
 */
public class SingletonRecord 
{
	
	private static SingletonRecord uniqueRecord = new SingletonRecord();
	private DatabaseRecord record;
	
	private String nameOfJar;
	private int numOfClasses;
	private int fullStability;
	private int fullUnstability;
	private int inbetweenStabilty;
	
	/**
	 * Private empty constructor used to create a Singleton pattern
	 */
	private SingletonRecord(){};
	
	/**
	 * Static method which returns a single instance of the class
	 * 
	 * @return Returns a SingleRecord type
	 */
	// Return single instance of SingletonRecord
	public static SingletonRecord getInstance()
	{
		return uniqueRecord;
	}
	
	/**
	 * Method to initialize a database record
	 * 
	 * @return Returns a DatabaseRecord 
	 */
	// Initialize record so it's ready to be saved
	public DatabaseRecord initializeRecord()
	{
		
		record = new DatabaseRecord(nameOfJar, numOfClasses, fullStability, fullUnstability, inbetweenStabilty);
		
		return record;
		
	}// End method dbRecord

	// =======
	// Setters
	// =======
	
	/**
	 * Method gets the number of inbetweenStabilty classes
	 * 
	 * @return Returns an integer 
	 */
	public int getInbetweenStabilty() 
	{
		return inbetweenStabilty;
	}

	/**
	 * Method sets the number of inbetweenStabilty classes
	 * 
	 * @param inbetweenStabilty Pass in the number of inbetweenStabilty classes
	 */
	public void setInbetweenStabilty(int inbetweenStabilty)
	{
		this.inbetweenStabilty = inbetweenStabilty;
	}
	
	/**
	 * Method gets the name of jar file
	 * 
	 * @return Returns a string
	 */
	public String getNameOfJar()
	{
		return this.nameOfJar;
	}

	/**
	 * Method sets the name of the jar
	 * 
	 * @param nameOfJar Pass in the name of a jar 
	 */
	public void setNameOfJar(String nameOfJar)
	{
		this.nameOfJar = nameOfJar;
	}

	/**
	 * Method gets the number of classes in jar
	 * 
	 * @param numOfClasses Pass in integer of number of classes
	 */
	public void setNumOfClasses(int numOfClasses) 
	{
		this.numOfClasses = numOfClasses;
	}

	/**
	 * Method gets the number of classes that are fully stable
	 * 
	 * @param fullStability Pass in integer of number of classes that are fully stable
	 */
	public void setFullStability(int fullStability)
	{
		this.fullStability = fullStability;
	}

	/**
	 * Method gets the number of classes that are fully unstable
	 * 
	 * @param fullUnstability Pass in integer of number of classes that are fully unstable
	 */
	public void setFullUnstability(int fullUnstability) 
	{
		this.fullUnstability = fullUnstability;
	}

}// End class PopulateRecord
