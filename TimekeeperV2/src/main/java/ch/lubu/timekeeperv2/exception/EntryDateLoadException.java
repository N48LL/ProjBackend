package ch.lubu.timekeeperv2.exception;

public class EntryDateLoadException extends RuntimeException {

    public EntryDateLoadException() {
        super("EntryDates could not be loaded.");
    }

}