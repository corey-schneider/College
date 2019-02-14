package model;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * 
 * @author Corey Schneider
 *
 */

@SuppressWarnings("serial")
public class Textbook implements Serializable {

	private String title;
	private String author;
	private String publisher;
	private String ISBN;
	private double price;
	DecimalFormat df = new DecimalFormat("###.00");
	
	public Textbook() { }
	
	public Textbook(String title, String author, String publisher, String ISBN, double price) {
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.ISBN = ISBN;
		this.price = price;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public String getPublisher() {
		return publisher;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public Textbook(Textbook textbook) {
		this.title = textbook.title;
		this.author = textbook.author;
		this.publisher = textbook.publisher;
		this.ISBN = textbook.ISBN;
		this.price = textbook.price;
	}
	
	public Textbook deepCopy(Textbook textbook) {
		return new Textbook(textbook);
	}
	
	public Textbook shallowCopy(Textbook textbook) {
		return textbook;
	}

	@Override
	public String toString() {
		return "\""+title + "\" by " + author + " - Publisher: "+publisher+" - ISBN: " + ISBN + " - Cost: $"+ df.format(price);
	}
}
