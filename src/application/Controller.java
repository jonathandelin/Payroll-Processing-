package application;

import java.io.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Payroll Processing Controller that allows the fxml file to take actions
 * based on user input. The methods used for this class are employeeTypeSelected(), removeEmployee(), addEmployee(), 
 * setHours(), calculatePayments(), importFile(), exportFile(), printStatements(), printDate(), printDepart(),
 * testCases(), selectAdmin(), selectDepart(), and clearTextArea(). 
 * 
 * @author Jasmin Kaur, Jonathan Delin
 *
 */

public class Controller {
	//Company that holds employees
	Company company = new Company();
	String name;
	String department;
	String date;
	Date dateHired;
	String num;
	double pay;
	double comp;
	String role;
	String hours;
	
	//FXML elements
	@FXML
	private Button addButton, removeButton, setHoursButton, calculatePaymentsButton,
	 importButton, exportButton; 
	
	@FXML
	private RadioButton parttimeButton, fulltimeButton, managementButton, csButton, eceButton, itButton, managerButton, departmentHeadButton, 
	directorButton; 

	@FXML
	private TextField personName, parttimeHours, hourlyPay, salary;
	
	@FXML
	private DatePicker date_hired;
	
	@FXML
	private TextArea print_message, print;
	
	@FXML
	private MenuItem printButton, printByDateButton, printByDepartmentButton;
	
	/**
	 * disables/enables hours and management positions until correct employee type is selected
	 * 
	 * @param click button clicked
	 */
	@FXML
	void employeeTypeSelected(ActionEvent click) {
		//if part-time is selected, disable management positions and salary field
		if(parttimeButton.isSelected()) {
			setHoursButton.setDisable(false);
			managerButton.setDisable(true);
			departmentHeadButton.setDisable(true);
			directorButton.setDisable(true);
			fulltimeButton.setSelected(false);
			managementButton.setSelected(false);
			
			parttimeHours.setDisable(false);
			hourlyPay.setDisable(false);
			salary.setDisable(true);
			
			//setHoursButton.setSelected(false);
			managerButton.setSelected(false);
			departmentHeadButton.setSelected(false);
			directorButton.setSelected(false);
		}
		
		//if full-time selected disable everything
		if(fulltimeButton.isSelected()) {
			setHoursButton.setDisable(true);
			managerButton.setDisable(true);
			departmentHeadButton.setDisable(true);
			directorButton.setDisable(true);
			parttimeButton.setSelected(false);
			managementButton.setSelected(false);
			
			
			parttimeHours.setDisable(true);
			hourlyPay.setDisable(true);
			salary.setDisable(false);
			
			managerButton.setSelected(false);
			departmentHeadButton.setSelected(false);
			directorButton.setSelected(false);
		}
		
		//if management selected, disable hours, enable positions
		if(managementButton.isSelected()) {
			setHoursButton.setDisable(true);
			managerButton.setDisable(false);
			departmentHeadButton.setDisable(false);
			directorButton.setDisable(false);
			fulltimeButton.setSelected(false);
			parttimeButton.setSelected(false);
			
			parttimeHours.setDisable(true);
			hourlyPay.setDisable(true);
			salary.setDisable(false);
			
			managerButton.setSelected(false);
			departmentHeadButton.setSelected(false);
			directorButton.setSelected(false);
		}
	}
	
