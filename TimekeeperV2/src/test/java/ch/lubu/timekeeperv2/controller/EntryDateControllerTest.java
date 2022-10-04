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
     * @param TestContent The Content of the Post Request - Modify to alter the Result
     * Return Value of Date in Second xD
     */
    @Test
    void testSaveDate2() throws Exception {
        String TestContent = "{\"id\":null,\"date\":1670799600000,\"comment\":\"CommentText\",\"year\":\"2022\",\"times\":null}";
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
                .andExpect(MockMvcResultMatchers.content().string(TestContent));
    }

    /**
     * Tested Method: {@link EntryDateController#saveDate(EntryDateDto)}
     * This Test checks if the method returns a 400 HTTP Status if the date could not be saved.
     */
    @Test
    public void testSaveDate() throws Exception {
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

    /**
     * Tested Method: {@link EntryDateController#saveDate(EntryDateDto)}
     * This Test checks if the method returns Validation Error when comment is over 1024 characters.
     */
    @Test
    public void whenPostRequestToEntrydate_ErroronCommentSize() throws Exception {
        String comment = "{\"comment\": null }";
        String year = "{\"year\": null }";
        EntryDateDto entryDateDto = new EntryDateDto();
        entryDateDto.setComment("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean euismod bibendum laoreet. Proin gravida dolor sit amet lacus accumsan et viverra justo commodo. Proin sodales pulvinar tempor. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nam fermentum, nulla luctus pharetra vulputate, felis tellus mollis orci, sed rhoncus sapien nunc eget odio. :)");
        entryDateDto.setYear("2021");
        //entryDateDto.setMonth("11");
        //entryDateDto.setDay("12");
        mockMvc.perform(MockMvcRequestBuilders.post("/date/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(entryDateDto.toString()))
                //.andExpect(MockMvcResultMatchers.jsonPath("$.date").value("2021-11-10T23:00:00.000+00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.comment").value("Kommentar darf nicht länger als 1024 Zeichen sein."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value("2021"))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

}

