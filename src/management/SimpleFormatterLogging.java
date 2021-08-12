// SimpleFormatter in Java Logging API
package management;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SimpleFormatterLogging{
	public Logger logger;
	FileHandler fh;
	
	public SimpleFormatterLogging(String file_name) throws SecurityException, IOException {
		// TODO Auto-generated constructor stub
		File f = new File(file_name);
		if(!f.exists()) {
			f.createNewFile();
		}
		
		fh = new FileHandler(file_name,true);
		logger = Logger.getLogger("test");
		logger.addHandler(fh);
		SimpleFormatter formatter  = new SimpleFormatter();
		fh.setFormatter(formatter);
	}
}