package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 * This Class contains all of the calculator's logic and input handling code. It
 * also contains the main Method for the software, allowing it to be run and
 * operated. Furthermore, this Class is the Observable in the in the
 * Observable-Observer pattern, which is a simplified version of the MVC
 * pattern. Whenever a new number needs to be displayed on the GUI JTextField,
 * this Class will using its notifyObservers() Method to signal.
 * 
 * @author Andrew Sand
 */
@SuppressWarnings("deprecation")
public class Calculator extends Observable implements ActionListener {
	private GUI observer;

	/**
	 * Member variables that store the first number, second number, displayed
	 * number, operator, and result number
	 */
	private String s1, s2, sd, so, sr, mem;

	/**
	 * A custom Constructor Method for the Class. It initialises member variables to
	 * their proper default values and adds the passed in instance of GUI as this
	 * Object's Observer.
	 * 
	 * @param gui - the Observer GUI for this instance
	 */
	public Calculator(GUI gui) {
		// Add the GUI as an Observer and set default values
		this.addObserver(gui);
		observer = gui;
		s1 = s2 = sd = so = sr = "";
		mem = "0";
	}

	/**
	 * The main method for running this software. The method instantiates all
	 * necessary instances of software Objects and creates the GUI and threads for
	 * the program to operate.
	 * 
	 * @param args - Program arguments
	 */
	public static void main(String args[]) {
		// Variable declaration/instantiation
		GUI gui = new GUI();
		Calculator calc = new Calculator(gui);

		// Attempting to initialise the GUI
		if (!gui.initialise(calc)) {
			// GUI failed initialisation
			System.err.println("Program terminated, the GUI failed initialisation!");
			System.exit(0);
		}

		// Display the GUI
		gui.display();
	}

	/**
	 * Converts the passed in Strings into floats, calculates their sum, then gets
	 * the appropriate amount of decimal places using the check_decimal(float f)
	 * helper Method. Public only for JUnit tests to work.
	 * 
	 * @param s1 - the first String containing the number to add
	 * @param s2 - the second String containing the number to add
	 * @return - the String representation of the mathematical result
	 */
	public String add(String s1, String s2) {
		// Parse the strings and add them together
		float f = Float.parseFloat(s1) + Float.parseFloat(s2);
		// Return the value once the decimal place has been checked
		return check_decimal(f);
	}

	/**
	 * Converts the passed in Strings into floats, calculates their differences,
	 * then gets the appropriate amount of decimal places using the
	 * check_decimal(float f) helper Method. Public only for JUnit tests to work.
	 * 
	 * @param s1 - the first String containing the number to subtract
	 * @param s2 - the second String containing the number to subtract
	 * @return - the String representation of the mathematical result
	 */
	public String subtract(String s1, String s2) {
		// Parse the strings and subtract them
		float f = Float.parseFloat(s1) - Float.parseFloat(s2);
		// Return the value once the decimal place has been checked
		return check_decimal(f);
	}

	/**
	 * Converts the passed in Strings into floats, calculates their product, then
	 * gets the appropriate amount of decimal places using the check_decimal(float
	 * f) helper Method. Public only for JUnit tests to work.
	 * 
	 * @param s1 - the first String containing the number to multiply
	 * @param s2 - the second String containing the number to multiply
	 * @return - the String representation of the mathematical result
	 */
	public String multiply(String s1, String s2) {
		// Parse the strings and multiply them together
		float f = Float.parseFloat(s1) * Float.parseFloat(s2);
		// Return the value once the decimal place has been checked
		return check_decimal(f);
	}

	/**
	 * Converts the passed in Strings into floats, calculates their quotient, then
	 * gets the appropriate amount of decimal places using the check_decimal(float
	 * f) helper Method. Public only for JUnit tests to work.
	 * 
	 * @param s1 - the first String containing the number to divide
	 * @param s2 - the second String containing the number to divide
	 * @return - the String representation of the mathematical result
	 */
	public String divide(String s1, String s2) {
		// Parse the strings and divide them
		float f = Float.parseFloat(s1) / Float.parseFloat(s2);
		// Return the value once the decimal place has been checked
		return check_decimal(f);
	}

