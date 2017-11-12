package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.EventBook;

/**
 * Clears the address book.
 */
public class ClearCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";

    public static String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public CommandResult executeUndoableCommand() {
        requireNonNull(model);
        model.resetData(new AddressBook());
        model.resetEventData(new EventBook());
        model.saveToEncryptedFile();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
