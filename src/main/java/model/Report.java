package model;

import java.util.ArrayList;
import java.util.List;
import error.Error;

/**
 * Représentation d'un report contenant les informations de chacune des références, et le cas échéant, les erreurs.
 * 
 * @author Kendall
 *
 */
public class Report {
	
	public String inputFile;
	public List<Reference> references;
	public List<Error> errors;
	
	public Report() {
		inputFile = "";
		references = new ArrayList<Reference>();
		errors = new ArrayList<Error>();
	}

	public String getInputFile() {
		return inputFile;
	}

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public List<Reference> getReferences() {
		return references;
	}

	public void setReferences(List<Reference> references) {
		this.references = references;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

}
