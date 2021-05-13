package application;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

/** This class is an array-based container class that implements
 * the employee database. The array stores a list of employees (full-time, part-time,
 * management) and grows by 4 if it is full. Methods used in this class are 
 * grow(), find(), add(), remove(), setHours(), processPayments(), print(), sortByDept()
 * printByDepartment(), sortByDate(), printByDate(), exportDatabase(), and getNumEmployee().
 * 
 * @author Jasmin Kaur, Jonathan Delin
 *
 */

public class Company {
	static final int INVALID_RETURN_VALUE = -1;
	static final int INCREASE_CAPACITY = 4;
	static final int GROUP_SIZE = 3;
	
	/**
	 * array of employee objects initiated to size of 4
	 */
	private Employee[] emplist = new Employee[INCREASE_CAPACITY];
	
	private int numEmployee;
	
	
	/**
	 * Finds the index of a given employee.
	 * Returns the index value of the employee as a string.
     * Otherwise, returns invalid index value of -1.
	 * @param employee, is the Employee that is being searched for
	 * @return index as int if found, return invalid index value if not found.
	 */
	private int find(Employee employee) {
		int i = 0;
		while (i < numEmployee) {
			if (emplist[i].getPerson().equals(employee.getPerson())) {
				return i; //location employee is at
			}
			i++;
		}
		return INVALID_RETURN_VALUE; //returns value not found
	}
	
	/**
	 * Increases book array capacity by 4 
	 */
	private void grow() {
		
		Employee[] newList = new Employee[emplist.length + INCREASE_CAPACITY];
		for (int i = 0; i < emplist.length; i++) {
			newList[i] = emplist[i];
		}

		emplist = newList;
	}
	
	/**
	 * Adds employee to the employee list
	 * 
	 * @param employee, Employee that is to be added
	 * @return boolean,return true if employee has been successfully added and false if employee already exists
	 */
	public boolean add(Employee employee) { //check the profile before adding
			int i = 0;
			if (find(employee) == INVALID_RETURN_VALUE) { //employee does not already exist
			while (true) {

				if (emplist[i] == null) {
					emplist[i] = employee;
					numEmployee++;
					//return true;

					if (numEmployee == emplist.length) {
						grow();
					}
					
					return true; //employee successfully added
				}
				i++;
			}
			
			}
			//System.out.println("Employee is already in the list.");
			return false; //employee already exists in system
		}

	
	/**
	 * Removes the employee from the employee list
	 * 
	 * @param employee Employee that is to be removed
	 * @return true if found and removed, false otherwise
	 */
	public boolean remove (Employee employee) { //maintain the original sequence
		int empLocation = find(employee); 
		if (find(employee) != INVALID_RETURN_VALUE) { //employee exists in the system

			emplist[empLocation] = null;
			int i = empLocation + 1;
			while(i < emplist.length) {
				emplist[i - 1] = emplist[i];
				i++;
			}
			
			
			numEmployee--;
			

			return true; //employee removed
		}

		return false; //employee does not exist
	}
	
	/**
	 * Method to set hours of a part-time employee
	 * @param employee object whose hours are being set
	 * @return true if successful, false if not
	 */
	public boolean setHours(Employee employee) { //set working hours for a part time
		
		if(find(employee) != INVALID_RETURN_VALUE) {
			((Parttime) emplist[find(employee)]).inputHours(((Parttime) employee).getHours());
			//System.out.println(((Parttime) employee).getHours());
			return true;
		} else {
			//System.out.println("Employee does not exist.");
		return false; //cannot set hours 
	}
	}
	
	/**
	 * Method to process payments for all employees
	 */
	public void processPayments() { //process payments for all employees
		for(int i = 0; i < numEmployee; i++) {
			
			emplist[i].calculatePayment();
		}
	}
	