	/**
	 * Converts the passed in String into a float, calculates its square, then gets
	 * the appropriate amount of decimal places using the check_decimal(float f)
	 * helper Method. Public only for JUnit tests to work.
	 * 
	 * @param s - the String containing the number to square
	 * @return - the String representation of the mathematical result
	 */
	public String square(String s) {
		// Parse the string and multiply it with itself
		float f = Float.parseFloat(s) * Float.parseFloat(s);
		// Return the value once the decimal place has been checked
		return check_decimal(f);
	}

	/**
	 * Converts the passed in String into a float, calculates its square root, then
	 * gets the appropriate amount of decimal places using the check_decimal(float
	 * f) helper Method. Public only for JUnit tests to work.
	 * 
	 * @param s - the String containing the number to square root
	 * @return - the String representation of the mathematical result
	 */
	public String square_root(String s) {
		// Parse the string and square root it
		float f = (float) Math.sqrt(Double.parseDouble(s));
		// Return the value once the decimal place has been checked
		return check_decimal(f);
	}

	/**
	 * A helper method for the calculator that determines when a number should have
	 * a decimal place. It checks whether or not a float ends in .0. It truncates
	 * the decimal if so and leaves the value alone otherwise. Additionally, the
	 * method handles the conversion from a float to a String. For example, this
	 * method would result in 4.0 + 6.0 = 10 instead of 4.0 + 6.0 = 10.0.
	 * 
	 * @param f - the float to check for an empty decimal.
	 * @return s - the resulting string from the conversion
	 */
	private String check_decimal(float f) {
		// Variable declaration
		String s;

		// Checking if there is a decimal in the float
		if (f % 1 == 0) {
			// If not, convert it to an int to remove the .0
			s = String.valueOf((int) f);
		} else {
			// Otherwise, there is a decimal that needs to be retained
			s = String.valueOf(f);
		}

		// Return the appropriate string
		return s;
	}

