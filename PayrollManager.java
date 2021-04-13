/**
 * The purpose of Program2 is to develop a payroll system that accepts a file
 * with the employees' information and hours for the week. The program will then 
 * calculate their pay for the week. The PayrollManager class is responsible for handling the inputs 
 * and outputs of the program.
 * 
 * @author Jeremy Hunton
 */
package edu.tridenttech.cpt237.Hunton.Program2;

import java.util.ArrayList;
import java.util.Scanner;

public class PayrollManager 
{
	//declare class CONSTANTS
	private static final String[] MENU_OPTIONS = {"Load the Employee List", "Load the Payroll Log", "Process and Quit"};
	private static final char[] MENU_CHARS = {'A', 'B', 'Q'};

	public static void main(String[] args) 
	{
		//declare and initialize scanner object
		Scanner input = new Scanner(System.in);
		
		//declare local variables
		char menuSelection = ' ';
		String fileName = "";
		
		//declare a new Payroll object
		Payroll payroll = new Payroll();
		
		//display the welcome banner
		displayWelcomeBanner();
		
		//request and validate the main menu
		menuSelection = validateMainMenu(input);
		
		//while loop for Quit menu option
		while(menuSelection != 'Q')
		{
			//selection structure for load employee list
			if(menuSelection == 'A')
			{
				//request the file name
				fileName = requestEmpList(input);
				
				//return the file name to the Payroll class 
				payroll.loadEmployees(fileName);
			}//end menuSelection A- load the employeeList
			
			//else menu selection B is selected for load the payroll log
			else
			{
				//request the file name
				fileName = requestTimeLog(input);
				
				//return the file name to the Payroll class
				payroll.loadHours(fileName);
			}//end else menu selection B- load payroll
			
			//display that the file was successfully loaded
			displaySuccessfulFileLoad();
			
			//return to the main menu
			menuSelection = validateMainMenu(input);
		}//end quit main menu option selected
		
		//pass the EmployeeList from Payroll to the displayPayrollReport method
		displayPayrollReport(payroll.getEmployeeList(), payroll.getEmployeeTypeError(), payroll.getEmployeeIDError());
		
		//pass the DailyHours from Payroll to the displayPayStats method
		displayPayStats(payroll.getDayList());
		
		//display the farewell message
		displayFarewellMessage();

		//close the scanner object
		input.close();
	}//end main method
	
	//start of VOID methods
	
	//the displayWelcomeBanner displays the welcome message
	private static void displayWelcomeBanner()
	{
		//display welcome banner
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Welcome to the Payroll Manager! Please follow the menu");
		System.out.println("options to load an employee list, load a payroll log, or to");
		System.out.println("process the loaded files and quit. At the end of the");
		System.out.println("program, you will be given the payroll information for the");
		System.out.println("week and daily statistics for workers hours and pay.");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}//end displayWelcomeBanner
	
	//the displayMainMenu displays the main menu options
	private static void displayMainMenu()
	{
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("MAIN MENU");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		//for loop cycling the menu options
		for(int i=0; i< MENU_CHARS.length; i++)
		{
			System.out.printf("%-3c%-4s%25s\n", MENU_CHARS[i], "for", MENU_OPTIONS[i]);
		}//end for loop cycling the menu options
	}//end displayMainMenu
	
