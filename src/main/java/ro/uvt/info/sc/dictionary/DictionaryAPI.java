package ro.uvt.info.sc.dictionary;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public class DictionaryAPI {
	
	private final Map<String, Integer> responseCache = new HashMap<>();
	
	private final URI baseUri = URI.create("https://api.dictionaryapi.dev/api/v2/entries/");
	
	@Setter private Language language;

	public DictionaryAPI() {
		this(Language.EnglishUS);
	}
	
	public int find(String word) throws IOException {
		if (responseCache.containsKey(word)) {
			return responseCache.get(word).intValue();
		} else {
			int responseCode = getConnection(word).getResponseCode();
			responseCache.put(word, new Integer(responseCode));
			return responseCode;
		}
	}
	
	private HttpURLConnection getConnection(String word) throws IOException {
		StringBuilder specBuilder = new StringBuilder();
		specBuilder.append(language.getURL()).append(word);
		
		URL relativeUrl = new URL(baseUri.toURL(), specBuilder.toString());
		HttpURLConnection connection = (HttpURLConnection) relativeUrl.openConnection();
		connection.setRequestMethod("GET");
		
		return connection;
	}

}
