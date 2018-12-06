package com.damon.order.domain.test;

import com.damon.ucenter.shared.common.OrderIdFactory;
import com.damon.ucenter.starter.UcenterApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = UcenterApplication.class)
public class MaterialFacadeTest {

    private MockMvc mvc;

    @Before
    public void setup() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(new MaterialFacadeImpl()).build();
        OrderIdFactory.nextId();
    }

    @Test
    public void testQueryMaterials() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/bp/v1/materials").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
    }
}
