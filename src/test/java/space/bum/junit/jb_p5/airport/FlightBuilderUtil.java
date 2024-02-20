package space.bum.junit.jb_p5.airport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FlightBuilderUtil {
  public static Flight buildFlightFromCsv(String flightNumber, int seats,
      String fileName)
      throws FileNotFoundException, IOException {
    Flight flight = new Flight(flightNumber, seats);

    flight.setOrigin("Seoul(ICN)");
    flight.setDestination("Shanghai(PVG)");

    try (BufferedReader br = new BufferedReader(
        new FileReader(fileName))) {
      String line = null;
      while ((line = br.readLine()) != null) {
        String[] passengerStr = line.toString().split(";");
        Passenger p = new Passenger(passengerStr[0].trim(),
            passengerStr[1].trim(), passengerStr[2].trim());
        flight.addPassenger(p);
      }
    }
    return flight;
  }
}