	/**
	 * called when remove employee button is selected
	 * checks to see if employee info was entered before removing employee by calling remove function in
	 * company class
	 * 
	 * @param click button clicked
	 */
	@FXML
	void removeEmployee(ActionEvent click) {
		if(company.getNumEmployee() == 0) {
			//System.out.println("Employee database is empty.");
			print_message.appendText("Employee database is empty.\n");
			return;
		}
		
		testCases();
		
		name = personName.getText();
		date = date_hired.getEditor().getText();
		dateHired = new Date(date);
		
		if(!dateHired.isValid()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Error");
			alert.setHeaderText(date_hired.getEditor().getText() + " is not a valid date!");
			alert.showAndWait();
			//print_message.appendText(date_hired.getText() + " is not a valid date!");
		}
		
		Profile tempProfile = new Profile(name, department, dateHired);
		Parttime temp = new Parttime(tempProfile, 0, "PARTTIME", 0, 0);
		
		//company.remove(temp);
		if(company.remove(temp)) {
			//System.out.println("Employee removed.");
			print_message.appendText("Employee removed.\n");
		} else {
			//System.out.println("Employee does not exist.");
			print_message.appendText("Employee does not exist.\n");
		}
		return;
	}
	
	
	/**
	 * called when add employee button is selected
	 * checks to see if correct employee information is entered before adding employee by calling
	 * add function in company class.
	 * 
	 * @param click button clicked
	 */
	@FXML
	void addEmployee(ActionEvent click) {
		
		boolean employeeSelected = (parttimeButton.isSelected() || fulltimeButton.isSelected() 
				|| managementButton.isSelected());
	
		if(!employeeSelected) {
			print_message.appendText("Please select employee type.\n");
			return;
		}
		
		testCases();

		name = personName.getText();
		date = date_hired.getEditor().getText();
		dateHired = new Date(date);
		
		if(!dateHired.isValid()) {
			print_message.appendText(date_hired.getEditor().getText() + " is not a valid date!");
			return;
		}
		
		if(parttimeButton.isSelected()) {
			if(hourlyPay.getText().equals("")) {
				print_message.appendText("Please enter hourly payrate.\n");
				return;
			}
			num = hourlyPay.getText();
			pay = Double.parseDouble(num);
			
			if(pay < 0) {
				print_message.appendText("Payrate cannot be negative.\n");
				return;
			}
			Profile tempProfile = new Profile(name, department, dateHired);
			Parttime temp = new Parttime(tempProfile, 0, "PARTTIME", 0, pay);
			
			if(company.add(temp)== true) {
				//System.out.println("Employee added.");
				print_message.appendText("Employee added.\n");
				return;
			} else {
				print_message.appendText("Employee is already in the list.\n");
				return;
			}
		}
		
		if(fulltimeButton.isSelected()) {
			
			if(salary.getText().equals("")) {
				print_message.appendText("Please enter the salary value.\n");
				return;
			}
			
			num = salary.getText();
			pay = Double.parseDouble(num);
			
			
			if(pay < 0) {
				print_message.appendText("Salary cannot be negative.\n");
				return;
			}
			
			
			Profile temp2 = new Profile(name, department, dateHired);
			Fulltime fullTemp = new Fulltime(temp2, 0, "FULL TIME", pay);
			
			if(company.add(fullTemp)== true) {
				//System.out.println("Employee added.");
				print_message.appendText("Employee added.\n");
				return;
			} else {
				print_message.appendText("Employee is already in the list.\n");
				return;
			}
		}
		
		if(managementButton.isSelected()) {
			
			if(salary.getText().equals("")) {
				print_message.appendText("Please enter the salary value.\n");
				return;
			}
			
			num = salary.getText();
			pay = Double.parseDouble(num);
			
			if(pay < 0) {
				print_message.appendText("Salary cannot be negative.\n");
				return;
			}
			
			boolean managementSelected = (managerButton.isSelected() || departmentHeadButton.isSelected() 
					|| directorButton.isSelected());
			
			if(!managementSelected) {
				print_message.appendText("Please select management type.\n");
				return;
			}
			
			if(managerButton.isSelected()) {
				role = "Manager";
			}
			
			if (departmentHeadButton.isSelected()) {
				role = "Department Head";
			}
			
			if(directorButton.isSelected()) {
				role = "Director";
			}
			
			Profile temp3 = new Profile(name, department, dateHired);
			Management manTemp = new Management(temp3, 0, "FULL TIME", pay, role, 0);
			if(company.add(manTemp)== true) {
				//System.out.println("Employee added.");
				print_message.appendText("Employee added.\n");
				return;
			} else {
				print_message.appendText("Employee is already in the list.\n");
				return;
			}
		}
	}
	
	/**
	 * called when set hours button is selected	
	 * checks to see if the correct employee information is entered then calls set hours function in
	 * company class to set hours for employee
	 * 
	 * @param click button clicked
	 */
	@FXML
	void setHours(ActionEvent click) {
	
		testCases();
		if(parttimeHours.getText().equals("")) {
			print_message.appendText("Please enter hours worked.\n");
			return;
		}

		name = personName.getText();
		date = date_hired.getEditor().getText();
		dateHired = new Date(date);
		hours = parttimeHours.getText();

		int numHours = Integer.parseInt(hours);

		if(numHours < 0) {
			print_message.appendText("Working hours cannot be negative.\n");
			return;
		}

		if(numHours > 100) {
			print_message.appendText("Invalid Hours: over 100.\n");
			return;
		}

		Profile hoursHolder = new Profile(name, department, dateHired);

		Parttime hoursTemp = new Parttime(hoursHolder, 0, "PART TIME", numHours, 0);
		if(company.setHours(hoursTemp)) {
			//System.out.println("Working hours set.");
			print_message.appendText("Working hours set.\n");
			return;
		} else {
			print_message.appendText("Employee does not exist.\n");
			return;
		}
	
	}

