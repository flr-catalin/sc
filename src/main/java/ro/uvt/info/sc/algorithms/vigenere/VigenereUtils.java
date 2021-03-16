package ro.uvt.info.sc.algorithms.vigenere;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.io.IOUtils;

public class VigenereUtils {

	public static void chiSquared(String message) {

	}

	public static Float indexOfCoincidence(String message) {
		SortedMap<Character, Float> frequencies = getFrequencies(message);

		return calculateIndexOfCoincidence(message, frequencies);
	}

	public static Float indexOfCoincidenceDelta(String message) {
		String fileName = "sample.txt";
		ClassLoader classLoader = VigenereUtils.class.getClassLoader();

		try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
			String fileMessage = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

			Float sourceIndexOfCoincidence = indexOfCoincidence(message);
			Float targetIndexOfCoincidence = indexOfCoincidence(fileMessage);

			Float delta = sourceIndexOfCoincidence - targetIndexOfCoincidence;

			return new Float(Math.abs(delta.floatValue()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new Float(-1f);
	}

	private static SortedMap<Character, Float> getFrequencies(String message) {
		SortedMap<Character, Float> frequencies = new TreeMap<>();

		for (char current : formatMessage(message).toCharArray()) {
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

	private static Float calculateIndexOfCoincidence(String message, SortedMap<Character, Float> frequencies) {
		Float sum = new Float(0f);
		for (Character character : frequencies.keySet()) {
			Float frequency = frequencies.get(character);
			sum = sum + (frequency * (frequency - new Float(1f)));
		}

		int messageLength = formatMessage(message).length();

		return sum / (messageLength * (messageLength - 1));
	}

	private static String formatMessage(String message) {
		return message.toLowerCase().replaceAll("\\p{Punct}| |\\n|\\r", "");
	}

}
