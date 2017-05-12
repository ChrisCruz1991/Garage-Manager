import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * 
 */

/** This class is to use the 
 *  connections to Oracle's DB
 * @author Christopher Cruz
 *
 */
public class SQLConnections {

	/**
	 * static methods to call the class
	 * to use Oracle
	 */
	public static final String DBURL = "jdbc:oracle:thin:@localhost:1521:XE";
	public static String dbUser;
	public static String dbPass;

	/**
	 * loginDb method
	 */

	public static void loginDb(String user, String pass) {

		dbUser = user;
		dbPass = pass;

		//Load Oracle JDBC
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection(DBURL, dbUser, dbPass);
			//System.out.println("Accedistes a la base de datos");
			con.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Incorrect username/passsword.");
			System.exit(0);
			e1.printStackTrace();
		}
	}

	/**
	 * deleteClient method
	 * @throws SQLException 
	 */

	public static void deleteClient(String id) {

		String ID = id;
		String carSql = "DELETE FROM CAR WHERE CLIENT_ID = ?";
		String clientSql = "DELETE FROM CLIENT WHERE ID = ?";

		try {
			Connection con = DriverManager.getConnection(DBURL, dbUser, dbPass);
			PreparedStatement stmt = con.prepareStatement(carSql);
			stmt.setString(1, id);
			stmt.executeUpdate();

			PreparedStatement stmt1 = con.prepareStatement(clientSql);
			stmt1.setString(1, id);
			stmt1.executeUpdate();

			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


//		System.out.println("Los datos del cliente "+ ID+" fueron eliminados.");
//		System.out.println("Sigues en la base de datos.");
	}

	/**
	 * searchDb method
	 */

	public static void searchDb(String id) {
		String searchId = id;
		try{
			Connection con = DriverManager.getConnection(DBURL, dbUser, dbPass);

			Statement statement = con.createStatement();

			ResultSet rs1 = statement.executeQuery("SELECT * FROM CLIENT WHERE ID = "+searchId+"");
			if (rs1.next()) {

				String NAME = rs1.getString("NAME");
				String LAST_NAME = rs1.getString("LAST_NAME"); 
				String PHONE_NUMBER = rs1.getString("PHONE_NUMBER");
				String EMAIL = rs1.getString("EMAIL");

				JOptionPane.showMessageDialog(null,"Table: Client"+'\n'+"ID: "+searchId+'\n'+"Name: "+NAME+'\n'
						+"Last Name: "+LAST_NAME+'\n'+"Phone: "
						+PHONE_NUMBER+'\n'+"Email: "+EMAIL);

				ResultSet rs = statement.executeQuery("SELECT * FROM CAR WHERE CLIENT_ID =" +searchId+"");
				if (rs.next()) {
					String ID = rs.getString("ID");
					String MAKE = rs.getString("MAKE");
					String MODEL = rs.getString("MODEL"); 
					String YEAR = rs.getString("YEAR");
					String LICENSE_PLATE = rs.getString("LICENSE_PLATE");
					String DESCRIPCION = rs.getString("DESCRIPCION"); 

					JOptionPane.showMessageDialog(null,"Table: Car"+'\n'+"ID: "+ID+'\n'
							+"Make: "+MAKE+'\n'+"Model: "+MODEL
							+'\n'+"Year: "+YEAR+'\n'+"License Plate: "
							+LICENSE_PLATE+'\n'+"Description: "+DESCRIPCION);
					rs.close();
				}
				rs1.close();
			}
			con.close();
		}
		catch(SQLException e){
			//add exception later
			JOptionPane.showMessageDialog(null, "Account Not Found.");
			e.printStackTrace();
		}
	}

	/**
	 * addDb method
	 * @param descriptions 
	 * @param year 
	 * @param licencesPlate 
	 * @param model 
	 * @param make 
	 * @param carId 
	 * @param email 
	 * @param telephone 
	 * @param lastname 
	 * @param firstname 
	 * @param clientId 
	 */

	public static void addDb(String clientId, String firstname, String lastname, 
			String telephone, String email, String carId, String make, 
			String model, String licencesPlate, 
			String year, String descriptions) 
	{

		//VARIABLES
		String CLIENT_ID, NAME, LAST_NAME, PHONE_NUMBER, EMAIL;			//cliente
		String CAR_ID, MAKE, MODEL, YEAR, LICENSE_PLATE, DESCRIPCION;	//auto

		//Initialize all variables
		CLIENT_ID = clientId;
		NAME = firstname;
		LAST_NAME = lastname;
		PHONE_NUMBER = telephone;
		EMAIL = email;

		//Car variables
		CAR_ID = carId;
		MAKE = make;
		MODEL = model;
		YEAR = year;
		LICENSE_PLATE = licencesPlate;
		DESCRIPCION = descriptions;

		Connection con;
		try {
			con = DriverManager.getConnection(DBURL, dbUser, dbPass);
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("INSERT INTO CLIENT VALUES('"+CLIENT_ID+"','"+NAME+"','"+LAST_NAME+"','"+PHONE_NUMBER+"','"+EMAIL+"')");
			ResultSet rs1 = statement.executeQuery("INSERT INTO CAR VALUES('"+CAR_ID+"','"+MAKE+"','"+MODEL+"','"+YEAR+"','"+LICENSE_PLATE+"','"+DESCRIPCION+"','"+CLIENT_ID+"')");

			rs.close();
			rs1.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is to connect the database with
	 * the update client button
	 * @param id
	 * @param name
	 * @param lastname
	 * @param telephone
	 * @param email
	 */

	public static void updateClient(String id, String name, String lastname, String telephone, String email) 
	{
		//Variables
		String ID = id;
		String NAME = name;
		String LAST_NAME = lastname;
		String PHONE_NUMBER = telephone;
		String EMAIL = email;

		//Update client

		Connection con;
		try{
			con = DriverManager.getConnection(DBURL, dbUser, dbPass);
			Statement statement = con.createStatement();
			//System.out.println(ID);
			ResultSet rs1 = statement.executeQuery("UPDATE CLIENT "+
					" SET NAME = '" + NAME +
					"',LAST_NAME = '"+LAST_NAME+
					"',PHONE_NUMBER = '" + PHONE_NUMBER+
					"',EMAIL = '" + EMAIL +"' WHERE ID =" + ID +" ");
			//Display message
			JOptionPane.showMessageDialog(null,"Table: Client"+'\n'+"ID: "+ID+'\n'+"Name: "+NAME+'\n'
					+"Last Name: "+LAST_NAME+'\n'+"Phone: "
					+PHONE_NUMBER+'\n'+"Email: "+EMAIL);
			//System.out.println("Su informacion fue actualizada!!!");
			rs1.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param id
	 * @param make
	 * @param model
	 * @param year
	 * @param plates
	 * @param description
	 */

	public static void updateCar(String id, String make, String model, String year, String plates, String description) 
	{
		//Variables
				String CLIENT_ID = id;
				String MAKE = make;
				String MODEL = model;
				String YEAR = year;
				String LICENSE_PLATE = plates;
				String DESCRIPCION = description;
				String CAR_ID;

				//Update client

				Connection con;
				try{
					con = DriverManager.getConnection(DBURL, dbUser, dbPass);
					Statement statement = con.createStatement();
					//System.out.println(CLIENT_ID);
					ResultSet rs1 = statement.executeQuery("UPDATE CAR "+
							" SET MAKE = '" + MAKE +
							"',MODEL = '"+MODEL+
							"',YEAR = '" + YEAR+
							"',LICENSE_PLATE = '" + LICENSE_PLATE +
							"',DESCRIPCION = '" + DESCRIPCION +"' WHERE CLIENT_ID =" + CLIENT_ID +" ");
					
					//Second query
					
					ResultSet rs = statement.executeQuery("SELECT ID FROM CAR WHERE CLIENT_ID = " + CLIENT_ID);
					if(rs.next())
					{CAR_ID = rs.getString("ID");
					//Display message
					JOptionPane.showMessageDialog(null,"Table: Car"+'\n'+"ID: "+CAR_ID+'\n'+"Make: "+MAKE+'\n'
							+"Model: "+MODEL+'\n'+"Year: " + YEAR + 
							"\nLicense Plate: " + LICENSE_PLATE + 
							"\nDescription: " + DESCRIPCION + 
							"\nClient ID: " + CLIENT_ID);
					//System.out.println("Su informacion fue actualizada!!!");
					rs1.close();
					con.close();
					}	} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
}