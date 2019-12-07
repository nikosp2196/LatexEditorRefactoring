package controller.commands;

import model.FileManager;
import model.VersionsManager;

public class SaveCommand extends AbstractCommand {
	
	public SaveCommand(VersionsManager versionsManager) {
		super(versionsManager);
	}
	
	@Override
	public void execute() {
		FileManager fManager = new FileManager();
		String filename = versionsManager.getFilename();
		String contents = versionsManager.getDocument().getContents();
		fManager.saveToFile(filename, contents);
	}

}
