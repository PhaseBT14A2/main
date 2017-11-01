package seedu.address.model;

import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.alias.exceptions.DuplicateAliasException;
import seedu.address.model.alias.exceptions.UnknownCommandException;
import seedu.address.model.event.ReadOnlyEvent;
import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.person.exceptions.UnrecognisedParameterException;
import seedu.address.model.tag.Tag;
import seedu.address.model.user.ReadOnlyUser;
import seedu.address.model.user.exceptions.DuplicateUserException;

/**
 * The API of the Model component.
 */
public interface Model {
    //==================================AddressBook Components=============================================
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<ReadOnlyPerson> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<ReadOnlyUser> PREDICATE_SHOW_ALL_USERS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<ReadOnlyEvent> PREDICATE_SHOW_ALL_EVENTS = unused -> true;

    /**
     * Clears existing backing model and replaces with the provided new data.
     */
    void resetData(ReadOnlyAddressBook newData);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns the Account
     */
    ReadOnlyAccount getAccount();

    /**
     * Deletes the given person.
     */
    void deletePerson(ReadOnlyPerson target) throws PersonNotFoundException;

    /**
     * Order the list based on a parameter
     */
    void orderList(String parameter) throws UnrecognisedParameterException;

    /**
     * Adds the given person
     */
    void addPerson(ReadOnlyPerson person) throws DuplicatePersonException;

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     *
     * @throws DuplicatePersonException if updating the person's details causes the person to be equivalent to
     *                                  another existing person in the list.
     * @throws PersonNotFoundException  if {@code target} could not be found in the list.
     */
    void updatePerson(ReadOnlyPerson target, ReadOnlyPerson editedPerson)
            throws DuplicatePersonException, PersonNotFoundException;

    //@@author tingtx

    /**
     * Group the given person(s)
     */
    void groupPerson(Person target, Group group) throws PersonNotFoundException;
    //@@author

    /**
     * Delete the given tag on every person in the Addressbook
     */
    void deleteTag(Tag tag) throws PersonNotFoundException, DuplicatePersonException;

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<ReadOnlyPerson> getFilteredPersonList();

    /**
     * Returns a list of commands.
     */
    ArrayList<ArrayList<String>> getCommands();

    /**
     * Returns the set alias for command, null otherwise
     */
    String getAliasForCommand(String commandName);

    /**
     * Updates the filter of the filtered person list to show all persons
     */
    void updateFilteredListToShowAll();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<ReadOnlyPerson> predicate);
    //=====================================================================================================

    //==================================EventBook Components=============================================


    /**
     * Clears existing backing model and replaces with the provided new data.
     */
    void resetEventData(ReadOnlyEventBook newData);

    /**
     * Returns the EventBook
     */
    ReadOnlyEventBook getEventBook();

    /**
     * Deletes the given event.
     */
    void deleteEvent(ReadOnlyEvent target) throws EventNotFoundException;

    /**
     * Adds the given event
     */
    void addEvent(ReadOnlyEvent event) throws DuplicateEventException;

    /**
     * Replaces the given event {@code target} with {@code editedEvent}.
     *
     * @throws DuplicateEventException if updating the event's details causes the person to be equivalent to
     *                                 another existing event in the list.
     * @throws EventNotFoundException  if {@code target} could not be found in the list.
     */
    void updateEvent(ReadOnlyEvent target, ReadOnlyEvent editedEvent)
            throws DuplicateEventException, EventNotFoundException;

    /**
     * Returns an unmodifiable view of the filtered event list
     */
    ObservableList<ReadOnlyEvent> getFilteredEventList();

    /**
     * Updates the filter of the filtered event list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<ReadOnlyEvent> predicate);

    /**
     * Order the event list based on a parameter
     */
    void orderEventList(String parameter) throws UnrecognisedParameterException;
    //===================================================================================================

    /**
     * Updates the alias of the given function with the given {@code alias}.
     *
     * @throws NullPointerException if {@code alias} is null.
     */
    void setAlias(String command, String alias) throws UnknownCommandException, DuplicateAliasException;

    void persistUserAccount(ReadOnlyUser user) throws DuplicateUserException;

    byte[] retrieveDigestFromStorage();

    String retrieveSaltFromStorage(String userId);
}
