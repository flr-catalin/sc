package ro.uvt.info.sc.algorithms.utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for working with String objects.
 * 
 * @author Catalin Florea
 */
public class StringUtils {
	
	/**
	 * Processes the unencrypted message. Only keeps
	 * the separators and the letters.
	 * 
	 * @param unencryptedMessage the unencrypted message
	 * @return the processed message
	 */
	public static char[] processUnencryptedMessage(String unencryptedMessage) {
		return processUnencryptedMessage(unencryptedMessage, true);
	}
	
	/**
	 * Processes the unencrypted message. You can choose
	 * whether the separators are kept.
	 * 
	 * @param unencryptedMessage the unencrypted message
	 * @param keepSeparators whether to keep separators
	 * @return the processed message
	 */
	public static char[] processUnencryptedMessage(String unencryptedMessage, boolean keepSeparators) {
		char[] messageArray = unencryptedMessage.toLowerCase().toCharArray();
		
		StringBuilder processedMessage = new StringBuilder();
		for (char character : messageArray) {
			if (isLetter(character) || (isSeparator(character) && keepSeparators)) {
				processedMessage.append(character);
			}
		}
		
		return processedMessage.toString().toCharArray();
	}
	
	/**
	 * Chechs whether the character is a separator
	 * (new line, tab, space).
	 * 
	 * @param character the checked character
	 * @return true, if the character is a separator
	 */
	public static boolean isSeparator(char character) {
		return ('\n' == character) || ('\r' == character) || ('\t' == character) || (' ' == character);
	}
	
	/**
	 * Splits the given strings into subsequences of the given size.
	 * 
	 * @param string the string
	 * @param size the size of the subsequences
	 * @return the split string
	 */
	public static List<String> getSubsequences(String string, int size) {
	    List<StringBuilder> subsequenceBuilders = new ArrayList<>(size);
	    for (int i = 0; i < size; i++) {
	    	subsequenceBuilders.add(i, new StringBuilder());
	    }

	    for (int i = 0; i < string.length(); i++) {
	    	subsequenceBuilders.get(i % size).append(string.charAt(i));
	    }
	    
	    List<String> subsequences = new ArrayList<>();
	    for (StringBuilder builder : subsequenceBuilders) {
	    	subsequences.add(builder.toString());
	    }
	    
	    return subsequences;
	}
	
	/**
	 * Checks whether the given string is null or empty.
	 * 
	 * @param string the string to check
	 * @return true, if the string is null or empty
	 */
	public static boolean isEmpty(String string) {
		return hasContents(string) == false;
	}
	
	/**
	 * Checks whether the given string has contents.
	 * 
	 * @param string the string to check
	 * @return true, if the string has contents
	 */
	public static boolean hasContents(String string) {
		return string != null && string.length() > 0;
	}
	
	/**
	 * Gets the length of the given string.
	 * 
	 * @param string the string to check
	 * @return the length of the string
	 */
	public static int length(String string) {
		if (string == null) {
			return 0;
		}
		
		return string.length();
	}
	
	/**
	 * Checks whether the character is a lowercase letter
	 * from the English alphabet.
	 */
	private static boolean isLetter(char character) {
		return ('a' <= character) && (character <= 'z');
	}

}
