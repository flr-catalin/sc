package ro.uvt.info.sc.algorithms.caesar.validation.pojo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import ro.uvt.info.sc.algorithms.caesar.validation.dictionary.DictionaryAPI;

/**
 * Token list POJO.
 * 
 * @author flrca
 */
public class TokenList {

	/** The list of tokens. */
	private List<Token> tokens;
	
	/** The language of the tokens. */
	private Language language;
	
	/** The current index. */
	private int index;

	/**
	 * Instantiates a new TokenList object.
	 * 
	 * @param tokens the list of tokens
	 * @param language the language of the tokens
	 */
	public TokenList(List<Token> tokens, Language language) {
		this.tokens = tokens;
		this.language = language;
		this.index = 0;
	}
	
	/**
	 * Checks whether there are more available tokens.
	 * 
	 * @return true, if there are more tokens
	 */
	public boolean hasMoreTokens() {
		return index < tokens.size();
	}
	
	/**
	 * Gets the next token. Before getting it,
	 * its validity value is set.
	 * 
	 * @return the next token after validation
	 */
	public Token nextToken() {
		try {
			validateNextToken();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return tokens.get(index++);
	}
	
	/**
	 * Validates the next token. If there is a HTTP_OK
	 * response from the DictionaryAPI it means that
	 * the token is a valid word in the given language.
	 * 
	 * @throws IOException could not create connection
	 */
	public void validateNextToken() throws IOException {
		Token token = tokens.get(index);
		int code = DictionaryAPI.getInstance(language).find(token.getValue());
		
		if (code == HttpURLConnection.HTTP_OK) {
			token.setValid(true);
		}
		
		tokens.set(index, token);
	}
	
	/**
	 * Counts the number of tokens.
	 * 
	 * @return the number of tokens
	 */
	public int countTokens() {
		return tokens.size();
	}
	
	/**
	 * Counts the number of valid tokens.
	 * 
	 * @return the number of valid tokens.
	 */
	public int countValidTokens() {
		return (int) tokens.stream().filter(t -> t.isValid()).count();
	}
	
	/**
	 * Counts the remaining tokens.
	 * 
	 * @return the number of remaining tokens
	 */
	public int countRemainingTokens() {
		return tokens.size() - index;
	}
	
	/**
	 * Calculates the maximum ratio of validity that can be achieved
	 * by the token list, taking into consideration the already existing
	 * validity values.
	 * 
	 * @return the maximum ratio of validity
	 */
	public float maximumRatio() {
		return ((float) countValidTokens() + countRemainingTokens()) / countTokens(); 
	}
	
	/**
	 * Calculates the current ratio of validity.
	 * 
	 * @return the current ratio of validity
	 */
	public float ratio() {
		return ((float) countValidTokens()) / countTokens();
	}
	
}
