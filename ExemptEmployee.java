/**
 * The purpose of Program2 is to develop a payroll system that accepts a file
 * with the employees' information and hours for the week. The program will then 
 * calculate their pay for the week. The ExemptEmployee class is a subclass of Employee responsible for creating each
 * employee that is only on a salary and is unaffected by the hours they work.
 * 
 * @author Jeremy Hunton
 */
package edu.tridenttech.cpt237.Hunton.Program2;

public class ExemptEmployee extends Employee
{
	//create a class constant for the salary hours a week
	private final double WEEKLY_HOURS = 40.00;
	
	//declare subclass constructor
	ExemptEmployee(String id, String lastName, String firstName, double salary)
	{
		//pull args from super class constructor
		super(id, lastName, firstName, salary);
	}//end subclass constructor
	
	//start of GETTERS
	
	//calculatePayAmount OVERRIDING METHOD calculates the pay amount for the week with the salary hours of 40
	@Override
	public double calculatePayAmount()
	{
		return WEEKLY_HOURS * salary;
	}//end calculatePayAmount
	
}//end ExemptEmployee
