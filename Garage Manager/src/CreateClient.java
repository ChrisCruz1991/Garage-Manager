import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateClient extends JFrame{

	private final int WINDOW_WIDTH = 500; 			// window with
	private final int WINDOW_HEIGHT = 200; 		

	//Client Labels
	private JLabel id;
	private JLabel name;
	private JLabel lastname;
	private JLabel telephone;
	private JLabel email;
	private JLabel empty1; 
	private JLabel empty2; 
	private JLabel empty3; 

	//Cars Labels
	private JLabel carId;
	private JLabel make;
	private JLabel model;
	private JLabel plates;
	private JLabel description;
	private JLabel year; 

	//Client TextFields
	private JTextField idText;
	private JTextField nameText;
	private JTextField lastNameText;
	private JTextField telephoneText;
	private JTextField emailText;


	//Cars TextFields
	private JTextField carIdText;
	private JTextField makeText;
	private JTextField modelText;
	private JTextField platesText;
	private JTextField yearText; 
	private JTextField descriptionText;


	private JButton addButton;
	private JButton returnButton;
	private JPanel panel;

	/**
	 * Constructor
	 */

	public CreateClient() {

		//setTitle
		setTitle("Create Client");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT); 


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//set layout
		setLayout(new GridLayout(7, 4)); 


		setLocationRelativeTo(null);


		//Create the labels
		id = new JLabel("Client ID");
		name = new JLabel("Name");
		lastname = new JLabel("Last Name");
		telephone = new JLabel("Telephone");
		email = new JLabel("Email(Optional)");
		empty1 = new JLabel("");
		empty2 = new JLabel("");
		empty3 = new JLabel("");

		//Car labels
		carId = new JLabel("Car ID");
		make = new JLabel("Make");
		model = new JLabel("Model");
		plates = new JLabel("Plates");
		year = new JLabel("Year"); 
		description = new JLabel("Description");


		//Client textFields
		idText = new JTextField(5);
		nameText = new JTextField(10);
		lastNameText = new JTextField(10);
		telephoneText = new JTextField(10);
		emailText = new JTextField(10);

		//Car textFields
		carIdText = new JTextField(10);
		makeText = new JTextField(10);
		modelText = new JTextField(10);
		platesText = new JTextField(10);
		yearText = new JTextField(5); 
		descriptionText = new JTextField(10);

		//button
		addButton = new JButton("Add");
		returnButton = new JButton("Main menu");

		addButton.addActionListener(new ButtonListener());
		returnButton.addActionListener(new ButtonListener());



		//		add everything to the panel
		//		first row
		add(id);
		add(idText);
		add(carId);
		add(carIdText);
		//		second row
		add(name);
		add(nameText);
		add(make);
		add(makeText);
		//		third row
		add(lastname);
		add(lastNameText);
		add(model);
		add(modelText);
		//		fourth row
		add(telephone);
		add(telephoneText);
		add(plates);
		add(platesText);
		//		fifth row
		add(email);
		add(emailText);
		add(year);
		add(yearText);
		//		sixth row
		add(empty1);
		add(empty2);
		add(description);
		add(descriptionText);

		//		sixth row
		add(empty3);
		add(addButton);
		add(returnButton);


		//setVisible
		setVisible(true);
	}

	/**
	 * Main method
	 * @param args
	 */

	private class ButtonListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			//Declare variables
			String clientId,firstname,lastname,phoneNum,
			email,carId,make,model,year,licencesPlate,descriptions;

			//Client
			clientId = idText.getText();
			firstname = nameText.getText();
			lastname = lastNameText.getText();
			phoneNum = telephoneText.getText();
			email = emailText.getText();

			//car
			carId = carIdText.getText();
			make = makeText.getText();
			model = modelText.getText();
			licencesPlate = platesText.getText();
			year = yearText.getText();
			descriptions = descriptionText.getText();

			String actionCommand = e.getActionCommand(); 

			if(actionCommand.equals("Add"))
			{
				//SQLConnnections method
				SQLConnections.addDb(clientId,firstname,lastname,phoneNum,email,
						carId,make,model,licencesPlate,year,descriptions);

				JOptionPane.showMessageDialog(null, "The new client account has been created");
				setVisible(false);
				new GuiProgram().setVisible(true);
			}
			else if(actionCommand.equals("Main menu"))
			{
				setVisible(false);
				new GuiProgram().setVisible(true);

			}

		}

	}

}