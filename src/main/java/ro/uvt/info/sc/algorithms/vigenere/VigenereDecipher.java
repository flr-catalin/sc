package ro.uvt.info.sc.algorithms.vigenere;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import ro.uvt.info.sc.algorithms.caesar.CaesarCipher;
import ro.uvt.info.sc.algorithms.utility.StringUtils;
import ro.uvt.info.sc.algorithms.vigenere.utility.FileUtils;

/**
 * This class is used for performing the cryptanalysis
 * against the Vigenere cipher.
 * 
 * @author Catalin Florea
 */
public class VigenereDecipher {
	
	/** The maximum key length. */
	private int maximumKeyLength;

	/** The encrypted message. */
	private String encryptedMessage;

	/** The subsequence lists by key length. */
	private Map<Integer, SubsequenceList> subsequenceListByKeyLength;
	
	/** The subsequence lists by average IC. */
	private SortedMap<Float, SubsequenceList> subsequenceListByAverageIC;
	
	/** Whether to try all key lenghts. */
	private boolean tryAllKeyLengths;
	
	/**
	 * Instantiates a VigenereDecipher object.
	 * 
	 * @param fileName the name of the file containing the encrypted message
	 * @param tryAllKeyLengths whether to try all the key lengths
	 */
	public VigenereDecipher(String fileName, boolean tryAllKeyLengths) {
		this(fileName, 10, tryAllKeyLengths);
	}
	
	/**
	 * Instantiates a VigenereDecipher object.
	 * 
	 * @param fileName the name of the file containing the encrypted message
	 * @param maximumKeyLength the maximum key length
	 * @param tryAllKeyLengths whether to try all the key lengths
	 */
	public VigenereDecipher(String fileName, int maximumKeyLength, boolean tryAllKeyLengths) {
		this.encryptedMessage = FileUtils.readFromFile(fileName);
		this.maximumKeyLength = maximumKeyLength;
		this.subsequenceListByKeyLength = new HashMap<>();
		this.subsequenceListByAverageIC = new TreeMap<>(Collections.reverseOrder());
		this.tryAllKeyLengths = tryAllKeyLengths;
	}
	
	/**
	 * Executes the cryptanalysis and prints information in the console.
	 */
	public void execute() {
		buildSubsequenceListByKeyLength();
		displaySubsequenceListByKeyLength();
		
		buildSubsequenceListByAverageIC();
		displaySubsequenceListByAverageIC();
		
		findPotentialKeys();
	}

	/**
	 * Builds a map of subsequences by key length.
	 */
	private void buildSubsequenceListByKeyLength() {
		for (int keyLength = 1; keyLength < maximumKeyLength; keyLength++) {
			List<String> subsequences = StringUtils.getSubsequences(encryptedMessage, keyLength);
			SubsequenceList subsequenceList = new SubsequenceList(subsequences);
			subsequenceListByKeyLength.put(keyLength, subsequenceList);
		}
	}
	
	/**
	 * Displays the map of subsequences by key length.
	 */
	private void displaySubsequenceListByKeyLength() {
		for (Integer keyLength : subsequenceListByKeyLength.keySet()) {
			SubsequenceList subsequenceList = subsequenceListByKeyLength.get(keyLength);
			
			System.out.format("Key length: %d \n", keyLength);
			for (Subsequence subsequence : subsequenceList.subsequenceList) {
				System.out.format("%11s [%8f] \t %s \n", "IC:", subsequence.IC, subsequence.message);
			}
			System.out.format("%11s [%8f] \n \n", "Average IC:", subsequenceList.averageIC);
		}
	}
	
	/**
	 * Builds the map of subsequences by average IC.
	 */
	private void buildSubsequenceListByAverageIC() {
		for (SubsequenceList subsequenceList : subsequenceListByKeyLength.values()) {
			subsequenceListByAverageIC.put(subsequenceList.averageIC, subsequenceList);
		}
	}
	
