package ro.uvt.info.sc.algorithms.caesar;

import java.util.ArrayList;
import java.util.List;

import ro.uvt.info.sc.algorithms.caesar.validation.MessageValidator;
import ro.uvt.info.sc.algorithms.caesar.validation.pojo.Language;
import ro.uvt.info.sc.algorithms.utility.StringUtils;

/**
 * Caesar cipher implementation. 
 * 
 * @author Catalin Florea
 */
public class CaesarCipher {

	/**
	 * Ciphers a message with the Caesar cipher.
	 * 
	 * @param unencryptedMessage the unencrypted message
	 * @param offset the key
	 * @return the encrypted message
	 */
	public static String cipher(String unencryptedMessage, int offset) {
		// only keep the separators and the letters 
		char[] unencryptedMessageArray = StringUtils.processUnencryptedMessage(unencryptedMessage);
		
		// encrypt each letter, skip the separators
		StringBuilder encryptedMessage = new StringBuilder(); 
		for (char character : unencryptedMessageArray) {
			encryptedMessage.append(getEncryptedCharacter(character, offset));
		}
		
		return encryptedMessage.toString();
	}
	
	/**
	 * Deciphers the message by encrypting with
	 * the complementary offset.
	 * 
	 * @param message the encrypted message
	 * @param offset the key
	 * @return the unencrypted message
	 */
	public static String decipher(String message, int offset) {
		int complementaryOffset = 26 - (offset % 26);

		return cipher(message, complementaryOffset);
	}
	
	/**
	 * Performs a force decipher.
	 * 
	 * @param message the encrypted message
	 * @return the list of possible decrypted messages
	 */
	public static List<String> forceDecipher(String message) {
		List<String> result = new ArrayList<>();
		for (int offset = 1; offset < 26; offset++) {
			result.add(decipher(message, offset));
		}
		
		return result;
	}
	
	/**
	 * Performs a decipher using the Dictionary API.
	 * 
	 * @param message the encrypted message
	 * @param language the encrypted message language
	 * @param tolerance the minimum validity percent
	 * @param results the maximum number of results
	 * @return the list of possible decrypted messages
	 */
	public static List<String> dictionaryDecipher(String message, Language language, float tolerance, int maxResults) {
		List<String> resultList = new ArrayList<>();
		
		// iterate through all possible solutions
		for (int offset = 1; offset < 26; offset++) {
			System.out.print("Offset: " + offset + "... ");
			String decipher = decipher(message, offset);
			
			// perform a dictionary validation
			MessageValidator tokenizer = new MessageValidator(decipher, language, tolerance);
			float ratio = tokenizer.execute();
			
			if (tokenizer.getSkippedAt() != null) {
				// if the maximum possible ratio at any point of the validation
				// is too low, we skip the entry to gain performance
				System.out.println("Skipped at " + tokenizer.getSkippedAt() * 100
						+ "%: maximum possible validity ratio too low.");
			} else {
				// otherwise we add it to the result list
				System.out.println("Validity ratio: " + ratio * 100 + "%");
				resultList.add(decipher);
			}
			
			// check if there are enough results
			if (resultList.size() == maxResults) {
				break;
			}
		}
		
		return resultList;
	}

	
	/**
	 * Encrypts letters with the key. Skips separators.
	 */
	private static char getEncryptedCharacter(char character, int offset) {
		if (StringUtils.isSeparator(character)) {
			return character;
		}
		
		int originalAlphabetPosition = character - 'a';
		int newAlphabetPosition = (originalAlphabetPosition + offset) % 26;

		return (char) ('a' + newAlphabetPosition);
	}
	
}
