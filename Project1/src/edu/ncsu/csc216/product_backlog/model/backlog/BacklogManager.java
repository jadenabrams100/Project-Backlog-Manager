/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.backlog;

import java.util.ArrayList;

import edu.ncsu.csc216.product_backlog.model.command.Command;
import edu.ncsu.csc216.product_backlog.model.product.Product;
import edu.ncsu.csc216.product_backlog.model.task.Task;
import edu.ncsu.csc216.product_backlog.model.task.Task.Type;;

/**
 * Contains all the necessary methods for the backlog manager
 * @author Jaden Abrams
 *
 */
public class BacklogManager {

	/** holds the products in the manager*/
	private ArrayList<Product> products;
	/** holds the current instance of the manager */
	private static BacklogManager singleton;
	/** the current product */
	private Product currentProduct;
	/** creates a new manager */
	private BacklogManager() {
		products = new ArrayList<Product>();
	}
	/**
	 * returns the current instance of the backlog
	 * @return the backlog
	 */
	public static BacklogManager getInstance() {
		return singleton;
	}
	/**
	 * saves the products to a file
	 * @param fileName the file to save to
	 */
	public void saveToFile(String fileName) {
		
	}
	/**
	 * loads the products from a file
	 * @param fileName the file to load from
	 */
	public void loadFromFile(String fileName) {
		
	}
	/**
	 * loads a product for display
	 * @param productName the product name
	 */
	public void loadProduct(String productName) {
		
	}
	/**
	 * checks if 2 products are duplicates
	 * @param productName the other product
	 */
	private void isDuplicateProduct(String productName) {
		
	}
	/**
	 * returns the tasks as a 2D array
	 * @return the array
	 */
	public String[][] getTasksAsArray() {
		return null;
	}
	/**
	 * gets a task by the ID
	 * @param id the task id
	 * @return the task
	 */
	public Task getTaskById(int id) {
		return null;
		
	}
	/**
	 * executes a command on a task
	 * @param id the task id
	 * @param c the command
	 */
	public void executeCommand(int id, Command c) {
		
	}
	/**
	 * deletes a task
	 * @param id the task id
	 */
	public void deleteTaskById(int id) {
		
	}
	/**
	 * adds a task to the product
	 * @param name the task name
	 * @param type the task type
	 * @param creator the task creator
	 * @param note the task note
	 */
	public void addTaskToProduct(String name, Type type, String creator, String note) {
		
	}
	/**
	 * gets the current product name
	 * @return the name
	 */
	public String getProductName() {
		return null;
		
	}
	/**
	 * gets the list of products
	 * @return the products
	 */
	public String[] getProductList() {
		return null;
		
	}
	/**
	 * clears the product list
	 */
	public void clearProducts() {
		
	}
	/**
	 * edits the current product
	 * @param productName the product to edit
	 */
	public void editProduct(String productName) {
		
	}
	/**
	 * adds a new product to the manager
	 * @param productName the product to add
	 */
	public void addProduct(String productName) {
		
	}
	/**
	 * removes the active product
	 */
	public void deleteProduct() {
		
	}
	/**
	 * resets the manager
	 */
	protected void resetManager() {
		
	}
}
