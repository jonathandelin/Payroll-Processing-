package application;

import java.text.DecimalFormat;

/**
 * Full-time class is a subclass for Employee and serves as a superclass for Management. It holds
 * the information that is designated to a Full-time employee such as the salary and how 
 * to calculate the biweekly payment. The methods used in this class are toString(), equals(),
 * calculatePayment(), and getSalary().
 * 
 * @author Jasmin Kaur, Jonathan Delin
 */

public class Fulltime extends Employee {
	
	static final double PAY_PERIOD = 26;
	
	private double salary;
	
	/**
	 * Allows the generation of a Full-time employee object.
	 * 
	 * @param person the profile of the employee
	 * @param pay the current calculated payment that they have received
	 * @param type the kind of employee that they are (FULL TIME)
	 * @param salary the yearly salary the full time employee gets paid
	 */
	public Fulltime(Profile person, double pay, String type, double salary) {
		super(person, pay, type); //inherited from superclass
		this.salary = salary;
		
	}

	/**
	 * Returns string with the full time employee's Profile and Employee information
	 * along with their unique full time information such as their salary
	 * @return String of the Full time Employee's basic information
	 */
	@Override
	public String toString() {
		//Doe,Jane::ECE::1/1/2005::Payment $0.00::FULL TIME::Annual Salary $85,000.00
		DecimalFormat df = new DecimalFormat("'$'#0.00");
		df.setGroupingUsed(true);
	    df.setGroupingSize(3);
		return super.toString() +"Annual Salary " + df.format(this.salary);
	}
	
	/**
	 * Override equals method. Checks if all parameters are true for both objects.
	 * @param obj Object we are checking for equality
	 */ 
	@Override
	public boolean equals(Object obj) { //compare name, department and dateHired
		
		boolean checkEquals = super.equals(obj);
		if ((obj instanceof Fulltime) == false) {
			return false;
		}
		
		return checkEquals;
		
	}
	
	/**
	 * Method that calculates the pay of the full time employee based on salary
	 */
	@Override
	public void calculatePayment() {
		double pay = salary;
		double payment = pay/26;
		super.setPay(payment); //update the current payment in super class to this one
		
	}
	
	/**
	 * Getter method to get the yearly salary of a full time employee
	 * 
	 * @return salary of the full time employee
	 */
	public double getSalary() {
		return this.salary;
	}
	

	
}
