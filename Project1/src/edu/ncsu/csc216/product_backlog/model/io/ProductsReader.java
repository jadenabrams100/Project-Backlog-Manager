/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.ncsu.csc216.product_backlog.model.product.Product;
import edu.ncsu.csc216.product_backlog.model.task.Task;

/**
 * contains the methods to read products from the file system
 * @author Jaden Abrams
 *
 */
public class ProductsReader {

	/**
	 * takes a file and pulls the tasks from it
	 * @param fileName the file with the products
	 * @return the products
	 * @throws IllegalArgumentException if the file cannot be found or loaded
	 */
	public static ArrayList<Product> readProductsFile(String fileName) {
		try {
			Scanner fileReader = new Scanner(new FileInputStream(fileName));
			ArrayList<Product> products = new ArrayList<Product>();
			String fileLine = "";
			//read the whole file into the string above
			while(fileReader.hasNextLine()) {
				fileLine = fileLine + fileReader.nextLine() + "\n";
			}
			Scanner s1 = new Scanner(fileLine);
			//use the first delimiter to separate the products
			s1.useDelimiter("\\r?\\n?[#]");
			// read each product token into processProduct
			while(s1.hasNext()) {
				Product p = processProduct(s1.next());
				if(p != null && p.getTasks().size() != 0) {
					products.add(p);
				}
			}
			//add the resulting product to products
			fileReader.close();
			s1.close();
			
			return products;
			
			
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		
		
	}
	/**
	 * takes in a line and makes a new product from it
	 * @param productLine the line with the product
	 * @return the product
	 */
	private static Product processProduct(String productLine) {
		try {
			Scanner s = new Scanner(productLine);
			Product p = new Product(s.nextLine().trim());
			s.useDelimiter("\\r?\\n?[*]");
			while(s.hasNext()) {
				try {
					Task t = processTask(s.next());
					if(t != null) {
						p.addTask(t);
					}
				} catch(IllegalArgumentException e) {
					continue;
				}
			}
			//create product with product name
			//use second delimiter to separate the tasks
			//read each task into processTask
			//add each task to the product
			s.close();
			return p;
		} catch (Exception e) {
			return null;
		}
		
	}
	/**
	 * takes in a line and makes a task from it
	 * @param taskLine the line with the task
	 * @return the task
	 */
	private static Task processTask(String taskLine) {
		try {
			Scanner s = new Scanner(taskLine);
			ArrayList<String> notes = new ArrayList<String>();
			String taskInfo = s.nextLine().trim();
			int id;
			String state, title, type, creator, owner, verified;
			Scanner s1 = new Scanner(taskInfo);
			s1.useDelimiter(",");
			id = s1.nextInt();
			state = s1.next();
			title = s1.next();
			type = s1.next();
			creator = s1.next();
			owner = s1.next();
			verified = s1.next();
			s1.close();
			s.useDelimiter("\\r?\\n?[-]");
			while(s.hasNext()) {
				notes.add(s.next().trim());
			}
			//create list to store the notes
			// use comma delimiter to get task parameters
			// use last delimiter to separate notes
			// create and return task
			s.close();
			return new Task(id, state, title, type, creator, owner, verified, notes);
		} catch(Exception e) {
			return null;
		}
		
	}
	
	
}

