package edu.ncsu.csc216.product_backlog.model.product;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

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

	/** a product name */
	private static final String NAME = "My Product";
	/** a product id for testing */
	private static final int ID_ONE = 4;
	/** a second product ID for testing */
	private static final int ID_TWO = 2;
	/** a task title for testing */
	private static final String TITLE = "Title";
	/** a task type for testing */
	private static final Type TYPE = Task.Type.FEATURE;
	/** a task creator for testing */
	private static final String CREATOR = "Me";
	/** a task note for testing */
	private static final String NOTE = "note";
	/**
	 * ensures that the Product constructor works as intended
	 */
	
	
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
		
		Product p2 = new Product("Shopping Cart Simulation");
		ArrayList<String> notes1 = new ArrayList<String>();
		notes1.add("[Backlog] Regular carts always choose the shortest line excluding the express register line (at index 0). If there are multiple shortest lines, a regular cart chooses one with the smallest index.");
		notes1.add("[Owned] Adding to sesmith5 backlog.");
		Task t6 = new Task(2, "Owned", "Regular Carts", "F", "jep", "sesmith5", "false", notes1);
		p2.addTask(t6);
		ArrayList<String> notes2 = new ArrayList<String>();
		notes2.add("[Backlog] Special carts are failing system tests associated with wait time.");
		notes2.add("[Owned] Adding to sesmith5 backlog.");
		notes2.add("[Processing] Replicated failure locally in unit test.");
		notes2.add("[Verifying] Implementation complete. Requires peer inspection.");
		Task t7 = new Task(5, "Verifying", "Calculating Wait Time", "B", "jdyoung2", "sesmith5", "false", notes2);
		p2.addTask(t7);
		ArrayList<String> notes3 = new ArrayList<String>();
		notes3.add("[Backlog] Learn more about Swing to debug GUI.");
		notes3.add("[Owned] Adding to sesmith5 backlog.");
		notes3.add("[Processing] Found Swing tutorials at http://docs.oracle.com/javase/tutorial/uiswing/start/.");
		Task t8 = new Task(3, "Processing", "Java Swing", "KA", "sesmith5", "sesmith5", "false", notes3);
		p2.addTask(t8);
		ArrayList<String> notes4 = new ArrayList<String>();
		notes4.add("[Backlog] Express carts always choose the shortest line. If there are multiple shortest lines, an express cart chooses the one with the smallest index.");
		Task t9 = new Task(1, "Backlog", "Express Carts", "F", "jep", "unowned", "false", notes4);
		p2.addTask(t9);
		ArrayList<String> notes5 = new ArrayList<String>();
		notes5.add("[Backlog] Add flatbed carts to simulation.");
		notes5.add("[Owned] Rejected. Flatbed carts won't fit through physical register stations.");
		Task t10 = new Task(10, "Rejected", "Flatbed Carts", "F", "jep", "unowned", "false", notes5);
		p2.addTask(t10);
		ArrayList<String> notes6 = new ArrayList<String>();
		notes6.add("[Backlog] This task should be ignored due to a duplicate id number");
		Task t11 = new Task(5, "Backlog", "Ignored", "F", "sesmith5", "unowned", "false", notes6);
		assertThrows(IllegalArgumentException.class, () -> p2.addTask(t11));
		//p2.addTask(t11);
		ArrayList<String> notes7 = new ArrayList<String>();
		notes7.add("[Backlog] Special carts always choose the shortest special register line.\n"
				+ "If there are multiple shortest special register lines, a special cart\n"
				+ "chooses one with the smallest index.");
		notes7.add("[Owned] Adding to sesmith5 backlog.");
		notes7.add("[Processing] Created hierarchy to prepare for other cart types.");
		notes7.add("[Verifying] Implementation complete. Requires peer inspection.");
		notes7.add("[Done] No problems found during inspection.");
		Task t12 = new Task(8, "Done", "Special Carts", "F", "jep", "sesmith5", "true", notes7);
		p2.addTask(t12);
		assertEquals(6, p2.getTasks().size());
		
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
		p.executeCommand(1, new Command(Command.CommandValue.CLAIM, "Also Me", "New Note"));
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
