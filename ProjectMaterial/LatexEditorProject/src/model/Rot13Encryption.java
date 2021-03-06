package model;

public class Rot13Encryption{

	public Document executeRot13(Document doc) {
		String inputText = doc.getContents();
		char[] textArray = inputText.toCharArray();
		String outputText = "";
		
	    for (char ch : textArray) {
	      int input = (int)ch;
	      int output = rotateCharacters(input);
	      outputText += String.valueOf((char) output);
	    }
	    doc.setContents(outputText);
	    return doc;
	}

	public static int rotateCharacters(int input) {
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
	    return start + (value - start + 13)%26;
	}
}
