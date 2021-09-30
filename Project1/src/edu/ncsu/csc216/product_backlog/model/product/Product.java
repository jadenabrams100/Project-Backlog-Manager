package edu.ncsu.csc216.product_backlog.model.product;

import java.util.List;

import edu.ncsu.csc216.product_backlog.model.command.Command;
import edu.ncsu.csc216.product_backlog.model.task.Task;
import edu.ncsu.csc216.product_backlog.model.task.Task.Type;;
/**
 * Contains the constructor and all the necessary methods for a Product
 * @author Jaden Abrams
 *
 */
public class Product {

	/** the productName*/
	private String productName;
	/** the counter */
	private int counter;
	/**the list of tasks*/
	private List<Task> tasks;
	
	/**
	 * Creates a Product Object
	 * @param productName the product name
	 */
	public Product(String productName){
		
	}
	/**
	 * Sets the product name
	 * @param productName the product name
	 */
	public void setProductName(String productName) {
		
	}
	/**
	 * gets the product name
	 * @return the product name
	 */
	public String getProductName() {
		return null;
	}
	
	/**
	 * Sets the task counter as the largest ID plus one
	 */
	private void setTaskCounter() {
		
	}
	/**
	 * empties the list
	 */
	private void emptyList() {
		
	}
	/**
	 * adds a new task
	 * @param t the task to add
	 */
	public void addTask(Task t) {
		
	}
	/**
	 * Creates a new task and adds it to the product
	 * @param title the title
	 * @param type the type
	 * @param creator the creator
	 * @param note the note
	 */
	public void addTask(String title, Type type, String creator, String note) {
		
	}
	
	/**
	 * returns the tasks
	 * @return the tasks
	 */
	public List<Task> getTasks() {
		return null;
	}
	/**
	 * gets a specific task by ID
	 * @param id the task id
	 * @return the task
	 */
	public Task getTaskById(int id) {
		return null;
	}
	/**
	 * Executes a command on a task
	 * @param id the task id
	 * @param c the command to execute
	 */
	public void executeCommand(int id, Command c) {
		
	}
	/**
	 * removes a task from the product
	 * @param id the task id
	 */
	public void deleteTaskById(int id) {
		
	}
}
