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
		type = defineTemplate(fileContents.trim());
		vManager.setDocumentType(type);
		vManager.setDocument(currentDocument);
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
