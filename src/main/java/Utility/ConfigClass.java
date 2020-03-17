package Utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import GlobalVariables.amazon_Var;

public class ConfigClass {

	 public static Properties properties;
	 public static String workingDir = System.getProperty("user.dir");
	 public static String propertyFilePath=workingDir+amazon_Var.WorkPATH;
 
	// This Function will create/read the config file reader
	 
	public static void ConfigFileReader()
	{
		BufferedReader reader;
		try 
		{
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}
}
