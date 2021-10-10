package edu.ncsu.csc216.product_backlog.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.product_backlog.model.product.Product;
/**
 * ensures that ProductsReader works as intended
 * @author Jaden Abrams
 *
 */
class ProductsReaderTest {
	/** a shorthand for the file with products and tasks in a valid format */
	private static final String VALID_FILE = "test-files/exp_tasks.txt";

	/**
	 * ensures that readProductsFile works as intended
	 */
	@Test
	void testReadProductsFile() {
		ArrayList<Product> products;
		products = ProductsReader.readProductsFile(VALID_FILE);
		assertEquals(1, products.size());
		assertEquals("Test Product", products.get(0).getProductName());
		assertEquals(2, products.get(0).getTasks().size());
		
		products.clear();
		
		products = ProductsReader.readProductsFile("test-files/tasks2.txt");
		assertEquals(1, products.size());
		
		products.clear();
		
		products = ProductsReader.readProductsFile("test-files/tasks3.txt");
		assertEquals(0, products.size());
		
		
		
	}

}
