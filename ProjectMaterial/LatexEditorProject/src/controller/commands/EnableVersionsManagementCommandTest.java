package controller.commands;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.DocumentManager;
import model.VersionsManager;


class EnableVersionsManagementCommandTest {
	private DocumentManager documentManager = new DocumentManager();
	private VersionsManager versionsManager = new VersionsManager();
	private CreateCommand createCommand = new CreateCommand(documentManager, versionsManager);
	private EnableVersionsManagementCommand enableCommand = new EnableVersionsManagementCommand(versionsManager);
	private DisableVersionsManagementCommand disableCommand = new DisableVersionsManagementCommand(versionsManager);
	
	@Test
	void testVolatile() {
		disableCommand.execute();
		versionsManager.setDocumentType("letterTemplate");
		createCommand.execute();
		String initialContent = versionsManager.getDocument().getContents();
		versionsManager.setStrategyString("volatile");
		enableCommand.execute();
		versionsManager.setContent("Some new contents.");
		versionsManager.saveContents();
		String previousContent = versionsManager.getStrategy().getVersion().getContents();
		
		assertEquals(initialContent, previousContent);
	}
	@Test
	void testStable() {
		disableCommand.execute();
		versionsManager.setDocumentType("letterTemplate");
		createCommand.execute();
		String initialContent = versionsManager.getDocument().getContents();
		versionsManager.setStrategyString("stable");
		enableCommand.execute();
		versionsManager.setContent("Some new contents.");
		versionsManager.saveContents();
		String previousContent = versionsManager.getStrategy().getVersion().getContents();
		
		assertEquals(initialContent, previousContent);
	}
}
