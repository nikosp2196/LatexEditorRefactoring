package model;

public class AtbashEncryption {

	public Document executeAtbash(Document doc) {
		String inputText = doc.getContents();
		char[] textArray = inputText.toCharArray();
		String outputText = "";
		
	    for (char ch : textArray) {
	      int input = (int)ch;
	      int output = reverseCharacters(input);
	      outputText += String.valueOf((char) output);
	    }
	    doc.setContents(outputText);
	    return doc;
	}
	

	public static int reverseCharacters(int input) {
	    int output = -1;
	    if (input>=65 && input <= 90){
	      output = encrypt(65, 90, input);
	    }else if(input>=97 && input <= 122) {
	      output = encrypt(97, 122, input);
	    }else {
	      output = input;
	    }
	    return output;
	}

	public static int encrypt(int start, int end, int value){
		return start + (end - value);
	}
}
