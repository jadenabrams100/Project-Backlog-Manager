package edu.ncsu.csc216.product_backlog.model.product;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.product_backlog.model.command.Command;
import edu.ncsu.csc216.product_backlog.model.task.Task;
import edu.ncsu.csc216.product_backlog.model.task.Task.Type;
/**
 * ensures that Product works as intended
 * @author Jaden Abrams
 *
 */
class ProductTest {

	/**
	 * ensures that the Product constructor works as intended
	 */
	
	private final String NAME = "My Product";
	private final int ID_ONE = 4;
	private final int ID_TWO = 2;
	private final String TITLE = "Title";
	private final Type TYPE = Task.Type.FEATURE;
	private final String CREATOR = "Me";
	private final String NOTE = "note";
	
	@Test
	void testProduct() {
		assertDoesNotThrow(() -> new Product(NAME));
		Product p = new Product(NAME);
		assertEquals("My Product", p.getProductName());
		assertEquals(0, p.getTasks().size());
		
	}

	/**
	 * ensures that setProductName works as intended
	 */
	@Test
	void testSetProductName() {
		Product p = new Product(NAME);
		p.setProductName("My Swag Product");
		assertEquals("My Swag Product", p.getProductName());
		assertThrows(IllegalArgumentException.class, () -> new Product(null));
		assertThrows(IllegalArgumentException.class, () -> new Product(""));

	}

	/**
	 * ensures that getProductName works as intended
	 */
	@Test
	void testGetProductName() {
		Product p = new Product(NAME);
		assertEquals("My Product", p.getProductName());
	}

	/**
	 * ensures that addTask(Task) works as intended
	 */
	@Test
	void testAddTaskTask() {
		Product p = new Product(NAME);
		Task t1 = new Task(ID_ONE, TITLE, TYPE, CREATOR, NOTE);
		p.addTask(t1);
		Task t2 = new Task(ID_TWO, TITLE, TYPE, CREATOR, NOTE);
		p.addTask(t2);
		Task t3 = new Task(1, TITLE, TYPE, CREATOR, NOTE);
		Task t4 = new Task(12, TITLE, TYPE, CREATOR, NOTE);
		Task t5 = new Task(3, TITLE, TYPE, CREATOR, NOTE);
		
		assertEquals(2, p.getTasks().size());
		assertEquals(t2, p.getTasks().get(0));
		assertEquals(t1, p.getTasks().get(1));
		p.addTask(t3);
		assertEquals(t3, p.getTasks().get(0));
		assertEquals(t2, p.getTasks().get(1));
		assertEquals(t1, p.getTasks().get(2));
		p.addTask(t4);
		assertEquals(t3, p.getTasks().get(0));
		assertEquals(t2, p.getTasks().get(1));
		assertEquals(t1, p.getTasks().get(2));
		assertEquals(t4, p.getTasks().get(3));
		p.addTask(t5);
		assertEquals(t3, p.getTasks().get(0));
		assertEquals(t2, p.getTasks().get(1));
		assertEquals(t5, p.getTasks().get(2));
		assertEquals(t1, p.getTasks().get(3));
		assertEquals(t4, p.getTasks().get(4));
		assertThrows(IllegalArgumentException.class, () -> p.addTask(t2));
		
		
	}

	/**
	 * ensures that addTask(String,Type,String,String) works as intended
	 */
	@Test
	void testAddTaskStringTypeStringString() {
		Product p = new Product(NAME);
		p.addTask(TITLE, TYPE, CREATOR, NOTE);
		p.addTask(TITLE, TYPE, CREATOR, NOTE);
		p.addTask(TITLE, TYPE, CREATOR, NOTE);
		p.addTask(TITLE, TYPE, CREATOR, NOTE);
		assertEquals(1, p.getTasks().get(0).getTaskId());
		assertEquals(2, p.getTasks().get(1).getTaskId());
		assertEquals(3, p.getTasks().get(2).getTaskId());
		assertEquals(4, p.getTasks().get(3).getTaskId());
	}

	/**
	 * ensures that getTasks works as intended
	 */
	@Test
	void testGetTasks() {
		Product p = new Product(NAME);
		assertEquals(0, p.getTasks().size());
		Task t1 = new Task(ID_ONE, TITLE, TYPE, CREATOR, NOTE);
		p.addTask(t1);
		Task t2 = new Task(ID_TWO, TITLE, TYPE, CREATOR, NOTE);
		p.addTask(t2);
		Task t3 = new Task(1, TITLE, TYPE, CREATOR, NOTE);
		Task t4 = new Task(12, TITLE, TYPE, CREATOR, NOTE);
		Task t5 = new Task(3, TITLE, TYPE, CREATOR, NOTE);
		p.addTask(t3);
		p.addTask(t4);
		p.addTask(t5);
		assertEquals(5, p.getTasks().size());
		
	}

	/**
	 * ensures that getTaskById works as intended
	 */
	@Test
	void testGetTaskById() {
		Product p = new Product(NAME);
		Task t1 = new Task(ID_ONE, TITLE, TYPE, CREATOR, NOTE);
		p.addTask(t1);
		Task t2 = new Task(ID_TWO, TITLE, TYPE, CREATOR, NOTE);
		p.addTask(t2);
		Task t3 = new Task(1, TITLE, TYPE, CREATOR, NOTE);
		Task t4 = new Task(12, TITLE, TYPE, CREATOR, NOTE);
		Task t5 = new Task(3, TITLE, TYPE, CREATOR, NOTE);
		p.addTask(t3);
		p.addTask(t4);
		p.addTask(t5);
		assertEquals(t4, p.getTaskById(12));
		assertEquals(null, p.getTaskById(121));
	}

	/**
	 * ensures that executeCommand works as intended
	 */
	@Test
	void testExecuteCommand() {
		Product p = new Product(NAME);
		Task t1 = new Task(ID_ONE, TITLE, TYPE, CREATOR, NOTE);
		p.addTask(t1);
		Task t2 = new Task(ID_TWO, TITLE, TYPE, CREATOR, NOTE);
		p.addTask(t2);
		Task t3 = new Task(1, TITLE, TYPE, CREATOR, NOTE);
		Task t4 = new Task(12, TITLE, TYPE, CREATOR, NOTE);
		Task t5 = new Task(3, TITLE, TYPE, CREATOR, NOTE);
		p.addTask(t3);
		p.addTask(t4);
		p.addTask(t5);
		p.executeCommand(1, new Command(Command.CommandValue.CLAIM,"Also Me", "New Note"));
		assertEquals("Owned", p.getTaskById(1).getStateName());
		assertEquals("Also Me", p.getTaskById(1).getOwner());
	}

	/**
	 * ensures that deleteTaskById works as intended
	 */
	@Test
	void testDeleteTaskById() {
		Product p = new Product(NAME);
		Task t1 = new Task(ID_ONE, TITLE, TYPE, CREATOR, NOTE);
		p.addTask(t1);
		Task t2 = new Task(ID_TWO, TITLE, TYPE, CREATOR, NOTE);
		p.addTask(t2);
		Task t3 = new Task(1, TITLE, TYPE, CREATOR, NOTE);
		Task t4 = new Task(12, TITLE, TYPE, CREATOR, NOTE);
		Task t5 = new Task(3, TITLE, TYPE, CREATOR, NOTE);
		p.addTask(t3);
		p.addTask(t4);
		p.addTask(t5);
		p.deleteTaskById(21);
		assertEquals(5, p.getTasks().size());
		p.deleteTaskById(ID_ONE);
		assertEquals(4, p.getTasks().size());
		assertEquals(null, p.getTaskById(ID_ONE));
		
	}

}
