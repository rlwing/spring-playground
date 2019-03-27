package com.galvanize.springplayground.Controllers;

import com.galvanize.springplayground.json.model.Flight;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;

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
}
