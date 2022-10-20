package ch.lubu.timekeeperv2.exception;

/**
 * This Exception is thrown when a list of {@link ch.lubu.timekeeperv2.model.Category} objects could not be loaded.
 *
 * @author Lukas Bühler
 *
 */

public class CategoryLoadException extends RuntimeException {

    public CategoryLoadException() {
        super("Categories could not be loaded.");
    }

}
