package controller.commands;


import model.FileManager;
import model.VersionsManager;

public class LoadCommand extends AbstractCommand {
	
	public LoadCommand(VersionsManager versionsManager) {
		super(versionsManager);
	}

	@Override
	public void execute() {
		FileManager fManager = new FileManager();
		fManager.loadFromFile(versionsManager);
		fManager.arrangeType(versionsManager);
	}
}
