import java.util.*; 

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GuiProgram extends JFrame {

	private JButton button1;		// Search
	private JButton button2;		// Update
	private JButton button3;		// Add
	private JButton button4;		// Delete


	private JPanel panel; 							// button panel to hold components
	private final int WINDOW_WIDTH = 300; 			// window with
	private final int WINDOW_HEIGHT = 200; 			// window height

	public GuiProgram()
	{

		setTitle("Garage Manager");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT); 

		setLayout(new GridLayout(4,1)); 

		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		button1 = new JButton("Search");
		button2 = new JButton("Add");
		button3 = new JButton("Update");
		button4 = new JButton("Delete");




		button1.addActionListener(new ButtonListener());
		button2.addActionListener(new ButtonListener());
		button3.addActionListener(new ButtonListener());
		button4.addActionListener(new ButtonListener());



		add(button1);	
		add(button2);
		add(button3);
		add(button4);

		setVisible(true);


	}


	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			String actionCommand = e.getActionCommand(); 

			if(actionCommand.equals("Search"))
			{
				String st = JOptionPane.showInputDialog("Enter Client ID");	
				SQLConnections.searchDb(st);
			}
			else if(actionCommand.equals("Add"))
			{
				//me lleva a crear todo nuevo		CreateClient
				new CreateClient();
				setVisible(false);

			}

			else if(actionCommand.equals("Update"))
			{
				String st3 = JOptionPane.showInputDialog("Enter client ID");
				setVisible(false);
				new UpdateClient(st3); 
			}
			else if(actionCommand.equals("Delete"))	
			{
				String st4 = JOptionPane.showInputDialog("Entre client ID");
				SQLConnections.deleteClient(st4);
				JOptionPane.showMessageDialog(null, "Client Deleted");

				//codigo
			}

		}
	}
	public static void main(String[] args) throws SQLException
	{
		Connection con = DriverManager.getConnection(Login.DBURL, Login.dbUser, Login.dbPass);

		//System.out.println("Entrastes a la base de datos!!");
		//setVisible(false);
		new GuiProgram(); 

	}


}
