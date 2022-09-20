package ch.lubu.timekeeper.model;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.text.ParseException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class TimeTest {


    /**
     * Method under test: {@link Time#setDate(String, String, String)}
     */
    @Test
    void testSetDate() throws ParseException {
        (new Time()).setDate("2021", "01", "22");
    }
    @Test
    void testConstructor() {
        assertNull((new Time()).getDate());
    }
}

