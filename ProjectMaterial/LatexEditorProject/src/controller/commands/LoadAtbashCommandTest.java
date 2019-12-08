package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import model.DocumentManager;
import model.VersionsManager;



class LoadAtbashCommandTest {
	private DocumentManager documentManager = new DocumentManager();
	private VersionsManager versionsManager = new VersionsManager();
	private LoadAtbashCommand atbash = new LoadAtbashCommand(versionsManager);
	private CreateCommand createCommand = new CreateCommand(documentManager, versionsManager);
	
	@Test
	void testLoadAtbash() {
		versionsManager.setFilename("atbash_test.tex");
		atbash.execute();
		String decodedInput = versionsManager.getDocument().getContents();
		String encodedInput = "";
		try {
			Scanner scanner = new Scanner(new FileInputStream("test.tex"));
			while(scanner.hasNextLine()) {
				encodedInput = encodedInput+ scanner.nextLine() + "\n";
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		versionsManager.setDocumentType("articleTemplate");
		createCommand.execute();
		String expectedContent = versionsManager.getDocument().getContents();
		
		assertEquals(expectedContent, decodedInput);
	}
}
