package ro.uvt.info.sc.algorithms.vigenere.utility;

import java.util.SortedMap;
import java.util.TreeMap;

import lombok.Getter;

/**
 * Static class that contains frequency maps
 * for the English language.
 * 
 * @author Catalin Florea
 */
public class EnglishFrequencies {

	/** The English frequencies by Morse. */
	@Getter private static SortedMap<Character, Float> morseEnglishFrequencies = new TreeMap<>();
	
	/** The English frequencies by Oxford. */
	@Getter private static SortedMap<Character, Float> oxfordEnglishFrequencies = new TreeMap<>();
	
	static {
		morseEnglishFrequencies.put(new Character('e'), new Float(12000f));
		morseEnglishFrequencies.put(new Character('t'), new Float(9000f));
		morseEnglishFrequencies.put(new Character('a'), new Float(8000f));
		morseEnglishFrequencies.put(new Character('i'), new Float(8000f));
		morseEnglishFrequencies.put(new Character('n'), new Float(8000f));
		morseEnglishFrequencies.put(new Character('o'), new Float(8000f));
		morseEnglishFrequencies.put(new Character('s'), new Float(8000f));
		morseEnglishFrequencies.put(new Character('h'), new Float(6400f));
		morseEnglishFrequencies.put(new Character('r'), new Float(6200f));
		morseEnglishFrequencies.put(new Character('d'), new Float(4400f));
		morseEnglishFrequencies.put(new Character('l'), new Float(4000f));
		morseEnglishFrequencies.put(new Character('u'), new Float(3400f));
		morseEnglishFrequencies.put(new Character('c'), new Float(3000f));
		morseEnglishFrequencies.put(new Character('m'), new Float(3000f));
		morseEnglishFrequencies.put(new Character('f'), new Float(2500f));
		morseEnglishFrequencies.put(new Character('w'), new Float(2000f));
		morseEnglishFrequencies.put(new Character('y'), new Float(2000f));
		morseEnglishFrequencies.put(new Character('g'), new Float(1700f));
		morseEnglishFrequencies.put(new Character('p'), new Float(1700f));
		morseEnglishFrequencies.put(new Character('b'), new Float(1600f));
		morseEnglishFrequencies.put(new Character('v'), new Float(1200f));
		morseEnglishFrequencies.put(new Character('k'), new Float(800f));
		morseEnglishFrequencies.put(new Character('q'), new Float(500f));
		morseEnglishFrequencies.put(new Character('j'), new Float(400f));
		morseEnglishFrequencies.put(new Character('x'), new Float(400f));
		morseEnglishFrequencies.put(new Character('z'), new Float(200f));
		
		oxfordEnglishFrequencies.put(new Character('e'), new Float(56.88f));
		oxfordEnglishFrequencies.put(new Character('a'), new Float(43.31f));
		oxfordEnglishFrequencies.put(new Character('r'), new Float(38.64f));
		oxfordEnglishFrequencies.put(new Character('i'), new Float(38.45f));
		oxfordEnglishFrequencies.put(new Character('o'), new Float(36.51f));
		oxfordEnglishFrequencies.put(new Character('t'), new Float(35.43f));
		oxfordEnglishFrequencies.put(new Character('n'), new Float(33.92f));
		oxfordEnglishFrequencies.put(new Character('s'), new Float(29.23f));
		oxfordEnglishFrequencies.put(new Character('l'), new Float(27.98f));
		oxfordEnglishFrequencies.put(new Character('c'), new Float(23.13f));
		oxfordEnglishFrequencies.put(new Character('u'), new Float(18.51f));
		oxfordEnglishFrequencies.put(new Character('d'), new Float(17.25f));
		oxfordEnglishFrequencies.put(new Character('p'), new Float(16.14f));
		oxfordEnglishFrequencies.put(new Character('m'), new Float(15.36f));
		oxfordEnglishFrequencies.put(new Character('h'), new Float(15.31f));
		oxfordEnglishFrequencies.put(new Character('g'), new Float(12.59f));
		oxfordEnglishFrequencies.put(new Character('b'), new Float(10.56f));
		oxfordEnglishFrequencies.put(new Character('f'), new Float(9.24f));
		oxfordEnglishFrequencies.put(new Character('y'), new Float(9.06f));
		oxfordEnglishFrequencies.put(new Character('w'), new Float(6.57f));
		oxfordEnglishFrequencies.put(new Character('k'), new Float(5.61f));
		oxfordEnglishFrequencies.put(new Character('v'), new Float(5.13f));
		oxfordEnglishFrequencies.put(new Character('x'), new Float(1.48f));
		oxfordEnglishFrequencies.put(new Character('z'), new Float(1.39f));
		oxfordEnglishFrequencies.put(new Character('j'), new Float(1.00f));
		oxfordEnglishFrequencies.put(new Character('q'), new Float(1f));
	}
	
}
