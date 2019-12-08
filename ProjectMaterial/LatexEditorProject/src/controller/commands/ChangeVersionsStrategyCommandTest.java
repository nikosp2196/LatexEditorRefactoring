package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.DocumentManager;
import model.VersionsManager;
import model.strategies.VersionsStrategy;

class ChangeVersionsStrategyCommandTest {
	//private LatexEditorView latexEditorView = new LatexEditorView();
	private DocumentManager documentManager = new DocumentManager();
	private VersionsManager versionsManager = new VersionsManager();
	private ChangeVersionsStrategyCommand changeCommand = new ChangeVersionsStrategyCommand(versionsManager);

	@Test
	void testVolatile() {
		VersionsStrategy currentStrategy = versionsManager.getStrategy();
		
		versionsManager.setStrategyString("stable");		
		changeCommand.execute();
		
		VersionsStrategy changedStrategy = versionsManager.getStrategy();
		
		boolean notChanged = changedStrategy.equals(currentStrategy);
		assertEquals(false, notChanged);
	}
	@Test
	void testStable() {	
		versionsManager.setStrategyString("stable");
		changeCommand.execute();
		
		VersionsStrategy currentStrategy = versionsManager.getStrategy();
		
		versionsManager.setStrategyString("volatile");
		changeCommand.execute();
		
		VersionsStrategy changedStrategy = versionsManager.getStrategy();
		
		boolean notChanged = changedStrategy.equals(currentStrategy);
		assertEquals(false, notChanged);
	}
		
}
