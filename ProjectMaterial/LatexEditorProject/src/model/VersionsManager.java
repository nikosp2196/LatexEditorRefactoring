package model;

import javax.swing.JOptionPane;

import model.strategies.StableVersionsStrategy;
import model.strategies.VersionsStrategy;
import model.strategies.VolatileVersionsStrategy;
import view.LatexEditorView;

public class VersionsManager {
	private boolean enabled;
	private VersionsStrategy strategy;
	private Document activeDocument;
	private String filename;
	private String documentType;
	private String content;
	
	public VersionsManager(VersionsStrategy versionsStrategy) {
		this.strategy = versionsStrategy;
		enabled = false;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void enable() {
		enabled = true;
	}

	public void disable() {
		enabled = false;
	}
	
	//TODO: Useless method same as setDocument().
	public void setCurrentVersion(Document document) {
		setDocument(document);
	}

	public String getType() {
		// TODO Auto-generated method stub
		return getDocumentType();
	}
	
	//TODO: Check where this method is called in view package.
	public void saveContents() {
		// TODO Auto-generated method stub
		if(isEnabled()) {
			putVersion(activeDocument);
			activeDocument.changeVersion();
		}
		activeDocument.setContents(content);
	}

	public void enableStrategy() {
		// TODO Auto-generated method stub
		String strategyType = latexEditorView.getStrategy();
		if(strategyType.equals("volatile") && strategy instanceof VolatileVersionsStrategy) {
			enable();
		}
		else if(strategyType.equals("stable") && strategy instanceof VolatileVersionsStrategy) {
			//allagh apo to ena sto allo
			VersionsStrategy newStrategy = new StableVersionsStrategy();
			newStrategy.setEntireHistory(strategy.getEntireHistory());
			strategy = newStrategy;
			enable();
		}
		else if(strategyType.equals("volatile") && strategy instanceof StableVersionsStrategy) {
			VersionsStrategy newStrategy = new VolatileVersionsStrategy();
			newStrategy.setEntireHistory(strategy.getEntireHistory());
			strategy = newStrategy;
			enable();
		}
		else if(strategyType.equals("stable") && strategy instanceof StableVersionsStrategy) {
			enable();
		}
	}

	public void changeStrategy() {
		// TODO: Klaiw xaxaxaxaxa fix this!!!!!
		String strategyType = latexEditorView.getStrategy();
		if(strategyType.equals("stable") && strategy instanceof VolatileVersionsStrategy) {
			VersionsStrategy newStrategy = new StableVersionsStrategy();
			newStrategy.setEntireHistory(strategy.getEntireHistory());
			strategy = newStrategy;
			enable();
		}
		else if(strategyType.equals("volatile") && strategy instanceof StableVersionsStrategy) {
			VersionsStrategy newStrategy = new VolatileVersionsStrategy();
			newStrategy.setEntireHistory(strategy.getEntireHistory());
			strategy = newStrategy;
			enable();
		}
	}

	public void  putVersion(Document document) {
		// TODO Auto-generated method stub
		strategy.putVersion(document);
	}

	public void rollbackToPreviousVersion() {
		// TODO Auto-generated method stub
		if(isEnabled() == false) {
			JOptionPane.showMessageDialog(null, "Strategy is not enabled", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			Document doc = strategy.getVersion();
			if(doc == null) {
				JOptionPane.showMessageDialog(null, "No version available", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				strategy.removeVersion();
				setDocument(doc);
			}
		}
		
	}

	public VersionsStrategy getStrategy() {
		// TODO Auto-generated method stub
		return strategy;
	}
	
	public void setStrategy(VersionsStrategy strategy) {
		this.strategy = strategy;
	}
	
	public String getDocumentType() {
		return documentType;
	}
	
	public void setDocumentType(String newType) {
		this.documentType = newType;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public Document getDocument() {
		return activeDocument;
	}
	
	public void setDocument(Document newDocument) {
		this.activeDocument = newDocument;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
