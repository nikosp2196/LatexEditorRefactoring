package model;

import java.io.File;
import java.util.HashMap;

public class DocumentManager {
	private HashMap<String, Document> templates  = new HashMap<String, Document>();
	
	public DocumentManager() {
		getTemplatesFromFile();
	}
	
	public Document createDocument(String type) {
		return templates.get(type).clone();
	}
	
	public void getTemplatesFromFile() {
		FileManager fManager = new FileManager();
		File templateDir = new File("tex-templates"); 

		for(String filename : templateDir.list()) {
			if(filename.endsWith(".tex")) {
				String templatePath = templateDir.getPath() +"/"+filename;
				String[] temp = fManager.loadFromFile(templatePath);
				Document doc = new Document();
				doc.setContents(temp[1]);
				templates.put(temp[0], doc);
			}
		}
		Document doc = new Document();
		doc.setContents("");
		templates.put("emptyTemplate", doc);
		
	}
}
