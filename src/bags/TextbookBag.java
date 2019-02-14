package bags;

import java.io.*;
import java.util.Scanner;

import util.Utilities;
import model.Textbook;

public class TextbookBag {

	Textbook[] textbookArray;
	private int nElems;


	public TextbookBag(int maxSize) {
		textbookArray = new Textbook[maxSize];
		nElems = 0;
	}

	public TextbookBag() { }

	/**
	 * Individually insert Textbook datatype to the textbookArray
	 * @param textbook Textbook(s) to be inserted into the array
	 */
	public void add(Textbook... textbook) {
		/*		for(int j = 0; j < textbook.length; j++) {
			for(int i = 0; i < textbookArray.length; i++) {
				if(textbookArray[i] == null) {
					textbookArray[i] = textbook[j];
					break;
				}
			}
		}*/
		for(int i = 0; i < textbook.length; i++)
			textbookArray[nElems++] = textbook[i];
	}


	public void display() {
		toString();
	}

	/**
	 * Search for a textbook by the ISBN number
	 * @param isbn Textbook's ISBN
	 */
	public Textbook find(String isbn) {
		try {
			int i;
			for(i = 0; i < nElems; i++) {
				if(textbookArray[i].getISBN().equalsIgnoreCase(isbn)) {
					return textbookArray[i];
				}
			}
		} catch(NullPointerException e) {
			System.out.println("Are you sure "+isbn+" is the ISBN? Couldn't find anything.");
		}
		return null;
	}

	/**
	 * 
	 * @param id Textbook's title / name of book
	 * @return
	 */
	public Textbook delete(String id) {
		int i = -1;
		for(i = 0; i < nElems; i++) {
			if(textbookArray[i].getTitle().equalsIgnoreCase(id)) {
				break;
			}
		}
		if(i == nElems) {
			return null;
		} else {
			Textbook temp = textbookArray[i];
			for(int j = i; j < nElems - 1; j++) {
				textbookArray[j] = textbookArray[j + 1];
			}
			nElems--;
			System.out.println("nElems: "+nElems+"  --  textbookArray length: "+textbookArray.length);
			return temp;
		}
	}

	/**
	 * 
	 * @param id Textbook's title / name of book
	 * @return
	 */
	public Textbook deleteISBN(String isbn) {
		int i = -1;
		for(i = 0; i < nElems; i++) {
			if(textbookArray[i].getISBN().equalsIgnoreCase(isbn)) {
				break;
			}
		}
		if(i == nElems) {
			return null;
		} else {
			Textbook temp = textbookArray[i];
			for(int j = i; j < nElems - 1; j++) {
				textbookArray[j] = textbookArray[j + 1];
			}
			nElems--;
			System.out.println("nElems: "+nElems+"  --  textbookArray length: "+textbookArray.length);
			return temp;
		}
	}


	public void save() {
		String fileName = "data/textbook_bag.dat";
		FileOutputStream outFile;
		ObjectOutputStream outStream;
		Textbook tempTextbook;

		try {
			outFile = new FileOutputStream(fileName);
			outStream = new ObjectOutputStream(outFile);

			boolean foundData = false;
			for (int i = 0; i < nElems; i++) {
				if(textbookArray[i] != null) {
					foundData = true;
					tempTextbook = textbookArray[i];
					outStream.writeObject(tempTextbook); // this one line writes an object
					if(Utilities.DEBUG)
						System.out.println("Obj written: "+tempTextbook);
				}
				if(i == nElems-1 && !foundData)
					System.out.println("[TextbookBag]: There are no objects in the array for us to save. Did you load() or importData()?");
			}

			outStream.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * method to load data from a binary file previously saved for that bag to use.
	 */
	public void load() {
		String fileName = "data/textbook_bag.dat";
		FileInputStream inFile;
		ObjectInputStream inStream = null;
		Textbook tempTextbook;

		try {
			inFile = new FileInputStream(fileName);
			inStream = new ObjectInputStream(inFile);

			while (true) {

				tempTextbook = (Textbook)inStream.readObject();
				//String title, String author, String publisher, String ISBN, double price
				Textbook txtbk = new Textbook(tempTextbook.getTitle(), tempTextbook.getAuthor(), tempTextbook.getPublisher(), tempTextbook.getISBN(), tempTextbook.getPrice());
				add(txtbk);
			}

		} catch (FileNotFoundException e) {
			System.out.println("File named "+ fileName +" not found.\n");
		} catch (EOFException e) { // catch EOF
			try {
				if(Utilities.DEBUG)
					System.out.println("[TextbookBag]: Loaded "+fileName+" into memory successfully.");
				inStream.close();
			} catch (IOException ex) { }
		} catch (IOException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
	}

	public void exportData() {
		FileWriter writer;
		try {
			writer = new FileWriter("data/output/textbook_bag_output.txt");

			writer.write("## Book title - Author - ISBN - Cost");
			writer.write(String.format("%n")); //new line
			writer.flush();

			// data
			for(int i = 0; i < textbookArray.length; i++) {
				if(textbookArray[i] != null) {
					//writer.write(textbookArray[i]+""); //writes the array as-is
					writer.write(textbookArray[i].getTitle()+", "+textbookArray[i].getAuthor()+", "+textbookArray[i].getISBN()+", "+textbookArray[i].getPrice());
					writer.write(String.format("%n"));
				}
			}
			writer.write("\n");
			writer.flush();

			writer.close();
			System.out.println("Successfully exported!");
		} catch (IOException e) {
			System.out.println("Error exporting data : TextbookBag class; exportData method");
		} 
	}


	/**
	 * read data from txt file, create objects using data to put into the array.
	 */
	public void importData() {
		String fileName = "data/textbook_import.txt";
		File file = new File(fileName);
		try {
			Scanner in = new Scanner(file);
			while (in.hasNextLine()) {
				String data = in.nextLine();
				if(data.contains("##"))
					continue;
				String[] tokens = data.split(", ");
				Textbook txtbk = new Textbook(tokens[0], tokens[1], tokens[2], tokens[3], Double.parseDouble(tokens[4]));
				add(txtbk);
			}
			in.close();
			if(Utilities.DEBUG)
				System.out.println("[TextbookBag]: Imported "+fileName+" to memory successfully.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		String data = "";
		for(int i = 0; i < nElems; i++) {
			if(textbookArray[i] != null) {
				data += textbookArray[i]+"\n";
			}
		}
		return data;
	}

	/**
	 * Lists all textbooks
	 * @return All textbooks
	 */
	public Textbook[] listAllTextbooks() {
		Textbook[] textbooks = new Textbook[nElems];
		for(int i = 0; i < nElems; i++) {
			if(textbookArray[i] != null) {
				textbooks[i] = textbookArray[i];
			}
		}
		return textbooks;
	}

	/**
	 * Lists all textbooks
	 * @return All textbooks
	 */
	public String[] listAllTextbookISBN() {
		String[] ISBNs = new String[nElems];
		for(int i = 0; i < nElems; i++) {
			if(textbookArray[i] != null) {
				ISBNs[i] = textbookArray[i].getISBN();
			}
		}
		return ISBNs;
	}

	public String[] listAllTextbooksString() {
		String[] textbooks = new String[nElems];
		for(int i = 0; i < nElems; i++) {
			if(textbookArray[i] != null) {
				textbooks[i] = textbookArray[i].getISBN()+" -- "+textbookArray[i].getTitle();
			}
		}
		return textbooks;
	}

}
