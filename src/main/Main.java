package main;

import java.util.Scanner;
import calculator.RateCalculator;
import entries.CheckEntries;
import exceptions.FormatException;
import exceptions.NoCurrencyException;
import utilities.Currency;
import utilities.CurrencyPair;
/**
 * 
 * @author Anthony Paulin (paulin.anthony@gmail.com)
 * @version 1.0
 * @since 22-03-2017
 * 
 */
public class Main 
{
	//Initialize the currencies.
	private final static Currency LIST_CURRENCY[] =
	{
		new Currency("AUD"),new Currency("CAD"),new Currency("CNY"),
		new Currency("CZK"),new Currency("DKK"), new Currency("EUR"),
		new Currency("GBP"),new Currency("JPY",0),new Currency("NOK"),
		new Currency("NZD"),new Currency("USD")
	};

	//Initialize a pair of currencies tab.
	//To not use memory for nothing, pass the object by reference from listCurrency.
	//Avoid cloning an object, not very readable, but more efficient.
	private final static CurrencyPair LIST_CURRENCY_PAIR_PRICE[] =
	{
		    new CurrencyPair(LIST_CURRENCY[5],LIST_CURRENCY[10],1.2315),//EURUSD
			new CurrencyPair(LIST_CURRENCY[0],LIST_CURRENCY[10],0.8371),//AUDUSD
			new CurrencyPair(LIST_CURRENCY[1],LIST_CURRENCY[10],0.8711),//CADUSD
			new CurrencyPair(LIST_CURRENCY[10],LIST_CURRENCY[2],6.1715),//USDCNY
			new CurrencyPair(LIST_CURRENCY[6],LIST_CURRENCY[10],1.5683),//GBPUSD
			new CurrencyPair(LIST_CURRENCY[9],LIST_CURRENCY[10],0.7750),//NZDUSD
			new CurrencyPair(LIST_CURRENCY[10],LIST_CURRENCY[7],119.95),//USDJPY
			new CurrencyPair(LIST_CURRENCY[5],LIST_CURRENCY[3],27.6028),//EURCZK
			new CurrencyPair(LIST_CURRENCY[5],LIST_CURRENCY[4],7.4405),//EURDKK
			new CurrencyPair(LIST_CURRENCY[5],LIST_CURRENCY[8],8.6651),//EURNOK
	};

	@SuppressWarnings("resource")
	public static void main(String[] args) 
	{
		//Start the program
		System.out.println("******Welcome to fxCalculator******");
		System.out.println("type \"list\" to see the currencies.");
		System.out.println("type \"help\" for help.");
		System.out.println("type \"exit\" to exit the programme.");
		System.out.println("***********************************");
		
		//"str" is used to check if "exit","help","list" or to get the data.
		String str = new String();
		
		while(str.compareTo("exit")!=0)//While exit is not entered, continue
		{
			//Check entries
			Scanner sc = new Scanner(System.in);
			str = sc.nextLine();
			
			//Check the menu
			if(str.compareTo("help")==0)//If need help
			{
				System.out.println("Type \"<ccy1> <amount> in <ccy2>\" to calculate a rate. \n ex: AUD 100.00 in JPY.");
			}
			else if(str.compareTo("list")==0)
			{
				System.out.println("Currencies :");
				for(int i=0;i<Main.getListCurrency().length;i++)
				{
					System.out.println("Name :"+Main.getListCurrency()[i].getName()+" Precision :"+Main.getListCurrency()[i].getPrecision());
				}
				System.out.println("Pair list :");
				for(int i=0;i<Main.getListCurrencyPair().length;i++)
				{
					System.out.println(Main.getListCurrencyPair()[i].getBase().getName()+""+Main.getListCurrencyPair()[i].getTerms().getName()+" = "+Main.getListCurrencyPair()[i].getRate());
				}
			}
			else if(str.compareTo("exit")!=0)
			{
				//Put the entries into a tab
				String[] tab = str.split(" ");
				try
				{
					//Check if all entries are correct and save them.
					CheckEntries check = new CheckEntries(tab);
					
					/*
					 *	Format and round the numbers with getPrecision. 
					 *	Can change with the localization : EN-US ".", FR-BE "," 
					 */
					String myNumberFormatedCcy1 = RateCalculator.Format(check.getAmount(), check.getCcy1().getPrecision());
					String myNumberFormatedCcy2 = RateCalculator.Format(RateCalculator.calculator(check.getCcy1().getName(), check.getCcy2().getName(),check.getAmount()), check.getCcy2().getPrecision());
					
					/*
					 * Show the rounded, calculated and formated numbers
					 */
					System.out.println(check.getCcy1().getName()+" "+myNumberFormatedCcy1+" = "+check.getCcy2().getName()+" "+myNumberFormatedCcy2);       	
				}
				catch (NoCurrencyException e) 
				{
					/*
					 * To know exactly what RATE is unavailable, use e.toString();
					 */
					System.out.println("Unable to find rate for "+tab[0]+"/"+tab[3]);
				} 
				catch (FormatException e) 
				{
					System.out.println(e.toString());
				} 
				catch(java.lang.NumberFormatException e)
				{
					System.out.println("Amount must be a number (####.##)");
				}
				catch(Exception e)
				{
					//Catch if unknown exceptions and show the message.
					System.out.println(e.getMessage());
				}
			}
		}
		System.exit(0);//Stop the program
	}
	
	/**
	 * 
	 * @return the currency list
	 */
	public static Currency[] getListCurrency() 
	{
		return LIST_CURRENCY;
	}
	
	/**
	 * 
	 * @return the currency pair list
	 * 
	 */
	public static CurrencyPair[] getListCurrencyPair() 
	{
		return LIST_CURRENCY_PAIR_PRICE;
	}
}
