package ro.uvt.info.sc.algorithms.vigenere;

import java.util.SortedMap;
import java.util.TreeMap;

import ro.uvt.info.sc.algorithms.utility.StringUtils;
import ro.uvt.info.sc.algorithms.vigenere.utility.EnglishFrequencies;
import ro.uvt.info.sc.algorithms.vigenere.utility.FileUtils;

/**
 * Utility methods for deciphering Vigenere chiphers.
 *  
 * @author Catalin Florea
 */
public class VigenereCipher {

	/**
	 * Deciphers the message.
	 * 
	 * @param encryptedMessage the encrypted message
	 * @param key the key
	 * @return the unencrypted message
	 */
	public static String decipher(String encryptedMessage, final String key) {
		String unencryptedMessage = "";
		encryptedMessage = encryptedMessage.toUpperCase();
		
		for (int i = 0, j = 0; i < encryptedMessage.length(); i++) {
			char c = encryptedMessage.charAt(i);
			if (c < 'A' || c > 'Z') {
				continue;
			}
			
			unencryptedMessage += (char) ((c - key.charAt(j) + 26) % 26 + 'A');
			j = ++j % key.length();
		}
		
		return unencryptedMessage;
	}
	
	/**
	 * Calculates chi squared for the given message.
	 * 
	 * @param message the given message
	 * @return the chi squared
	 */
	public static Float chiSquared(String message) {
		char[] messageArray = StringUtils.processUnencryptedMessage(message, false);
		int messageLength = messageArray.length;
		
		SortedMap<Character, Float> messageFrequencies = getFrequencies(messageArray);
		
		// set the target frequencies here
		SortedMap<Character, Float> englishFrequencies = EnglishFrequencies.getScaledOxfordEnglishFrequencies(messageLength);
		
		Float sum = new Float(0f);
		for (Character character : englishFrequencies.keySet()) {
			Float messageFrequency = messageFrequencies.getOrDefault(character, new Float(0f));
			Float englishFrequency = englishFrequencies.get(character);
			
			double value = Math.pow(messageFrequency - englishFrequency, new Float(2f));
			sum = sum + (new Float(value) / englishFrequency);
		}
		
		return sum;
	}

	/**
	 * Calculates the index of coincidence of the given message.
	 * 
	 * @param message the message
	 * @return the index of coincidence
	 */
	public static Float indexOfCoincidence(String message) {
		char[] messageArray = StringUtils.processUnencryptedMessage(message, false);
		int messageLength = messageArray.length;

		SortedMap<Character, Float> frequencies = getFrequencies(messageArray);
		
		Float sum = new Float(0f);
		for (Character character : frequencies.keySet()) {
			Float frequency = frequencies.get(character);
			sum = sum + (frequency * (frequency - new Float(1f)));
		}

		return sum / (messageLength * (messageLength - 1));
	}

	/**
	 * Calculates the delta between the index of coincidence
	 * of the given message and the message located in
	 * sample.txt
	 * 
	 * @param message the message
	 * @return the delta
	 */
	public static Float indexOfCoincidenceDelta(String message) {
		String fileName = "sample.txt";
		
		String fileMessage = FileUtils.readFromFile(fileName);
		if (StringUtils.isEmpty(fileMessage)) {
			return new Float(-1f);
		}
		
		Float sourceIndexOfCoincidence = indexOfCoincidence(message);
		Float targetIndexOfCoincidence = indexOfCoincidence(fileMessage);

		Float delta = sourceIndexOfCoincidence - targetIndexOfCoincidence;

		return new Float(Math.abs(delta.floatValue()));
	}

	/**
	 * Gets the frequencies of characters in the given message.
	 */
	private static SortedMap<Character, Float> getFrequencies(char[] messageArray) {
		SortedMap<Character, Float> frequencies = new TreeMap<>();
		
		for (char current : messageArray) {
			Character character = new Character(current);
			if (frequencies.containsKey(character)) {
				Float frequency = frequencies.get(character);
				frequencies.put(character, frequency + new Float(1f));
			} else {
				frequencies.put(character, new Float(1f));
			}
		}

		return frequencies;
	}

}
