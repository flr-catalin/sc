package ro.uvt.info.sc.algorithms.caesar.validation.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * Token POJO.
 * 
 * @author Catalin Florea
 */
public class Token {

	/** The value of the token */
	@Getter private String value;
	
	/** The token validity value. */
	@Getter @Setter private boolean valid;
	
	/** 
	 * Instantiates a Token object.
	 * 
	 * @param value the value of the token
	 */
	public Token(String value) {
		this.value = value;
		this.valid = false;
	}
	
}
