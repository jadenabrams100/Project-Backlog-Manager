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
	private final BacklogState backlogState = new BacklogState();
	/**The owned state for state FSM*/
	private final OwnedState ownedState = new OwnedState();
	/**The verifying state for state FSM*/
	private final VerifyingState verifyingState = new VerifyingState();
	/**The processing state for state FSM*/
	private final ProcessingState processingState = new ProcessingState();
	/**The done state for state FSM*/
	private final DoneState doneState = new DoneState();
	/**The rejected state for state FSM*/
	private final RejectedState rejectedState = new RejectedState();
	
	
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
	public static final String UNOWNED = "unowned";
	
	/**
	 * Constructor for a simple task
	 * @param id the task ID
	 * @param title the task title
	 * @param type the task type
	 * @param creator the task creator
	 * @param note a starter note for the task
	 */
	public Task(int id, String title, Type type, String creator, String note) {
		setTaskId(id);
		setTitle(title);
		setType(type);
		setCreator(creator);
		setOwner(UNOWNED);
		setVerified("false");
		notes = new ArrayList<String>();
		setState(BACKLOG_NAME);
		addNoteToList(note);
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
		setTaskId(id);
		setTitle(title);
		setTypeFromString(type);
		setCreator(creator);
		setOwner(owner);
		setVerified(verified);
		setNotes(notes);
		setState(state);
		
	}
	
	/**
	 * sets the task ID
	 * @param taskId task id to set
	 * @throws new IllegalArgumentException if the id is zero or less
	 */
	private void setTaskId(int taskId) {
		if (taskId <= 0) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		this.taskId = taskId;
	}
	
	/**
	 * sets the task title
	 * @param title the title to set
	 * @throws IllegalArgumentException if the title is null or empty
	 */
	private void setTitle(String title) {
		if(title == null) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		if(title.length() == 0) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		this.title = title;
	}
	
	/**
	 * sets the task type
	 * @param type the type to set
	 * @throws IllegalArgmentException if type is null
	 */
	private void setType(Type type) {
		if(type == null) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		this.type = type;
	}
	/**
	 * sets the task creator
	 * @param creator the creator to set
	 * @throws IllegalArgumentException if creator is null or empty
	 */
	private void setCreator(String creator) {
		if(creator == null) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		if(creator.length() == 0) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		this.creator = creator;
	}
	/**
	 * sets the task owner
	 * @param owner the owner to set
	 * @throws IllegalArgumentException if owner is null or empty
	 */
	private void setOwner(String owner) {
		if(owner == null) {
			this.owner = owner;
			return;
		}
		if(owner.length() == 0) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		this.owner = owner;
	}
	/**
	 * sets whether the task is verified or not
	 * @param verified whether the task is verified
	 * @throws IllegalArgumentException if verified is null or not true of false, ignoring case
	 */
	private void setVerified(String verified) {
		if(verified == null) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		if(!"true".equalsIgnoreCase(verified) && !"false".equalsIgnoreCase(verified)) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		isVerified = Boolean.valueOf(verified);
	}
	/**
	 * sets the notes for the task
	 * @param notes the notes to set
	 * @throws IllegalArgumentException if notes is null
	 */
	private void setNotes(ArrayList<String> notes) {
		if(notes == null) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		this.notes = notes;
	}
	/**
	 * adds a note to the list
	 * @param note the note to add
	 * @return how many notes there are in the list
	 * @throws IllegalArgumentException if note is empty or null
	 */
	public int addNoteToList(String note) {
		if(note == null) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		if(note.length() == 0) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		notes.add("[" + getStateName() + "] " + note);
		return notes.size() - 1;
	}
	/**
	 * gets the task id
	 * @return the id
	 */
	public int getTaskId() {
		return taskId;
	}
	/**
	 * returns the task state as a string
	 * @return the state name
	 */
	public String getStateName() {
		return currentState.getStateName();
	}
	/**
	 * sets the state
	 * @param state the state to set
	 * @throws IllegalArgumentException if state is null, empty, 
	 * backlog or rejected with an owner that is not onowned
	 * done, not verified, and not a knowledge acquisition type, or otherwise
	 * is not the name of a valid state
	 */
	private void setState(String state) {
		if(state == null) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		if(state.length() == 0) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		if(state.equalsIgnoreCase(BACKLOG_NAME) && !owner.equals(UNOWNED)) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		if(state.equalsIgnoreCase(REJECTED_NAME) && !owner.equals(UNOWNED)) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		if(state.equalsIgnoreCase(DONE_NAME) && !isVerified && (type == Type.FEATURE || type == Type.BUG || type == Type.TECHNICAL_WORK)) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		if(state.equalsIgnoreCase(DONE_NAME) && isVerified && type == Type.KNOWLEDGE_ACQUISITION) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		
		if(state.equalsIgnoreCase(BACKLOG_NAME)) {
			currentState = backlogState;
		}
		else if(state.equalsIgnoreCase(OWNED_NAME)) {
			currentState = ownedState;
		}
		else if(state.equalsIgnoreCase(PROCESSING_NAME)) {
			currentState = processingState;
		}
		else if(state.equalsIgnoreCase(VERIFYING_NAME)) {
			currentState = verifyingState;
		}
		else if(state.equalsIgnoreCase(DONE_NAME)) {
			currentState = doneState;
		}
		else if(state.equalsIgnoreCase(REJECTED_NAME)) {
			currentState = rejectedState;
		}
		else {
			throw new IllegalArgumentException("Invalid task information.");
		}
		
	}
	/**
	 * sets the type
	 * @param t the type to set
	 * @throws IllegalArgumentException if t is null, empty, or does not match the name of a type
	 */
	private void setTypeFromString(String t) {
		if(t == null) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		if(t.length() == 0) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		if(t.equals(T_FEATURE)) {
			type = Type.FEATURE;
		}
		else if(t.equals(T_BUG)) {
			type = Type.BUG;
		}
		else if(t.equals(T_TECHNICAL_WORK)) {
			type = Type.TECHNICAL_WORK;
		}
		else if(t.equals(T_KNOWLEDGE_ACQUISITION)) {
			type = Type.KNOWLEDGE_ACQUISITION;
		}
		else {
			throw new IllegalArgumentException("Invalid task information.");
		}
		
	}
	/**
	 * gets the type
	 * @return the type
	 */
	public Type getType() {
		return type;
	}
	/**
	 * gets the type short name
	 * @return the type short name
	 */
	public String getTypeShortName() {
		if(type == Type.BUG) {
			return T_BUG;
		}
		if(type == Type.FEATURE) {
			return T_FEATURE;
		}
		if(type == Type.KNOWLEDGE_ACQUISITION) {
			return T_KNOWLEDGE_ACQUISITION;
		}
		return T_TECHNICAL_WORK;
	}
	/**
	 * gets the type long name
	 * @return the long name
	 */
	public String getTypeLongName() {
		if(type == Type.BUG) {
			return BUG_NAME;
		}
		if(type == Type.FEATURE) {
			return FEATURE_NAME;
		}
		if(type == Type.KNOWLEDGE_ACQUISITION) {
			return KNOWLEDGE_ACQUISITION_NAME;
		}
		return TECHNICAL_WORK_NAME;
	}
	/**
	 * returns the owner
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * returns the title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * gets the creator
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}
	/**
	 * returns whether the task is verified
	 * @return if it is verified
	 */
	public boolean isVerified() {
		return isVerified;
	}
	/**
	 * gets the array notes
	 * @return the notes
	 */
	public ArrayList<String> getNotes() {
		return notes;
	}
	/**
	 * gets the notes as a string
	 * @return the notes
	 */
	public String getNotesList() {
		String notesList = "";
		for(int i = 0; i < notes.size(); i++) {
			notesList = notesList + "- " + notes.get(i) + "\n";
		}
		//notesList = notesList + "- " + notes.get(notes.size()-1);
		return notesList;
	}
	/**
	 * returns the task as a string
	 * @return the task
	 */
	public String toString() {
		return "* " + getTaskId() + "," + getStateName() + "," + getTitle() + "," + getTypeShortName() + "," + getCreator() + "," + getOwner() + "," + isVerified() + "\n" + getNotesList();
	}
	/**
	 * tells the task to update
	 * @param c how the task is to update
	 */
	public void update(Command c) {
		currentState.updateState(c);
	}
	/**
	 * gets the notes as a 1D array
	 * @return the notes
	 */
	public String[] getNotesArray() {
		String[] notesArray = new String[notes.size()];
		for (int i = 0; i < notes.size(); i++) {
			notesArray[i] = notes.get(i);
		}
		return notesArray;
	}
	/**
	 * all the necessary behaviors for a task in the backlog state
	 * @author Jaden Abrams
	 *
	 */
	public class BacklogState implements TaskState {
		/**
		 * changes the state
		 * @param c the command
		 * @throws IllegalArgumentException if the command is anything other than CLAIM or REJECT
		 */
		@Override
		public void updateState(Command c) {
			if(c.getCommand() == Command.CommandValue.CLAIM) {
				currentState = ownedState;
				setOwner(c.getOwner());
				addNoteToList(c.getNoteText());
			}
			else if(c.getCommand() == Command.CommandValue.REJECT) {
				currentState = rejectedState;
				addNoteToList(c.getNoteText());
			}
			else {
				throw new UnsupportedOperationException("Invalid transition.");
			}
		}

		/**
		 * gets the state name
		 * @return the state
		 */
		@Override
		public String getStateName() {

			return BACKLOG_NAME;
		}
		
	}
	/**
	 * contains all the behaviors for the owned state
	 * @author Jaden Abrams
	 *
	 */
	public class OwnedState implements TaskState {
		/**
		 * updates the state
		 * @param c the command
		 * @throws IllegalArgumentException if command is not PROCESS, REJECT, or BACKLOG
		 */
		@Override
		public void updateState(Command c) {
			if(c.getCommand() == Command.CommandValue.PROCESS) {
				currentState = processingState;
				addNoteToList(c.getNoteText());
			}
			else if(c.getCommand() == Command.CommandValue.REJECT) {
				currentState = rejectedState;
				setOwner(null);
				addNoteToList(c.getNoteText());
			}
			else if(c.getCommand() == Command.CommandValue.BACKLOG) {
				currentState = backlogState;
				setOwner(null);
				addNoteToList(c.getNoteText());
			}
			else {
				throw new UnsupportedOperationException("Invalid transition.");
			}
		}

		/**
		 * gets the state
		 * @return the state
		 */
		@Override
		public String getStateName() {
			return OWNED_NAME;
		}
		
	}
	/**
	 * contains the behaviors for the processing state
	 * @author Jaden Abrams
	 *
	 */
	public class ProcessingState implements TaskState {
		/**
		 * updates the state
		 * @param c the command
		 * @throws IllegalArgumentException if the command is invalid according to the FSM
		 */
		@Override
		public void updateState(Command c) {
			if(c.getCommand() == Command.CommandValue.PROCESS) {
				addNoteToList(c.getNoteText());
			}
			else if(c.getCommand() == Command.CommandValue.VERIFY) {
				if(type != Type.KNOWLEDGE_ACQUISITION) {
					currentState = verifyingState;
					addNoteToList(c.getNoteText());
				}
				else {
					throw new UnsupportedOperationException("Invalid transition.");
				}
			}
			else if(c.getCommand() == Command.CommandValue.COMPLETE) {
				if(type == Type.KNOWLEDGE_ACQUISITION) {
					currentState = doneState;
					addNoteToList(c.getNoteText());
				}
				else {
					throw new UnsupportedOperationException("Invalid transition.");
				}
			}
			else if(c.getCommand() == Command.CommandValue.BACKLOG) {
				currentState = backlogState;
				setOwner(null);
				addNoteToList(c.getNoteText());
			}
			else {
				throw new UnsupportedOperationException("Invalid transition.");
			}
		}

		/**
		 * gets the state
		 * @return the state
		 */
		@Override
		public String getStateName() {

			return PROCESSING_NAME;
		}
		
	}
	/**
	 * contains the behaviors for the verifying state
	 * @author Jaden Abrams
	 *
	 */
	public class VerifyingState implements TaskState {
		/**
		 * updates the state
		 * @param c the command
		 * @throws IllegalArgumentException if the command is anything other than COMPLETE or PROCESS
		 */
		@Override
		public void updateState(Command c) {
			if(c.getCommand() == Command.CommandValue.COMPLETE) {
				currentState = doneState;
				addNoteToList(c.getNoteText());
				isVerified = true;
			}
			else if(c.getCommand() == Command.CommandValue.PROCESS) {
				currentState = processingState;
				addNoteToList(c.getNoteText());
				isVerified = false;
			}
			else {
				throw new UnsupportedOperationException("Invalid transition.");
			}
			
		}

		/**
		 * gets the state
		 * @return the state
		 */
		@Override
		public String getStateName() {
	
			return VERIFYING_NAME;
		}
		
	}
	
	/**
	 * contains the behaviors for the done state
	 * @author Jaden Abrams
	 *
	 */
	public class DoneState implements TaskState {
		/**
		 * updates the state
		 * @param c the command
		 * @throws IllegalArgumentException if command is anything other than PROCESS or BACKLOG
		 */
		@Override
		public void updateState(Command c) {
			if(c.getCommand() == Command.CommandValue.PROCESS) {
				currentState = processingState;
				addNoteToList(c.getNoteText());
				isVerified = false;
			}
			else if(c.getCommand() == Command.CommandValue.BACKLOG) {
				currentState = backlogState;
				setOwner(null);
				addNoteToList(c.getNoteText());
				isVerified = false;
			}
			else {
				throw new UnsupportedOperationException("Invalid transition.");
			}
			
		}

		/**
		 * gets the state
		 * @return the state
		 */
		@Override
		public String getStateName() {
			return DONE_NAME;
		}
		
	}
	/**
	 * contains the behaviors for the rejected state
	 * @author Jaden Abrams
	 *
	 */
	public class RejectedState implements TaskState {
		/**
		 * updates the state
		 * @param c the command
		 * @throws IllegalArgumentException if command is anything other than BACKLOG
		 */
		@Override
		public void updateState(Command c) {
			if(c.getCommand() == Command.CommandValue.BACKLOG) {
				currentState = backlogState;
				addNoteToList(c.getNoteText());
				setOwner(null);
			}
			else {
				throw new UnsupportedOperationException("Invalid transition.");
			}
		}

		/**
		 * gets the state name
		 * @return the command
		 */
		@Override
		public String getStateName() {
			return REJECTED_NAME;
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
