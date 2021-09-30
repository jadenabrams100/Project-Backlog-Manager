package edu.ncsu.csc216.product_backlog.model.task;

import java.util.ArrayList;

import edu.ncsu.csc216.product_backlog.model.command.Command;
/**
 * Contains all the necessary state, constructors, methods, and inner classes and interfaces to manage tasks using a FSM
 * @author Jaden Abrams
 *
 */
public class Task {

	/** the possible types of types of tasks. */
	public enum Type { FEATURE, BUG, TECHNICAL_WORK, KNOWLEDGE_ACQUISITION }
	/** the task ID*/
	private int taskId;
	/** the task title*/
	private String title;
	/** the task creator */
	private String creator;
	/** the task owner*/
	private String owner;
	/** whether the task has been verified or not */
	private boolean isVerified;
	/** all the notes attached to a task*/
	private ArrayList<String> notes;
	/** the task type*/
	private Type type;
	/** the task state*/
	private TaskState currentState;
	
	/**The backlog state for state FSM*/
	private BacklogState backlogState;
	/**The owned state for state FSM*/
	private OwnedState ownedState;
	/**The verifying state for state FSM*/
	private VerifyingState verifyingState;
	/**The processing state for state FSM*/
	private ProcessingState processingState;
	/**The done state for state FSM*/
	private DoneState doneState;
	/**The rejected state for state FSM*/
	private RejectedState rejectedState;
	
	
	/** the state of a task in the backlog state*/
	public static final String BACKLOG_NAME = "Backlog";
	/** the state of a task in the owned state*/
	public static final String OWNED_NAME = "Owned";
	/** the state of a task in the processing state*/
	public static final String PROCESSING_NAME = "Processing";
	/** the state of a task in the verifying state*/
	public static final String VERIFYING_NAME = "Verifying";
	/** the state of a task in the done state*/
	public static final String DONE_NAME = "Done";
	/** the state of a task in the rejected state*/
	public static final String REJECTED_NAME = "Rejected";
	/** the type for a feature task */
	public static final String FEATURE_NAME = "Feature";
	/** the type for a bug task */
	public static final String BUG_NAME = "Bug";
	/** the type for a technical work task */
	public static final String TECHNICAL_WORK_NAME = "Technical Work";
	/** the type for a knowledge acquisition task */
	public static final String KNOWLEDGE_ACQUISITION_NAME = "Knowledge Acquisition";
	/** shorthand symbol for a feature*/
	public static final String T_FEATURE = "F";
	/** shorthand symbol for a bug*/
	public static final String T_BUG = "B";
	/** shorthand symbol for technical work*/
	public static final String T_TECHNICAL_WORK = "TW";
	/** shorthand symbol for knowledge acquisition*/
	public static final String T_KNOWLEDGE_ACQUISITION = "KA";
	/** owner name for unowned tasks */
	public static final String UNOWNED = "Unowned";
	
	/**
	 * Constructor for a simple task
	 * @param id the task ID
	 * @param title the task title
	 * @param type the task type
	 * @param creator the task creator
	 * @param note a starter note for the task
	 */
	public Task(int id, String title, Type type, String creator, String note) {
		
	}
	/**
	 * constructor for a task with more data associated with it
	 * @param id the task ID
	 * @param state the task State
	 * @param title the task title
	 * @param type the task type
	 * @param creator the task creator
	 * @param owner the task owner
	 * @param verified whether the task is verified or not
	 * @param notes notes associated with the task
	 */
	public Task(int id, String state, String title, String type, String creator, String owner, String verified, ArrayList<String> notes) {
		
		
		
	}
	
	/**
	 * sets the task ID
	 * @param taskId task id to set
	 */
	private void setTaskId(int taskId) {
		
	}
	
	/**
	 * sets the task title
	 * @param title the title to set
	 */
	private void setTitle(String title) {
		
	}
	
