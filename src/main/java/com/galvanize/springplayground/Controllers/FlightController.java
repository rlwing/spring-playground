package com.galvanize.springplayground.Controllers;

import com.galvanize.springplayground.json.model.Flight;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.Date;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @GetMapping("flight")
    public Flight getFlight(){
        Flight flight = new Flight();
        flight.setDeparts(new Date());
        flight.addTicket(new Flight.Ticket(
                new Flight.Person("Rob", "Wing"), 200));

        return flight;
    }
}
