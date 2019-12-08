package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Document;
import model.DocumentManager;
import model.VersionsManager;
import model.strategies.StableVersionsStrategy;
import model.strategies.VersionsStrategy;
import model.strategies.VolatileVersionsStrategy;
class EnableVersionsManagementCommandTest {
	private DocumentManager documentManager = new DocumentManager();
	private VersionsManager versionsManager = new VersionsManager();
	private CreateCommand createCommand = new CreateCommand(documentManager, versionsManager);
	private EditCommand editCommand = new EditCommand(versionsManager);
	private EnableVersionsManagementCommand enableCommand = new EnableVersionsManagementCommand(versionsManager);
	
	@Test
	void testVolatile() {
		Document doc = new Document();
		versionsManager.setDocument(doc);
		//versionsManager.disable();
		String initialContent = versionsManager.getDocument().getContents();
		enableCommand.execute();
		versionsManager.setContent("Some new contents.");
		versionsManager.saveContents();
		String previousContent = versionsManager.getStrategy().getVersion().getContents();
		
		assertEquals(initialContent, previousContent);
	}
	@Test
	void testStable() {
		VersionsStrategy strategy = new StableVersionsStrategy();
		versionsManager.setStrategyString(strategy);
		
		latexEditorView.setType("articleTemplate");
		latexEditorView.setVersionsManager(versionsManager);
		createCommand.execute();
		String actualContents = latexEditorView.getCurrentDocument().getContents();
		latexEditorView.setStrategyString("stable");
		enableCommand.execute();
		latexEditorView.setText("test edit contents\n");
		editCommand.execute();
		
		String contents = strategy.getVersion().getContents();
		
		assertEquals(contents, actualContents);
	}
}
