package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Reference;
import model.Report;
import error.Error;

public class TextLoader {
	
	/**
	 * 
	 */
	public String fileName;
	
	public TextLoader() {
		fileName = "";
	}
	
	/**
	 * Permet de r�cup�rer le fichier et de cr�er un BufferedReader utilis� pour r�cup�rer les informations du fichier
	 * 
	 * @param filePath - chemin vers le fichier
	 * @return un objet de type BufferedReader
	 * @throws FileNotFoundException
	 */
	public BufferedReader readFile(String filePath) throws FileNotFoundException {
		File file = new File(filePath);
		FileReader input = new FileReader(filePath);
		this.fileName = file.getName();
		return new BufferedReader(input);
	}
	
	/**
	 * Permet de g�n�rer un objet de type Report qui repr�sentera l'ensemble des informations du fichier pass� en param�tre
	 * 
	 * @param buffReader - l'objet de type BufferedReader g�n�r� lors de la r�cup�ration du fichier
	 * @return un objet de type Report
	 * @throws IOException
	 */
	public Report parseFile(BufferedReader buffReader) throws IOException {
		Report report = new Report();
		String referenceLine = null;
		int lineNumber = 0;
		report.setInputFile(fileName);
		while((referenceLine = buffReader.readLine()) != null) {
			lineNumber++;
			String[] line = referenceLine.split(";");
			if (line.length != 4) {
				Error error = new Error();
				error.setLine(lineNumber);
				error.setMessage("La ligne ne contient pas toutes les informations");
				StringBuffer buffer = new StringBuffer();
				for (String attribute : line) {
					buffer.append(attribute);
					buffer.append(";");
				}
				error.setValue(buffer.substring(0, buffer.length()-1));
				report.getErrors().add(error);
			} else {
				Reference reference = new Reference();
				reference.setNumReference(line[0]);
				reference.setColor(line[1]);
				reference.setPrice(Double.parseDouble(line[2]));
				reference.setSize(Integer.parseInt(line[3]));
				report.getReferences().add(reference);
			}			
		}
		return report;
	}
	
}
