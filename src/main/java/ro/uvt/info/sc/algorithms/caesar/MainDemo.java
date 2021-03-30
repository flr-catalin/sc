package ro.uvt.info.sc.algorithms.caesar;

import java.util.List;

import ro.uvt.info.sc.algorithms.caesar.validation.pojo.Language;

public class MainDemo {

	public static void main(String[] args) {	
		
		List<String> decipher = CaesarCipher.dictionaryDecipher(
				  "uif bjn pg uijt sfqpsu jt up eftdsjcf boe bttftt uif mfjtvsf gbdjmjujft bwbjmbcmf jo bozupxo. "
				+ "ju jt cbtfe po jogpsnbujpo nbef bwbjmbcmf cz uif bozupxo upvsjtu pggjdf, boe po wjfxt fyqsfttfe "
				+ "cz mpdbm qfpqmf xip xfsf joufswjfxfe. bozupxo jt xfmm-qspwjefe xjui mfjtvsf gbdjmjujft gps b "
				+ "upxo pg jut tjaf boe uiftf bsf xfmm- vtfe cz uif upxotqfpqmf, po uif xipmf. tqpsu tffnt up cf "
				+ "uif nptu qpqvmbs mfjtvsf bdujwjuz xijmf dvmuvsbm bdujwjujft mjlf wjtjujoh uif nvtfvn ps bsu "
				+ "hbmmfsz bqqfbsfe up cf uif mfbtu qpqvmbs bnpohtu uif bozupxofst. qfsibqt uif djuz dpvodjm "
				+ "tipvme dpotjefs mbvodijoh b qvcmjdjuz dbnqbjho up tipx ipx nvdi uiftf gbdjmjujft ibwf up pggfs.",
				Language.EnglishUS, .9f, 1);
		
		decipher.stream().forEachOrdered(System.out::println);
		
	}

}