	/**
	 * sets the task type
	 * @param type the type to set
	 */
	private void setType(Type type) {
		
	}
	/**
	 * sets the task creator
	 * @param creator the creator to set
	 */
	private void setCreator(String creator) {
		
	}
	/**
	 * sets the task owner
	 * @param owner the owner to set
	 */
	private void setOwner(String owner) {
		
	}
	/**
	 * sets whether the task is verified or not
	 * @param verified whether the task is verified
	 */
	private void setVerified(String verified) {
		
	}
	/**
	 * sets the notes for the task
	 * @param notes the notes to set
	 */
	private void setNotes(ArrayList<String> notes) {
		
	}
	/**
	 * adds a note to the list
	 * @param note the note to add
	 * @return something the project doesn't say
	 */
	public int addNoteToList(String note) {
		return 0;
	}
	/**
	 * gets the task id
	 * @return the id
	 */
	public int getTaskId() {
		return 0;
	}
	/**
	 * returns the task state as a string
	 * @return the state name
	 */
	public String getStateName() {
		return null;
	}
	/**
	 * sets the state
	 * @param state the state to set
	 */
	private void setState(String state) {
		
	}
	/**
	 * sets the type
	 * @param t the type to set
	 */
	private void setTypeFromString(String t) {
		
	}
	/**
	 * gets the type
	 * @return the type
	 */
	public Type getType() {
		return null;
	}
	/**
	 * gets the type short name
	 * @return the type short name
	 */
	public String getTypeShortName() {
		return null;
	}
	/**
	 * gets the type long name
	 * @return the long name
	 */
	public String getTypeLongName() {
		return null;
	}
	/**
	 * returns the owner
	 * @return the owner
	 */
	public String getOwner() {
		return null;
	}
	/**
	 * returns the title
	 * @return the title
	 */
	public String getTitle() {
		return null;
	}
	/**
	 * gets the creator
	 * @return the creator
	 */
	public String getCreator() {
		return null;
	}
	/**
	 * returns whether the task is verified
	 * @return if it is verified
	 */
	public boolean isVerified() {
		return false;
	}
	/**
	 * gets the array notes
	 * @return the notes
	 */
	public ArrayList<String> getNotes() {
		return null;
	}
	/**
	 * gets the notes as a string
	 * @return the notes
	 */
	public String getNotesList() {
		return null;
	}
	/**
	 * returns the task as a string
	 * @return the task
	 */
	public String toString() {
		return null;
	}
	/**
	 * tells the task to update
	 * @param c how the task is to update
	 */
	public void update(Command c) {
		
	}
	/**
	 * gets the notes as a 1D array
	 * @return the notes
	 */
	public String[] getNotesArray() {
		return null;
	}
	/**
	 * all the necessary behaviors for a task in the backlog state
	 * @author Jaden Abrams
	 *
	 */
	public class BacklogState implements TaskState {

		/**
		 * creates a backlog state
		 */
		private BacklogState() {
			
		}
		/**
		 * changes the state
		 * @param c the command
		 */
		@Override
		public void updateState(Command c) {

			
		}

		/**
		 * gets the state name
		 * @return the state
		 */
		@Override
		public String getStateName() {

			return null;
		}
		
	}
	/**
	 * contains all the behaviors for the owned state
	 * @author Jaden Abrams
	 *
	 */
	public class OwnedState implements TaskState {

		/**
		 * Constructor for the owned state
		 */
		private OwnedState() {
			
		}
		/**
		 * updates the state
		 * @param c the command
		 */
		@Override
		public void updateState(Command c) {

			
		}

		/**
		 * gets the state
		 * @return the state
		 */
		@Override
		public String getStateName() {
			return null;
		}
		
	}
	/**
	 * contains the behaviors for the processing state
	 * @author Jaden Abrams
	 *
	 */
	public class ProcessingState implements TaskState {

		/**
		 * Constructor for the processing state
		 */
		private ProcessingState() {
			
		}
		/**
		 * updates the state
		 * @param c the command
		 */
		@Override
		public void updateState(Command c) {

			
		}

		/**
		 * gets the state
		 * @return the state
		 */
		@Override
		public String getStateName() {

			return null;
		}
		
	}
	/**
	 * contains the behaviors for the verifying state
	 * @author Jaden Abrams
	 *
	 */
	public class VerifyingState implements TaskState {

		/**
		 * Constructor for the verifying state
		 */
		private VerifyingState() {
			
		}
		/**
		 * updates the state
		 * @param c the command
		 */
		@Override
		public void updateState(Command c) {

			
		}

		/**
		 * gets the state
		 * @return the state
		 */
		@Override
		public String getStateName() {
	
			return null;
		}
		
	}
	
	/**
	 * contains the behaviors for the done state
	 * @author Jaden Abrams
	 *
	 */
	public class DoneState implements TaskState {

		/**
		 * constructor for the done state
		 */
		private DoneState() {
			
		}
		
		/**
		 * updates the state
		 * @param c the command
		 */
		@Override
		public void updateState(Command c) {

			
		}

		/**
		 * gets the state
		 * @return the state
		 */
		@Override
		public String getStateName() {
			return null;
		}
		
	}
	/**
	 * contains the behaviors for the rejected state
	 * @author Jaden Abrams
	 *
	 */
	public class RejectedState implements TaskState {

		/**
		 * Constructor for the rejected state
		 */
		private RejectedState() {
			
		}
		/**
		 * updates the state
		 * @param c the command
		 */
		@Override
		public void updateState(Command c) {
			
		}

		/**
		 * gets the state name
		 * @return the command
		 */
		@Override
		public String getStateName() {
			return null;
		}
		
	}
	
	
	
	/**
	 * Interface for states in the Task State Pattern.  All 
	 * concrete task states must implement the TaskState interface.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu) 
	 */
	private interface TaskState {
		
		/**
		 * Update the Task based on the given Command
		 * An UnsupportedOperationException is thrown if the Command is not a
		 * is not a valid action for the given state.  
		 * @param c Command describing the action that will update the Task
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		void updateState(Command c);
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		String getStateName();
	
	}
}
