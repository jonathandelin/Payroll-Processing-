package application;


import java.text.DecimalFormat;

/**
 * This class is for the Management type employee which retains characteristics of the base Employee class
 * but also with unique characteristics of a management employee. It is a subclass of the
 * Full-time class as Management employees are Full-time employees as well. Therefore, 
 * it contains a similar parameter called "salary" since employees of this type are not 
 * paid hourly. The methods used for this class are toString(), equals(), calculatePayment(), 
 * getRole(), setRole() and setCompensation(). 
 * 
 * @author Jasmin Kaur, Jonathan Delin
 */

public class Management extends Fulltime {
	static final double BONUS_MANAGER =  5000;
	static final double BONUS_DEP_HEAD = 9500;
	static final double BONUS_DIRECTOR = 12000;
	static final double PAY_PERIOD = 26;
	
	private String role;
	private double compensation;
	
	/**
	 * Allows the generation of a Management employee object.
	 * 
	 * @param person the profile of the employee
	 * @param pay the current calculated payment that they have received
	 * @param type type the kind of employee that they are (FULL TIME)
	 * @param salary the yearly salary the full time employee gets paid
	 * @param role the management role of the employee
	 * @param compensation the compensation pay of the management employee
	 */
	public Management(Profile person, double pay, String type, double salary, String role, double compensation) {
		super(person, pay, type, salary); //inherited from superclass AND the full-time class params
		this.role = role; //parameter specifically for this class
		this.compensation = compensation; //unique to management
	}
	
	
	/**
	 * Returns string with the management employee's Profile and Employee information
	 * along with their unique management information such as their salary, management role, and compensation
	 * @return String of the Management Employee's basic information
	 */
	@Override
	public String toString() {
		//Doe,Jane::IT::2/28/2012::Payment $3,461.54::FULL TIME::Annual Salary $85,000.00::Manager Compensation $192.31
		DecimalFormat df = new DecimalFormat("'$'#0.00");
		df.setGroupingUsed(true);
	    df.setGroupingSize(3);
		return super.toString() + "::" + this.role + " Compensation " + df.format(this.compensation);
	}
	
	/**
	 * Override equals method. Checks if all parameters are true for both objects.
	 * @param obj Object we are checking for equality
	 */ 
	@Override
	public boolean equals(Object obj) { //compare name, department and dateHired
		boolean checkEquals = super.equals(obj);
		if ((obj instanceof Management) == false) {
			return false;
		}
		
		return checkEquals;
	}
	
	/**
	 * Method that calculates the pay of the management employee based on their salary
	 * and compensation
	 */
	@Override
	public void calculatePayment() {
		double pay = 0;
		pay = super.getSalary();
		double payment = 0;
		payment = pay/PAY_PERIOD;
		double temp_comp = 0;
		
		if(role.equals("Manager")) {
			temp_comp = BONUS_MANAGER/PAY_PERIOD;
			payment += temp_comp;
		} else if (role.equals("DepartmentHead")) {
			temp_comp = BONUS_DEP_HEAD/PAY_PERIOD;
			payment += temp_comp;
		} else { //director
			temp_comp = BONUS_DIRECTOR/PAY_PERIOD;
			payment += temp_comp;
		}
		
		//String.format("%.2f", payment);
		this.compensation = temp_comp;
		super.setPay(payment); //sets the pay in Employee itself!
	}
	
	/**
	 * Getter method to retrieve the management role of the employee
	 * 
	 * @return role the management role of the employee
	 */
	public String getRole() {
		return this.role;
	}

	/**
	 * Setter method to set the role of the management employee
	 * 
	 * @param role String of the role
	 */
	public void setRole(String role) {
		this.role = role;
	}
	

	/**
	 * Setter method to set the compensation value of the Management employee
	 * 
	 * @param role the compensation of the management employee based on their salary and role
	 */
	public void setCompensation(String role) {
		
		if(role.equals("Manager")) {
			this.compensation = BONUS_MANAGER/PAY_PERIOD;
			new DecimalFormat("#.##").format(compensation);
			
		} else if (role.equals("DepartmentHead")) {
			this.compensation = BONUS_DEP_HEAD/PAY_PERIOD;
			new DecimalFormat("#.##").format(compensation);
			
		} else { //director
			this.compensation = BONUS_DIRECTOR/PAY_PERIOD;
			new DecimalFormat("#.##").format(compensation);
			
		}
	}
	


}
