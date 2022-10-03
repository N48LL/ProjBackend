package ch.lubu.timekeeperv2.exception;

public class EntryDateNotFoundException extends RuntimeException {

    public EntryDateNotFoundException(int year, int month) {
        super("EntryDate for " + year + "-" + month + " could not be found. Year can only be between 1900 and 2100. Month can only be between 1 and 12.");
    }

    public EntryDateNotFoundException(int year) {
        super("EntryDate for " + year + " could not be found. Illegal String");
    }

}