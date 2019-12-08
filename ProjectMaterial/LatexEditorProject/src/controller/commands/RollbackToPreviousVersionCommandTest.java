package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.DocumentManager;
import model.VersionsManager;


class RollbackToPreviousVersionCommandTest {
	
	private DocumentManager documentManager = new DocumentManager();
	private VersionsManager versionsManager = new VersionsManager();
	private CreateCommand createCommand = new CreateCommand(documentManager, versionsManager);
	private EditCommand editCommand = new EditCommand(versionsManager);
	private RollbackToPreviousVersionCommand rollback = new RollbackToPreviousVersionCommand(versionsManager);
	
	@Test
	void testVolatile() {
		versionsManager.setDocumentType("articleTemplate");
		createCommand.execute();
		String actualContents = versionsManager.getDocument().getContents();
		versionsManager.setContent("test edit contents\n");
		editCommand.execute();
		rollback.execute();
		String contents = versionsManager.getDocument().getContents();
		
		assertEquals(contents, actualContents);
	}
}
