package calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import main.Main;
/**
 * 
 * @author Anthony Paulin (paulin.anthony@gmail.com)
 * @version 1.0
 * @since 22-03-2017
 * 
 */
public final class RateCalculator 
{
	/**
	 * 
	 * @param base the base to check
	 * @param terms the terms to check
	 * @param amount the amount to convert
	 * @return The converted amount from base to terms
	 */
	public static Double calculator(String base,String terms,Double amount)
	{
		if(base.compareTo(terms)==0)
		{
			return amount;
		}
		else
		{
			String termsObjective = new String(terms);
			String baseObjective = new String(base);
			return checkInTab(base,terms,amount,baseObjective,termsObjective,true);
		}
	}
	
	/**
	 * 
	 * @param base base to check
	 * @param terms terms to check
	 * @param amount amount to convert (Can change multiple time with crossed conversion
	 * @param baseObjective Keep in memory the objective to rate
	 * @param termsObjective Keep in memory the objective to rate
	 * @param first Change if a crossing between numbers was done.
	 * @return the converted amount from base to terms, can be -1 if a problem occurred.(ex : Bad value in the tab)
	 */
	private static Double checkInTab(String base,String terms,Double amount,String baseObjective,String termsObjective,boolean first)
	{
		/*System.out.println("base : "+base);
		System.out.println("terms : "+terms);
		System.out.println("First : "+first);
		System.out.println("amount : "+amount);
		System.out.println("----------------------");*/
		//Check if last calculation was a crossed.
		if(first != true)
		{
			if(base.compareTo(termsObjective) != 0  && terms.compareTo(termsObjective) != 0)//If replace the terms
			{
				if(base.compareTo(baseObjective) != 0)//If base = first
				{
					terms = termsObjective;
				}
				else
				{
					base = termsObjective;
				}
			}
			else if(base.compareTo(baseObjective) != 0  && terms.compareTo(baseObjective) != 0) //If replaced the base
			{
				if(base.compareTo(termsObjective) != 0)//If base = first
				{
					terms = baseObjective;
				}
				else
				{
					base = termsObjective;
				}
			}
		}
				
		//Check the tab for the first time, this is the best case
		for(int i = 0;i<Main.getListCurrencyPair().length;i++)
		{
			//If it's direct, we convert and return the amount
			if(Main.getListCurrencyPair()[i].getBase().getName().compareTo(base)==0 && Main.getListCurrencyPair()[i].getTerms().getName().compareTo(terms)==0)
			{
				return amount * Main.getListCurrencyPair()[i].getRate();
			}//If it's the invert, we invert the rate and return the amount
			else if(Main.getListCurrencyPair()[i].getBase().getName().compareTo(terms)==0 && Main.getListCurrencyPair()[i].getTerms().getName().compareTo(base)==0)
			{
				return amount * (1/Main.getListCurrencyPair()[i].getRate());//Invert the rate;
			}
		}
		
		/*
		 * If no results have been discovered, we will process the tab to create virtually a conversion table.
		 * This Method call itself when it needs to find a new result
		 * Ex: 
		 * try to find JPY in AUD
		 * first, check, no correspondence, then find than JPY can be convert in USD
		 * convert JPY in USD, and send the new amount.
		 * 
		 * the method finally finds that the USD can be convert in AUD (What we need)
		 * convert the USD in AUD and send the result to the main class.
		 * 
		 * if processed with all currency, a "cross-via" matrix will be created. 
		 * This is just a way to not keeping the matrix as a data in the program.
		 */
		for(int i = 0;i<Main.getListCurrencyPair().length;i++)
		{
			if(Main.getListCurrencyPair()[i].getBase().getName().compareTo(base)==0)//If the base equal the tab base
			{
				//Re calculate the amount for terms correspondence.
				amount = amount * Main.getListCurrencyPair()[i].getRate();
				//Put the new terms as a base let the old terms as a terms
				return checkInTab(Main.getListCurrencyPair()[i].getTerms().getName(),terms,amount,baseObjective,termsObjective,true);
			}
			else if(Main.getListCurrencyPair()[i].getTerms().getName().compareTo(base)==0)//If the base equal the tab terms
			{
				//Re calculate the amount for terms correspondence.
				amount = amount * (1/Main.getListCurrencyPair()[i].getRate());
				//Invert put the old base as a terms and the new base as a base.
				return checkInTab(Main.getListCurrencyPair()[i].getBase().getName(),base,amount,baseObjective,termsObjective,false);
			}
		}
		return null;//In case of someone modified the datatable, the program should return null (And throwing a nullException in the main, catched by Exception)
	}
	
	/**
	 * Round a number With a bigDecimal to avoid rounded problem with Double.
	 * @param value value to round
	 * @param places number of decimal
	 * @return converted number
	 */
	public static double round(double value, int places)
	{
	    if (places < 0) throw new IllegalArgumentException();
	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	/**
	 * Format a number (Use also the localization)
	 * @param number number to format
	 * @param decimal number of decimal
	 * @return Formated number
	 */
	public static String Format(double number,int decimal)
	{
		NumberFormat format = NumberFormat.getInstance(); 
		format.setMinimumFractionDigits(decimal);
		return format.format(RateCalculator.round(number,decimal));
	}
}
