package ch.lubu.timekeeper.exception;

public class TimeLoadException extends RuntimeException {
    public TimeLoadException() {
        super("Times could not be loaded.");
    }
}