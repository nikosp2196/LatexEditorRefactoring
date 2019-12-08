package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.LatexEditorController;
import controller.commands.CreateCommand;
import controller.commands.EditCommand;

class LatexSyntaxManagerTest {
	private LatexEditorController controller = new LatexEditorController();
	private DocumentManager documentManager = new DocumentManager();
	private VersionsManager versionsManager = new VersionsManager();
	private CreateCommand createCommand = new CreateCommand(documentManager, versionsManager);
	private EditCommand editCommand = new EditCommand(versionsManager);
	
	@Test
	void testChapter() {
		versionsManager.setDocumentType("emptyTemplate");
		createCommand.execute();
		versionsManager.setContent(controller.initializeSyntaxManager("", "", "chapter"));
		editCommand.execute();
		String expectedField = "\n\\chapter{...}\n";
		
		assertEquals(expectedField, versionsManager.getDocument().getContents());
	}
	
	@Test
	void testSection() {
		versionsManager.setDocumentType("emptyTemplate");
		createCommand.execute();
		versionsManager.setContent(controller.initializeSyntaxManager("", "", "section"));
		editCommand.execute();
		String expectedField = "\n\\section{...}\n";
		
		assertEquals(expectedField, versionsManager.getDocument().getContents());
	}
	
	@Test
	void testSubsection() {
		versionsManager.setDocumentType("emptyTemplate");
		createCommand.execute();
		versionsManager.setContent(controller.initializeSyntaxManager("", "", "subsection"));
		editCommand.execute();
		String expectedField = "\n\\subsection{...}\n";
		
		assertEquals(expectedField, versionsManager.getDocument().getContents());
	}
	
	@Test
	void testSubsubsection() {
		versionsManager.setDocumentType("emptyTemplate");
		createCommand.execute();
		versionsManager.setContent(controller.initializeSyntaxManager("", "", "subsubsection"));
		editCommand.execute();
		String expectedField = "\n\\subsubsection{...}\n";
		
		assertEquals(expectedField, versionsManager.getDocument().getContents());
	}
	
	@Test
	void testEnumerate() {
		versionsManager.setDocumentType("emptyTemplate");
		createCommand.execute();
		versionsManager.setContent(controller.initializeSyntaxManager("", "", "enumerate"));
		editCommand.execute();
		String expectedField = "\\begin{enumerate}\n"+
				"\\item ...\n"+
				"\\item ...\n"+
				"\\end{enumerate}\n";
		
		assertEquals(expectedField, versionsManager.getDocument().getContents());
	}
	
	@Test
	void testItemize() {
		versionsManager.setDocumentType("emptyTemplate");
		createCommand.execute();
		versionsManager.setContent(controller.initializeSyntaxManager("", "", "itemize"));
		editCommand.execute();
		String expectedField = "\\begin{itemize}\n"+
				"\\item ...\n"+
				"\\item ...\n"+
				"\\end{itemize}\n";
		
		assertEquals(expectedField, versionsManager.getDocument().getContents());
	}
	
	@Test
	void testTable() {
		versionsManager.setDocumentType("emptyTemplate");
		createCommand.execute();
		versionsManager.setContent(controller.initializeSyntaxManager("", "", "table"));
		editCommand.execute();
		String expectedField = "\\begin{table}\n"+
				"\\caption{....}\\label{...}\n"+
				"\\begin{tabular}{|c|c|c|}\n"+
				"\\hline\n"+
				"... &...&...\\\\\n"+
				"... &...&...\\\\\n"+
				"... &...&...\\\\\n"+
				"\\hline\n"+
				"\\end{tabular}\n"+
				"\\end{table}\n";
		
		assertEquals(expectedField, versionsManager.getDocument().getContents());
	}
	
	@Test
	void testFigure() {
		versionsManager.setDocumentType("emptyTemplate");
		createCommand.execute();
		versionsManager.setContent(controller.initializeSyntaxManager("", "", "figure"));
		editCommand.execute();
		String expectedField = "\\begin{figure}\n"+
				"\\includegraphics[width=...,height=...]{...}\n"+
				"\\caption{....}\\label{...}\n"+
				"\\end{figure}\n";
		
		assertEquals(expectedField, versionsManager.getDocument().getContents());
	}
}