	/**
	 * called when calculate payments button is selected
	 * calls process payments method in company class to process payments for employees
	 * @param click button clicked
	 */
	@FXML
	void calculatePayments(ActionEvent click) {
		if(company.getNumEmployee() == 0) {
			//System.out.println("Employee database is empty.");
			print_message.appendText("Employee database is empty.\n");
			return;
		} else {
			company.processPayments();
			//System.out.println("Calculation of employee payments is done.");
			print_message.appendText("Calculation of employee payments is done.\n");
			return;
		}
	}

	/**
	 * Called when import button is clicked.
	 * Allows user to import a file with employee information.
	 * 
	 * @param click button clicked
	 */
	@FXML
	void importFile(ActionEvent click) {
		FileChooser file = new FileChooser();
		file.setTitle("Open Source File to Import");
		file.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		Stage stage = new Stage();
		try {
			File importFile = file.showOpenDialog(stage);
			Scanner scan = new Scanner(importFile);
			try {
			
				while(scan.hasNext()) {
					String command = scan.nextLine();
					StringTokenizer tok = new StringTokenizer(command, ",");
					//int tokenAmount = tok.countTokens();
					String letter = tok.nextToken(); 
				
					switch(letter) {
				
					case "P":
						
						name = tok.nextToken();
						department = tok.nextToken();
						date = tok.nextToken();
						dateHired = new Date(date);
						num = tok.nextToken();
						pay = Double.parseDouble(num);
						
						if(!dateHired.isValid()) {
							print.appendText("Please enter date hired.\n");
							break;
						}
						
						if(pay<0) {
							print.appendText("Payrate cannot be negative.\n");
							break;
						}
						
						Profile holder1 = new Profile(name, department, dateHired);
						Parttime partTemp = new Parttime(holder1, 0, "PART TIME", 0, pay); //hours are initially 0 until set by command
						if(company.add(partTemp)) {
							print.appendText("Employee added.\n");
							break;
						} else {
							print.appendText("Employee is already in the list.\n");
							break;
						}
					
					case "F":
						
						name = tok.nextToken();
						department = tok.nextToken();
						date = tok.nextToken();
						dateHired = new Date(date);
						num = tok.nextToken();
						pay = Double.parseDouble(num);
						
						if(!dateHired.isValid()) {
							print.appendText("Please enter date hired.\n");
							break;
						}
						
						if(pay<0) {
							print.appendText("Salary cannot be negative.\n");
							break;
						}
					
						Profile holder2 = new Profile(name, department, dateHired);
						Fulltime fullTemp = new Fulltime(holder2, 0, "FULL TIME", pay);
						if(company.add(fullTemp)== true) {
							print.appendText("Employee added.\n");
							break;
						} else {
							print.appendText("Employee is already in the list.\n");
							break;
						}
					
					case "M":
						name = tok.nextToken();
						department = tok.nextToken();
						date = tok.nextToken();
						dateHired = new Date(date);
						num = tok.nextToken();
						pay = Double.parseDouble(num);
						String position = tok.nextToken();
						
						if(!dateHired.isValid()) {
							print.appendText("Please enter date hired.\n");
							break;
						}
						
						if(pay<0) {
							print.appendText("Salary cannot be negative.\n");
							break;
						}
						
						if(!position.equals("1")) {
							if(!position.equals("2")) {
								if(!position.equals("3")) {
										print.appendText("Invalid management code.\n");
										break;
								}
							}
						}
						Profile holder3 = new Profile(name, department, dateHired);
						Management manTemp = new Management(holder3, 0, "FULL TIME", pay, position, 0);
					
						if(company.add(manTemp)== true) {
							print.appendText("Employee added.\n");
							break;
						} else {
							print.appendText("Employee is already in the list.\n");
							break;
						}
					
					default:
						scan.nextLine();
					}
				}
			} catch (InputMismatchException e) {
				scan.close();
			}
		} catch(NullPointerException e) {
			print.appendText("File not found.\n");
		
		} catch (FileNotFoundException e1) {
			print.appendText("File not found.\n");
		
		} catch (NoSuchElementException e2) {
			print.appendText("Invalid file format.\n");
		}
	}

