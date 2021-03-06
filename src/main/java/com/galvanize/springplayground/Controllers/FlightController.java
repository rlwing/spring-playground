package com.galvanize.springplayground.Controllers;

import com.galvanize.springplayground.json.model.Flight;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@RestController
//@RequestMapping("/flights")
public class FlightController {

    @GetMapping("/flights/flight")
    public Flight getFlight(){
        Flight flight = new Flight();
        flight.setDeparts(new Date());
        flight.addTicket(new Flight.Ticket(
                new Flight.Person("Rob", "Wing"), 200));

        return flight;
    }

    @GetMapping("/flights")
    public List<Flight> getFlights(){
        List<Flight> flights = new ArrayList<>();
        Flight flight1 = new Flight();
        flight1.setDeparts(new Date());
        flight1.addTicket(new Flight.Ticket(
                new Flight.Person("Rob", "Wing"), 200));
        flights.add(flight1);

        Flight flight2 = new Flight();
        flight2.setDeparts(new Date());
        flight2.addTicket(new Flight.Ticket(
                new Flight.Person("Barb", "Wing"), 550));
        flights.add(flight2);
        return flights;
    }

    @PostMapping("/flights/tickets/total")
    public String totalTickets(@RequestBody List<Flight> flights){
        int totalPrice = 0;
        for(Flight f : flights){
            for(Flight.Ticket t : f.getTickets()){
                totalPrice += t.getPrice();
            }
        }

        return String.format("{ \n\t\"result\": %s \n}", totalPrice);
    }
}
