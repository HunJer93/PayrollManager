/**
 * The purpose of Program2 is to develop a payroll system that accepts a file
 * with the employees' information and hours for the week. The program will then 
 * calculate their pay for the week. The DailyHours class keeps track of statistics of the
 * employees like total daily hours and total pay. 
 * 
 * @author Jeremy Hunton
 */
package edu.tridenttech.cpt237.Hunton.Program2;


public class DailyHours 
{
	//declare local variables
	private String date = "";
	private double hours = 0.00;
	private double pay = 0.00;
	
	//create DailyHours constructor that passes the String date 
	public DailyHours(String day)
	{
		date = day;
	}//end DailyHours class constructor
	

	//start of setter methods
	
	//addPay accumulates the total pay for the date (pay rate times the hours worked
	public void addPay(double empPay, double empHours)
	{
		pay += empPay * empHours;
	}//end addPay
	
	//addHours accumulates the total hours for the date
	public void addHours(double empHours)
	{
		hours += empHours;
	}//end addHours
	
	
	//start of GETTER methods
	
	//getDate returns the date
	public String getDate()
	{
		return date;
	}//end getDate 

	//getHours returns the hours
	public double getHours()
	{
		return hours;
	}//end getHours
	
	//getPay returns the pay
	public double getPay()
	{
		return pay;
	}//end getPay
	
}//end of DailyHours
