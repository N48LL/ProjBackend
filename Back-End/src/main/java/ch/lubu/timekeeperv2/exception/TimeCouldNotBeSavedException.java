package ch.lubu.timekeeperv2.exception;

import ch.lubu.timekeeperv2.model.Time;

/**
 * This exception is thrown when there is a problem with saving a
 * {@link ch.lubu.timekeeperv2.model.Time} object.
 * @author Lukas Bühler
 */
public class TimeCouldNotBeSavedException extends RuntimeException{
    public TimeCouldNotBeSavedException(Time time) {
        super("Der eintrag konnte nicht gespeichert werden.");
    }
}