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

  @Produces
  @FlightNumber(number = "AA1235")
  public Flight createFlight2() throws IOException {
    return FlightBuilderUtil.buildFlightFromCsv("AA1235", 21,
        "src/test/resources/flights_information2.csv");
  }

  @Produces
  @FlightNumber(number = "AA1236")
  public Flight createFlight3() throws IOException {
    return FlightBuilderUtil.buildFlightFromCsv("AA1236", 27,
        "src/test/resources/flights_information3.csv");
  }
}
