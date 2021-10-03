package edu.ncsu.csc216.product_backlog.model.command;

/**
 * contains all the necessary methods for a command
 * @author Jaden Abrams
 *
 */
public class Command {
	
	/** all possible command values for a state. */
	public enum CommandValue { BACKLOG, CLAIM, PROCESS, VERIFY, COMPLETE, REJECT }
	/** a note associated with the command */
	private String note;
	/** who owns a command */
	private String owner;
	/** Command value*/
	private CommandValue c;
	/** an error message for an invalid command */
	public static final String COMMAND_ERROR_MESSAGE = "Invalid command.";
	/**
	 * constructor for a valid command
	 * @param c the command value
	 * @param owner the command owner
	 * @param noteText a note associated with the command
	 * @throws IllegalArgumentException if the command is null, the note is null, or if there is no owner with the claimed command
	 * or if there is an owner included with any other command value
	 */
	public Command(CommandValue c, String owner, String noteText) {
		if(c == null) {
			throw new IllegalArgumentException();
		}
		if(noteText == null) {
			throw new IllegalArgumentException();
		}
		if(noteText.length() == 0) {
			throw new IllegalArgumentException();
		}
		if((owner == null || owner.length() == 0) && c == CommandValue.CLAIM) {
				throw new IllegalArgumentException();
		}
		if(owner != null && c != CommandValue.CLAIM) {
				throw new IllegalArgumentException();
		}
		
		this.c = c;
		this.owner = owner;
		note = noteText;
	}
	/**
	 * returns the command type
	 * @return the command type
	 */
	public CommandValue getCommand() {
		return c;
	}
	/**
	 * returns the note
	 * @return the note
	 */
	public String getNoteText() {
		return note;
	}
	/**
	 * returns who owns the command
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}
}
