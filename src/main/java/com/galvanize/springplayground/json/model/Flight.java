package com.galvanize.springplayground.json.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Flight {

    private Date departs;
    private List<Ticket> tickets;

    public Flight() { }

    public Flight(Date departs, List<Ticket> tickets) {
        this.departs = departs;
        this.tickets = tickets;
    }

    @JsonFormat(pattern = "MM-dd-yyyy HH:MM")
    public Date getDeparts() {
        return departs;
    }

    public void setDeparts(Date departs) {
        this.departs = departs;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket){
        if (tickets == null){
            tickets = new ArrayList<Ticket>();
        }
        tickets.add(ticket);
    }

    public static class Ticket{
        private Person passenger;
        private int price;

        public Ticket() { }

        public Ticket(Person passenger, int price) {
            this.passenger = passenger;
            this.price = price;
        }

        public Person getPassenger() {
            return passenger;
        }

        public void setPassenger(Person passenger) {
            this.passenger = passenger;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
    public static class Person{
        private String firstName;
        private String lastName;

        public Person() { }

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
}
