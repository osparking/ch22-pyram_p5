package space.bum.junit.jb_p5.airport.producer;

import java.io.IOException;

import javax.enterprise.inject.Produces;

import space.bum.junit.jb_p5.airport.Flight;
import space.bum.junit.jb_p5.airport.FlightBuilderUtil;
import space.bum.junit.jb_p5.airport.annot.FlightNumber;

public class FlightProducer {
  @Produces
  @FlightNumber(number = "AA1234")
  public Flight createFlight() throws IOException {
    return FlightBuilderUtil.buildFlightFromCsv("AA1234", 50,
        "src/test/resources/flights_information.csv");
  }
}