	/**
	 *Called when export button is clicked.
	 *Allows user to export employee database into a new document or overwrite an existing one.
	 *
	 *@param click button clicked
	 */
	@FXML
	void exportFile(ActionEvent Click) {
		if(company.getNumEmployee() == 0) {
			//System.out.println("Employee database is empty.");
			print.appendText("Employee database is empty cannot export.\n");
			return;
		}
		
		FileChooser file = new FileChooser();
		file.setTitle("Open Source File to Export to.");
		file.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		Stage stage = new Stage();
		File target = file.showSaveDialog(stage);
		FileWriter doc;
		try {
			doc = new FileWriter(target, true);
			//Employee[] employees = company.getEmployees();
			print.appendText(company.exportDatabase(doc));

			doc.flush();
			doc.close();
		
			//print.appendText("File was successfully exported.\n");
		
		} catch (IOException e) {

		} catch (NullPointerException e) {

		}
	
	}

	/**
	 * Called when printButton is selected.
	 * Calls print function in company class to print statements 
	 * in the order the employees were added.
	 * 
	 * @param click button clicked
	 */
	@FXML
	void printStatements(ActionEvent click) {
		if(company.getNumEmployee() == 0) {
			//System.out.println("Employee database is empty.");
			print.appendText("Employee database is empty.\n");
			return;
		}
		print.appendText(company.print());
		return;
	}

	/**
	 * Called when printByDateButton is selected.
	 * Calls printByDate function in company class to print statements in the
	 * order of date hired.
	 * 
	 * @param click button clicked
	 */
	@FXML
	void printDate(ActionEvent click) {
		if(company.getNumEmployee() == 0) {
			//System.out.println("Employee database is empty.");
			print.appendText("Employee database is empty.\n");
			return;
		}
		print.appendText(company.printByDate());
		return;
	}

	/**
	 * Called when printByDepartmentButton is selected.
	 * Calls printByDepartment function in company class to print statements in
	 * the order of employee department.
	 * 
	 * @param click button clicked
	 */
	@FXML
	void printDepart(ActionEvent click) {
		if(company.getNumEmployee() == 0) {
			//System.out.println("Employee database is empty.");
			print.appendText("Employee database is empty.\n");
			return;
		}
		print.appendText(company.printByDepartment());
		return;
	}
	
	
	/**
	 * Called in remove, add, and set hours methods
	 * to check for invalid data fields entered
	 * by user, also sets department fields based on
	 * the radio button pressed.
	 * 
	 */
	@FXML 
	void testCases() {
		if(personName.getText().equals("")) {
			print_message.appendText("Please enter name.\n");
			return;
		}
	
		if(date_hired.getEditor().getText().equals("")) {
			print_message.appendText("Please enter date hired.\n");
			return;
		}
	
		boolean departmentSelected = (csButton.isSelected() || eceButton.isSelected() 
				|| itButton.isSelected());
	
		if(!departmentSelected) {
			print_message.appendText("Please select department.\n");
			return;
		}
	
		if(csButton.isSelected()) {
			department = "CS";
		}
	
		if (eceButton.isSelected()) {
			department = "ECE";

		}
	
		if(itButton.isSelected()) {
			department = "IT";
		}
	
	}
	
	/**
	 * allows you to only select one management role at a time
	 * @param click button clicked
	 */
	@FXML
	void selectAdmin(ActionEvent click) {
		if(managerButton.isSelected()) {
			departmentHeadButton.setSelected(false);
			directorButton.setSelected(false);
		}
	
		if(departmentHeadButton.isSelected()) {
			managerButton.setSelected(false);
			directorButton.setSelected(false);
		}
	
		if(directorButton.isSelected()) {
			departmentHeadButton.setSelected(false);
			managerButton.setSelected(false);
		}
	}
	/**
	 * Allows you to only select one department at a time
	 * 
	 * @param click
	 */
	@FXML
	void selectDepart(ActionEvent click) {
		if(csButton.isSelected()) {
			itButton.setSelected(false);
			eceButton.setSelected(false);
		}
	
		if (eceButton.isSelected()) {
			itButton.setSelected(false);
			csButton.setSelected(false);
		}
	
		if(itButton.isSelected()) {
			eceButton.setSelected(false);
			csButton.setSelected(false);
		}
	}


	/**
	 * Clears the output text area when button is clicked.
	 * 
	 * @param click button clicked
	 */
	@FXML
	void clearTextArea(ActionEvent click) {
		print_message.clear();
	}

}












