package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import javax.swing.*;

import java.util.Observable;
import java.util.Observer;

/**
 * This Class contains the GUI code for the calculator software. Additionally,
 * it is the Observer in the Observable-Observer pattern, which is a simplified
 * version of the MVC pattern.
 * 
 * @author Andrew Sand
 */
@SuppressWarnings("deprecation")
public class GUI implements Observer {
	/**
	 * A member variable storing the GUI JFrame
	 */
	private JFrame f;

	/**
	 * A member variable storing the GUI JTextField
	 */
	private JTextField l;

	/**
	 * Member variables for all of the GUI buttons
	 */
	private JButton B0, B1, B2, B3, B4, B5, B6, B7, B8, B9, Badd, Bsub, Bdivide, Bmult, Bdec, Bclear, Bequals, BSquare,
			BSqrRoot, BMR, BMC, BMadd, BMsub, Bdel;

	/**
	 * Member variables that stores the JPanel containing the buttons and JTextField
	 */
	private JPanel p;

	/**
	 * A Method that initialised all of the GUI components. It prepares everything
	 * so that the GUI can be displayed properly. Calling this Method is a
	 * prerequisite to displaying the GUI.
	 * 
	 * @param calc - An instance of the Calculator Class. The Observable for this
	 *             Observer
	 * @return true or false based on the initialisation success
	 */
	public boolean initialise(Observable calc) {
		// Instantiate Swing GUI Objects
		f = new JFrame("calculator");
		p = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		// Set starting properties of GUI Objects
		f.setLocationRelativeTo(null);
		p.setLayout(layout);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Attempt to match system UI
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			// Initialisation failed
			System.err.println("Error: Initialisation of the GUI Failed: " + e.getMessage());
			return false;
		}

		// Instantiate the text field
		l = new JTextField(16);

		// Disable editability on the text field
		l.setEditable(false);

		// Instantiate the number buttons
		B0 = new JButton("0");
		B1 = new JButton("1");
		B2 = new JButton("2");
		B3 = new JButton("3");
		B4 = new JButton("4");
		B5 = new JButton("5");
		B6 = new JButton("6");
		B7 = new JButton("7");
		B8 = new JButton("8");
		B9 = new JButton("9");

		// Instantiate the operation buttons
		Badd = new JButton("+");
		Bsub = new JButton("-");
		Bdivide = new JButton("/");
		Bmult = new JButton("*");
		BSquare = new JButton("^");
		BSqrRoot = new JButton("SR");

		// Instantiate miscellaneous buttons
		Bclear = new JButton("C");
		Bequals = new JButton("=");
		Bdec = new JButton(".");
		Bdel = new JButton("Del");

		// Instantiate the memory buttons
		BMR = new JButton("MR");
		BMC = new JButton("MC");
		BMadd = new JButton("M+");
		BMsub = new JButton("M-");

		// Add Event Listeners to all of the buttons
		B0.addActionListener((ActionListener) calc);
		B1.addActionListener((ActionListener) calc);
		B2.addActionListener((ActionListener) calc);
		B3.addActionListener((ActionListener) calc);
		B4.addActionListener((ActionListener) calc);
		B5.addActionListener((ActionListener) calc);
		B6.addActionListener((ActionListener) calc);
		B7.addActionListener((ActionListener) calc);
		B8.addActionListener((ActionListener) calc);
		B9.addActionListener((ActionListener) calc);
		Badd.addActionListener((ActionListener) calc);
		Bsub.addActionListener((ActionListener) calc);
		Bdivide.addActionListener((ActionListener) calc);
		Bmult.addActionListener((ActionListener) calc);
		BSquare.addActionListener((ActionListener) calc);
		BSqrRoot.addActionListener((ActionListener) calc);
		Bclear.addActionListener((ActionListener) calc);
		Bequals.addActionListener((ActionListener) calc);
		Bdec.addActionListener((ActionListener) calc);
		BMR.addActionListener((ActionListener) calc);
		BMC.addActionListener((ActionListener) calc);
		BMadd.addActionListener((ActionListener) calc);
		BMsub.addActionListener((ActionListener) calc);
		Bdel.addActionListener((ActionListener) calc);

