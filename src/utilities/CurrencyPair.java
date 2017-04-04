package utilities;
/**
 * 
 * @author Anthony Paulin (paulin.anthony@gmail.com)
 * @version 1.0
 * @since 22-03-2017
 * 
 */
public class CurrencyPair 
{
	private Currency base;
	private Currency terms;
	private double rate;
	
	/***
	 * Create a new pair of currencies
	 * @param base currency egal to 1 compare to rate
	 * @param terms currency compared to base
	 * @param rate 
	 */
	public CurrencyPair(Currency base,Currency terms,double rate)
	{
		this.base = base;
		this.terms = terms;
		this.rate = rate;
	}
	/**
	 * 
	 * @return the base saved
	 */
	public Currency getBase() 
	{
		return base;
	}
	/**
	 * 
	 * @return the terms
	 */
	public Currency getTerms() 
	{
		return terms;
	}
	/**
	 * 
	 * @return the rate to convert
	 */
	public double getRate() 
	{
		return rate;
	}
}
