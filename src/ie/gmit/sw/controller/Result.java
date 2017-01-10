package ie.gmit.sw.controller;

/**
 *
 *  This class is used to store the results for the instability score
 *
 */
public class Result 
{
	
	private Class<?> cls;
	private double ceScore;
	private double caScore;
	private double instabilityScore;

	/**
	 * The constructor is used to initialize the Result type
	 * 
	 * @param cl The class that is being measured
	 * @param ce The number of efferent couplings
	 * @param ca The number of afferent couplings
	 * @param i The instability score
	 */
	public Result(Class<?> cl, double ce, double ca, double i) {
		super();
		this.cls = cl;
		this.ceScore = ce;
		this.caScore = ca;
		this.instabilityScore = i;
	}

	/**
	 * Method returns a type Class
	 * 
	 * @return The class being measured
	 */
	public Class<?> getCl()
	{
		return cls;
	}

	/**
	 * Method initializes class 
	 * 
	 * @param cl Pass in a class type
	 */
	public void setCl(Class<?> cl)
	{
		this.cls = cl;
	}

	/**
	 * Method returns a double
	 * 
	 * @return Returns the efferent score of the class
	 */
	public double getCe() 
	{
		return ceScore;
	}

	/**
	 * Method initializes efferent score
	 * 
	 * @param ce Pass in the efferent score
	 */
	public void setCe(double ce)
	{
		this.ceScore = ce;
	}

	/**
	 * Method returns a double
	 * 
	 * @return Returns the Afferent score of the class
	 */
	public double getCa()
	{
		return caScore;
	}

	/**
	 * Method initializes afferent score
	 * 
	 * @param ca Pass in the afferent score
	 */
	public void setCa(double ca)
	{
		this.caScore = ca;
	}

	/**
	 * Method returns a double
	 * 
	 * @return Returns the instability score of the class
	 */
	public double getI()
	{
		return instabilityScore;
	}

	/**
	 * Method initializes instability score
	 * 
	 * @param i Pass in the instability score
	 */
	public void setI(double i)
	{
		this.instabilityScore = i;
	}
		
}// End class result
