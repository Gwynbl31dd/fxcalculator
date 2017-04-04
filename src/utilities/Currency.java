package utilities;
/**
 * 
 * @author Anthony Paulin (paulin.anthony@gmail.com)
 * @version 1.0
 * @since 22-03-2017
 * 
 */
public class Currency 
{
	private int precision;
	private String name;
	
	/**
	 * Create a new currency
	 * @param name Name of the currency
	 * @param precision Precision of the currency
	 */
	public Currency(String name,int precision)
	{
		this.precision = precision;
		this.name = name;
	}
	
	/**
	 * Create a new currency (precision 2 by default)
	 * @param name Name of the currency
	 */
	public Currency(String name)
	{
		this.name = name;
		this.precision = 2;
	}
	/**
	 * 
	 * @return the precision (number of decimal)
	 */
	public int getPrecision() 
	{
		return precision;
	}

	/**
	 * 
	 * @return the name of the currency
	 */
	public String getName() 
	{
		return name;
	}

}
