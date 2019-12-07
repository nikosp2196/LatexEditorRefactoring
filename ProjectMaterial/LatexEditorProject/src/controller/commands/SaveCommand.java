package controller.commands;

import model.FileManager;
import model.VersionsManager;

public class SaveCommand extends AbstractCommand {
	
	public SaveCommand(VersionsManager versionsManager) {
		super(versionsManager);
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		String filename = versionsManager.getFilename();
		FileManager fManager = new FileManager(versionsManager);
		fManager.saveToFile(filename);
	}

}
