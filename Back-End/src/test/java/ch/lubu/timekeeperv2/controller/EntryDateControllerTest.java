package ch.lubu.timekeeperv2.controller;

import ch.lubu.timekeeperv2.Dto.EntryDateDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {EntryDateController.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc
class EntryDateControllerTest {
    @Autowired
    private EntryDateController entryDateController;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EntryDateRepository entryDateRepository;
    @MockBean
    private TimeRepository timeRepository;
    @MockBean
    private CategoryRepository categoryRepository;



    /**
     * Tested Method: {@link EntryDateController#saveDate(EntryDateDto)}
     * This Test attempts to make a Post Request to the save Data
     * @param testContent The Content of the Post Request - Modify to alter the expected result
     * Return Value of Date in Second xD
     */
    @Test
    void whenPostRequestToEntryDateAndValidEntries() throws Exception {
        String testReturnContent = "{\"id\":null,\"date\":1670799600000,\"comment\":\"CommentText\",\"times\":null,\"year\":\"2022\"}";
        EntryDateDto entryDateDto = new EntryDateDto();

        entryDateDto.setComment("CommentText");
        entryDateDto.setDay("12");
        entryDateDto.setId(1);
        entryDateDto.setMonth("12");
        entryDateDto.setTime(new ArrayList<>());
        entryDateDto.setYear("2022");

        String content = (new ObjectMapper()).writeValueAsString(entryDateDto);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/date/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(entryDateController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.content().string(testReturnContent));
    }

    /**
     * Tested Method: {@link EntryDateController#saveDate(EntryDateDto)}
     * This Test checks if the method returns a 400 HTTP Status if the date could not be saved.
     */
    @Test
    public void WhenPostRequestToEntryDateAndValidConnection() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/date/add")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new EntryDateDto(1)));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(entryDateController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(status().is(400));
    }

}

