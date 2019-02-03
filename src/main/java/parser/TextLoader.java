package parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TextLoader {
	
	public TextLoader() {
	}
	
	public static BufferedReader readFile(String filePath) throws FileNotFoundException {
		FileReader input = new FileReader(filePath);
		return new BufferedReader(input);
	}
	
	public static List<Reference> parseFile(BufferedReader buffReader) {
		
	}
	
}
