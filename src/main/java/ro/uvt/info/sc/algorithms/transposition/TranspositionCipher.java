package ro.uvt.info.sc.algorithms.transposition;

/**
 * Transposition cipher implementation.
 * 
 * @author Catalin Florea
 */
public class TranspositionCipher {

	/**
	 * Ciphers a message with the transpositon cipher.
	 * 
	 * @param unencryptedMessage the unencrypted message
	 * @param key the key
	 * @return the encrypted message
	 */
	public static String cipher(String unencryptedMessage, String key) {
		char[] keyArray = key.toCharArray();
		char[] messageArray = unencryptedMessage.toLowerCase().replaceAll("\\p{Punct}", "").replaceAll(" ", "").toCharArray();
		
		int columns = keyArray.length;
		
		int rows = messageArray.length / keyArray.length + 1;
		if (messageArray.length % keyArray.length != 0) {
			rows++;
		}
		
		char[][] charMatrix = new char[rows][columns];
		charMatrix[0] = keyArray;

		int messageIndex = 0;
		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (messageIndex >= messageArray.length) {
					charMatrix[i][j] = 'x';
					continue;
				}
				
				charMatrix[i][j] = messageArray[messageIndex++];
			}
		}
		
		for (int j = 0; j < columns - 1; j++) {
			for (int k = j + 1; k < columns; k++) {
				if (charMatrix[0][j] > charMatrix[0][k]) {
					for (int i = 0; i < rows; i++) {
						char aux = charMatrix[i][j];
						charMatrix[i][j] = charMatrix[i][k];
						charMatrix[i][k] = aux;
					}
				}
			}
		}
		
		StringBuilder result = new StringBuilder();
		for (int j = 0; j < rows; j++) {
			for (int i = 1; i < columns; i++) {
				result.append(charMatrix[i][j]);
			}
		}
		
		return result.toString();
	}
	
}
