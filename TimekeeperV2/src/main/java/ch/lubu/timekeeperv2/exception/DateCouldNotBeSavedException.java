package ch.lubu.timekeeperv2.exception;

import ch.lubu.timekeeperv2.model.EntryDate;
import ch.lubu.timekeeperv2.model.Time;

/**
 * This exception is thrown when there is a problem with saving a
 * {@link Time} object.
 * @author Lukas Bühler
 */
public class DateCouldNotBeSavedException extends RuntimeException{
    public DateCouldNotBeSavedException(EntryDate entryDate) {
        super("The time " + entryDate + " could not be saved.");
    }

}
