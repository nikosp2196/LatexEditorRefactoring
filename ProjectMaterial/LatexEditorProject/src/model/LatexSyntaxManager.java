package model;

import java.util.HashMap;

public class LatexSyntaxManager {
	private final String[] syntaxNameList = {"chapter", "section", "subsection", "subsubsection", "enumerate", "itemize", "table", "figure"};
	private final String[] syntaxValueList = {
			"\n\\chapter{...}\n",
			"\n\\section{...}\n",
			"\n\\subsection{...}\n",
			"\n\\subsubsection{...}\n",
			"\\begin{enumerate}\n"+
					"\\item ...\n"+
					"\\item ...\n"+
					"\\end{enumerate}\n",
			"\\begin{itemize}\n"+
					"\\item ...\n"+
					"\\item ...\n"+
					"\\end{itemize}\n",
			"\\begin{table}\n"+
					"\\caption{....}\\label{...}\n"+
					"\\begin{tabular}{|c|c|c|}\n"+
					"\\hline\n"+
					"... &...&...\\\\\n"+
					"... &...&...\\\\\n"+
					"... &...&...\\\\\n"+
					"\\hline\n"+
					"\\end{tabular}\n"+
					"\\end{table}\n",
			"\\begin{figure}\n"+
					"\\includegraphics[width=...,height=...]{...}\n"+
					"\\caption{....}\\label{...}\n"+
					"\\end{figure}\n"};
	private String before;
	private String after;
	private HashMap<String, String> syntax = new HashMap<>();
	public LatexSyntaxManager(String before, String after) {
		this.before = before;
		this.after = after;
		for (int i = 0; i < syntaxNameList.length; i++) {
			syntax.put(syntaxNameList[i], syntaxValueList[i]);
		}
	}
	
	public void setBefore(String before) {
		this.before =  before;
	}
	
	public void setAfter(String after) {
		this.after = after;
	}
	
	public String addSyntax(String newSyntax) {
		return before + syntax.get(newSyntax) + after;
	}
}
