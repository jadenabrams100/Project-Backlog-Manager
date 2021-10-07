package edu.ncsu.csc216.product_backlog.model.task;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.product_backlog.model.command.Command;
/**
 * ensures that Task works as intended
 * @author Jaden Abrams
 *
 */
class TaskTest {

	/** a task ID for testing */
	private static final int ID = 4;
	/** a task title for testing */
	private static final String TITLE = "title";
	/** a task creator for testing */
	private static final String CREATOR = "me";
	/** a task owner for testing */
	private static final String OWNER = "me";
	/** a task state for testing */
	private static final String STATE = "Owned";
	/** a task verifier for testing */
	private static final String FALSE = "false";
	/** a task verifier for testing */
	private static final String TRUE = "true";
	/** a task note for testing */
	private static final String NOTE = "note";
	/** a task type in string form for testing */
	private static final String TYPE = "F";
	
	
	/**
	 * ensures that Task(int,String,Type,String,String) works as intended
	 */
	@Test
	void testTaskIntStringTypeStringString() {
		assertDoesNotThrow(() -> new Task(ID, TITLE, Task.Type.FEATURE, CREATOR, NOTE));
		Task t = new Task(ID, TITLE, Task.Type.FEATURE, CREATOR, NOTE);
		
		//check state
		assertEquals("Backlog", t.getStateName());
		//check verified
		assertFalse(t.isVerified());
		//check notes list
		assertEquals(1, t.getNotes().size());
		
		assertThrows(IllegalArgumentException.class, () -> new Task(ID, TITLE, null, CREATOR, NOTE));
	}

