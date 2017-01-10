package ie.gmit.sw.model;

/**
 * 
 * Class represents one record in the database
 *
 */
public class DatabaseRecord
{
	
	// Metadata for jar file
	
	private String nameOfJar;
	private int numOfClasses;
	private int fullStability;
	private int fullUnstability;
	private int inbetweenStabilty;
	
	//  ========================================================
	//  This constructor will represent 1 record in the database
	//  ========================================================
	/**
	 * Constructor will initialize a record for the database
	 * 
	 * @param nameOfJar Pass in string that is name of the jar file
	 * @param numOfClasses Pass in integer that is the number of classes in jar file
	 * @param fullStability Pass in integer that is the number of classes that are fully stable
	 * @param fullUnstability Pass in integer that is the number of classes that are fully unstable
	 * @param inbetweenStabilty Pass in integer that is the number of classes that are neither fully stable or fully unstable
	 */
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
	
	/**
	 * Method to get name of jar
	 * 
	 * @return Returns a string that is name of jar
	 */
	public String getNameOfJar() 
	{
		return nameOfJar;
	}

	/**
	 * Method sets the name of the jar
	 * 
	 * @param nameOfJar Pass in a string
	 */
	public void setNameOfJar(String nameOfJar) 
	{
		this.nameOfJar = nameOfJar;
	}

	/**
	 * Method to get the number of classes in a jar
	 * 
	 * @return Returns an integer 
	 */
	public int getNumOfClasses()
	{
		return numOfClasses;
	}

	/**
	 * Method that sets the number of classes in the jar
	 * 
	 * @param numOfClasses Pass in an integer
	 */
	public void setNumOfClasses(int numOfClasses) 
	{
		this.numOfClasses = numOfClasses;
	}

	/**
	 * Method to get the number of fully stable classes in a jar
	 * 
	 * @return Returns an integer
	 */
	public int getFullStability()
	{
		return fullStability;
	}

	/**
	 * Method that sets the number of fully stable classes in the jar
	 * 
	 * @param fullStability Pass in an integer
	 */
	public void setFullStability(int fullStability) 
	{
		this.fullStability = fullStability;
	}

	/**
	 * Method to get the number of fully unstable classes in a jar
	 * 
	 * @return Returns an integer
	 */
	public int getFullUnstability()
	{
		return fullUnstability;
	}

	/**
	 * Method that sets the number of fully unstable classes in the jar
	 * 
	 * @param fullUnstability Pass in an integer
	 */
	public void setFullUnstability(int fullUnstability) 
	{
		this.fullUnstability = fullUnstability;
	}

	/**
	 * Method to get the number of classes that are neither fully stable or fully unstable
	 * 
	 * @return Returns an integer
	 */
	public int getInbetweenStabilty() 
	{
		return inbetweenStabilty;
	}

	/**
	 * Method that sets the number classes that are neither fully stable or fully unstable
	 * 
	 * @param inbetweenStabilty Pass in an integer
	 */
	public void setInbetweenStabilty(int inbetweenStabilty)
	{
		this.inbetweenStabilty = inbetweenStabilty;
	}
	
}// End class DatabaseRecord
