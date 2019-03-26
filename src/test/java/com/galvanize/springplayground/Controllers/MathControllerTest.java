package com.galvanize.springplayground.Controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(MathController.class)
public class MathControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getPi() throws Exception {
        mvc.perform(get("/math/pi").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));
    }

    @Test
    public void testCalc() throws Exception{
        mvc.perform(get("/math/calculate?operation=add&x=5&y=4").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("5 + 4 = 9"));

        mvc.perform(get("/math/calculate?operation=subtract&x=5&y=4").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("5 - 4 = 1"));

        mvc.perform(get("/math/calculate?operation=multiply&x=5&y=4").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("5 * 4 = 20"));

        mvc.perform(get("/math/calculate?operation=divide&x=5&y=4").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("5 / 4 = 1"));

        mvc.perform(get("/math/calculate?x=15&y=14").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("15 + 14 = 29"));
    }

    @Test
    public void testVolume() throws Exception{
        mvc.perform(post("/math/volume/3/4/5").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));

        mvc.perform(patch("/math/volume/3/4/5").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));
    }

    @Test
    public void testAreaCircle() throws Exception{
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "circle")
                .param("radius", "3.0");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a circle with a radius of 3.0 is 28.274333882308138"));
    }

    @Test
    public void testAreaRectangle() throws Exception{
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "rectangle")
                .param("height", "3.0")
                .param("width", "5.0");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a 3.0x5.0 rectangle is 15.0"));
    }
}
