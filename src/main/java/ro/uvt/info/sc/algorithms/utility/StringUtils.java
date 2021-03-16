package ro.uvt.info.sc.algorithms.utility;

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
	 * Checks whether the character is a lowercase letter
	 * from the English alphabet.
	 */
	private static boolean isLetter(char character) {
		return ('a' <= character) && (character <= 'z');
	}

}