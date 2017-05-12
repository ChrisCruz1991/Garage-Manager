import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Login extends JFrame {

	private final int WINDOW_WIDTH = 300; 			// window with
	private final int WINDOW_HEIGHT = 100; 	

	private JLabel username;
	private JLabel password;

	private JTextField userText;
	private JTextField passwordText;

	private JButton enter;

	//connection things with oracle db
	public static final String DBURL = "jdbc:oracle:thin:@localhost:1521:XE";
	public static String dbUser;
	public static String dbPass;

	public Login()
	{
		setTitle("Login");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT); 

		setLayout(new GridLayout(3,2)); 

		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		username = new JLabel("Username");
		password = new JLabel("Password");

		userText = new JTextField(10);
		passwordText = new JTextField(10);

		enter = new JButton("Enter");

		enter.addActionListener(new ButtonListener());

		//first row
		add(username);
		add(userText);
		//second row
		add(password);
		add(passwordText);

		add(enter);

		setVisible(true);
	}

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) 
		{
			dbUser = userText.getText();
			dbPass = passwordText.getText();

			String actionCommand = e.getActionCommand(); 

			if(actionCommand.equals("Enter"))
			{
				SQLConnections.loginDb(dbUser, dbPass);
				setVisible(false);
				new GuiProgram(); 
			}
		}
	}	

	public static void main(String args[])
	{
		new Login(); 
	}

}
