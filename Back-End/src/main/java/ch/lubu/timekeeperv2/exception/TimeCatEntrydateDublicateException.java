package ch.lubu.timekeeperv2.exception;

/**
 * This exception is thrown when there is a problem with saving a
 * {@link ch.lubu.timekeeperv2.model.Time} object.
 * @author Lukas Bühler
 */

public class TimeCatEntrydateDublicateException extends RuntimeException{
    public TimeCatEntrydateDublicateException() {
        super("Kategorie für diesen Tag bereits vorhanden");
    }
}