package ro.uvt.info.sc.algorithms.vigenere.utility;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

/**
 * Utility class for working with file resources.
 * 
 * @author Catalin Florea
 */
public class FileUtils {

	/** The class loader. */
	private final static ClassLoader classLoader = FileUtils.class.getClassLoader();
	
	/**
	 * Reads a file from the resources folder.
	 * 
	 * @param fileName the name of the file
	 * @return the content of the file as a string
	 */
	public static String readFromFile(String fileName) {
		try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
			return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.err.println("The file '" + fileName + "' was not found in src/main/resources/.");
			return null;
		}
	}
	
}