		// Add the elements to the panel
		gbc.gridwidth = 4;
		gbc.gridx = 0;
		gbc.gridy = 0;
		p.add(l, gbc);
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		p.add(BSquare, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		p.add(BSqrRoot, gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		p.add(Bdel, gbc);
		gbc.gridx = 3;
		gbc.gridy = 1;
		p.add(Bclear, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		p.add(BMadd, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		p.add(BMsub, gbc);
		gbc.gridx = 2;
		gbc.gridy = 2;
		p.add(BMR, gbc);
		gbc.gridx = 3;
		gbc.gridy = 2;
		p.add(BMC, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		p.add(Bdivide, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		p.add(B7, gbc);
		gbc.gridx = 2;
		gbc.gridy = 3;
		p.add(B8, gbc);
		gbc.gridx = 3;
		gbc.gridy = 3;
		p.add(B9, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		p.add(Bmult, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		p.add(B4, gbc);
		gbc.gridx = 2;
		gbc.gridy = 4;
		p.add(B5, gbc);
		gbc.gridx = 3;
		gbc.gridy = 4;
		p.add(B6, gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		p.add(Bsub, gbc);
		gbc.gridx = 1;
		gbc.gridy = 5;
		p.add(B1, gbc);
		gbc.gridx = 2;
		gbc.gridy = 5;
		p.add(B2, gbc);
		gbc.gridx = 3;
		gbc.gridy = 5;
		p.add(B3, gbc);
		gbc.gridx = 0;
		gbc.gridy = 6;
		p.add(Badd, gbc);
		gbc.gridx = 1;
		gbc.gridy = 6;
		p.add(B0, gbc);
		gbc.gridx = 2;
		gbc.gridy = 6;
		p.add(Bdec, gbc);
		gbc.gridx = 3;
		gbc.gridy = 6;
		p.add(Bequals, gbc);

		// Set the background colour
		p.setBackground(Color.gray);

		// Set the text field's text alignment to right
		l.setHorizontalAlignment(SwingConstants.RIGHT);

		// Add the panel to the JFrame
		f.getContentPane().add(p, BorderLayout.CENTER);

		// Set the Window size (Width, Height)
		f.setSize(450, 250);

		// Initialisation successful
		return true;
	}

	/**
	 * Method to display the GUI. The initialise(Observable calc) Method must be
	 * called before this.
	 */
	public void display() {
		f.show();
	}

	/**
	 * A Method that sets a button's text colour to red, symbolising it is selected
	 * and has been pressed by the user.
	 * 
	 * @param s - the String representing the button that was pressed
	 */
	public void set_button(String s) {
		// Reset all of the button text colours to black
		this.reset_buttons();
		// Test which button was pressed and set its text colour to red (selected)
		switch (s) {
		case "+":
			Badd.setForeground(Color.red);
			break;
		case "-":
			Bsub.setForeground(Color.red);
			break;
		case "*":
			Bmult.setForeground(Color.red);
			break;
		case "/":
			Bdivide.setForeground(Color.red);
			break;
		case "^":
			BSquare.setForeground(Color.red);
			break;
		case "SR":
			BSqrRoot.setForeground(Color.red);
			break;
		}
	}

	/**
	 * A Method that resets all of the button's text colours to black
	 */
	public void reset_buttons() {
		Badd.setForeground(Color.black);
		Bsub.setForeground(Color.black);
		Bmult.setForeground(Color.black);
		Bdivide.setForeground(Color.black);
		BSquare.setForeground(Color.black);
		BSqrRoot.setForeground(Color.black);
	}

	/**
	 * Override Method required by the Observer Interface. This Method will be
	 * called every time the Observed Calculator instance calls its
	 * notifyObservers() Method. It updates what is displayed in the JTextField,
	 * which acts as the calculator's screen.
	 */
	@Override
	public void update(Observable o, Object arg) {
		// Set the text field to the observed value
		l.setText((String) arg);
	}
}
