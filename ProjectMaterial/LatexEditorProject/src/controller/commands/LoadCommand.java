package controller.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.Document;
import model.VersionsManager;

public class LoadCommand implements Command {
	private VersionsManager versionsManager;
	
	public LoadCommand(VersionsManager versionsManager) {
		super();
		this.versionsManager = versionsManager;
	}

	public VersionsManager getVersionsManager() {
		return versionsManager;
	}

	public void setVersionsManager(VersionsManager versionsManager) {
		this.versionsManager = versionsManager;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		String fileContents = "";
		Document currentDocument;
		String type;
		String filename = versionsManager.getFilename();
		
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
		versionsManager.setDocumentType(type);
		versionsManager.setDocument(currentDocument);
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
