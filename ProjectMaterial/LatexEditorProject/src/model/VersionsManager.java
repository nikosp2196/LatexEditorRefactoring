package model;

import javax.swing.JOptionPane;
import model.strategies.VersionsStrategy;
import model.strategies.VersionsStrategyFactory;


public class VersionsManager {
	private boolean enabled;
	private VersionsStrategyFactory factory;
	private String strategyString;
	private VersionsStrategy strategy;
	private Document activeDocument;
	private String filename;
	private String documentType;
	private String content;
	
	public VersionsManager() {
		factory = new VersionsStrategyFactory();
		strategy = factory.createStrategy("volatile");
		enabled = true;
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
			activeDocument.increaseVersionID();
		}
		activeDocument.setContents(content);
	}

	public void enableStrategy() {
		// TODO Auto-generated method stub
		setStrategy();
		enable();
	}

	public void setStrategy() {
		VersionsStrategy newStrategy = factory.createStrategy(strategyString);
		newStrategy.setEntireHistory(strategy.getEntireHistory());
		strategy = newStrategy;
	}

	public void  putVersion(Document document) {
		// TODO Auto-generated method stub
		strategy.putVersion(document);
	}

	public void rollbackToPreviousVersion() {
		// TODO Auto-generated method stub
		if(isEnabled()) {
			Document doc = strategy.getVersion();
			if(doc == null) {
				JOptionPane.showMessageDialog(null, "No version available", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
			}else{
				strategy.removeVersion();
				setDocument(doc);
			}
		}else{
			JOptionPane.showMessageDialog(null, "Strategy is not enabled", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}

	public String getStrategyString() {
		// TODO Auto-generated method stub
		return strategyString;
	}
	
	public void setStrategyString(String strategyString) {
		this.strategyString = strategyString;
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
