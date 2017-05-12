import java.util.*; 

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateClient extends JFrame{

	private final int WINDOW_WIDTH = 200; 			// window with
	private final int WINDOW_HEIGHT = 150; 	

	private JButton button1;						// cliente
	private JButton button2;						// car
	private JButton button3;
	
	private String id;

	public UpdateClient(String id)
	{
		this.id = id;
		setTitle("Update Client");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT); 

		setLayout(new GridLayout(3,1)); 

		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		button1 = new JButton("Client");
		button2 = new JButton("Car");
		button3 = new JButton("Main Menu");

		button1.addActionListener(new ButtonListener());
		button2.addActionListener(new ButtonListener());
		button3.addActionListener(new ButtonListener());


		add(button1);		// add client to grid
		add(button2);		// add car to grid
		add(button3);

		setVisible(true);

	}

	private class ButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			String actionCommand = e.getActionCommand(); 

			//String id = JOptionPane.showInputDialog("Enter client ID:");

			if(actionCommand.equals("Client"))
			{
				setVisible(false);
				new Client(id);
			}
			else if(actionCommand.equals("Car"))
			{
				setVisible(false);
				new Car(id);
			}
			else if (actionCommand.equals("Main Menu"))
			{
				setVisible(false);
				new GuiProgram(); 
			}
		}
	}	
}