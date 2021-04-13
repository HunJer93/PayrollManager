/**
 * The purpose of Program2 is to develop a payroll system that accepts a file
 * with the employees' information and hours for the week. The program will then 
 * calculate their pay for the week. The Payroll class is responsible for accepting the loaded
 * files and creating/managing the employee list.
 * 
 * @author Jeremy Hunton
 */
package edu.tridenttech.cpt237.Hunton.Program2;

//import ArrayList, fileInputStream, IOException, and scanner
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Payroll 
{
	//create local variables to be returned as errors
	private String empTypeError = "";
	private String empIDError = "";
	
	//create a simple date format
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
	//create the Employee ArrayList
	ArrayList<Employee> empList = new ArrayList<Employee>();
	
	//create an ArrayList that holds the DailyHours
	ArrayList<DailyHours> dailyHourLog = new ArrayList<DailyHours>();
	
	
	
	//declare Payroll class constructor
	public Payroll()
	{
		
	}//end Payroll class constructor
	
	//Start of SETTER methods
	
	//the loadEmployees setter method takes the String input for a file name, unpacks the file and assigns the employees to the empList <Employee> ArrayList 
	public void loadEmployees(String fileName)
	{
		//try block to attempt to open the file
		try
		{
			//declare local variables for the file name and data fields within the employeelist text file
			//these will all be Strings when pulled from the file, and then converted to the proper data type when they are assigned to the Employee objects
			String empID = "";
			String empLastName = "";
			String empFirstName = "";
			String empType = "";	
			String empSalary = ""; //will convert to double
			
			//scan for file
			Scanner infile = new Scanner(new FileInputStream(fileName));
			
			//while loop searching file records
			while(infile.hasNext())
			{
				//split the field into segments with the comma (,) being the delimiter (employee ID, Last Name, First Name, employee type, and employee salary)
				String line = infile.nextLine();
				String[] fields = line.split(",");
				empID = fields[0];
				empLastName = fields[1];
				empFirstName = fields[2];
				empType = fields[3];
				empSalary = fields[4];
				
				//create a selection structure that creates the type of employee based on the empType
				if(empType.equalsIgnoreCase("H"))
				{
					//create an HourlyEmployee and convert empSalary to a double
					HourlyEmployee hourlyEmp = new HourlyEmployee(empID, empLastName, empFirstName, Double.parseDouble(empSalary));
					
					//add hourlyEmp to the Employee ArrayList empList
					empList.add(hourlyEmp);
				}//end create hourly employee
				
				//else if create an exempt employee for E
				else if(empType.equalsIgnoreCase("E"))
				{
					//create an exempt employee (salary) and convert the empSalary to a double
					ExemptEmployee salaryEmp = new ExemptEmployee(empID, empLastName, empFirstName, Double.parseDouble(empSalary));
					
					//add salaryEmp to the Employee ArrayList empList
					empList.add(salaryEmp);
				}//end create exempt (salary) employee
				
				//else if create a contractor 
				else if(empType.equalsIgnoreCase("C"))
				{
					//create a contract employee and convert the empSalary to a double
					ContractEmployee contractEmp = new ContractEmployee(empID, empLastName, empFirstName, Double.parseDouble(empSalary));
					
					//add contractEmp to the Employee ArrayList empList
					empList.add(contractEmp);
				}//end create contractor employee
				
				//else if create a day laborer
				else if(empType.equalsIgnoreCase("d"))
				{
					//create a day laborer and convert the empSalary to a double
					DayLaborer laborer = new DayLaborer(empID, empLastName, empFirstName, Double.parseDouble(empSalary));
					
					//add laborer to the Employee ArrayList empList
					empList.add(laborer);
				}//end create day laborer employee
				
				//else ignore the employee (looking at you Greyworm!)
				else
				{
					//save the employee type and id to return as an error
					empTypeError = empType;
					empIDError = empID;
					
					//ignore the employee 
					empType = null;
				}//end ignore X employee
			}//end while loop cycling the records in the employeelist
			
			//close infile when done
			infile.close();
		}//end of try block opening employeelist.txt file
		
		//catch block if file not found
		catch(IOException ex)
		{
			//call ex object and display message
			ex.printStackTrace();
		}//end of catch for file not found
	}//end loadEmployees
	
	//the loadHours setter method accepts a file name and cycles each line. While cycling the lines, it matches the empID to the employee and adds their hours
	public void loadHours(String fileName)
	{
		//try block to try and open the file
		try
		{
			//declare local variables that will be assigned to the employee. These will be converted after split. 
			String empID = "";
			String dailyHours = "";
			String day = "";
			//scan for the file 
			Scanner infile = new Scanner(new FileInputStream(fileName));
			
			//while loop if there is a next line
			while(infile.hasNext())
			{
				//declare the line that was pulled as a single string 
				String line = infile.nextLine();
				
				//break the line up by the delimiter (,)
				String fields[] = line.split(",");
				
				//assign the first field to day, 2nd to employeeID, and 3rd to dailyHours
				day = fields[0];
				empID = fields[1];
				dailyHours = fields[2];
				
				//cycle the Employee arrayList and see which employee ID matches the added ID
				for (Employee employee : empList)
				{
					//selection structure checking if the empID matches the Employee list ID
					if(employee.getEmpoyeeId().equalsIgnoreCase(empID))
					{
						//add the hours to the employee object (convert dailyHours to a Double)
						employee.addHours(Double.parseDouble(dailyHours));
						
						//now that the hours have been added to that individual employee, the hours need to be added to the date in the 
						//DailyHours class by calling findEntryByDate
						addToDailyReport(day, Double.parseDouble(dailyHours), employee.getSalary());
					}//end employee ID found
					
					//else not found so set to null
					else
					{
						employee = null;
					}//end not found 
				}//end for loop cycling the empList	
			}//end the file has no more lines
		}//end try block
		
		//catch block incase try fails for OPENING THE FILE
		catch(IOException ex)
		{
			//call ex object and display message
			ex.printStackTrace();
		}//end of catch block	
	}//end loadHours method

	
	//addToDailyReport adds the employee hours and pay to a single day
	private void addToDailyReport(String day, double empHours, double empPay)
	{
		//reset singleDay just in case
		DailyHours singleDay = null;
		
		//call the findEntryByDate method to find the date
		singleDay = findEntryByDate(day);
		
		//if the date is not found, create a new date object.
		if(singleDay == null)
		{
			//date not found, so create a new date object
			singleDay = new DailyHours(day);
			
			//add the day to our inventory
			dailyHourLog.add(singleDay);
		}//end create new day 
		
		//add the employee hours and pay to that day
		singleDay.addHours(empHours);
		singleDay.addPay(empPay,empHours);
	}//end addToDailyReport
	
	//the findEntryByDate method searches the dailyHourLog ArrayList to see if the date arg being passed exists in the dailyHourLog. IF the date exists,
	//the pay and hours are added to that date. If it does NOT exist, the new date is created.
	private DailyHours findEntryByDate(String day)
	{
		//cycle the dailyHourLog arraylist
		for(DailyHours singleDay : dailyHourLog)
		{
			//selection structure returning true that the date matches 
			if(singleDay.getDate().equals(day))
			{
				return singleDay;
						
			}//end selection structure returning the date equals 
		}//end for loop cycling the dailyHourLog ArrayList
		
		//else the value was not found, so return null
		return null;
	}//end findEntryByDate
	
	
	
	//start of GETTERS
	
	//getEmployeeList gets the employee list returns the employee list
	public ArrayList<Employee> getEmployeeList()
	{
		return new ArrayList<Employee>(empList);
	}//end getEmployeeList
	
	//getDayList returns the DailyHours ArrayList
	public ArrayList<DailyHours> getDayList()
	{
		return new ArrayList<DailyHours>(dailyHourLog);
	}//end getDayList\
	
	//getEmployeeTypeError return the employee type error
	public String getEmployeeTypeError()
	{
		return empTypeError;
	}//end getEmployeeTypeError
	
	//getEmployeeIDError returns the employee ID error
	public String getEmployeeIDError()
	{
		return empIDError;
	}//end getEMployeeIDError
}//end of Payroll 
