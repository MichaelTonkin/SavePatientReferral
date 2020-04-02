/*
 * Class: DatabaseManagementSystem
 * Description: handles connecting to the database as well as getting and saving data to database.
 * Author: Michael Tonkin.
 * Created: 02/03/2020
 * Updated: 18/03/2020
 */

package hospmansys.database;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseManagementSystem {

	private final String HOST = "jdbc:mysql://sql164.main-hosting.eu/u181092848_hospmansysdb"; // url for the database we will be
												// using
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // get the
																// driver for
																// connecting to
																// the database
	// the credentials we will use to log into the database
	private final String USER = "u181092848_root";
	private final String PASS = "McD0nalds";
	
	
	private Connection conn = null;
	private Statement statement = null;
	
	/*
	 * Method: connectToDB 
	 * Description: allows for connection to hospmansys
	 * database. 
	 * Warnings: 1. run this before using rest of class.
	 */
	public void connectToDB() {
		try {

			Class.forName(JDBC_DRIVER); // register the JDBC driver

			// open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(HOST, USER, PASS);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * Method: extractData
	 * Description: gets a row of data from specified table by using the row id.
	 * Parameters: table - the table in database to be read from. id - the key for that row.
	 * Returns: an array of columns and their data from the selected table and row with matching id.
	 */
	public ArrayList<String> extractData(String table, String id)
	{	      
		ArrayList<String> result = new ArrayList<String>();
	      try
	      {
	    	  
	      statement = conn.createStatement();
	      String sql;
	      //we are selecting all details from the row that contains the id passed
	      //into parameters
	      sql = "SELECT * FROM " + table + " WHERE " + id + ";";
	      													
	      ResultSet rs = statement.executeQuery(sql);
	      int colNums = rs.getMetaData().getColumnCount();
	      
	      while(rs.next())
	      {
	    	  for(int i = 0; i <= colNums; i++)
	    	  {
	    	      try //prevent unnecessary out of bounds exception
	    	      {
	    		  result.add(rs.getString(i));
	    	      }
	    	      catch (Exception e){
	    	    	  //nothing needs to be done.
	    	      }
	    	  }
	      }
	      }
	      catch (Exception e)
	      {
	    	  e.printStackTrace();
	      }
	     return result;
	}
	
	/*
	 * Method: createEntry
	 * Description: Allows the user to create an entry into the database.
	 * Parameters:
	 * String table - the table to be edited.
	 * String cols - the columns to be changed. Formatted as "column1, column2, column3..."
	 * String data - the data to be added. This again must be in format "column1, column2, column3..."
	 */
	public void createEntry(String table, String cols, String data)
	{	      
	      try
	      {
	    	  
	      statement = conn.createStatement();
	      String sql;
	      //we are selecting all details from the row that contains the id passed
	      //into parameters
	      sql = "INSERT INTO " + table + " (" + cols + ") VALUES (" + data + ");";
	      	//INSERT INTO referral_reports (surname, dob, referral_data, permissions) VALUES (" + data + ");												
	      int rs = statement.executeUpdate(sql);
	      
	      }
	      catch (Exception e)
	      {
	    	  e.printStackTrace();
	      }
	}
	
	/*
	 * Method: deleteEntry
	 * Description: deletes an entry in a table
	 * Parameters: 
	 * String table - the table to be edited.
	 * String primary_key - the name of the primary key for this table
	 * String id - the id of the item to be deleted.
	 * Warning: dangerous. Do not use without proper knowledge.
	 */
	public void deleteEntry(String table, String primary_key, int id)
	{
	      try
	      {
	    	  
	      statement = conn.createStatement();
	      String sql;
	      //we are selecting all details from the row that contains the id passed
	      //into parameters
	      sql = "DELETE FROM " + table + " WHERE " + primary_key + " = " + id;
	      													
	      int rs = statement.executeUpdate(sql);
	      System.out.println("deleted item of id: " + id);
	      }
	      catch (Exception e)
	      {
	    	  e.printStackTrace();
	      }
	}
	
	/*
	 * Method: updateEntry
	 * Description: updates an entry in a table
	 * Parameters: 
	 * String table - the table to be edited.
	 * String cols_new_value - the columns to be updated along with their new values. Can contain one or more columns. For example:
	 * "firstname = \"mydude\", lastname = \"mcgee\"
	 * 
	 * String id - the id for the row we are updating.
	 */
	public void updateEntry(String table, String cols_new_val, int id)
	{
	      try
	      {
	    	  
	      statement = conn.createStatement();
	      String sql;
	      //we are selecting all details from the row that contains the id passed
	      //into parameters
	      sql = "UPDATE " + table + " SET " + cols_new_val + " WHERE " + id;										
	      int rs = statement.executeUpdate(sql);
	      System.out.println("table updated");
	      }
	      catch (Exception e)
	      {
	    	  e.printStackTrace();
	      }
	}
}
