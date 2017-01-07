package ie.gmit.sw.model;

public class DatabaseRecord
{
	
	// Metadata for jar file
	
	private String nameOfJar;
	private int numOfClasses;
	private int fullStability;
	private int fullUnstability;
	private int inbetweenStabilty;
	
	//  =====================================================
	//  This constructor will represent 1 record in the database
	//  =====================================================
	public DatabaseRecord(String nameOfJar, int numOfClasses, int fullStability, int fullUnstability, int inbetweenStabilty)
	{
		
		this.nameOfJar = nameOfJar;
		this.numOfClasses = numOfClasses;
		this.fullStability = fullStability;
		this.fullUnstability = fullUnstability;
		this.inbetweenStabilty = inbetweenStabilty;
		
	}

	//  ===================
	//  Getters and Setters
	//  ===================
	
	public String getNameOfJar() 
	{
		return nameOfJar;
	}

	public void setNameOfJar(String nameOfJar) 
	{
		this.nameOfJar = nameOfJar;
	}

	public int getNumOfClasses()
	{
		return numOfClasses;
	}

	public void setNumOfClasses(int numOfClasses) 
	{
		this.numOfClasses = numOfClasses;
	}

	public int getFullStability()
	{
		return fullStability;
	}

	public void setFullStability(int fullStability) 
	{
		this.fullStability = fullStability;
	}

	public int getFullUnstability()
	{
		return fullUnstability;
	}

	public void setFullUnstability(int fullUnstability) 
	{
		this.fullUnstability = fullUnstability;
	}

	public int getInbetweenStabilty() 
	{
		return inbetweenStabilty;
	}

	public void setInbetweenStabilty(int inbetweenStabilty)
	{
		this.inbetweenStabilty = inbetweenStabilty;
	}
	
}// End class DatabaseRecord
