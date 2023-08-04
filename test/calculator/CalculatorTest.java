package calculator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import calculator.Calculator;
import calculator.GUI;

/**
 * This Class contains a series of JUnit test methods for testing the Calculator
 * Class. The test methods are focused around testing the basic functionality of
 * the calculator's logic.
 * 
 * @author Andrew Sand
 */
public class CalculatorTest {
	/**
	 * Member variables that store the testing instances of the Calculator and GUI
	 */
	GUI gui;
	Calculator test;

	/**
	 * @Before method that instantiates new, clean instances of the Calculator and
	 *         GUI before each test
	 */
	@Before
	public void setup() {
		gui = new GUI();
		test = new Calculator(gui);
	}

	/**
	 * Testing addition with whole numbers
	 */
	@Test
	public void add() {
		assertTrue(test.add("1", "3").compareTo("4") == 0);
	}

	/**
	 * Testing addition with decimals
	 */
	@Test
	public void add_dec() {
		assertTrue(test.add("1.5", "3.63").compareTo("5.13") == 0);
	}

	/**
	 * Testing subtraction with whole numbers
	 */
	@Test
	public void subtract() {
		assertTrue(test.subtract("172", "7").compareTo("165") == 0);
	}

	/**
	 * Testing subtraction with decimals
	 */
	@Test
	public void subtract_dec() {
		assertTrue(test.subtract("17.6", "5.69").compareTo("11.91") == 0);
	}

	/**
	 * Testing multiplication with whole numbers
	 */
	@Test
	public void multiply() {
		assertTrue(test.multiply("8", "7").compareTo("56") == 0);
	}

	/**
	 * Testing multiplication with decimals
	 */
	@Test
	public void multiply_dec() {
		assertTrue(test.multiply("63.354", "8.31").compareTo("526.47174") == 0);
	}

	/**
	 * Testing division with whole numbers
	 */
	@Test
	public void divide() {
		assertTrue(test.divide("72", "9").compareTo("8") == 0);
	}

	/**
	 * Testing division with decimals
	 */
	@Test
	public void divide_dec() {
		assertTrue(test.divide("987.5", "4").compareTo("246.875") == 0);
	}

	/**
	 * Testing squaring with whole numbers
	 */
	@Test
	public void square() {
		assertTrue(test.square("16").compareTo("256") == 0);
	}

	/**
	 * Testing squaring with decimals
	 */
	@Test
	public void square_dec() {
		assertTrue(test.square("96.35").compareTo("9283.322") == 0);
	}

	/**
	 * Testing square root with whole numbers
	 */
	@Test
	public void square_root() {
		assertTrue(test.square_root("64").compareTo("8") == 0);
	}
	
	/**
	 * Testing square root with decimals
	 */
	@Test
	public void square_root_dec() {
		assertTrue(test.square_root("64.6").compareTo("8.037413") == 0);
	}
	
	/**
	 * Testing multiple operations
	 */
	@Test
	public void multi_op() {
		assertTrue(test.square(test.divide(test.multiply(test.add("8.4", "5.6"), "4"), "3.3")).compareTo("287.97064") == 0);
	}
}
