package ro.uvt.info.sc.algorithms.vigenere;

public class MainDemo {
	
	public static void main(String[] args) {
		System.out.println(VigenereCipher.indexOfCoincidence("To be, or not to be, that is the question-\r\n" + 
				"Whether 'tis Nobler in the mind to suffer\r\n" + 
				"The Slings and Arrows of outrageous Fortune,\r\n" + 
				"Or to take Arms against a Sea of troubles,\r\n" + 
				"And by opposing end them?\r\n" + 
				"William Shakespeare - Hamlet"));
	}

}
