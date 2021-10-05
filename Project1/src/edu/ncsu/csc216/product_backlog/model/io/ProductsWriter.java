/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.io;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;

import edu.ncsu.csc216.product_backlog.model.product.Product;
import edu.ncsu.csc216.product_backlog.model.task.Task;

/**
 * Contains all the methods for writing products to the file system
 * @author Jaden Abrams
 *
 */
public class ProductsWriter {

	/**
	 * writes products to the file system
	 * @param fileName the file to write to
	 * @param products the products to write
	 */
	public static void writeProductsToFile(String fileName, ArrayList<Product> products) {
		try {
			PrintStream fileWriter = new PrintStream(new File(fileName));
			for(Product p: products) {
				fileWriter.println("# " + p.getProductName());
				for(Task t: p.getTasks()) {
					fileWriter.print(t.toString());
				}
			}
			fileWriter.close();
		} catch(Exception e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
}
