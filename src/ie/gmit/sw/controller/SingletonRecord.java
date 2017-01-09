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

public class SingletonRecord 
{
	
	private static SingletonRecord uniqueRecord = new SingletonRecord();
	private DatabaseRecord record;
	
	private String nameOfJar;
	private int numOfClasses;
	private int fullStability;
	private int fullUnstability;
	private int inbetweenStabilty;
	
	private SingletonRecord(){};
	
	// Return single instance of SingletonRecord
	public static SingletonRecord getInstance()
	{
		
		return uniqueRecord;
	}
	
	// Initialize record so it's ready to be saved
	public DatabaseRecord initializeRecord()
	{
		
		record = new DatabaseRecord(nameOfJar, numOfClasses, fullStability, fullUnstability, inbetweenStabilty);
		
		return record;
		
	}// End method dbRecord

	// =======
	// Setters
	// =======
	
	public int getInbetweenStabilty() 
	{
		return inbetweenStabilty;
	}

	public void setInbetweenStabilty(int inbetweenStabilty)
	{
		this.inbetweenStabilty = inbetweenStabilty;
	}
	
	public String getNameOfJar()
	{
		
		return this.nameOfJar;
		
	}

	public void setNameOfJar(String nameOfJar)
	{
		this.nameOfJar = nameOfJar;
	}

	public void setNumOfClasses(int numOfClasses) 
	{
		this.numOfClasses = numOfClasses;
	}

	public void setFullStability(int fullStability)
	{
		this.fullStability = fullStability;
	}

	public void setFullUnstability(int fullUnstability) 
	{
		this.fullUnstability = fullUnstability;
	}

}// End class PopulateRecord
