package application;

import java.text.DecimalFormat;

/** This class is for the Part-time type employee which retains characteristics of the superclass
 * Employee but also with unique characteristics of a part time employee. Unique characteristics of
 * this class are the payRate and hours parameters as these employees are paid hourly unlike
 * Full-time and Management employees. The methods used for this class are toString(), equals(), 
 * getHours(), inputHours(), getPayRate(), and calculatePayment().
 * 
 * @author Jasmin Kaur, Jonathan Delin
 */
public class Parttime extends Employee {
	static final int BIWEEKLY_MAX_HOURS = 80;
	static final double ALTERED_OVERTIME_RATE = 1.5;
	
	private int hours;
	private double payRate;
	
	/**
	 * Allows the generation of a Part-time employee object. 
	 * 
	 * @param person the profile of the employee
	 * @param pay the current calculated payment that they have received 
	 * @param type the kind of employee that they are (PART TIME)
	 * @param hours the amount of hours that the part time employee has worked
	 * @param payRate the hourly pay rate of the part time employee
	 */
	public Parttime(Profile person, double pay, String type, int hours, double payRate) {
		super(person, pay, type);  //person is name::department::date hired, pay is current payment, type is PARTTIME
		this.hours = hours; //hours is unique to part time
		this.payRate = payRate;
	}
	
	/**
	 * Returns string with the part time employee's Profile and Employee information
	 * along with their unique part time information
	 * @return String of the Part time Employee's basic information
	 */
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("'$'#0.00");
		df.setGroupingUsed(true);
	    df.setGroupingSize(3);
		return super.toString() + "Hourly Rate " + df.format(this.payRate) + "::Hours worked this period: " + this.hours;
		
	}
	
	/**
	 * Override equals method. Checks if all parameters are true for both objects.
	 * @param obj Object we are checking for equality
	 */ 
	@Override
	public boolean equals(Object obj) { //compare name, department and dateHired
		boolean checkEquals = super.equals(obj);
		if ((obj instanceof Parttime) == false) {
			return false;
		}
		return checkEquals;
		
	}
	
	/**
	 * Getter method to retrieve the part time worker's hours worked
	 * @return the hours as an integer value
	 */
	public int getHours() {
		return hours;
	}
	
	/**
	 * Setter method to set the working hours of a part time employee
	 * @param hours integer representing an amount of hours
	 */
	public void inputHours(int hours) {
		this.hours = hours;
	}
	
	/**
	 * Getter method to retrieve the part time employee's pay rate
	 * @return payRate the hourly pay rate of the part time employee
	 */
	public double getPayRate() {
		return this.payRate;
	}
	
	
	/**
	 * Method that calculates the pay of the part time employee based on their hours worked
	 */
	@Override
	public void calculatePayment() {
		double payment;
		int overtimeHours;
		double overtimePay;
		//double pay = getPay();
		double pay = payRate;
		//int overtimeHours = hours - 80;
		if(hours > BIWEEKLY_MAX_HOURS) {
			overtimeHours = hours - BIWEEKLY_MAX_HOURS;
			overtimePay = overtimeHours*(pay*ALTERED_OVERTIME_RATE);
			payment = (BIWEEKLY_MAX_HOURS*pay) + overtimePay;
		} else { 
			payment = hours*pay;
		}

		new DecimalFormat("#.##").format(payment);
		
		super.setPay(payment); //update the current payment in super class Employee to this one	 
	}
	
	
}