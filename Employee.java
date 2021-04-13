/**
 * The purpose of Program2 is to develop a payroll system that accepts a file
 * with the employees' information and hours for the week. The program will then 
 * calculate their pay for the week. The Employee class is a superclass responsible for creating each
 * employee object and calculating their individual pay.
 * 
 * Children are HourlyEmployee, ExemptEmployee, ContractEmployee, and DayLaborer classes
 * 
 * @author Jeremy Hunton
 */
package edu.tridenttech.cpt237.Hunton.Program2;

public class Employee 
{
	//declare class variables (these will be pulled from the employeelist.txt file in Payroll class)
	//declare these variables protected so they can be shared among the children classes
	protected String id = "";
	protected String lastName = "";
	protected String firstName = "";
	protected double salary = 0.00;
	protected double hours = 0.00;
	
	//class constructor that accepts the fields from the file
	public Employee(String empId, String empLastName, String empFirstName, double empSalary)
	{
		id = empId;
		lastName = empLastName;
		firstName = empFirstName;
		salary = empSalary;
	}//end Employee class constructor
	
	//start of SETTER methods
	
	//addHours accepts the employee's hours in an argument and adds it to the current employee's hours worked
	public void addHours(double empHours)
	{
		hours += empHours;
	}//end addHours
	
	//Start of GETTER methods
	//getEmployeeId returns the employee ID
	public String getEmpoyeeId()
	{
		return id;
	}//end getEmployeeId
	
	//getLastName returns the employee last name
	public String getLastName()
	{
		return lastName;
	}//end getLastName
	
	//getFirstName returns the employee first name
	public String getFirstName()
	{
		return firstName;
	}//end getFirstName
	
	//getSalary returns the employee salary (hourly rate)
	public double getSalary()
	{
		return salary;
	}//end getSalary
	
	//getHours returns the hours of the employee
	public double getHours()
	{
		return hours;
	}//end getHours
	
	//calculatePayAmount calculates and returns the employee pay amount (hours * salary) for the week
	public double calculatePayAmount()
	{
		return salary*hours;
	}//end calculatePayAmount
	
	

}//end Employee class
