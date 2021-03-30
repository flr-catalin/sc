package ro.uvt.info.sc.algorithms.caesar;

import java.util.List;

import ro.uvt.info.sc.algorithms.caesar.validation.pojo.Language;
import ro.uvt.info.sc.algorithms.vigenere.utility.FileUtils;

public class MainDemo {

	public static void main(String[] args) {	
		
		// read message
		String encryptedMessage = FileUtils.readFromFile("subiect1.txt");
		
		// perform a force decipher
		List<String> forceDecipher = CaesarCipher.forceDecipher(encryptedMessage);
		for (int index = 0; index < forceDecipher.size(); index++) {
			System.out.format("[%d] %s \n", index + 1, forceDecipher.get(index));
		}
		System.out.format("\n");

		// display the final result
		String result = CaesarCipher.decipher(encryptedMessage, 11);
		System.out.format("Result key: [%d] %s \n \n", 11, result);
		
		// demonstration only - dictionary decipher
		dictionaryDecipher();
		
	}
	
	/**
	 * Dictionary decipher - could not be applied to
	 * the given subject because it would have required spaces. 
	 */
	public static void dictionaryDecipher() {
		System.out.format("Extra: Dictionary decipher (would have required spaces) \n");
		List<String> dictionaryDecipher = CaesarCipher.dictionaryDecipher(
				  "wkh dlp ri wklv uhsruw lv wr ghvfuleh dqg dvvhvv wkh ohlvxuh idflolwlhv dydlodeoh lq dqbwrzq lw lv edvhg rq lqirupdwlrq"
				+ "pdgh dydlodeoh eb wkh dqbwrzq wrxulvw riilfh dqg rq ylhzv hasuhvvhg eb orfdo shrsoh zkr zhuh lqwhuylhzhg dqbwrzq lv"
				+ "zhoosurylghg zlwk ohlvxuh idflolwlhv iru d wrzq ri lwv vlch dqg wkhvh duh zhoo xvhg eb wkh wrzqvshrsoh rq wkh zkroh"
				+ "vsruw vhhpv wr eh wkh prvw srsxodu ohlvxuh dfwlylwb zkloh fxowxudo dfwlylwlhv olnh ylvlwlqj wkh pxvhxp ru duw jdoohub"
				+ "dsshduhg wr eh wkh ohdvw srsxodu dprqjvw wkh dqbwrzqhuv shukdsv wkh flwb frxqflo vkrxog frqvlghu odxqfklqj d sxeolflwb"
				+ "fdpsdljq wr vkrz krz pxfk wkhvh idflolwlhv kdyh wr riihu",
				Language.EnglishUS, .9f, 1);
		
		System.out.format("\n");
		
		dictionaryDecipher.stream().forEachOrdered(System.out::println);
	}

}
