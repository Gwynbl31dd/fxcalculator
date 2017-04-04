package entries;

import exceptions.FormatException;
import exceptions.NoCurrencyException;
import main.Main;
import utilities.Currency;
/**
 * 
 * @author Anthony Paulin (paulin.anthony@gmail.com)
 * @version 1.0
 * @since 22-03-2017
 * 
 */
public class CheckEntries 
{
	private Double amount;//The amount to calculate
	private Currency ccy1;//The currency of the amount
	private Currency ccy2;//The currency to rate
	
	/**
	 * Check the entries and format the data
	 * @param tab is the entry from the user
	 * @param listCurrency list of the currencies
	 * @throws java.lang.ArrayIndexOutOfBoundsException Exception if size problem with the tab
	 * @throws NoCurrencyException 
	 * @throws FormatException 
	 */
	public CheckEntries(String[] tab) throws java.lang.ArrayIndexOutOfBoundsException, NoCurrencyException, FormatException,java.lang.NumberFormatException
	{
		if(tab.length != 4)
		{
			throw new FormatException();
		}
		setCcy1(tab[0].toUpperCase());
		setCcy2(tab[3].toUpperCase());
		this.amount = Double.valueOf(tab[1]);//Convert the string from the user to a Double (Can Throw a numberFormatException)
		setIn(tab[2].toLowerCase());//Check if in is IN (to keep the format asked for the exercise)
	}
	/**
	 * 
	 * @return the amount to rate
	 */
	public Double getAmount() 
	{
		return amount;
	}
	/**
	 * 
	 * @return the first currency
	 */
	public Currency getCcy1()
	{
		return ccy1;
	}
	/**
	 * 
	 * @param ccy1 the first currency (just the name)
	 * @throws NoCurrencyException Exception if not in the table)
	 */
	private void setCcy1(String ccy1) throws NoCurrencyException
	{
		this.ccy1 = isCurrency(ccy1);//Test in the list
	}
	/**
	 * 
	 * @return the second currency
	 */
	public Currency getCcy2()
	{
		return ccy2;
	}
	/**
	 * 
	 * @param ccy2 the first currency (just the name)
	 * @throws NoCurrencyException Exception if not in the table)
	 */
	private void setCcy2(String ccy2) throws NoCurrencyException
	{	
		this.ccy2 = isCurrency(ccy2);//Test in the list
	}
	/**
	 * 
	 * @param in just a word for the format
	 * @throws FormatException if there is no "in" will throw an exceptions
	 */
	private void setIn(String in) throws FormatException
	{
		if(in.compareTo("in")!=0)
		{
			throw new FormatException();
		}
	}

	/**
	 * Check if the currency asked is in the list
	 * @param currency the currency to check
	 * @throws NoCurrencyException Throw an exceptions if no currency
	 */
	private Currency isCurrency(String currency) throws NoCurrencyException
	{
		for(int i=0;i<Main.getListCurrency().length;i++)
		{
			if(Main.getListCurrency()[i].getName().compareTo(currency)==0)
			{
				return Main.getListCurrency()[i];//Stop, no need to go further
			}
		}
		throw new NoCurrencyException(currency);
	}
}
