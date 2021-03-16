package ro.uvt.info.sc.algorithms.caesar.validation.dictionary;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import ro.uvt.info.sc.algorithms.caesar.validation.pojo.Language;

/**
 * This singleton class provides access to the Dictionary API.
 * 
 * @author Catalin Florea
 */
public class DictionaryAPI {
	
	/** The single instance. */
	private static DictionaryAPI INSTANCE = null;
	
	/** The response cache. */
	private final Map<String, Integer> responseCache = new HashMap<>();
	
	/** The base URI of the API. */
	private final URI baseUri = URI.create("https://api.dictionaryapi.dev/api/v2/entries/");
	
	/** The dictionary language. */
	private final Language language;
	
	/**
	 * Private constructor to ensure there is a single instance.
	 */
	private DictionaryAPI(Language language) {
		this.language = language;
	}
	
	/**
	 * Gets the single instance. 
	 */
	public static DictionaryAPI getInstance(Language language) {
		if (INSTANCE == null) {
			INSTANCE = new DictionaryAPI(language);
		}
		
		return INSTANCE;
	}

	/**
	 * Finds a word in the dictionary using the
	 * local cache or the API.
	 * 
	 * @param word the word to be searched for
	 * @return the response code of the request
	 * 
	 * @throws IOException could not create connection
	 */
	public int find(String word) throws IOException {
		if (responseCache.containsKey(word)) {
			return getResponseFromCache(word);
		} else {
			return getResponseFromApi(word);
		}
	}

	/**
	 * Gets the response from the local cache. 
	 */
	private int getResponseFromCache(String word) {
		return responseCache.get(word).intValue();
	}
	
	/**
	 * Gets the response from the API.
	 */
	private int getResponseFromApi(String word) throws IOException {
		int responseCode = getConnection(word).getResponseCode();
		responseCache.put(word, new Integer(responseCode));
		return responseCode;
	}
	
	/**
	 * Creates the connection to the Dictionary API.
	 * <br><code>URI: {baseUri}/{language}/{word}</code>
	 */
	private HttpURLConnection getConnection(String word) throws IOException {
		StringBuilder specBuilder = new StringBuilder();
		specBuilder.append(language.getURL()).append(word);
		
		URL relativeUrl = new URL(baseUri.toURL(), specBuilder.toString());
		HttpURLConnection connection = (HttpURLConnection) relativeUrl.openConnection();
		connection.setRequestMethod("GET");
		
		return connection;
	}

}