	/**
	 * Default print method that prints the list of employees in its present order. 
	 * 
	 * @return print the emplist entry as a string
	 */
	public String print(){ //print earning statements for all employees
		String print = "";
		print += ("\n--Printing earning statements for all employees--\n");
		for (int i = 0; i < numEmployee; i++) {
			//System.out.println(emplist[i]);
			print += "\n" + emplist[i];
		}
		return print;
	}
	
	
	
	
	/**
	 * Sort method for arranging employee list in alphabetical order by department.
	 */
	public void sortByDept() {
		
		int currentDepIndex;
		String nextDep;
		Employee tempEmp;
		
		for (int i = 1; i < numEmployee; i++) {
			nextDep = emplist[i].getPerson().getDepartment();
			tempEmp = emplist[i];
			currentDepIndex = i;
			
			while (currentDepIndex > 0 && emplist[currentDepIndex - 1].getPerson().getDepartment().compareTo(nextDep) > 0) { //lexicographical sort
				
				
				emplist[currentDepIndex] = emplist[currentDepIndex - 1];
				currentDepIndex = currentDepIndex - 1;
				
			}
			
			emplist[currentDepIndex] = tempEmp;
			
		}	
	}
	
	
	/**
	 * Print method that prints the list of employees in alphabetical order by department. 
	 * 
	 * @return print the emplist entry as a string
	 */
	public String printByDepartment() { //print earning statements by department
		String print = "";
		print += ("\n--Printing earning statements by department--\n");
		//System.out.println("--Printing earning statements by department--");
		sortByDept();
		for (int i = 0; i < numEmployee; i++) {
			//System.out.println(emplist[i]);
			print += "\n" + emplist[i];
		}
		return print;
	}
	
	
	/**
	 * Sort method for arranging books in ascending order by their publish date.
	 */
	public void sortByDate() {
		int currentNum;
		Date nextNum;
		Employee temp;

		
		for (int i = 1; i < numEmployee; i++) {
			
			nextNum = emplist[i].getPerson().getDateHired();
			temp = emplist[i];
			currentNum = i;

			while (currentNum > 0 && emplist[currentNum - 1].getPerson().getDateHired().compareTo(nextNum) > 0) {
				emplist[currentNum] = emplist[currentNum - 1];
				currentNum = currentNum - 1;
			}
			
			
			emplist[currentNum] = temp;
		}
		
	}
	   
	/**
	 * Print method that prints the list of employees in ascending order by their hire date.
	 * 
	 * @return print the emplist entry as a string
	 */
	public String printByDate() { //print earning statements by date hired
		String print= "";
		//System.out.println("--Printing earning statements by date hired--");
		print += ("\n--Printing earning statements by date hired--\n");
			//sort by date, then print employee list
		sortByDate();
		for (int i = 0; i < numEmployee; i++) {
			//System.out.println(emplist[i]);
			print += "\n" + emplist[i];
		}
		return print;
	}
	
	/**
	 * Method that writes employee database into a document that was created in 
	 * Controller class.
	 * @param doc document to be written to
	 * @throws IOException e file cannot be written to
	 * @return print string with either error if method failed or success if file was exported.
	 */
	public String exportDatabase(FileWriter doc) throws IOException {
		String print="";
		try {
			DecimalFormat df = new DecimalFormat("'$'#0.00");
			df.setGroupingUsed(true);
		    df.setGroupingSize(GROUP_SIZE);
			for (int i = 0; i < numEmployee; i++) {
				doc.write(emplist[i].toString() + "\n");
			}
			print += "Database succesfully exported.\n";
			return print;
		} catch (IOException e) {
			print += "\n" + e.getMessage();
		}
		return print;
		
		
	}
	

	/**
	 * Getter method to retrieve the number of employees 
	 * 
	 * @return numEmployee the number of employees in the company
	 */
	public int getNumEmployee() {
		return numEmployee;
	}
	
	/**
	 * Gets the employees in the database
	 * 
	 * @return array of the employees
	 */
	public Employee[] getEmployees() {
		return emplist;
	}
	
}