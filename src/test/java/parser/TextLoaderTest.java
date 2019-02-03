package parser;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import model.Report;

public class TextLoaderTest {
	
	TextLoader textLoader;
	ClassLoader classLoader;
	
	@Before
	public void setup() {
		classLoader = getClass().getClassLoader();
		textLoader = new TextLoader();
	}

	@Test
	public void readFile_testOneWordOK() throws IOException {
		File file = new File(classLoader.getResource("parser/one_word.txt").getFile());
		BufferedReader buffReader = textLoader.readFile(file.getPath());

		String line = buffReader.readLine();
		assertEquals("Le texte est différent de 'test'", "test", line);
		line = buffReader.readLine(); 
		assertEquals("Le reader devrait contenir une ligne, il en contient plus", null, line);
	}
	
	@Test
	public void readFile_testOneLineOK() throws IOException {
		File file = new File(classLoader.getResource("parser/one_line.txt").getFile());
		BufferedReader buffReader = textLoader.readFile(file.getPath());

		String line = buffReader.readLine();
		assertEquals("Le texte est différent de 'test'", "1460100040;R;45.12;27", line);
		line = buffReader.readLine(); 
		assertEquals("Le reader devrait contenir une ligne, il en contient plus", null, line);
	}
	
	@Test
	public void readFile_testMultipleLinesOK() throws IOException {
		File file = new File(classLoader.getResource("parser/example.txt").getFile());
		BufferedReader buffReader = textLoader.readFile(file.getPath());

		String line = buffReader.readLine();
		assertEquals("Le texte est différent de 'test'", "1460100040;R;45.12;27", line);
		line = buffReader.readLine();
		assertEquals("Le reader devrait contenir plusieurs lignes", "1460900848;G;12.0;145", line);
	}
	
	@Test
	public void parseFile_testOneWordKO() throws IOException {
		File file = new File(classLoader.getResource("parser/one_word.txt").getFile());
		BufferedReader buffReader = textLoader.readFile(file.getPath());
		
		Report report = null;
		report = textLoader.parseFile(buffReader);
		assertEquals("Le rapport devrait ne contenir qu'une erreur", 1, report.getErrors().size());
		assertTrue("La liste des références devrait être vide", report.getReferences().isEmpty());
		assertEquals("Le nom du fichier devrait être 'one_word.txt'", "one_word.txt", report.getInputFile());
	}
	
	@Test
	public void parseFile_testOneLineOK() throws IOException {
		File file = new File(classLoader.getResource("parser/one_line.txt").getFile());
		BufferedReader buffReader = textLoader.readFile(file.getPath());
		
		Report report = null;
		report = textLoader.parseFile(buffReader);
		assertEquals("Le rapport devrait ne contenir aucune erreur", 0, report.getErrors().size());
		assertEquals("La liste des références devrait contenir une ligne", 1, report.getReferences().size());
		assertEquals("Le nom du fichier devrait être 'one_line.txt'", "one_line.txt", report.getInputFile());
	}
	
	@Test
	public void parseFile_testMultipleLinesOK() throws IOException {
		File file = new File(classLoader.getResource("parser/example.txt").getFile());
		BufferedReader buffReader = textLoader.readFile(file.getPath());
		
		Report report = null;
		report = textLoader.parseFile(buffReader);
		assertEquals("Le rapport devrait ne contenir qu'une erreur de code couleur", 1, report.getErrors().size());
		assertEquals("La liste des références devrait contenir 4 lignes", 4, report.getReferences().size());
		assertEquals("Le nom du fichier devrait être 'example.txt'", "example.txt", report.getInputFile());
	}
	
	@Test
	public void parseFile_testMultipleLinesKO() throws IOException {
		File file = new File(classLoader.getResource("parser/exampleErrors.txt").getFile());
		BufferedReader buffReader = textLoader.readFile(file.getPath());
		
		Report report = null;
		report = textLoader.parseFile(buffReader);
		assertEquals("Le rapport devrait contenir 4 erreurs", 4, report.getErrors().size());
		assertEquals("La liste des références devrait contenir 1 ligne", 1, report.getReferences().size());
		assertEquals("Le nom du fichier devrait être 'exampleErrors.txt'", "exampleErrors.txt", report.getInputFile());
	}

}
