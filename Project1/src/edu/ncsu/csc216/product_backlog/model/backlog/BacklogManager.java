/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.backlog;

import java.util.ArrayList;

import edu.ncsu.csc216.product_backlog.model.command.Command;
import edu.ncsu.csc216.product_backlog.model.io.ProductsReader;
import edu.ncsu.csc216.product_backlog.model.io.ProductsWriter;
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
		currentProduct = null;
	}
	/**
	 * returns the current instance of the backlog
	 * @return the backlog
	 */
	public static BacklogManager getInstance() {
		if(singleton == null) {
			singleton = new BacklogManager();
		}
		return singleton;
	}
	/**
	 * saves the products to a file
	 * @param fileName the file to save to
	 */
	public void saveToFile(String fileName) {
		if(currentProduct == null) {
			throw new IllegalArgumentException("Unable to save file.");
		}
		if(currentProduct.getTasks().size() == 0) {
			throw new IllegalArgumentException("Unable to save to file.");
		}
		ProductsWriter.writeProductsToFile(fileName, products);
	}
	/**
	 * loads the products from a file
	 * @param fileName the file to load from
	 */
	public void loadFromFile(String fileName) {
		ArrayList<Product> newProducts = ProductsReader.readProductsFile(fileName);
		currentProduct = newProducts.get(0);
		for(Product p : newProducts) {
			products.add(p);
		}
	}
	/**
	 * loads a product for display
	 * @param productName the product name
	 */
	public void loadProduct(String productName) {
		int idx = -1;
		for(int i = 0; i < products.size(); i++) {
			if(products.get(i).getProductName().equals(productName)) {
				idx = i;
			}
		}
		if(idx == -1) {
			throw new IllegalArgumentException("Product not available.");
		}
		currentProduct = products.get(idx);
	}
	/**
	 * checks if 2 products are duplicates
	 * @param productName the other product
	 */
	private void isDuplicateProduct(String productName) {
		for(Product p : products) {
			if(p.getProductName().equalsIgnoreCase(productName)) {
				throw new IllegalArgumentException("Invalid product name.");
			}
		}
	}
	/**
	 * returns the tasks as a 2D array
	 * @return the array
	 */
	public String[][] getTasksAsArray() {
		if(currentProduct == null) {
			return null;
		}
		String[][] taskArray = new String[currentProduct.getTasks().size()][4];
		for(int i = 0; i < currentProduct.getTasks().size(); i++) {
			taskArray[i][0] = "" + currentProduct.getTasks().get(i).getTaskId();
			taskArray[i][1] = currentProduct.getTasks().get(i).getStateName();
			taskArray[i][2] = currentProduct.getTasks().get(i).getTypeLongName();
			taskArray[i][3] = currentProduct.getTasks().get(i).getTitle();
		}
		return taskArray;
	}
	/**
	 * gets a task by the ID
	 * @param id the task id
	 * @return the task
	 */
	public Task getTaskById(int id) {
		if(currentProduct == null) {
			return null;
		}
		return currentProduct.getTaskById(id);
		
	}
	/**
	 * executes a command on a task
	 * @param id the task id
	 * @param c the command
	 */
	public void executeCommand(int id, Command c) {
		if(currentProduct != null) {
			currentProduct.executeCommand(id, c);
		}
	}
	/**
	 * deletes a task
	 * @param id the task id
	 */
	public void deleteTaskById(int id) {
		if(currentProduct != null) {
			currentProduct.deleteTaskById(id);
		}
	}
	/**
	 * adds a task to the product
	 * @param name the task name
	 * @param type the task type
	 * @param creator the task creator
	 * @param note the task note
	 */
	public void addTaskToProduct(String name, Type type, String creator, String note) {
		if(currentProduct != null) {
			currentProduct.addTask(name, type, creator, note);
		}
	}
	/**
	 * gets the current product name
	 * @return the name
	 */
	public String getProductName() {
		if(currentProduct == null) {
			return null;
		}
		return currentProduct.getProductName();
		
	}
	/**
	 * gets the list of products
	 * @return the products
	 */
	public String[] getProductList() {
		String[] names = new String[products.size()];
		for(int i = 0; i < products.size(); i++) {
			names[i] = products.get(i).getProductName();
		}
		return names;
		
	}
	/**
	 * clears the product list
	 */
	public void clearProducts() {
		products.clear();
		currentProduct = null;
	}
	/**
	 * edits the current product
	 * @param productName the product to edit
	 */
	public void editProduct(String productName) {
		if(productName == null) {
			throw new IllegalArgumentException("Invalid product name.");
		}
		if(productName.length() == 0) {
			throw new IllegalArgumentException("Invalid product name.");
		}
		isDuplicateProduct(productName);
		if(currentProduct == null) {
			throw new IllegalArgumentException("No product selected.");
		}
		currentProduct.setProductName(productName);
	}
	/**
	 * adds a new product to the manager
	 * @param productName the product to add
	 */
	public void addProduct(String productName) {
		if(productName == null) {
			throw new IllegalArgumentException("Invalid product name.");
		}
		if(productName.length() == 0) {
			throw new IllegalArgumentException("Invalid product name.");
		}
		isDuplicateProduct(productName);
		Product p = new Product(productName);
		currentProduct = p;
		products.add(p);
	}
	/**
	 * removes the active product
	 */
	public void deleteProduct() {
		if(currentProduct == null) {
			throw new IllegalArgumentException("No product selected");
		}
		products.remove(currentProduct);
		if(products.size() == 0) {
			currentProduct = null;
		}
		else {
			currentProduct = products.get(0);
		}
	}
	/**
	 * resets the manager
	 */
	protected void resetManager() {
		singleton = null;
	}
}
