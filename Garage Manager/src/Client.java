import java.util.*; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Client  extends JFrame{

	private final int WINDOW_WIDTH = 400; 			// window with
	private final int WINDOW_HEIGHT = 200; 	

	private JLabel name;
	private JLabel lastname;
	private JLabel telephone;
	private JLabel email;

	private JTextField nameText;
	private JTextField lastNameText;
	private JTextField telephoneText;
	private JTextField emailText;

	private JButton addButton;
	private JButton returnButton;
	private JPanel panel;
	
	private String id;	//for the client id

	public Client(String id)
	{
		
		//set the field witht the id of the client
		this.id = id;
		//setTitle
		setTitle("Client");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT); 


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//set layout
		setLayout(new GridLayout(5, 2)); 

		setLocationRelativeTo(null);

		name = new JLabel("Name");
		lastname = new JLabel("Last Name");
		telephone = new JLabel("Telephone");
		email = new JLabel("Email(optional)");

		nameText = new JTextField(10);
		lastNameText = new JTextField(10);
		telephoneText = new JTextField(10);
		emailText = new JTextField(10);

		//button
		addButton = new JButton("Update Client");
		returnButton = new JButton("Main menu");

		addButton.addActionListener(new ButtonListener());
		returnButton.addActionListener(new ButtonListener());

		//first row
		add(name);
		add(nameText);

		//second row
		add(lastname);
		add(lastNameText);

		//third row
		add(telephone);
		add(telephoneText);

		//fourth row
		add(email);
		add(emailText);

		//fifth row
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
			String name, lastname, telephone, email;

			name = nameText.getText();
			lastname = lastNameText.getText();
			telephone = telephoneText.getText();
			email = emailText.getText();


			if(actionCommand.equals("Update Client"))
			{
				//System.out.println(id);
				SQLConnections.updateClient(id,name,lastname,telephone,email);
				JOptionPane.showMessageDialog(null, "The client was updated");
				setVisible(false);
				new UpdateClient(id);
			}
			else if(actionCommand.equals("Main menu"))
			{
				setVisible(false);
				new GuiProgram(); 
			}

		}

	}

}
