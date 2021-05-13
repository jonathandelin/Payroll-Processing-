package application;

/** This class creates the employee Profile object. The profile includes the name, 
 * department, and dateHired of the employee. The methods used in this class are
 * toString(), equals(), getName(), dateToString(), getDepartment(), getDateHired().
 * 
 * @author Jasmin Kaur, Jonathan Delin
 */

public class Profile {
	
	/**
	 * employee's name in the form "lastname,firstname"
	 */
	private String name; 
	
	/**
	 * department code: CS, ECE, IT of employee
	 */
	private String department; 
	
	/**
	 * date employee was hired
	 */
	private Date dateHired;
	
	/**
	 * @param name	employee name
	 * @param department	employee department
	 * @param dateHired		employee hire date
	 */
	public Profile(String name, String department, Date dateHired) {
		this.name = name;
		this.department = department;
		this.dateHired = dateHired;
	}
	
	/**
	 * Overrides toString() method. Will return Profile parameters.
	 * @return String returns parameters of Profile object as a String
	 */
	@Override
	public String toString() {
		return this.name + "::" + this.department + "::" + 
		dateToString(dateHired);
	}
	
	/**
	 * Override equals method. Checks if all parameters are true for both objects.
	 * @param obj Object we are checking for equality
	 */ 
	@Override
	public boolean equals(Object obj) { //compare name, department and dateHired
		if (obj instanceof Profile) { //fixed?
			Profile temp = (Profile) obj;
			if (this.name.equals(temp.getName()) && this.department.equals(temp.getDepartment()) &&
					this.dateHired.compareTo(temp.getDateHired())==0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	
	/**
	 * Getter method to retrieve employee name
	 * @return name of employee
	 */
	public String getName() {
		return this.name;
	}
	
	 /**
	   * Method to convert the Date object parameters back into mm/dd/yyyy format.
	   * @param date, Date object that will be converted into a string
	   * @return the date object that was converted into a string
	   */
	public String dateToString(Date date) {
	     return(date.getMonth() + "/" + date.getDay() + "/" + date.getYear());
	}

	
   /**
	 * Getter method to retrieve employee department 
	 * @return department of employee
	 */
	public String getDepartment() {
		return this.department;
	}
   
   /**
	 * Getter method to hire date of employee
	 * @return date employee was hired.
	 */
	public Date getDateHired() {
		return this.dateHired;
	}
}