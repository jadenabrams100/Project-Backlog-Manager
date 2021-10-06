package edu.ncsu.csc216.product_backlog.model.backlog;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.product_backlog.model.command.Command;
import edu.ncsu.csc216.product_backlog.model.task.Task;
/**
 * Contains tests to ensure BacklogManager functions properly
 * @author Jaden Abrams
 *
 */
class BacklogManagerTest {

	
	private final String VALID_FILE = "test-files/exp_tasks.txt";
	/**
	 * Ensures that getInstance works properly
	 */
	@Test
	void testGetInstance() {
		assertTrue(BacklogManager.getInstance() != null);
	}

	/**
	 * Ensures that saveToFle works properly
	 */
	@Test
	void testSaveToFile() {
		BacklogManager bm = BacklogManager.getInstance();
		bm.loadFromFile(VALID_FILE);
		bm.saveToFile("test-files/new_exp_tasks.txt");
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
		bm.clearProducts();
	}

	/**
	 * Ensures that loadFromFile works properly
	 */
	@Test
	void testLoadFromFile() {
		BacklogManager bm = BacklogManager.getInstance();
		bm.loadFromFile(VALID_FILE);
		assertEquals("Test Product", bm.getProductName());
		bm.loadFromFile("test-files/exp_task_backlog.txt");
		assertEquals("Product", bm.getProductName());
		assertEquals("Test Product", bm.getProductList()[0]);
		assertEquals("Product", bm.getProductList()[1]);
		assertEquals("A Product", bm.getProductList()[2]);
		bm.clearProducts();
		
	}

	/**
	 * Ensures that loadProduct works properly
	 */
	@Test
	void testLoadProduct() {
		BacklogManager bm = BacklogManager.getInstance();
		bm.loadFromFile(VALID_FILE);
		bm.loadFromFile("test-files/exp_task_backlog.txt");
		bm.loadProduct("A Product");
		assertEquals("A Product", bm.getProductName());
		assertThrows(IllegalArgumentException.class, () -> bm.loadProduct("f"));
		assertThrows(IllegalArgumentException.class, () -> bm.loadProduct(null));
		bm.clearProducts();
	}

	/**
	 * Ensures that getTasksAsArray works properly
	 */
	@Test
	void testGetTasksAsArray() {
		BacklogManager bm = BacklogManager.getInstance();
		bm.loadFromFile(VALID_FILE);
		bm.loadFromFile("test-files/exp_task_backlog.txt");
		assertEquals("1", bm.getTasksAsArray()[0][0]);
		assertEquals("2", bm.getTasksAsArray()[1][0]);
		assertEquals("3", bm.getTasksAsArray()[2][0]);
		assertEquals("Backlog", bm.getTasksAsArray()[0][1]);
		assertEquals("Backlog", bm.getTasksAsArray()[1][1]);
		assertEquals("Backlog", bm.getTasksAsArray()[2][1]);
		assertEquals("Bug", bm.getTasksAsArray()[0][2]);
		assertEquals("Feature", bm.getTasksAsArray()[1][2]);
		assertEquals("Knowledge Acquisition", bm.getTasksAsArray()[2][2]);
		assertEquals("title1", bm.getTasksAsArray()[0][3]);
		assertEquals("title2", bm.getTasksAsArray()[1][3]);
		assertEquals("title3", bm.getTasksAsArray()[2][3]);
		bm.clearProducts();
	}

	/**
	 * Ensures that getTaskById works properly
	 */
	@Test
	void testGetTaskById() {
		BacklogManager bm = BacklogManager.getInstance();
		assertEquals(null, bm.getTaskById(0));
		bm.loadFromFile(VALID_FILE);
		assertEquals("Owned", bm.getTaskById(3).getStateName());
		bm.clearProducts();
	}

	/**
	 * Ensures that executeCommand works properly
	 */
	@Test
	void testExecuteCommand() {
		BacklogManager bm = BacklogManager.getInstance();
		assertDoesNotThrow(() -> bm.executeCommand(4, new Command(Command.CommandValue.CLAIM,"jlabrams","note")));
		bm.loadFromFile(VALID_FILE);
		bm.executeCommand(7, new Command(Command.CommandValue.CLAIM,"jlabrams","note"));
		assertEquals("Owned", bm.getTaskById(7).getStateName());
		bm.clearProducts();
	}

