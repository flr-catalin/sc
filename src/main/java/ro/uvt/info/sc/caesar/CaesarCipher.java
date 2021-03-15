package ro.uvt.info.sc.caesar;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import ro.uvt.info.sc.dictionary.DictionaryAPI;

public class CaesarCipher {

	public static String cipher(String message, int offset) {
		StringBuilder resultBuilder = new StringBuilder();
		message = message.toLowerCase().replaceAll("\\p{Punct}", "");

		for (char character : message.toCharArray()) {
			if (!isSeparator(character)) {
				resultBuilder.append(getNewCharacter(character, offset));
			} else {
				resultBuilder.append(character);
			}
		}

		return resultBuilder.toString();
	}
	
	public static List<String> decipher(String message) {
		List<String> resultList = new ArrayList<>();

		for (int offset = 1; offset < 26; offset++) {
			resultList.add(decipher(message, offset));
		}

		return resultList;
	}
	
	public static String decipher(String message, int offset) {
		int complementaryOffset = 26 - (offset % 26);

		return cipher(message, complementaryOffset);
	}
	
	public static List<String> decipherWithDictionary(String message) {
		return decipherWithDictionary(message, .9f);
	}
	
	public static List<String> decipherWithDictionary(String message, float tolerance) {
		return decipherWithDictionary(message, tolerance, 1);
	}

	public static List<String> decipherWithDictionary(String message, float tolerance, int maximumResults) {
		DictionaryAPI dictionary = new DictionaryAPI();

		List<String> resultList = new ArrayList<>();

		try {
			for (int offset = 1; offset < 26; offset++) {
				System.out.print("Offset: " + offset + "... ");
				
				String decipher = decipher(message, offset);
				StringTokenizer tokenizer = new StringTokenizer(decipher);

				float numberOfValidTokens = 0f;
				int numberOfTokens = tokenizer.countTokens();
				int remainingTokens = numberOfTokens;
				
				boolean skip = false;
				while (tokenizer.hasMoreTokens()) {
					String token = tokenizer.nextToken();
					remainingTokens--;
					
					float maximumRatio = (numberOfValidTokens + remainingTokens) / numberOfTokens;
					if (maximumRatio < tolerance) {
						skip = true;
						continue;
					}

					if (dictionary.find(token) == HttpURLConnection.HTTP_OK) {
						numberOfValidTokens++;
					}
				}
				
				if (skip) {
					System.out.println("Skipping, maximum validity ratio too low.");
					continue;
				}

				float ratio = numberOfValidTokens / numberOfTokens;
				
				System.out.println("Ratio: " + ratio * 100 + "%");
				
				if (ratio >= tolerance) {
					resultList.add(decipher);
					
					if (resultList.size() == maximumResults) {
						return resultList;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultList;
	}

	private static boolean isSeparator(char character) {
		return character == ' ';
	}

	private static char getNewCharacter(char character, int offset) {
		int originalAlphabetPosition = character - 'a';
		int newAlphabetPosition = (originalAlphabetPosition + offset) % 26;

		return (char) ('a' + newAlphabetPosition);
	}

}
