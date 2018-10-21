package com.damon.media.domain.test;

import com.damon.media.starter.MediaApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MediaApplication.class)
public class AppFacadeTest {

    private MockMvc mvc;

    @Before
    public void setup() {
        //mvc = MockMvcBuilders.standaloneSetup(new AppFacadeImpl()).build();

    }

    @Test
    public void testQueryMaterials() throws Exception {
//        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/ads/v1/media/apps").accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//
//        MockHttpServletResponse response = result.getResponse();
    }
}
