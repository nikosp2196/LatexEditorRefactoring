package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileManager {
	public void loadFromFile(VersionsManager vManager) {
		String fileContents = "";
		Document currentDocument;
		String type;
		String filename = vManager.getFilename();
		
		try {
			Scanner scanner = new Scanner(new FileInputStream(filename));
			while(scanner.hasNextLine()) {
				fileContents = fileContents + scanner.nextLine() + "\n";
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentDocument = new Document();
		currentDocument.setContents(fileContents);
		vManager.setDocument(currentDocument);
	}
	
	public String[] loadFromFile(String filename) {
		String fileContents = "";
		String type = "";
		String[] result = {"",""};
		try {
			Scanner scanner = new Scanner(new FileInputStream(filename));
			while(scanner.hasNextLine()) {
				fileContents = fileContents + scanner.nextLine() + "\n";
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		type = defineTemplate(fileContents.trim());
		result[0] = type;
		result[1] = fileContents;

		return result;
	}
	
	public void arrangeType(VersionsManager vManager) {
		String rawContents = vManager.getDocument().getContents();
		String type = defineTemplate(rawContents.trim());
		vManager.setDocumentType(type);
	}
	
	public void saveToFile(String filename, String contents) {
		try {
			PrintWriter printWriter = new PrintWriter(new FileOutputStream(filename));
			printWriter.write(contents);
			printWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String defineTemplate(String inputString) {
		
		String type = "emptyTemplate";
		if(inputString.startsWith("\\documentclass[11pt,twocolumn,a4paper]{article}")) {
			type = "articleTemplate";
		}
		else if(inputString.startsWith("\\documentclass[11pt,a4paper]{book}")) {
			type = "bookTemplate";
		}
		else if(inputString.startsWith("\\documentclass[11pt,a4paper]{report}")) {
			type = "reportTemplate";
		}
		else if(inputString.startsWith("\\documentclass{letter}")) {
			type = "letterTemplate";
		}
		
		return type;
	}
}