	/**
	 * Displays the map of subsequences by average IC.
	 */
	private void displaySubsequenceListByAverageIC() {
		for (SubsequenceList subsequenceList : subsequenceListByAverageIC.values()) {
			Integer keyLength = getKeyLengthForSubsequenceList(subsequenceList);
			System.out.format("Found average IC [%8f] for key length %d \n", subsequenceList.averageIC, keyLength);
		}
		System.out.format("\n");
	}
	
	/**
	 * Finds the key length for a given subsequence list.
	 */
	private Integer getKeyLengthForSubsequenceList(SubsequenceList subsequenceList) {
		for (Integer keyLength : subsequenceListByKeyLength.keySet()) {
			SubsequenceList foundSubsequenceList = subsequenceListByKeyLength.get(keyLength);
			
			if (foundSubsequenceList.equals(subsequenceList)) {
				return keyLength;
			}
		}
		
		return new Integer(0);
	}
	
	/**
	 * Finds the potential keys for decrypting the message.
	 */
	private void findPotentialKeys() {
		// iterate through all the generated subsequences by key length (first set has the highest average IC)
		for (SubsequenceList subsequenceList : subsequenceListByAverageIC.values()) {
			StringBuilder keyBuilder = new StringBuilder();

			Integer keyLength = getKeyLengthForSubsequenceList(subsequenceList);
			System.out.format("Trying decryption for key length %d... \n \n", keyLength);
			
			// find the minimum chi squared value from the subsequences (by key length)
			for (Subsequence subsequence : subsequenceList.subsequenceList) {
				Float minimumChiSquared = Float.MAX_VALUE;
				int minimumChiSquaredIndex = 0;

				for (int index = 0; index < subsequence.possibleDecryptions.size(); index++) {
					String possibleDecryption = subsequence.possibleDecryptions.get(index);
					Float chiSquared = VigenereCipher.chiSquared(possibleDecryption);
					
					if (chiSquared < minimumChiSquared) {
						minimumChiSquared = chiSquared;
						minimumChiSquaredIndex = index;
					}
					
					System.out.format("[%c] %7s [%8f] \t %s \n", 'A' + index, "Chi-Sq:", chiSquared, possibleDecryption);
				}

				// build the key
				keyBuilder.append((char) ('A' + minimumChiSquaredIndex));
				
				System.out.format("\n");
			}
			
			String key = keyBuilder.toString();
			System.out.format("Potential key found: %s \n", key);
			
			// perform decipher
			String decipheredMessage = VigenereCipher.decipher(encryptedMessage, key);
			System.out.format("%s \n \n", decipheredMessage);
			
			// skip others if wanted
			if (!tryAllKeyLengths) {
				break;
			}
		}
	}
	
	/**
	 * SubsequenceList POJO.
	 * 
	 * @author Catalin Florea
	 */
	private class SubsequenceList {
		
		/** The list of subsequences. */
		private List<Subsequence> subsequenceList;
		
		/** The average IC of the list. */
		private Float averageIC;
		
		/**
		 * Instantiates a SubsequenceList object. Also calculates the average IC.
		 * 
		 * @param subsequences the subsequences
		 */
		private SubsequenceList(List<String> subsequences) {
			this.subsequenceList = new ArrayList<>();
			this.averageIC = new Float(0f);
			
			for (String subsequence : subsequences) {
				Float IC = VigenereCipher.indexOfCoincidence(subsequence);
				
				subsequenceList.add(new Subsequence(subsequence, IC));
				averageIC += IC;
			}
			
			averageIC /= subsequenceList.size();
		}
		
	}
	
	/**
	 * Subsequence POJO.
	 * 
	 * @author Catalin Florea
	 */
	private class Subsequence {
		
		/** The message. */
		private String message;
		
		/** The IC. */
		private Float IC;
		
		/** All the possible decryptions. */
		private List<String> possibleDecryptions;

		/**
		 * Instantiates a Subsequence object. Also generates all the
		 * possible decryptions using the Caesar cipher.
		 * 
		 * @param message the message
		 * @param IC the IC
		 */
		private Subsequence(String message, Float IC) {
			this.message = message;
			this.IC = IC;
			this.possibleDecryptions = CaesarCipher.forceDecipher(message, true);
		}
		
	}
	
}
