package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import model.DocumentManager;
import model.VersionsManager;

class SaveRot13CommandTest {
	private DocumentManager documentManager = new DocumentManager();
	private VersionsManager versionsManager = new VersionsManager();
	private CreateCommand createCommand = new CreateCommand(documentManager, versionsManager);
	private SaveRot13Command rot13 = new SaveRot13Command(versionsManager);
	private EditCommand editCommand = new EditCommand(versionsManager);
	
	@Test
	void testRot13Save() {
		versionsManager.setDocumentType("emptyTemplate");
		createCommand.execute();
		versionsManager.setContent("Hello");
		editCommand.execute();
		versionsManager.setFilename("rot13_save.tex");
		rot13.execute();
		String encoded = "";
		try {
			Scanner scanner = new Scanner(new FileInputStream("rot13_save.tex"));
			while(scanner.hasNextLine()) {
				encoded = encoded + scanner.nextLine() + "\n";
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String expected = "Uryyb\n";
		assertEquals(encoded, expected);
	}
}
