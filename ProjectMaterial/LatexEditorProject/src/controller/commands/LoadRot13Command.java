package controller.commands;

import model.FileManager;
import model.Rot13Encryption;
import model.VersionsManager;

public class LoadRot13Command extends AbstractCommand {
	
	public LoadRot13Command(VersionsManager versionsManager) {
		super(versionsManager);
	}
	
	@Override
	public void execute() {
		FileManager fManager = new FileManager();
		Rot13Encryption rot13 = new Rot13Encryption();
		fManager.loadFromFile(versionsManager);
		versionsManager.setDocument(rot13.executeRot13(versionsManager.getDocument()));
		fManager.arrangeType(versionsManager);
	}

}