	/**
	 * This Method contains most of the calculator's logic code. It determines what
	 * button was pressed, the response to the button press, and the internal state
	 * of the calculator. Additionally, it signals to the GUI Observer using the
	 * notifyObservers() Method whenever the JTextField needs to be updated.
	 * 
	 * @param e - the ActionEvent that occurred (Button pressed)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Getting the symbol of the button pressed
		String s = e.getActionCommand();

		// Checking which button was pressed
		switch (s) {
		// Number
		case "0":
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
		case ".":
			// Testing if there is no operator in play (Edit string 1)
			if (so.isEmpty() && s1.length() < 15) {
				// Testing if a decimal can be placed
				if (s.compareTo(".") == 0 && s1.length() == 0
						|| (s.compareTo(".") == 0 && s1.length() == 1 && s1.charAt(0) == '-')) {
					// Can not be placed
					break;
				}
				// Testing if there is already a decimal in the String
				if (s.compareTo(".") == 0 && s1.indexOf('.') != -1) {
					// Decimal already in the string
					break;
				}
				// Add onto string 1
				s1 += s;
				sd = s1;
				this.setChanged();
			}
			// Otherwise, edit string 2
			else if (s2.length() < 7 && !so.isEmpty()) {
				// Testing if a decimal can be placed
				if (s.compareTo(".") == 0 && s2.length() == 0
						|| (s.compareTo(".") == 0 && s2.length() == 1 && s2.charAt(0) == '-')) {
					// Can not be placed
					break;
				}
				// Testing if there is already a decimal in the String
				if (s.compareTo(".") == 0 && s2.indexOf('.') != -1) {
					// Decimal already in the string
					break;
				}
				// Add onto string 2
				s2 += s;
				sd = s2;
				this.setChanged();
			}
			break;
		// Basic Operators
		case "+":
		case "-":
		case "*":
		case "/":
			// Testing if the user is inputting a negative sign
			if (s1.isEmpty() && s.compareTo("-") == 0) {
				// Set the negative sign
				s1 = "-";
				sd = s1;
			}
			// Otherwise, proceed with the operator as normal
			else {
				// Testing if there is something to operate
				if (!s1.isEmpty() && s2.isEmpty()) {
					so = s;
					observer.set_button(s);
				} else {
					this.clear();
					sd = "ERROR";
					observer.reset_buttons();
				}
			}
			this.setChanged();
			break;
		// Square & Square Root
		case "^":
		case "SR":
			// Testing if there is something to operate
			if (!s1.isEmpty() && s2.isEmpty()) {
				so = s;
				observer.set_button(s);
			} else {
				sd = "ERROR";
				observer.reset_buttons();
			}
			this.setChanged();
			break;
		// Add to memory
		case "M+":
			// Test if there is a valid result to store
			if (!sr.isEmpty()) {
				// Add to the memory
				mem = this.add(mem, sr);
				sd = "";
			}
			// Errors out if the store operation is illegal
			else {
				sd = "ERROR";
			}
			// Reset the GUI
			this.clear();
			this.setChanged();
			break;
		// Subtract from memory
		case "M-":
			// Test if there is a valid result to store
			if (!sr.isEmpty()) {
				// Subtract from the memory
				mem = this.subtract(mem, sr);
				sd = "";
			}
			// Errors out if the store operation is illegal
			else {
				sd = "ERROR";
			}
			// Reset the GUI
			this.clear();
			this.setChanged();
			break;
		// Recall Memory
		case "MR":
			// Check if there is memory to recall
			if (!mem.isEmpty()) {
				// Test if the recall is for the first operand
				if (s1.isEmpty()) {
					// Recall to the first string
					s1 = mem;
					sd = s1;
					this.setChanged();
				}
				// Test if the recall is for the second operand
				else if (!so.isEmpty()) {
					// Recall to the second string
					s2 = mem;
					sd = s2;
					this.setChanged();
				}
			}
			break;
		// Clear memory
		case "MC":
			mem = "0";
			break;
		// Delete
		case "Del":
			// Testing which string needs to be deleted from
			// String 1
			if (!s1.isEmpty() && so.isEmpty()) {
				// Remove the last character by creating a substring
				s1 = s1.substring(0, s1.length() - 1);
				sd = s1;
				this.setChanged();
			}
			// Operator
			else if (!s1.isEmpty() && !so.isEmpty() && s2.isEmpty()) {
				// Remove the operator and reset the operator buttons
				so = "";
				observer.reset_buttons();
				this.setChanged();
			}
			// String 2
			else if (!s2.isEmpty()) {
				// Remove the last character by creating a substring
				s2 = s2.substring(0, s2.length() - 1);
				// Testing what should be displayed
				if (s2.isEmpty()) {
					// String 2 is empty, display string 1
					sd = s1;
				} else {
					// String 2
					sd = s2;
				}
				this.setChanged();
			}
			break;
		// Clear
		case "C":
			// Clears all of the strings
			this.clear();
			sd = "";
			mem = "0";
			observer.reset_buttons();
			this.setChanged();
			break;
		// Evaluate
		case "=":
			// Testing if there is something to operate
			if (!s1.isEmpty() && !so.isEmpty() && !s2.isEmpty()) {
				// Testing which operation needs to occur
				switch (so) {
				// Addition
				case "+":
					sr = this.add(s1, s2);
					break;
				case "-":
					sr = this.subtract(s1, s2);
					break;
				case "*":
					sr = this.multiply(s1, s2);
					break;
				case "/":
					sr = this.divide(s1, s2);
					break;
				}
			}
			// Testing if the operator is one that requires only one number
			else if ((so.compareTo("^") == 0 || so.compareTo("SR") == 0) && !s1.isEmpty() && s2.isEmpty()) {
				// Testing which
				switch (so) {
				case "^":
					sr = this.square(s1);
					break;
				case "SR":
					sr = this.square_root(s1);
					break;
				}
			}
			// Otherwise, it is invalid input
			else {
				sr = "ERROR";
			}
			// Reset the non-result values
			s1 = "";
			s2 = "";
			so = "";
			// Display the result
			sd = sr;
			observer.reset_buttons();
			this.setChanged();
			break;
		}

		// Testing if the result is too large or too small for the calculator
		if (!sr.isEmpty() && sr.compareTo("ERROR") != 0 && sd.compareTo("ERROR") != 0
				&& (Float.parseFloat(sr) >= Integer.MAX_VALUE || Float.parseFloat(sr) <= Integer.MIN_VALUE)) {
			// Reset the non-result values
			this.clear();
			sd = "ERROR: OVERFLOW";
			this.setChanged();
		}

		// Notify the GUI that the text field needs changing
		this.notifyObservers(sd);
	}

	/**
	 * Helper method used by the actionPerformed(ActionEvent e) method to reset most
	 * of the String member variables of the calculator. Specifically, the method
	 * empties the s1, s2, so, and sr member variables.
	 */
	private void clear() {
		// Clear all strings excluding the memory and display
		s1 = "";
		s2 = "";
		so = "";
		sr = "";
	}
}
