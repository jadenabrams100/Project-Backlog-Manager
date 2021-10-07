package edu.ncsu.csc216.product_backlog.model.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
/**
 * Contains all the tests to ensure Command works properly
 * @author Jaden Abrams
 *
 */
class CommandTest {

	/** Constant name for the command owner to simplify testing */
	private static final String OWNER = "Jaden";
	/** Constant name for the command not to simplify testing. */
	private static final String NOTE = "get milk";
	/**
	 * Ensures Command works as intended
	 */
	@Test
	void testCommand() {
		// Test valid construction (claim, not null owner, not null note
		Command c = new Command(Command.CommandValue.CLAIM, OWNER, NOTE);
		assertEquals(Command.CommandValue.CLAIM, c.getCommand());
		assertEquals(OWNER, c.getOwner());
		assertEquals(NOTE, c.getNoteText());
		
		
		// Test null command value
		assertThrows(IllegalArgumentException.class, () -> new Command(null, OWNER, NOTE));
		// test null note
		assertThrows(IllegalArgumentException.class, () -> new Command(Command.CommandValue.CLAIM, OWNER, null));
		assertThrows(IllegalArgumentException.class, () -> new Command(Command.CommandValue.CLAIM, OWNER, ""));
		// test null owner with claim
		assertThrows(IllegalArgumentException.class, () -> new Command(Command.CommandValue.CLAIM, null, NOTE));
		// test 0 length owner with claim
		assertThrows(IllegalArgumentException.class, () -> new Command(Command.CommandValue.CLAIM, "", NOTE));
		
		// test non null owner with any other command
		assertThrows(IllegalArgumentException.class, () -> new Command(Command.CommandValue.BACKLOG, OWNER, NOTE));
	}

	/**
	 * ensures that getCommand works as intended
	 */
	@Test
	void testGetCommand() {
		Command c = new Command(Command.CommandValue.CLAIM, OWNER, NOTE);
		
		assertEquals(Command.CommandValue.CLAIM, c.getCommand());
	}

	/**
	 * ensures that getNoteText works as intended
	 */
	@Test
	void testGetNoteText() {
		Command c = new Command(Command.CommandValue.CLAIM, OWNER, NOTE);
		
		assertEquals(NOTE, c.getNoteText());
	}

	/**
	 * ensures that getOwner works as intended
	 */
	@Test
	void testGetOwner() {
		Command c = new Command(Command.CommandValue.CLAIM, OWNER, NOTE);
		
		assertEquals(OWNER, c.getOwner());
	}

}
