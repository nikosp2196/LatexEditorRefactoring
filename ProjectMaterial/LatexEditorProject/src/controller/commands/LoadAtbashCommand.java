package controller.commands;

import model.AtbashEncryption;
import model.FileManager;
import model.VersionsManager;

public class LoadAtbashCommand extends AbstractCommand{
	
	public LoadAtbashCommand(VersionsManager versionsManager) {
		super(versionsManager);
	}
	
	@Override
	public void execute() {
		FileManager fManager = new FileManager();
		AtbashEncryption atbash = new AtbashEncryption();
		fManager.loadFromFile(versionsManager);
		versionsManager.setDocument(atbash.executeAtbash(versionsManager.getDocument()));
		fManager.arrangeType(versionsManager);
	
	}

}
