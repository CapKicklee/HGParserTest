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
import utils.StringUtils;
import error.Error;

/**
 * Classe utilitaire permettant de r�cup�rer, parser et transformer un fichier txt en JSON
 * 
 * @author Kendall
 *
 */
public class TextLoader {

	/**
	 * 
	 */
	public String fileName;

	public TextLoader() {
		fileName = "";
	}

	/**
	 * Permet de r�cup�rer le fichier et de cr�er un BufferedReader utilis� pour
	 * r�cup�rer les informations du fichier
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
		
		// On boucle sur l'ensemble des lignes du fichier
		while((referenceLine = buffReader.readLine()) != null) {
			lineNumber++;
			// On r�cup�re les attributs s�par�s par des ";"
			String[] line = referenceLine.split(";");
			
			// S'il y a moins de 4 attributs, alors la r�f�rence n'est pas correcte
			if (line.length != 4) {
				report.getErrors().add(generateError(line, lineNumber, StringUtils.WRONG_ARGUMENTS_NUMBER));
			} else {
				double price = -1;
				int size = -1;
				price = Double.parseDouble(line[2]);
				size = Integer.parseInt(line[3]);
				String color = line[1].trim();
				
				// V�rification des diff�rentes r�gles
				if (price == -1) {
					report.getErrors().add(generateError(line, lineNumber, StringUtils.WRONG_PRICE_FORMAT));
				} else if (size == -1) {
					report.getErrors().add(generateError(line, lineNumber, StringUtils.WRONG_SIZE_FORMAT));
				} else if (!"R".equalsIgnoreCase(color) && !"B".equalsIgnoreCase(color) && !"G".equalsIgnoreCase(color)) {
					report.getErrors().add(generateError(line, lineNumber, StringUtils.WRONG_COLOR_CODE));
				} else if (line[0].length() != 10) {
					report.getErrors().add(generateError(line, lineNumber, StringUtils.WRONG_REFERENCE_NUMBER_SIZE));
				} 
				// Si les r�gles sont respect�es, la r�f�rences est valide et ajout�e
				else {
					Reference reference = new Reference();
					reference.setNumReference(line[0]);
					reference.setColor(color);
					reference.setPrice(price);
					reference.setSize(size);
					report.getReferences().add(reference);
				}
			}			
		}
		return report;
	}

	/**
	 * G�n�re une erreur � partir d'une ligne, un num�ro de ligne et un message d'erreur fournis en entr�e
	 * 
	 * @param line - Ligne contenant les informations de la r�f�rence en erreur
	 * @param lineNumber - Num�ro de la ligne en erreur
	 * @param errorMessage - Message d'erreur � afficher
	 * @return Un objet de type Error
	 */
	public Error generateError(String[] line, int lineNumber, String errorMessage) {
		Error error = new Error();
		error.setLine(lineNumber);
		error.setMessage(errorMessage);
		StringBuffer buffer = new StringBuffer();
		for (String attribute : line) {
			buffer.append(attribute);
			buffer.append(";");
		}
		error.setValue(buffer.substring(0, buffer.length() - 1));
		return error;
	}

}
