package parser;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class TextLoaderTest {
	
	TextLoader textLoader;
	ClassLoader classLoader;
	
	@Before
	public void setup() {
		classLoader = getClass().getClassLoader();
		textLoader = new TextLoader();
	}

	@Test
	public void testOneWordOK() throws IOException {
		File file = new File(classLoader.getResource("parser/one_word.txt").getFile());
		BufferedReader buffReader = textLoader.readFile(file.getPath());

		String line = buffReader.readLine();
		assertEquals("Le texte est différent de 'test'", "test", line);
		line = buffReader.readLine(); 
		assertEquals("Le reader devrait contenir une ligne, il en contient plus", null, line);
	}
	
	@Test
	public void testOneLineOK() throws IOException {
		File file = new File(classLoader.getResource("parser/one_line.txt").getFile());
		BufferedReader buffReader = textLoader.readFile(file.getPath());

		String line = buffReader.readLine();
		assertEquals("Le texte est différent de 'test'", "1460100040;R;45.12;27", line);
		line = buffReader.readLine(); 
		assertEquals("Le reader devrait contenir une ligne, il en contient plus", null, line);
	}
	
	@Test
	public void testMultipleLinesOK() throws IOException {
		File file = new File(classLoader.getResource("parser/example.txt").getFile());
		BufferedReader buffReader = textLoader.readFile(file.getPath());

		String line = buffReader.readLine();
		assertEquals("Le texte est différent de 'test'", "1460100040;R;45.12;27", line);
		line = buffReader.readLine();
		assertEquals("Le reader devrait contenir plusieurs lignes", "1460900848;G;12.0;145", line);
	}

}