	/**
	 * ensures that Task(int,String,String,String,String,String,String,ArrayList) works as intended
	 */
	@Test
	void testTaskIntStringStringStringStringStringStringArrayListOfString() {
		ArrayList<String> notes = new ArrayList<String>();
		assertDoesNotThrow(() -> new Task(ID, STATE, TITLE, TYPE, CREATOR, OWNER, FALSE, notes));
		assertDoesNotThrow(() -> new Task(ID, "Processing", TITLE, TYPE, CREATOR, OWNER, FALSE, notes));
		assertDoesNotThrow(() -> new Task(ID, "Verifying", TITLE, TYPE, CREATOR, OWNER, FALSE, notes));
		assertDoesNotThrow(() -> new Task(ID, "Done", TITLE, TYPE, CREATOR, OWNER, "true", notes));
		assertDoesNotThrow(() -> new Task(ID, "Rejected", TITLE, TYPE, CREATOR, "unowned", FALSE, notes));
		
		
		assertThrows(IllegalArgumentException.class, () -> new Task(0, STATE, TITLE, TYPE, CREATOR, OWNER, FALSE, notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(-1, STATE, TITLE, TYPE, CREATOR, OWNER, FALSE, notes));
		
		assertThrows(IllegalArgumentException.class, () -> new Task(ID, null, TITLE, TYPE, CREATOR, OWNER, FALSE, notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(ID, "", TITLE, TYPE, CREATOR, OWNER, FALSE, notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(ID, "Backlog", TITLE, TYPE, CREATOR, OWNER, FALSE, notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(ID, "Rejected", TITLE, TYPE, CREATOR, OWNER, FALSE, notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(ID, "Done", TITLE, TYPE, CREATOR, OWNER, FALSE, notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(ID, "Done", TITLE, "KA", CREATOR, OWNER, "true", notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(ID, "abc", TITLE, TYPE, CREATOR, OWNER, FALSE, notes));
		
		
		assertThrows(IllegalArgumentException.class, () -> new Task(ID, STATE, null, TYPE, CREATOR, OWNER, FALSE, notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(ID, STATE, "", TYPE, CREATOR, OWNER, FALSE, notes));
		
		assertThrows(IllegalArgumentException.class, () -> new Task(ID, STATE, TITLE, "", CREATOR, OWNER, FALSE, notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(ID, STATE, TITLE, null, CREATOR, OWNER, FALSE, notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(ID, STATE, TITLE, "abc", CREATOR, OWNER, FALSE, notes));
		
		assertThrows(IllegalArgumentException.class, () -> new Task(ID, STATE, TITLE, TYPE, null, OWNER, FALSE, notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(ID, STATE, TITLE, TYPE, "", OWNER, FALSE, notes));
		
		assertThrows(IllegalArgumentException.class, () -> new Task(ID, STATE, TITLE, TYPE, CREATOR, null, FALSE, notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(ID, STATE, TITLE, TYPE, CREATOR, "", FALSE, notes));
		
		assertThrows(IllegalArgumentException.class, () -> new Task(ID, STATE, TITLE, TYPE, CREATOR, OWNER, null, notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(ID, STATE, TITLE, TYPE, CREATOR, OWNER, "", notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(ID, STATE, TITLE, TYPE, CREATOR, OWNER, "so true", notes));
		
		assertThrows(IllegalArgumentException.class, () -> new Task(ID, STATE, TITLE, TYPE, CREATOR, OWNER, FALSE, null));
		
		
		
		
	}

	/**
	 * ensures that addNoteToList
	 */
	@Test
	void testAddNoteToList() {
		Task t = new Task(ID, TITLE, Task.Type.FEATURE, CREATOR, NOTE);
		
		assertThrows(IllegalArgumentException.class, () -> t.addNoteToList(null));
		assertThrows(IllegalArgumentException.class, () -> t.addNoteToList(""));
		assertEquals(1, t.addNoteToList(NOTE));
		assertEquals("[Backlog] note", t.getNotes().get(1));
	}

	/**
	 * ensures that getTaskId works as intended
	 */
	@Test
	void testGetTaskId() {
		Task t = new Task(ID, TITLE, Task.Type.FEATURE, CREATOR, NOTE);
		
		assertEquals(4, t.getTaskId());
	}

	/**
	 * ensures that getStateName works as intended
	 */
	@Test
	void testGetStateName() {
		Task t = new Task(ID, TITLE, Task.Type.FEATURE, CREATOR, NOTE);
		
		assertEquals("Backlog", t.getStateName());
		ArrayList<String> notes = new ArrayList<String>();

		Task t1 = new Task(ID, STATE, TITLE, TYPE, CREATOR, OWNER, FALSE, notes);
		assertEquals("Owned", t1.getStateName());
	}

	/**
	 * ensures that getType works as intended
	 */
	@Test
	void testGetType() {
		ArrayList<String> notes = new ArrayList<String>();
		Task t1 = new Task(ID, STATE, TITLE, TYPE, CREATOR, OWNER, FALSE, notes);
		assertEquals(Task.Type.FEATURE, t1.getType());
	}

	/**
	 * ensures that getTypeShortName works as intended
	 */
	@Test
	void testGetTypeShortName() {
		ArrayList<String> notes = new ArrayList<String>();
		assertEquals("F", new Task(ID, STATE, TITLE, TYPE, CREATOR, OWNER, FALSE, notes).getTypeShortName());
		assertEquals("B", new Task(ID, STATE, TITLE, "B", CREATOR, OWNER, FALSE, notes).getTypeShortName());
		assertEquals("TW", new Task(ID, STATE, TITLE, "TW", CREATOR, OWNER, FALSE, notes).getTypeShortName());
		assertEquals("KA", new Task(ID, STATE, TITLE, "KA", CREATOR, OWNER, FALSE, notes).getTypeShortName());
	}

	/**
	 * ensures that getTypeLongName works as intended
	 */
	@Test
	void testGetTypeLongName() {
		ArrayList<String> notes = new ArrayList<String>();
		assertEquals("Feature", new Task(ID, STATE, TITLE, TYPE, CREATOR, OWNER, FALSE, notes).getTypeLongName());
		assertEquals("Bug", new Task(ID, STATE, TITLE, "B", CREATOR, OWNER, FALSE, notes).getTypeLongName());
		assertEquals("Technical Work", new Task(ID, STATE, TITLE, "TW", CREATOR, OWNER, FALSE, notes).getTypeLongName());
		assertEquals("Knowledge Acquisition", new Task(ID, STATE, TITLE, "KA", CREATOR, OWNER, FALSE, notes).getTypeLongName());
	}

	/**
	 * ensures that getOwner works as intended
	 */
	@Test
	void testGetOwner() {
		ArrayList<String> notes = new ArrayList<String>();
		assertEquals("me", new Task(ID, STATE, TITLE, TYPE, CREATOR, OWNER, FALSE, notes).getOwner());
		
		assertEquals("unowned", new Task(ID, TITLE, Task.Type.FEATURE, CREATOR, NOTE).getOwner());
	}

	/**
	 * ensures that getTitle works as intended
	 */
	@Test
	void testGetTitle() {
		assertEquals("title", new Task(ID, TITLE, Task.Type.FEATURE, CREATOR, NOTE).getTitle());
	}

	/**
	 * ensures that getCreator works as intended
	 */
	@Test
	void testGetCreator() {
		assertEquals(CREATOR, new Task(ID, TITLE, Task.Type.FEATURE, CREATOR, NOTE).getCreator());
	}

	/**
	 * ensures that isVerified works as intended
	 */
	@Test
	void testIsVerified() {
		assertFalse(new Task(ID, TITLE, Task.Type.FEATURE, CREATOR, NOTE).isVerified());
	}

	/**
	 * ensures that getNotes works as intended
	 */
	@Test
	void testGetNotes() {
		ArrayList<String> notes = new ArrayList<String>();
		Task t1 = new Task(ID, STATE, TITLE, TYPE, CREATOR, OWNER, FALSE, notes);
		assertEquals(0, t1.getNotes().size());
		t1.addNoteToList(NOTE);
		assertEquals(1, t1.getNotes().size());
	}

	/**
	 * ensures that getNotesList works as intended
	 */
	@Test
	void testGetNotesList() {
		Task t1 = new Task(ID, TITLE, Task.Type.FEATURE, CREATOR, NOTE);
		assertEquals("- [Backlog] note\n", t1.getNotesList());
		t1.addNoteToList("NCSU");
		assertEquals("- [Backlog] note\n- [Backlog] NCSU\n", t1.getNotesList());
	}

	/**
	 * ensures that toString works as intended
	 */
	@Test
	void testToString() {
		Task t1 = new Task(ID, TITLE, Task.Type.FEATURE, CREATOR, NOTE);
		assertEquals("* 4,Backlog,title,F,me,unowned,false\n- [Backlog] note\n", t1.toString());
	}

	/**
	 * ensures that update works as intended
	 */
	@Test
	void testUpdate() {
		ArrayList<String> notes = new ArrayList<String>();
		Command backlog = new Command(Command.CommandValue.BACKLOG, null, NOTE);
		Command claim = new Command(Command.CommandValue.CLAIM, OWNER, NOTE);
		Command process = new Command(Command.CommandValue.PROCESS, null, NOTE);
		Command verify = new Command(Command.CommandValue.VERIFY, null, NOTE);
		Command complete = new Command(Command.CommandValue.COMPLETE, null, NOTE);
		Command reject = new Command(Command.CommandValue.REJECT, null, NOTE);
		//backlog commands below here
		Task t1 = new Task(ID, TITLE, Task.Type.FEATURE, CREATOR, NOTE);
		assertThrows(UnsupportedOperationException.class, () -> t1.update(backlog));
		assertThrows(UnsupportedOperationException.class, () -> t1.update(process));
		assertThrows(UnsupportedOperationException.class, () -> t1.update(verify));
		assertThrows(UnsupportedOperationException.class, () -> t1.update(complete));
		
		//rejected
		t1.update(reject);
		assertEquals("Rejected", t1.getStateName());
		//owned
		//Task t2 = new Task(ID, TITLE, Task.Type.FEATURE, CREATOR, NOTE);
		
		
		
		//owned commands below here
		
		Task t3 = new Task(ID, STATE, TITLE, TYPE, CREATOR, OWNER, FALSE, notes);
		assertThrows(UnsupportedOperationException.class, () -> t3.update(verify));
		assertThrows(UnsupportedOperationException.class, () -> t3.update(complete));
		assertThrows(UnsupportedOperationException.class, () -> t3.update(claim));
		t3.update(backlog);
		assertEquals("Backlog", t3.getStateName());
		assertEquals("unowned", t3.getOwner());
		//reject
		Task t4 = new Task(ID, STATE, TITLE, TYPE, CREATOR, OWNER, FALSE, notes);
		t4.update(reject);
		assertEquals("Rejected", t4.getStateName());
		assertEquals("unowned", t4.getOwner());
		
		//processing
		Task t5 = new Task(ID, STATE, TITLE, TYPE, CREATOR, OWNER, FALSE, notes);
		t5.update(process);
		assertEquals("Processing", t5.getStateName());
		

		
		//processing commands below here
		Task t6 = new Task(ID, "Processing", TITLE, TYPE, CREATOR, OWNER, FALSE, notes);
		assertThrows(UnsupportedOperationException.class, () -> t6.update(reject));
		assertThrows(UnsupportedOperationException.class, () -> t6.update(claim));
				//backlog
		t6.update(backlog);
		assertEquals("Backlog", t6.getStateName());
		assertEquals("unowned", t6.getOwner());
				//processing
		Task t7 = new Task(ID, "Processing", TITLE, TYPE, CREATOR, OWNER, TRUE, notes);
		t7.update(process);
		assertEquals("Processing", t7.getStateName());
				//verifying
		t7.update(verify);
		assertEquals("Verifying", t7.getStateName());
				//done
		Task t8 = new Task(ID, "Processing", TITLE, "KA", CREATOR, OWNER, TRUE, notes);
		t8.update(complete);
		assertEquals("Done", t8.getStateName());
		
		//verifying commands below here
		Task t9 = new Task(ID, "Verifying", TITLE, TYPE, CREATOR, OWNER, TRUE, notes);
		assertThrows(UnsupportedOperationException.class, () -> t9.update(backlog));
		assertThrows(UnsupportedOperationException.class, () -> t9.update(reject));
		assertThrows(UnsupportedOperationException.class, () -> t9.update(claim));
		assertThrows(UnsupportedOperationException.class, () -> t9.update(verify));
		t9.update(process);
		assertEquals("Processing", t9.getStateName());
		Task t10 = new Task(ID, "Verifying", TITLE, TYPE, CREATOR, OWNER, TRUE, notes);
		t10.update(complete);
		assertEquals("Done", t10.getStateName());
		
		Task t11 = new Task(ID, "Done", TITLE, TYPE, CREATOR, OWNER, TRUE, notes);
		assertThrows(UnsupportedOperationException.class, () -> t11.update(reject));
		assertThrows(UnsupportedOperationException.class, () -> t11.update(claim));
		assertThrows(UnsupportedOperationException.class, () -> t11.update(verify));
		assertThrows(UnsupportedOperationException.class, () -> t11.update(complete));
				//backlog
		t11.update(backlog);
		assertEquals("Backlog", t11.getStateName());
		assertEquals("unowned", t11.getOwner());
				//processing
		Task t12 = new Task(ID, "Done", TITLE, TYPE, CREATOR, OWNER, TRUE, notes);
		t12.update(process);
		assertEquals("Processing", t12.getStateName());
	}

	/**
	 * ensures that getNotesArray works as intended
	 */
	@Test
	void testGetNotesArray() {
		Task t1 = new Task(ID, TITLE, Task.Type.FEATURE, CREATOR, NOTE);
		assertEquals("[Backlog] note", t1.getNotesArray()[0]);
	}

}
