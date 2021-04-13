/**
 * The purpose of Program2 is to develop a payroll system that accepts a file
 * with the employees' information and hours for the week. The program will then 
 * calculate their pay for the week. The DayLaborer class is a subclass of Employee responsible for creating each
 * day laborer object that receives overtime pay for working more than 8 hours a day.
 * 
 * 
 * @author Jeremy Hunton
 */
package edu.tridenttech.cpt237.Hunton.Program2;

import java.util.ArrayList;

public class DayLaborer extends Employee
{
	//declare DailyHours array list
	ArrayList<Double> dailyHours = new ArrayList<Double>();
	ArrayList<Double> dailyTotals = new ArrayList<Double>();
	
	//declare class constants
	private final double OVERTIME_RATE = 1.5;
	private final double OVERTIME_CUTOFF = 8.00;
	
	//declare class variables
	private double overtimeHours = 0.00;
	private double overtimeWage = 0.00;
	//declare subclass constructor
	DayLaborer(String id, String lastName, String firstName, double salary)
	{
		//pull args from super class constructor
		super(id, lastName, firstName, salary);
	}//end subclass constructor

	
	//addHours OVERRIDING METHOD takes the hours worked from the loadHours method in the Payroll Class (line 166) and assigns them to the dailyHours ArrayList so they are separated by day
	@Override
	public void addHours (double hoursWorked)
	{
		//create a DailyHours object that keeps tracks of our day laborer's daily hours
		dailyHours.add(hoursWorked);
		
	}//end addHours
	
	//getHours OVERRIDING METHOD cycles the ArrayList that holds the hours to get the total hours
	@Override
	public double getHours()
	{
		//declare variable for total hours worked
		double totalHours = 0.00;
		
		//cycle the dailyHours ArrayList to get the total hours
		for(Double hoursWorked: dailyHours)
		{
			totalHours+= hoursWorked;
		}//end cycling hours worked 
		
		//return the total hours worked
		return totalHours;
	}//end getHours
	
	//calculatePayAmount OVERRIDING METHOD takes the hours worked PER DAY and pays overtime pay for any hours over the overtime cutoff. These daily totals are then accumulated to give the weekly total.
	@Override
	public double calculatePayAmount()
	{
		//declare local variable for the total pay
		double totalPay = 0.00;
		
		//use a for loop to cycle the dailyHours ArrayList
		for(Double hoursWorked : dailyHours)
		{
			//selection structure checking of the hours are more than the overtime cutoff
			if(hoursWorked > OVERTIME_CUTOFF)
			{
				//get the overtime hours worked
				overtimeHours = hoursWorked - OVERTIME_CUTOFF;
				
				//get the standard hours
				overtimeWage = overtimeHours*(salary* OVERTIME_RATE);
				
				//multiply the hours by their hourly rate to get the totalPay
				totalPay += (OVERTIME_CUTOFF * salary) + overtimeWage;
			}//end calculate overtime hours pay
			
			//else the employee did NOT work overtime hours, so the pay rate is standard
			else
			{
				totalPay += hoursWorked*salary;
			}//end else the pay is standard
		}//end for loop cycling the dailyHours worked
		
		return totalPay;
	}//end calculateDailyPay 
}//end DayLaborer
