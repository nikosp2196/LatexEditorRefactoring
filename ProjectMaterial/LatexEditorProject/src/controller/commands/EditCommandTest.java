package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.DocumentManager;
import model.VersionsManager;

class EditCommandTest {
	private DocumentManager documentManager = new DocumentManager();
	private VersionsManager versionsManager = new VersionsManager();
	private CreateCommand createCommand = new CreateCommand(documentManager, versionsManager);
	private EditCommand editCommand = new EditCommand(versionsManager);

	@Test
	void test() {
		versionsManager.setDocumentType("articleTemplate");
		createCommand.execute();
		
		versionsManager.setContent("test edit contents\n");
		editCommand.execute();
		String actualContents = versionsManager.getDocument().getContents();
		
		assertEquals("test edit contents\n", actualContents);
	}

}
