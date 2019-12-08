package controller;

import java.util.HashMap;

import controller.commands.Command;
import controller.commands.CommandFactory;
import model.LatexSyntaxManager;
import model.VersionsManager;

public class LatexEditorController{
	private HashMap<String, Command> commands;
	private VersionsManager versionsManager;
	private LatexSyntaxManager syntaxManager;
	private String[] commandList = {"loadRot13", "saveRot13", "loadAtbash", "saveAtbash", "changeVersionsStrategy", "create", 
			"disableVersionsManagement", "edit", "enableVersionsManagement", "load", "rollbackToPreviousVersion", "save"};
	public LatexEditorController() {
		versionsManager = new VersionsManager();
		CommandFactory commandFactory = new CommandFactory(versionsManager);
		commands = new HashMap<String, Command>();
		for (String comm : commandList) {
			commands.put(comm, commandFactory.createCommand(comm));
		}		
	}
	
	public void enact(String command) {
		commands.get(command).execute(); 
	}
	
	public VersionsManager getVersionsManager() {
		return versionsManager;
	}
	
	public String initializeSyntaxManager(String before, String after, String newSyntax) {
		
		syntaxManager = new LatexSyntaxManager(before, after);
		String newContent = syntaxManager.addSyntax(newSyntax);
		versionsManager.setContent(newContent);
		return newContent;
	}
}
