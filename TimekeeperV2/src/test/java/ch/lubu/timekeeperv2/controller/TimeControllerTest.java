package ch.lubu.timekeeperv2.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import ch.lubu.timekeeperv2.exception.TimeCouldNotBeSavedException;
import ch.lubu.timekeeperv2.model.Time;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TimeController.class})
@ExtendWith(SpringExtension.class)
class TimeControllerTest {
    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private EntryDateRepository entryDateRepository;

    @Autowired
    private TimeController timeController;

    @MockBean
    private TimeRepository timeRepository;

    /**
     * Tested Method: {@link TimeController#deleteTime(int)}
     * This Test checks if the method returns a 400 HTTP Status if the time could not be deleted.
     */
    @Test
    void testDeleteTime1() throws Exception {
        doThrow(new TimeCouldNotBeSavedException(new Time())).when(timeRepository).deleteById((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/time/delete/{id}", 1);
        MockMvcBuilders.standaloneSetup(timeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("\"BAD_REQUEST\""));
    }

    /**
     * Tested Method: {@link TimeController#deleteTime(int)}
     * This Test checks if the method returns a 202 HTTP Status if the time could be deleted.
     */
    @Test
    void testDeleteTime2() throws Exception {
        doNothing().when(timeRepository).deleteById((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/time/delete/{id}", 1);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(timeController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("\"ACCEPTED\""));
    }

    /**
     * Tested Method: {@link TimeController#getAllTimes()}
     * This Test checks if the method returns a list of all time entries.
     * Empty list is also valid.
     */
    @Test
    void testGetAllTimes() throws Exception {
        when(timeRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/time/all");
        MockMvcBuilders.standaloneSetup(timeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

