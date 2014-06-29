package org.askalon.jlibcloud.compute.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

	private Utils() {
	}

	/**
	 * Load a the properties from a given filePath
	 * 
	 * @param filePath
	 *            the path to the .properties file
	 * 
	 * @return the Properties object from the filePath
	 */
	public static Properties loadPropertiesFrom(String filePath) {
		Properties prop = new Properties();
		try {
			File f1 = new File(filePath);
			if (f1.exists()) {
				InputStream fis = new FileInputStream(f1);
				prop.load(fis);
				fis.close();
			} else {
				prop.load(Utils.class.getClassLoader().getResourceAsStream(
						filePath));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}
