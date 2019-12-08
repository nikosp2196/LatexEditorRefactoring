package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import model.DocumentManager;
import model.VersionsManager;

class SaveAtbashCommandTest {
	private DocumentManager documentManager = new DocumentManager();
	private VersionsManager versionsManager = new VersionsManager();
	private CreateCommand createCommand = new CreateCommand(documentManager, versionsManager);
	private SaveAtbashCommand atbash = new SaveAtbashCommand(versionsManager);
	private EditCommand editCommand = new EditCommand(versionsManager);
	
	@Test
	void testAtbashSave() {
		versionsManager.setDocumentType("emptyTemplate");
		createCommand.execute();
		versionsManager.setContent("Hello");
		editCommand.execute();
		versionsManager.setFilename("atbash_save.tex");
		atbash.execute();
		String encoded = "";
		try {
			Scanner scanner = new Scanner(new FileInputStream("atbash_save.tex"));
			while(scanner.hasNextLine()) {
				encoded = encoded + scanner.nextLine() + "\n";
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String expected = "Svool\n";
		assertEquals(encoded, expected);
	}

}
