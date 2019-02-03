package error;

/**
 * Représentation d'une erreur de parsing.
 * Elle contient les informations détaillées sur l'erreur et sa provenance.
 * 
 * @author Kendall
 *
 */
public class Error {
	
	public int line;
	public String message;
	public String value;
	
	public Error() {
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
