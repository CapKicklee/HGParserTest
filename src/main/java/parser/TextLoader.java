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
 * Classe utilitaire permettant de récupérer, parser et transformer un fichier txt en JSON
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
	 * Permet de récupérer le fichier et de créer un BufferedReader utilisé pour
	 * récupérer les informations du fichier
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
	 * Permet de générer un objet de type Report qui représentera l'ensemble des informations du fichier passé en paramètre
	 * 
	 * @param buffReader - l'objet de type BufferedReader généré lors de la récupération du fichier
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
			// On récupère les attributs séparés par des ";"
			String[] line = referenceLine.split(";");
			
			// S'il y a moins de 4 attributs, alors la référence n'est pas correcte
			if (line.length != 4) {
				report.getErrors().add(generateError(line, lineNumber, StringUtils.WRONG_ARGUMENTS_NUMBER));
			} else {
				double price = -1;
				int size = -1;
				price = Double.parseDouble(line[2]);
				size = Integer.parseInt(line[3]);
				String color = line[1].trim();
				
				// Vérification des différentes règles
				if (price == -1) {
					report.getErrors().add(generateError(line, lineNumber, StringUtils.WRONG_PRICE_FORMAT));
				} else if (size == -1) {
					report.getErrors().add(generateError(line, lineNumber, StringUtils.WRONG_SIZE_FORMAT));
				} else if (!"R".equalsIgnoreCase(color) && !"B".equalsIgnoreCase(color) && !"G".equalsIgnoreCase(color)) {
					report.getErrors().add(generateError(line, lineNumber, StringUtils.WRONG_COLOR_CODE));
				} else if (line[0].length() != 10) {
					report.getErrors().add(generateError(line, lineNumber, StringUtils.WRONG_REFERENCE_NUMBER_SIZE));
				} 
				// Si les règles sont respectées, la références est valide et ajoutée
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
	 * Génère une erreur à partir d'une ligne, un numéro de ligne et un message d'erreur fournis en entrée
	 * 
	 * @param line - Ligne contenant les informations de la référence en erreur
	 * @param lineNumber - Numéro de la ligne en erreur
	 * @param errorMessage - Message d'erreur à afficher
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
