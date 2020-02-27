import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class kisok extends JFrame implements ActionListener {
	private static JFrame frameOne;
	private static JFrame frameTwo;
	private static JFrame frameThree;
	private static JFrame frameFour;
	private JPanel panelOne;
	private JPanel panelTwo;
	private JPanel panelThree;
	private JPanel panelFour;
	private JPanel panelFive;
	private JTextField referenceTextField;
	private JTextField checkedReferenceNumberTextField;
	private JTextField customerLastNameTextField;
	private JTextField baggageVolumeTextField;
	private JTextField baggaeWeightTextField;
	private JButton searchBtn;
	private JButton lastNameVerifyButton;
	private JButton extraChargeButton;
	private JLabel checkInErrorLabel;
	private JLabel lastNameErrorLabel;
	private JLabel extraChargeLabel;

	private JButton home;
	private JButton checkInButton;
	private JButton summary;
	private JLabel errorLabel;
	private JLabel ReferenceNumberLabel;

	private String enteredReferenceNumber;
	CheckInMachine checkInMachineObj = new CheckInMachine();
	private String extracharge = "";

	public void createWindowOne() {
		frameOne = new JFrame("Main Window");
		frameOne.setDefaultCloseOperation(frameOne.EXIT_ON_CLOSE);
		createPanelOne();
		frameOne.setSize(1000, 200);
		frameOne.setVisible(true);
		frameOne.setLayout(new BorderLayout());
		frameOne.setLocation(200, 200);
	}
	
	public void createWindowTwo() {
		frameTwo = new JFrame("Baggage");
		frameTwo.setDefaultCloseOperation(frameTwo.DISPOSE_ON_CLOSE);
		createPanelThree();
		frameTwo.setSize(1000, 200);
		frameTwo.setVisible(true);
		frameTwo.setLayout(new BorderLayout());
		frameTwo.setLocation(200, 200);

		
	}
	
	public void createWindowThree() {
		frameThree= new JFrame("Summary");
		frameThree.setDefaultCloseOperation(frameThree.DISPOSE_ON_CLOSE);
		createPanelFour();
		frameThree.setSize(500, 500);
		frameThree.setVisible(true);
		frameThree.setLayout(new BorderLayout());
		frameThree.setLocation(200, 200);

	}
	
	public void createSummaryWindow() {

		frameFour = new JFrame("Summary");
		frameFour.setDefaultCloseOperation(frameFour.DISPOSE_ON_CLOSE);
		createPanelFive();
		frameFour.setSize(500, 500);
		frameFour.setVisible(true);
		frameFour.setLayout(new BorderLayout());
		frameFour.setLocation(200, 200);

	}
	
	
	public void createPanelOne() {
		referenceTextField = new JTextField(16);

		JLabel labelOne = new JLabel("Refernece Number");
		panelOne = new JPanel(new FlowLayout());
		panelOne.setVisible(true);
		searchBtn = new JButton("Verify");

		panelOne.setBackground(Color.white);
		panelOne.setSize(300, 300);

		panelOne.add(labelOne);
		panelOne.add(referenceTextField);
		panelOne.add(searchBtn);

		searchBtn.addActionListener(this);
		panelOne.setVisible(true);

		errorLabel = new JLabel("**** Invalid Reference Number **** Try again ****");
		errorLabel.setOpaque(true);
		errorLabel.setBackground(Color.RED);
		errorLabel.setVisible(false);

		checkInErrorLabel = new JLabel("**** You Are Already Checked In ****");
		checkInErrorLabel.setOpaque(true);
		checkInErrorLabel.setBackground(Color.RED);
		checkInErrorLabel.setVisible(false);

		panelOne.add(errorLabel);
		panelOne.add(checkInErrorLabel);

		frameOne.getContentPane().add(panelOne, BorderLayout.CENTER);
	}

	private void displayPanelTwo() {
		panelTwo = new JPanel(new FlowLayout());

		enteredReferenceNumber = referenceTextField.getText();

		checkedReferenceNumberTextField = new JTextField();
		checkedReferenceNumberTextField.setText(enteredReferenceNumber);
		ReferenceNumberLabel = new JLabel("Reference Number");
		panelTwo.add(ReferenceNumberLabel);
		panelTwo.add(checkedReferenceNumberTextField);
		checkedReferenceNumberTextField.setEnabled(false);

		JLabel lastNameLabel = new JLabel("Last Name");
		panelTwo.add(lastNameLabel);

		customerLastNameTextField = new JTextField(20);
		panelTwo.add(customerLastNameTextField);

		lastNameVerifyButton = new JButton("Check");
		lastNameVerifyButton.addActionListener(this);

		panelTwo.add(lastNameVerifyButton);

		lastNameErrorLabel = new JLabel("**** Invalid Last Name ****");
		lastNameErrorLabel.setOpaque(true);
		lastNameErrorLabel.setBackground(Color.red);
		lastNameErrorLabel.setVisible(false);
		panelTwo.add(lastNameErrorLabel);

		if (checkInMachineObj.isCheckin(enteredReferenceNumber)) {

			checkInErrorLabel.setVisible(true);
		} else if (checkInMachineObj.verifyReferenceNumber(enteredReferenceNumber)) {
			this.panelOne.setVisible(false);
			errorLabel.setVisible(false);
			this.panelTwo.setVisible(true);
			frameOne.getContentPane().add(panelTwo, BorderLayout.CENTER);

		} else {

			errorLabel.setVisible(true);
		}

	}

	

	public void createPanelThree() {

		panelThree = new JPanel(new FlowLayout());

		JLabel volLable = new JLabel("Volume of Baggage");

		baggageVolumeTextField = new JTextField(20);

		JLabel bagLable = new JLabel("Baggage Weight");
		baggaeWeightTextField = new JTextField(20);

		extraChargeButton = new JButton("Extra Charge");
		extraChargeButton.addActionListener(this);

		extraChargeLabel = new JLabel();

		checkInButton = new JButton("Check-IN");
		checkInButton.addActionListener(this);

		panelThree.add(volLable);
		panelThree.add(baggageVolumeTextField);
		panelThree.add(bagLable);
		panelThree.add(baggaeWeightTextField);
		panelThree.add(extraChargeButton);
		panelThree.add(extraChargeLabel);
		panelThree.add(checkInButton);
		
		frameTwo.getContentPane().add(panelThree, BorderLayout.WEST);

	}

	

	public void createPanelFour() {
		panelFour = new JPanel();

		home = new JButton("Home");
		summary = new JButton("Summary");

		home.addActionListener(this);
		summary.addActionListener(this);

		panelFour.add(home);
		panelFour.add(summary);
		

		frameThree.getContentPane().add(panelFour, BorderLayout.CENTER);

		
	}




	public void createPanelFive() {

		panelFive = new JPanel();

		JLabel checkedInPasslabel = new JLabel(
				"**** Total " + checkInMachineObj.totalPassengersCheckedIn + " Passengers Checked In ****");
		JLabel bagVollabel = new JLabel(
				"**** Total " + checkInMachineObj.totalBaggageVolumeCheckedIn + " volume of baagege checked in ****");
		JLabel bagWeighLabel = new JLabel(
				"**** Total " + checkInMachineObj.totalBaggageWeightCheckedIn + " weight of baagege checked in ****");
		JLabel feelabel = new JLabel("**** Total " + checkInMachineObj.totalExcessBaggageFeeCollected
				+ " execess baagege fee collected ****");

		panelFive.add(checkedInPasslabel);
		panelFive.add(bagVollabel);
		panelFive.add(bagWeighLabel);
		panelFive.add(feelabel);
		
		frameFour.getContentPane().add(panelFive);

	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == searchBtn) {
			displayPanelTwo();
		}

		if (e.getSource() == lastNameVerifyButton) {
			if (!checkInMachineObj.checkLastName(enteredReferenceNumber.trim(), customerLastNameTextField.getText().trim())) {

				lastNameErrorLabel.setVisible(true);
			} else {
				lastNameErrorLabel.setVisible(false);
				customerLastNameTextField.setEnabled(false);
				lastNameVerifyButton.setVisible(false);
				createWindowTwo();
			}
		}
		if (e.getSource() == extraChargeButton) {
			extracharge = checkInMachineObj.calcExtraCharge(enteredReferenceNumber.trim(), baggaeWeightTextField.getText().trim(),
					baggageVolumeTextField.getText().trim());
			extraChargeLabel.setText("Have to pay" + extracharge + " Pounds Exatra ");
			extraChargeLabel.setVisible(true);
		}

		if (e.getSource() == checkInButton) {
			checkInMachineObj.totalPassengersCheckedIn++;
			frameTwo.getContentPane().removeAll();
			frameTwo.repaint();
			frameTwo.dispose();
			createWindowThree();
		}

		if (e.getSource() == home) {
			frameOne.dispose();
			createWindowOne();
			frameThree.dispose();
		}
		
		if(e.getSource()==summary) {
			createSummaryWindow();
		}
	}

}

