/* Class: Firewall
 * Description: Is used in order to scan data before it can be sent to the database.
 * Created: 29/03/2020
 * Updated: 31/03/2020
 * Author/s: Michael Tonkin
 */

package hospmansys.database;

import java.util.ArrayList;

public class Firewall {

	ArrayList<String> badInput = new ArrayList<String>(); //the list containing any input that we don't want to pass to the database
	
	/*Method: scanData
	 * Description: Scans data before it can be sent to database. Returns true if data is safe to use.
	 * parameters: String data - the data to be scanned.
	 */
	public boolean scanData(String data)
	{
		badInput.add(";");
		
		for (int x = 0; x < badInput.size(); x++)
		{
			if (data.contains(badInput.get(x)))
			{
				System.out.println("Data is bad");
				return false;
			}
		}
		
		System.out.println("Data is clean");
		return true;
	}
	
}
