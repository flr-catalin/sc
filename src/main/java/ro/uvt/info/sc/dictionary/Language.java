package ro.uvt.info.sc.dictionary;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Language {

	EnglishUS("en_US/"),
	Hindi("hi/"),
	Spanish("es/"),
	French("fr/"),
	Japanese("ja/"),
	Russian("ru/"),
	EnglishUK("en_GB/"),
	German("de/"),
	Italian("it/"),
	Korean("ko/"),
	BrazilianPortuguese("pt-BR/"),
	Arabic("ar/"),
	Turkish("tr/");
	
	@Getter private String URL;
	
}
