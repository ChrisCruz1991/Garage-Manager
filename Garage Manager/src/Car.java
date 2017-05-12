import java.util.*; 

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Car extends JFrame {


	private final int WINDOW_WIDTH = 400; 			// window with
	private final int WINDOW_HEIGHT = 200; 	

	private JLabel make;
	private JLabel model;
	private JLabel plates;
	private JLabel description;
	private JLabel year;

	private JTextField makeText;
	private JTextField modelText;
	private JTextField platesText;
	private JTextField descriptionText;
	private JTextField yearText; 

	private JButton addButton;
	private JButton returnButton;
	private JPanel panel;

	private String id;

	public Car(String id)
	{

		this.id = id;
		setTitle("Client");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT); 


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//set layout
		setLayout(new GridLayout(6, 2)); 

		setLocationRelativeTo(null);

		make = new JLabel("Make");
		model = new JLabel("Model");
		plates = new JLabel("Plates");
		description = new JLabel("Description");
		year = new JLabel("Year"); 

		makeText = new JTextField(10);
		modelText = new JTextField(10);
		platesText = new JTextField(10);
		descriptionText = new JTextField(10);
		yearText = new JTextField(5);

		addButton = new JButton("Update Car");
		returnButton = new JButton("Main menu");

		addButton.addActionListener(new ButtonListener());
		returnButton.addActionListener(new ButtonListener());

		add(make);
		add(makeText);

		add(model);
		add(modelText);

		add(plates);
		add(platesText);

		add(year);
		add(yearText);

		add(description);
		add(descriptionText);

		add(addButton);
		add(returnButton);

		setVisible(true);

	}

	private class ButtonListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String actionCommand = e.getActionCommand(); 
			
			//Get variables from textFields
			String make, model, year, plates, description;

			make = makeText.getText();
			model = modelText.getText();
			year = yearText.getText();
			plates = platesText.getText();
			description = descriptionText.getText();
			
			if(actionCommand.equals("Update Car"))
			{
				SQLConnections.updateCar(id,make,model,year,plates,description);
				
				JOptionPane.showMessageDialog(null, "Client's car was updated");
				setVisible(false);
				new GuiProgram();
			}
			else if(actionCommand.equals("Main menu"))
			{
				setVisible(false);
				new GuiProgram(); 
			}

		}

	}

}



