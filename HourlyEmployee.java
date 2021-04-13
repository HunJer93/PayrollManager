/**
 * The purpose of Program2 is to develop a payroll system that accepts a file
 * with the employees' information and hours for the week. The program will then 
 * calculate their pay for the week. The HourlyEmployee class is a subclass of Employee responsible for creating each
 * employee that gets overtime pay and calculating their total pay.
 * 
 * 
 * @author Jeremy Hunton
 */
package edu.tridenttech.cpt237.Hunton.Program2;

public class HourlyEmployee extends Employee
{
	//declare class constants
	private final double OVERTIME_CUTOFF = 40.00;
	private final double OT_RATE = 1.5;
	private double overtimeWage = 0.00;

	//create super constructor for the HourlyEmployee
	public HourlyEmployee(String id, String lastName, String firstName, double salary)
	{

		//pull args from super class constructor
		super(id, lastName, firstName, salary);
	}//end of HourlyEmployee constructor
	
	//start of GETTERs
	
	//calculatePayAmount OVERRIDDING METHOD takes the calculate pay amount from the Employee class and calculates if the employee has OT, and what their total pay would be with OT pay.
	@Override
	public double calculatePayAmount()
	{
		//declare local variables
		double overtimeHours = 0.0;
		double totalPay = 0.0;
		
		//selection structure checking if the employee worked overtime
		if (hours > OVERTIME_CUTOFF)
		{
			//declare the overtime hours worked
			overtimeHours = hours - OVERTIME_CUTOFF;
			
			
			//multiply ovetimeHours by the OT_RATE by the salary to get the overtimeWage
			overtimeWage =  overtimeHours*(salary* OT_RATE);
			
			//define the totalPay amount to be the regular hours worked multiplied by salary added to the overtimeWage
			totalPay = (OVERTIME_CUTOFF * salary) + overtimeWage;
			
		}//end the employee worked overtime 
		
		//else there is no overtime hours, so we calculate normally
		else
		{
			totalPay = hours * salary;
		}//end else no overtime hours worked
		
		//return the totalPay amount
		return totalPay;
	}//end calculatePayAmount

}//end HourlyEmployee