	//displayPayrollReport displays the final payroll report 
	private static void displayPayrollReport(ArrayList<Employee>empList, String empType, String empID)
	{
		//declare local variable for totals
		double totalHours = 0.00;
		double totalPay = 0.00;
		
		//display Error
		System.out.println("\nERROR! The employee type " + empType + " is not supported.");
		System.out.println("ID " + empID + " not found...\n");
		//print report header
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Payroll Report");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("%-6s%-10s%-10s%-6s%-6s%10s\n", "ID", "First", "Last", "Wage", "Hours", "Pay");
		
		//enhanced for loop cycling the payroll
		for(Employee employee : empList)
		{
			//print out each employee one line at a time
			System.out.printf("%-6s%-10s%-10s%-6.2f%-6.2f%10.2f\n", employee.getEmpoyeeId(), employee.getFirstName(), employee.getLastName(),
					employee.getSalary(), employee.getHours(), employee.calculatePayAmount());
			
			//accumulate total hours
			totalHours += employee.getHours();
			
			//accumulate total pay
			totalPay += employee.calculatePayAmount();
		}//end of for loop cycling the employee list
		
		//display the total pay and hours
		System.out.printf("%-6s%31.2f%11.2f\n", "Total:", totalHours, totalPay);
		
	}//end displayPayrollReport
	
	//displayPayStats displays the final stats with the daily totals and total hours/pay
	private static void displayPayStats(ArrayList<DailyHours> dailyHourLog)
	{
		//declare local variables for totals
		double totalHours = 0.00;
		double totalPay = 0.00;
		
		//declare report header
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Statistics");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		System.out.printf("%-10s%8s%10s\n", "Date", "Hours", "Pay");
		//enhanced for loop cycling the daily hours
		for(DailyHours dayByDay : dailyHourLog)
		{
			//print out each day one line at a time
			System.out.printf("%-10s%8.2f%10.2f\n", dayByDay.getDate(), dayByDay.getHours(), dayByDay.getPay());
			
			//accumulate each of the total hours and pay
			totalHours += dayByDay.getHours();
			totalPay += dayByDay.getPay();
		}//end cycling the dailyHourLog
		
		//display the total pay and hours
		System.out.printf("%-10s%8.2f%10.2f\n", "Total:", totalHours, totalPay);
	}//end displayPayStats
	
	
	//displaySuccessfulFileLoad displays a message that the file was successfully loaded
	private static void displaySuccessfulFileLoad()
	{
		System.out.println("\nSuccess! The file was successfully loaded.");
	}//end displaySuccessfulFileLoad
	//displayFarewellMessage displays the farewell message 
	private static void displayFarewellMessage()
	{
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Thank you for using the Payroll Manager. We hope you have");
		System.out.println("great day! Now shutting down...");
	}//end displayFarewellMessage
	
	//end of VOID METHODS
	
	//start of VR METHODS
	
	//validateMainMenu take the main menu display and validates the input
	private static char validateMainMenu(Scanner input)
	{
		//declare local variable
		char menuSelection = ' ';
		
		//call the displayMainMenu
		displayMainMenu();
		
		//request the user input
		System.out.print("\nPlease enter your selection here:");
		menuSelection = input.next().toUpperCase().charAt(0);
		
		//validate the input using a while loop
		while(menuSelection != 'A' && menuSelection != 'B' && menuSelection != 'Q')
		{
			//display error 
			System.out.println("ERROR! This is not a valid input.");
			System.out.println("Please try again...");
			
			//call the displayMainMenu
			displayMainMenu();
			
			//request the user input
			System.out.print("\nPlease enter your selection here:");
			menuSelection = input.next().toUpperCase().charAt(0);
		}//end while loop validating the input
		
		//return the menuSelection
		return menuSelection;
	}//end validateMainMenu
	
	//requestEmpList requests the employee list input
	private static String requestEmpList(Scanner input)
	{
		//declare local variable
		String fileName = "";
		
		//request the user input
		System.out.print("Please enter the EMPLOYEE LIST file name (with .txt) here:");
		fileName = input.next();
		
		//return the file name
		return fileName;
	}//end requestEmpList 
	
	//requestTimeLog requests the employee list input
		private static String requestTimeLog(Scanner input)
		{
			//declare local variable
			String fileName = "";
			
			//request the user input
			System.out.print("Please enter the DAILY LOG file name (with .txt) here:");
			fileName = input.next();
			
			//return the file name
			return fileName;
		}//end requestTimeLog

}//end PayrollManager