	/**
	 * Ensures that deleteTaskById works properly
	 */
	@Test
	void testDeleteTaskById() {
		BacklogManager bm = BacklogManager.getInstance();
		bm.loadFromFile(VALID_FILE);
		bm.deleteTaskById(7);
		assertEquals(1, bm.getTasksAsArray().length);
		bm.clearProducts();
	}

	/**
	 * Ensures that addTaskToProduct works properly
	 */
	@Test
	void testAddTaskToProduct() {
		BacklogManager bm = BacklogManager.getInstance();
		assertDoesNotThrow(() -> bm.addTaskToProduct(VALID_FILE, Task.Type.BUG, VALID_FILE, VALID_FILE));
		bm.loadFromFile(VALID_FILE);
		bm.addTaskToProduct("title", Task.Type.BUG, "jlabrams", "note");
		assertEquals(3, bm.getTasksAsArray().length);
		bm.clearProducts();

	}

	/**
	 * Ensures that getProductName works properly
	 */
	@Test
	void testGetProductName() {
		BacklogManager bm = BacklogManager.getInstance();
		assertEquals(null, bm.getProductName());
		bm.loadFromFile(VALID_FILE);
		assertEquals("Test Product", bm.getProductName());
		bm.clearProducts();
	}

	/**
	 * Ensures that getProductList works properly
	 */
	@Test
	void testGetProductList() {
		BacklogManager bm = BacklogManager.getInstance();
		assertEquals(0, bm.getProductList().length);
		bm.loadFromFile(VALID_FILE);
		assertEquals("Test Product", bm.getProductList()[0]);
		bm.clearProducts();
		
	}

	/**
	 * Ensures that clearProducts works properly
	 */
	@Test
	void testClearProducts() {
		BacklogManager bm = BacklogManager.getInstance();
		bm.loadFromFile(VALID_FILE);
		bm.loadFromFile("test-files/exp_task_backlog.txt");
		assertEquals(3, bm.getProductList().length);
		assertEquals("Product", bm.getProductName());
		bm.clearProducts();
		assertEquals(0, bm.getProductList().length);
		assertNull(bm.getProductName());
		
	}

	/**
	 * Ensures that editProduct works properly
	 */
	@Test
	void testEditProduct() {
		BacklogManager bm = BacklogManager.getInstance();
		assertThrows(IllegalArgumentException.class, () -> bm.editProduct("My Product"));
		bm.loadFromFile(VALID_FILE);
		bm.loadFromFile("test-files/exp_task_backlog.txt");
		assertThrows(IllegalArgumentException.class, () -> bm.editProduct("A Product"));
		assertThrows(IllegalArgumentException.class, () -> bm.editProduct(null));
		assertThrows(IllegalArgumentException.class, () -> bm.editProduct(""));
		bm.editProduct("My Product");
		assertEquals("My Product", bm.getProductName());
		bm.resetManager();
	}

	/**
	 * Ensures that addProduct works properly
	 */
	@Test
	void testAddProduct() {
		BacklogManager bm = BacklogManager.getInstance();
		bm.loadFromFile(VALID_FILE);
		bm.loadFromFile("test-files/exp_task_backlog.txt");
		assertThrows(IllegalArgumentException.class, () -> bm.addProduct("Product"));
		assertThrows(IllegalArgumentException.class, () -> bm.editProduct(null));
		assertThrows(IllegalArgumentException.class, () -> bm.editProduct(""));
		bm.addProduct("My Product");
		assertEquals("My Product", bm.getProductName());
		bm.clearProducts();
	}

	/**
	 * Ensures that deleteProduct works properly
	 */
	@Test
	void testDeleteProduct() {
		BacklogManager bm = BacklogManager.getInstance();
		assertThrows(IllegalArgumentException.class,() -> bm.deleteProduct());
		bm.loadFromFile(VALID_FILE);
		bm.deleteProduct();
		assertNull(bm.getProductName());
		bm.loadFromFile("test-files/exp_task_backlog.txt");
		bm.deleteProduct();
		assertEquals("A Product", bm.getProductName());
	}



}
