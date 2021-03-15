package ro.uvt.info.sc.dictionary;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public class DictionaryAPI {
	
	private final URI baseUri = URI.create("https://api.dictionaryapi.dev/api/v2/entries/");
	
	@Setter private Language language;

	public DictionaryAPI() {
		this(Language.EnglishUS);
	}
	
	public int find(String word) throws IOException {
		return getConnection(word).getResponseCode();
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
