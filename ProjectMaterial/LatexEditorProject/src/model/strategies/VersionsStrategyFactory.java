package model.strategies;

import java.util.HashMap;

public class VersionsStrategyFactory {
	private HashMap<String, VersionsStrategy> strategies;
	
	public VersionsStrategyFactory() {
		strategies = new HashMap<String, VersionsStrategy>();
		strategies.put("volatile", new VolatileVersionsStrategy());
		strategies.put("stable", new StableVersionsStrategy());
	}
	public VersionsStrategy createStrategy(String type) {
		return strategies.get(type);
	}
}
