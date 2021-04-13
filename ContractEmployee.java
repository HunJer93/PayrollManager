/**
 * The purpose of Program2 is to develop a payroll system that accepts a file
 * with the employees' information and hours for the week. The program will then 
 * calculate their pay for the week. The ContractEmployee class is a subclass of Employee responsible for creating each
 * employee that is contracted out and does not get paid overtime.
 * 
 * @author Jeremy Hunton
 */
package edu.tridenttech.cpt237.Hunton.Program2;

public class ContractEmployee extends Employee
{
	//create ContractEmployee constructor
	ContractEmployee(String id, String firstName, String lastName, double salary)
	{
		//pass args from the super constructor
		super(id, firstName, lastName, salary);
	}

}//end of ContractEmployee
