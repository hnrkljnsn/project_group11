package no.ntnu.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public int flightId;

    public String airline;

    public boolean roundTrip;

    public String departureCity;

    public String returnCity;

    public LocalDate departureDate;

    public LocalDate returnDate;

    public int price;
}