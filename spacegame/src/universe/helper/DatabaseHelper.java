package universe.helper;

import java.sql.Timestamp;
import java.util.Date;

public class DatabaseHelper {

	public static Timestamp toTimeStamp(Date d) {
		if (d == null) {
			return null;
		}
		
		return new Timestamp(d.getTime());
	}
	
}
