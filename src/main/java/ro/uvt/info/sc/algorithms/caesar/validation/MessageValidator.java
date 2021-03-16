package ro.uvt.info.sc.algorithms.caesar.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import lombok.Getter;
import ro.uvt.info.sc.algorithms.caesar.validation.pojo.Language;
import ro.uvt.info.sc.algorithms.caesar.validation.pojo.Token;
import ro.uvt.info.sc.algorithms.caesar.validation.pojo.TokenList;

/**
 * Validates a message using the dictionary API.
 * 
 * @author Catalin Florea
 */
public class MessageValidator {

	/** The decrypted message. */
	private String message;
	
	/** The language of the message. */
	private Language language;
	
	/** The minimum ratio of validity in percents. */
	private float tolerance;
	
	/** The internal list of tokens. */
	private TokenList tokenList;
	
	/** The ratio at which the validation was skipped. */
	@Getter private Float skippedAt;
	
	/**
	 * Instantiates a MessageValidator object.
	 * 
	 * @param message the decrypted message
	 * @param language the decrypted message language
	 * @param tolerance the minimum validity percent
	 */
	public MessageValidator(String message, Language language, float tolerance) {
		this.message = message;
		this.language = language;
		this.tolerance = tolerance;
		this.skippedAt = null;
	}

	/**
	 * Executes validation process.
	 * 
	 * @return the validity percent
	 */
	public float execute() {
		// tokenize the message
		tokenize();
		
		// iterate through all the tokens
		while (tokenList.hasMoreTokens()) {
			// when nextToken is called, the validity of the token is set
			tokenList.nextToken();
			
			// if the maximum possible validity ratio of the
			// decrypted message is lower than the tolerance,
			// that ratio gets saved and returned
			if (tokenList.maximumRatio() < tolerance) {
				skippedAt = new Float(tokenList.ratio());
				return tokenList.ratio();
			}
		}
		
		// return the final ratio which is higher than the tolerance
		return tokenList.ratio();
	}

	/**
	 * Tokenizes the message. Populates the token list.
	 */
	private void tokenize() {
		StringTokenizer tokenizer = new StringTokenizer(message);
		
		List<Token> tokens = new ArrayList<>();
		while (tokenizer.hasMoreTokens()) {
			tokens.add(new Token(tokenizer.nextToken()));
		}
		
		tokenList = new TokenList(tokens, language);
	}
	
}
