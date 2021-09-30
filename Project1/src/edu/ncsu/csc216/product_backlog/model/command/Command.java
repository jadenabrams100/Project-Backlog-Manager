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
	 * constructor for a command
	 * @param c the command value
	 * @param owner the command owner
	 * @param noteText a note associated with the command
	 */
	public Command(CommandValue c, String owner, String noteText) {
		
	}
	/**
	 * returns the command type
	 * @return the command type
	 */
	public CommandValue getCommand() {
		return null;
	}
	/**
	 * returns the note
	 * @return the note
	 */
	public String getNoteText() {
		return null;
	}
	/**
	 * returns who owns the command
	 * @return the owner
	 */
	public String getOwner() {
		return null;
	}
}
