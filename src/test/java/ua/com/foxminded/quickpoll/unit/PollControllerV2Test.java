package ua.com.foxminded.quickpoll.unit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import ua.com.foxminded.quickpoll.Application;
import ua.com.foxminded.quickpoll.domain.Option;
import ua.com.foxminded.quickpoll.domain.Poll;
import ua.com.foxminded.quickpoll.repository.PollRepository;
import ua.com.foxminded.quickpoll.v2.controller.PollController;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ContextConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
@EnableSpringDataWebSupport
public class PollControllerV2Test {

    @InjectMocks
    PollController pollController;

    @MockBean
    private PollRepository pollRepository;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(pollController).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers(new ViewResolver() {
                    @Override
                    public View resolveViewName(String viewName, Locale locale) throws Exception {
                        return new MappingJackson2JsonView();
                    }
                })
                .build();
    }

    @Test
    public void testCreatePoll() throws Exception {
        when(pollRepository.save(any())).thenReturn(pollCreator());
        this.mockMvc
                .perform(post("/v2/polls")
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .content("{\n" + "\"question\": \"Where is my car dude?\",\n" + "\"options\": [\n" +
                                          "{\"value\": \"Who did you say that to?\"},\n" +
                                          "{\"value\": \"Oh sh*t, here we go again?\"},\n" +
                                          "{\"value\": \"Valera, it's your time!\"}\n" +
                                          "]\n" + "}"))
                .andExpect(status().isCreated());

    }

    @Test
    public void getPollTest() throws Exception {
        Poll poll = pollCreator();
        when(pollRepository.findById(any())).thenReturn(java.util.Optional.of(poll));
        mockMvc.perform(get("/v2/polls/1"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.content().json("{\"id\":1,\"question\":\"Where is my car dude?\"," +
                                                               "\"options\":[{\"id\":4,\"value\":\"Valera, it's your time!\"},{\"id\":2,\"value\":\"Who did you say that to?\"},{\"id\":3,\"value\":\"Oh sh*t, here we go again?\"}]}",
                                                               false));
        verify(pollRepository, times(2)).findById(any());
    }

    @Test
    public void deletePollTest() throws Exception {
        Poll poll = pollCreator();
        when(pollRepository.findById(any())).thenReturn(java.util.Optional.of(poll));
        mockMvc.perform(delete("/v2/polls/1")).andExpect(status().isOk());
        verify(pollRepository, times(1)).deleteById(any());
        verify(pollRepository, times(1)).findById(any());
        verify(pollRepository).deleteById(any());
    }

    @Test
    public void updatePollTest() throws Exception {
        Poll poll = pollCreator();
        when(pollRepository.findById(any())).thenReturn(java.util.Optional.of(new Poll()));
        when(pollRepository.save(any())).thenReturn(poll);
        this.mockMvc
                .perform(put("/v2/polls/1")
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .content("{\n" + "\"question\": \"Where is my car dude?\",\n" + "\"options\": [\n" +
                                          "{\"value\": \"Who did you say that to?\"},\n" +
                                          "{\"value\": \"Oh sh*t, here we go again?\"},\n" +
                                          "{\"value\": \"Valera, it's your time!\"},\n" +
                                          "{\"value\": \"London - is a capital of Great Britain!\"}\n" +
                                          "]\n" + "}"))
                .andExpect(status().isOk());
        verify(pollRepository, times(1)).findById(any());
        verify(pollRepository, times(1)).save(any());
    }

    @Test
    public void getAllPolls() throws Exception {
        when(pollRepository.findAll()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/v2/polls"))
               .andExpect(status().isOk());
        verify(pollRepository).findAll((Pageable) any());
    }

    private Poll pollCreator() {
        Poll poll = new Poll();
        poll.setQuestion("Where is my car dude?");
        poll.setId(1L);
        Option option1 = new Option();
        option1.setValue("Who did you say that to?");
        option1.setId(2L);
        Option option2 = new Option();
        option2.setValue("Oh sh*t, here we go again?");
        option2.setId(3L);
        Option option3 = new Option();
        option3.setValue("Valera, it's your time!");
        option3.setId(4L);
        Set<Option> optionHashSet = new HashSet<>();
        optionHashSet.add(option1);
        optionHashSet.add(option2);
        optionHashSet.add(option3);
        poll.setOptions(optionHashSet);
        return poll;
    }

}

