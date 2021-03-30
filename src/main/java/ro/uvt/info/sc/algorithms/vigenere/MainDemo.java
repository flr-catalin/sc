package ro.uvt.info.sc.algorithms.vigenere;

public class MainDemo {
	
	public static void main(String[] args) {

		VigenereDecipher vigenereDecipher = new VigenereDecipher("subiect2.txt", false);
		vigenereDecipher.execute();
		
	}
	
}
