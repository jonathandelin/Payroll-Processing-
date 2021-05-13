package application;


/** This class defines the properties of a Date object. A date object contains year 
* a month, day, and year. The methods used in this class are isValid(), getYear(), 
* setYear(), getMonth(), setMonth(), getDate(), and setDate().
* 
* @author Jasmin Kaur, Jonathan Delin
*/

import java.util.StringTokenizer;
import java.util.Calendar;

public class Date implements Comparable<Date> {
	private int year;
	private int month;
	private int day;
	
 
	/**
	 * This compares two dates to see if one is greater or equal than the other
	 * 
	 * @param date is the date that will be used as comparison
	 * @return integer -1 if comparison date is less than the reference date, 0 if they are equal, or 1 if comparison date is greater than reference date 
	 */
	@Override
	public int compareTo(Date date) { //return -1, 0 or 1
		//pass the date for comparison, the date being passed is the nextNum
		
		//check if the dates are equal
		if (this.year == date.getYear() && this.month == date.getMonth() && this.day == date.getDay()) {
			return 0;	
		}
		
		
		
		if (this.year > date.getYear()) { //compare year values
			return 1;
			
		} else if (this.month > date.getMonth() && this.year == date.getYear()) {//compare month values only if equal year
			return 1;

		} else if (this.day > date.getDay() && this.month == date.getMonth() && this.year == date.getYear()) { //compare day values only if equal year and month
			return 1;
		} else {
			return -1;
		}

	}

    /**
	 * Getter method to retrieve year of a date
	 * @return year field of the Date object
     */
	public int getYear() {
		return year;
	}
  
    /**
	 * Setter method to set year of a date
	 * @param year, int representing a year
	 */
	public void setYear(int year) {
		this.year = year;
	}
  
    /**
	 * Getter method to retrieve year of a date
	 * @return year field of the Date object
	 */
	public int getMonth() {
		return month;
	}
  
    /**
	 * Setter method to set month of a date
	 * @param month, int representing a month
	 */
	public void setMonth(int month) {
		this.month = month;
	}
  
    /**
	 * Getter method to retrieve year of a date
	 * @return year field of the Date object
	 */
	public int getDay() {
		return day;
	}
  
    /**
	 * Setter method to set day of a date
	 * @param day, int representing a day
	 */
	public void setDay(int day) {
		this.day = day;
	}
  
    /**
     * Creates an object using date string parameter.
     * Tokenizes date string parameter using "/" to get date object parameters to create Date object.
     * @param date string, raw date string in the format mm/dd/yyyy 
     */
	public Date(String date) { 

       StringTokenizer tok = 
      			new StringTokenizer(date, "/");
       	
      	this.month = Integer.parseInt(tok.nextToken());
      	this.day = Integer.parseInt(tok.nextToken());
      	this.year = Integer.parseInt(tok.nextToken());
       		
	}
	
    /**
     * Creates an object with today's date.
     */
	public Date() { 
		Calendar todays_date = Calendar.getInstance();
		
		this.day = todays_date.get(Calendar.DATE);
		this.month = 1 + todays_date.get(Calendar.MONTH);
		this.year = todays_date.get(Calendar.YEAR);	
	}

	/**
     * Checks if date is valid.
     * @return true if date meets all requirements, returns false otherwise 
     */
	public boolean isValid() {
		Date current_date = new Date();
		int current_day = current_date.getDay();
		int current_month = current_date.getMonth();
		int current_year = current_date.getYear();
		
		//Year condition checks
		if(this.year < DateConstants.VALID_YEAR) {
			return false;
		}

		if(this.year > current_year) {
			return false;
			
		} else if (this.year == current_year) { 
			if (this.month > current_month) {
				return false;
				
			} else if (this.month == current_month) {
				if (this.day > current_day) {
					return false;
					
				} 
			}
			
		} 
		
		//Check if year is a leap year
		boolean LeapYearCheck;
		
		if (this.year % DateConstants.QUADRENNIAL == 0) {
			
			if (this.year % DateConstants.CENTENNIAL == 0) {
				
				if (this.year % DateConstants.QUATERCENTENNIAL == 0) {
					
					LeapYearCheck = true;
					
				} else {
					
					LeapYearCheck = false;
				}
				
			} else {
				LeapYearCheck = true;
			}
				
		} else {
			LeapYearCheck = false;
		}
		
		//Month condition checks
		if (this.month == DateConstants.JAN || this.month == DateConstants.MAR || this.month == DateConstants.MAY || this.month == DateConstants.JUL || this.month == DateConstants.AUG || this.month == DateConstants.OCT || this.month == DateConstants.DEC) {
			if (this.day <= DateConstants.DAYS_31 && this.day >= 1) {
				return true;
			} else {
				return false;
			}
		}
		
		if (this.month == DateConstants.APR || this.month == DateConstants.JUN || this.month == DateConstants.SEP || this.month == DateConstants.NOV) {
			if (this.day <= DateConstants.DAYS_30 && this.day >= 1) {
				return true;
			} else {
				return false;
			}
		}
		
		//Verify how many days in February are in this year
		if (this.month == DateConstants.FEB) {
			
			if (LeapYearCheck) {
				if (this.day <= DateConstants.FEB_LEAP_MAX && this.day >= 1) {
					return true;
				} else {
					return false;
				}
			} else {
				if (this.day <= DateConstants.FEB_DEFAULT_MAX && this.day >= 1) {
					return true;
				} else {
					return false;
				}
				
			}
			
		}	
		return false;	
	}

	


}