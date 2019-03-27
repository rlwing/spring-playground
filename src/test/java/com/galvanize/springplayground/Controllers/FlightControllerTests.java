package com.galvanize.springplayground.Controllers;

import com.galvanize.springplayground.json.model.Flight;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import sun.reflect.annotation.ExceptionProxy;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
public class FlightControllerTests {
    @Autowired
    MockMvc mvc;

    @Test
    public void testFlight() throws Exception {
        mvc.perform(
                get("/flights/flight")
                    .accept(MediaType.APPLICATION_JSON_UTF8)
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("Tickets[0].Passenger.FirstName", is("Rob")))
                .andExpect(jsonPath("Tickets[0].Price", is(200)));
    }

    @Test
    public void testFlights() throws Exception {
        mvc.perform(
                get("/flights")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].Tickets[0].Passenger.FirstName", is("Rob")))
                .andExpect(jsonPath("$.[0].Tickets[0].Price", is(200)))
                .andExpect(jsonPath("$.[1].Tickets[0].Passenger.FirstName", is("Barb")))
                .andExpect(jsonPath("$.[1].Tickets[0].Price", is(550)));
    }

    @Test
    public void testTotalPrice() throws Exception{
        String json = getJSON("/flights.json");
        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{ \n\t\"result\": 750 \n}"))
                .andExpect(jsonPath("$.result", is(750)));
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }
}
