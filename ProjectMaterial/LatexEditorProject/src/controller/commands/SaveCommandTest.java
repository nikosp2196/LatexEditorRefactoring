package controller.commands;

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import model.DocumentManager;
import model.VersionsManager;

class SaveCommandTest {
	private DocumentManager documentManager = new DocumentManager();
	private VersionsManager versionsManager = new VersionsManager();
	private CreateCommand createCommand = new CreateCommand(documentManager, versionsManager);
	private SaveCommand saveCommand = new SaveCommand(versionsManager);

	@Test
	void test() {
		versionsManager.setDocumentType("articleTemplate");
		createCommand.execute();
		versionsManager.setFilename("test1.tex");
		saveCommand.execute();
		String fileContents = "";
		try {
			Scanner scanner = new Scanner(new FileInputStream("test1.tex"));
			while(scanner.hasNextLine()) {
				fileContents = fileContents + scanner.nextLine() + "\n";
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actualContents = versionsManager.getDocument().getContents();
		
		assertEquals(fileContents, actualContents);
	}

}
