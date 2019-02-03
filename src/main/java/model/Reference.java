package model;

/**
 * Représentation d'une référence dans un fichier
 * 
 * @author Kendall
 *
 */
public class Reference {
	
	/**
	 * Déclaration des attributs
	 */
	public String numReference;
	public String color;
	public double price;
	public int size;
	
	/**
	 * Constructeur de la référence
	 */
	public Reference() {
		
	}
	
	/**
	 * Accesseur de l'attribut numReference
	 * @return la valeur de l'attribut numReference
	 */
	public String getNumReference() {
		return numReference;
	}

	/**
	 * Modificateur de l'attribut numReference
	 * @param numReference - nouvelle valeur de l'attribut numReference
	 */
	public void setNumReference(String numReference) {
		this.numReference = numReference;
	}

	/**
	 * Accesseur de l'attribut color
	 * @return la valeur de l'attribut color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Modificateur de l'attribut numReference
	 * @param color - nouvelle valeur de l'attribut color
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Accesseur de l'attribut price
	 * @return la valeur de l'attribut price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Modificateur de l'attribut numReference
	 * @param price - nouvelle valeur de l'attribut price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Accesseur de l'attribut size
	 * @return la valeur de l'attribut size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Modificateur de l'attribut numReference
	 * @param size - nouvelle valeur de l'attribut size
	 */
	public void setSize(int size) {
		this.size = size;
	}	

}
