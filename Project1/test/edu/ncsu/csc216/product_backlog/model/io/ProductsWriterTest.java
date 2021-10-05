package edu.ncsu.csc216.product_backlog.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.product_backlog.model.product.Product;
/**
 * ensures that ProductsWriter works as intended
 * @author Jaden Abrams
 *
 */
class ProductsWriterTest {
	
	private final String VALID_FILE = "test-files/exp_tasks.txt";

	/**
	 * ensures that writeProductsToFile works as intended
	 */
	@Test
	void testWriteProductsToFile() {
		ArrayList<Product> products = ProductsReader.readProductsFile(VALID_FILE);
		ProductsWriter.writeProductsToFile("test-files/new_exp_tasks.txt", products);
		try {
			Scanner fileReader = new Scanner(new FileInputStream(VALID_FILE));
			Scanner fileReader2 = new Scanner(new FileInputStream("test-files/new_exp_tasks.txt"));
			while(fileReader.hasNextLine() && fileReader2.hasNextLine()) {
				assertEquals(fileReader.nextLine(), fileReader2.nextLine());
			}
			if(fileReader.hasNextLine() || fileReader.hasNextLine()) {
				fail("One of the files has too many lines");
			}
		}catch(Exception e){
			fail();
		}
	}

}
