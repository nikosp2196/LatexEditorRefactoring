package controller.commands;

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import model.DocumentManager;
import model.VersionsManager;

class LoadRot13CommandTest {
	private DocumentManager documentManager = new DocumentManager();
	private VersionsManager versionsManager = new VersionsManager();
	private LoadRot13Command rot13 = new LoadRot13Command(versionsManager);
	private CreateCommand createCommand = new CreateCommand(documentManager, versionsManager);
	
	@Test
	void test() {
		versionsManager.setFilename("rot13_test.tex");
		rot13.execute();
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
