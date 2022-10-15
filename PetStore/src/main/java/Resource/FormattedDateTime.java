package Resource;

import java.util.Calendar;

public class FormattedDateTime {
	
	
	public String getDateTime() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR)+"_"+c.get(Calendar.MONTH)+"_"+c.get(Calendar.DAY_OF_MONTH)+"_"+c.get(Calendar.HOUR)+"_"+c.get(Calendar.MINUTE)+"_"+c.get(Calendar.SECOND);
	}
	
	
	

}
