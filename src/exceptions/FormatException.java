package exceptions;
/**
 * 
 * @author Anthony Paulin (paulin.anthony@gmail.com)
 * @version 1.0
 * @since 22-03-2017
 * 
 */
@SuppressWarnings("serial")
public class FormatException extends Exception
{
	public String toString()
	{
		return "The format is not correct. You should write \"<currency1> <amount> in <currency2>\"";
	}
}
