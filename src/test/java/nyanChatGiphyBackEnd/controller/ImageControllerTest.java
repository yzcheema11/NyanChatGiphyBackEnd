package nyanChatGiphyBackEnd.controller;

import nyanChatGiphyBackEnd.model.ResponseData;
import nyanChatGiphyBackEnd.service.GiphyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@WebMvcTest(value=ImageController.class, secure = false)
public class ImageControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GiphyService giphyService;

    private ResponseData mockGiphy1, mockGiphy2, mockGiphy3;

    @Before
    public void setUp(){
        mockGiphy1 = new ResponseData("url1", "width1", "height1");
        mockGiphy2 = new ResponseData("url2", "width2", "height2");
        mockGiphy3 = new ResponseData("url3", "width3", "height3");
    }

    @Test
    public void getImageStills() throws Exception {
        Mockito.when(giphyService.getImagesFromAPI()).thenReturn(Arrays.asList(mockGiphy1, mockGiphy2, mockGiphy3));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status()
                .isOk()).andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].url", is("url1")))
                .andExpect(jsonPath("$[0].width", is("width1")))
                .andExpect(jsonPath("$[0].height", is("height1")))
                .andExpect(jsonPath("$[1].url", is("url2")))
                .andExpect(jsonPath("$[1].width", is("width2")))
                .andExpect(jsonPath("$[1].height", is("height2")))
                .andExpect(jsonPath("$[2].url", is("url3")))
                .andExpect(jsonPath("$[2].width", is("width3")))
                .andExpect(jsonPath("$[2].height", is("height3"))).andReturn();
    }

    @Test
    public void getImageStillsNull() throws Exception {
        Mockito.when(giphyService.getImagesFromAPI()).thenThrow(IOException.class);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status()
                .isNotFound()).andReturn();
    }

}