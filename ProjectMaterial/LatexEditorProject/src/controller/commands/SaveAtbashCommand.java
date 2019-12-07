package controller.commands;

import model.AtbashEncryption;
import model.Document;
import model.FileManager;
import model.VersionsManager;

public class SaveAtbashCommand extends AbstractCommand{
	
	public SaveAtbashCommand(VersionsManager versionsManager) {
		super(versionsManager);
	}
	
	@Override
	public void execute() {
		FileManager fManager = new FileManager();
		AtbashEncryption atbash = new AtbashEncryption();
		Document encoded = atbash.executeAtbash(versionsManager.getDocument());
		System.out.println(encoded.getContents());
		String filename = versionsManager.getFilename();
		String contents = encoded.getContents();
		fManager.saveToFile(filename, contents);
		
	}

}
