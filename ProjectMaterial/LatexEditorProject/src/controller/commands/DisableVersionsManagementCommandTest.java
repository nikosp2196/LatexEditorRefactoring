package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.DocumentManager;
import model.VersionsManager;

class DisableVersionsManagementCommandTest {
	private DocumentManager documentManager = new DocumentManager();
	private VersionsManager versionsManager = new VersionsManager();
	private CreateCommand createCommand = new CreateCommand(documentManager, versionsManager);
	private EditCommand editCommand = new EditCommand(versionsManager);
	private DisableVersionsManagementCommand disableCommand = new DisableVersionsManagementCommand(versionsManager);
	@Test
	void testVolatile() {
		
		
		versionsManager.setDocumentType("articleTemplate");
		createCommand.execute();
		versionsManager.setContent("Some Changes.");
		editCommand.execute();
		int historySize = versionsManager.getStrategy().getEntireHistory().size();
		disableCommand.execute();
		versionsManager.setContent("Some more changes.");
		editCommand.execute();
		
		assertEquals(versionsManager.isEnabled(), false);
		assertEquals(versionsManager.getStrategy().getEntireHistory().size(), historySize);
	}
	@Test
	void testStable() {
		versionsManager.setStrategyString("stable");
		versionsManager.setDocumentType("articleTemplate");
		createCommand.execute();
		versionsManager.setContent("Some Changes.");
		editCommand.execute();
		int historySize = versionsManager.getStrategy().getEntireHistory().size();
		disableCommand.execute();
		versionsManager.setContent("Some more changes.");
		editCommand.execute();
		
		assertEquals(versionsManager.isEnabled(), false);
		assertEquals(versionsManager.getStrategy().getEntireHistory().size(), historySize);
	}
}
