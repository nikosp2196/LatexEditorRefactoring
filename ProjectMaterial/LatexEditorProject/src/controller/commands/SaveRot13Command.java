package controller.commands;

import model.Document;
import model.FileManager;
import model.Rot13Encryption;
import model.VersionsManager;

public class SaveRot13Command extends AbstractCommand{
	
	public SaveRot13Command(VersionsManager versionsManager) {
		super(versionsManager);
	}
	
	@Override
	public void execute() {
		FileManager fManager = new FileManager();
		Rot13Encryption rot13 = new Rot13Encryption();
		Document encoded = rot13.executeRot13(versionsManager.getDocument());
		String filename = versionsManager.getFilename();
		String contents = encoded.getContents();
		fManager.saveToFile(filename, contents);
	}

}
