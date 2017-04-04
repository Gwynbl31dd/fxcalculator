package exceptions;
/**
 * 
 * @author Anthony Paulin (paulin.anthony@gmail.com)
 * @version 1.0
 * @since 22-03-2017
 * 
 */
@SuppressWarnings("serial")
public class NoCurrencyException extends Exception
{
	String currencyName;
	/**
	 * 
	 * @param currencyName the name of the currency not found
	 */
	public NoCurrencyException(String currencyName)
	{
		this.currencyName = currencyName;
	}

	public String toString()
	{
		return "Currency "+currencyName+" not defined";//Get the name of the currency not defined
	}
}
