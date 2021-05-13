package application;

import java.text.DecimalFormat;
/**
 *
 * This class creates the Employee object which serves as the superclass
 * for the Part-time and Full-time classes. Full-time then serves as a superclass for the 
 * Management class. The methods used in this class are toString(), equals(), getPerson(), 
 * getPay(), getType(), setPay(), and calculatePayment().
 * 
 * @author Jasmin Kaur, Jonathan Delin
 *
 */

public abstract class Employee {
	
	private String type;
	private Profile person;
	private double pay; //This is the current CALCULATED pay where the math will be done in the parttime, fulltime, or management
	
	//private String role;
	
	/**
	 * Employee constructor creates employees in the company and extends its constructor 
	 * class to Part-time, and Full-time.
	 * 
	 * @param type 	The type of Employee, either Part-time or Full-time
	 * @param person Employee profile
	 * @param pay Employee current money they were paid, starts at $0.00
	 */
	public Employee(Profile person, double pay, String type) {
		this.person = person; //profile object that holds name, hire date, and department
		this.pay = pay; //total current payment
		this.type = type;
		
	}
	
	/**
	 * Returns string with the person's Profile information by calling the toString() method in Profile class
	 * along with their employee information
	 * @return String of the Employee's basic information
	 */
	@Override
	public String toString() {
		//return this.person + "::" + this.pay + "::";
		//Base toString covers: Doe,Jane::CS::7/1/2020::Payment $0.00::WHAT TIME::
		DecimalFormat df = new DecimalFormat("'$'#0.00");
		df.setGroupingUsed(true);
	    df.setGroupingSize(3);
		return person.toString() +"::Payment " + df.format(this.pay) + "::" + this.type + "::";
	}
	
	/**
	 * Override equals method. Checks if all parameters are true for both objects.
	 * @param obj Object we are checking for equality
	 */ 
	@Override
	public boolean equals(Object obj) { 
		Employee emp = (Employee) obj;
		boolean checkEquals = person.equals(emp.getPerson());
		
		return checkEquals;	
	}
	
	/**
	 * Getter method to retrieve the person's Profile
	 * @return the person's Profile
	 */
	public Profile getPerson() {
		return this.person;
	}
	
	/**
	 * Getter method to retrieve the employee's pay
	 * @return double value of employee's pay
	 */
	public double getPay() {
		return this.pay;
	}
	
	/**
	 * Getter method to retrieve the type of employee that the employee is
	 * @return String value of the employee's type
	 */
	public String getType() {
		return this.type;
	}


	/**
	 * Method to set an employee's pay
	 * @param pay, a double of the current value of what the employee has been payed so far 
	 */
	public void setPay(double pay) {
		this.pay = pay;
	}

	/**
	 * Method used to calculate the biweekly payment for each employee type in there
	 * individual classes.
	 */
	public void calculatePayment() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}