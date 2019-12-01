package controller.commands;

import model.VersionsManager;

public abstract class AbstractCommand implements Command{
	protected VersionsManager versionsManager;
	
	public AbstractCommand(VersionsManager versionsManager) {
		this.versionsManager = versionsManager;
	}
}
