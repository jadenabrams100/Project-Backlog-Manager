package edu.ncsu.csc216.product_backlog.model.product;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.product_backlog.model.command.Command;
import edu.ncsu.csc216.product_backlog.model.task.Task;
import edu.ncsu.csc216.product_backlog.model.task.Task.Type;
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
		setProductName(productName);
		emptyList();
		setTaskCounter();
	}
	/**
	 * Sets the product name
	 * @param productName the product name
	 */
	public void setProductName(String productName) {
		if(productName == null) {
			throw new IllegalArgumentException("Invalid product name.");
		}
		if(productName.length() == 0) {
			throw new IllegalArgumentException("Invalid product name.");
		}
		this.productName = productName;
	}
	/**
	 * gets the product name
	 * @return the product name
	 */
	public String getProductName() {
		return productName;
	}
	
	/**
	 * Sets the task counter as the largest ID plus one
	 */
	private void setTaskCounter() {
		if(tasks.size() == 0) {
			counter = 1;
		}
		else {
			int max = 0;
			for(int i = 0; i < tasks.size(); i++) {
				if(tasks.get(i).getTaskId() > max) {
					max = tasks.get(i).getTaskId();
				}
			}
			counter = max + 1;
		}
	}
	/**
	 * empties the list
	 */
	private void emptyList() {
		tasks = new ArrayList<Task>();
	}
	/**
	 * adds a new task
	 * @param t the task to add
	 */
	public void addTask(Task t) {
		for(Task task : tasks) {
			if(task.getTaskId() == t.getTaskId()) {
				throw new IllegalArgumentException("Task cannot be added.");
			}
		}
		for(int i = 0; i < tasks.size() - 1; i++) {
			if(tasks.get(i).getTaskId() < t.getTaskId() && t.getTaskId() < tasks.get(i + 1).getTaskId()) {
				tasks.add(i + 1, t);
				setTaskCounter();
			}
		}
		if(tasks.indexOf(t) == -1) {
			tasks.add(t);
			setTaskCounter();
		}
		
	}
	/**
	 * Creates a new task and adds it to the product
	 * @param title the title
	 * @param type the type
	 * @param creator the creator
	 * @param note the note
	 */
	public void addTask(String title, Type type, String creator, String note) {
		addTask(new Task(counter, title, type, creator, note));
	}
	
	/**
	 * returns the tasks
	 * @return the tasks
	 */
	public List<Task> getTasks() {
		return tasks;
	}
	/**
	 * gets a specific task by ID
	 * @param id the task id
	 * @return the task
	 */
	public Task getTaskById(int id) {
		for(Task t : tasks) {
			if(t.getTaskId() == id) {
				return t;
			}
		}
		return null;
	}
	/**
	 * Executes a command on a task
	 * @param id the task id
	 * @param c the command to execute
	 */
	public void executeCommand(int id, Command c) {
		for(Task t : tasks) {
			if (t.getTaskId() == id) {
				t.update(c);
			}
		}
	}
	/**
	 * removes a task from the product
	 * @param id the task id
	 */
	public void deleteTaskById(int id) {
		int idx = -1;
		for(int i = 0; i < tasks.size(); i++) {
			if(tasks.get(i).getTaskId() == id) {
				idx = 1;
			}
		}
		tasks.remove(idx);
		setTaskCounter();
	}
}
