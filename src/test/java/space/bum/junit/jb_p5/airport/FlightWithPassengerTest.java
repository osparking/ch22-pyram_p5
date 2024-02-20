package space.bum.junit.jb_p5.airport;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import space.bum.junit.jb_p5.airport.annot.FlightNumber;
import space.bum.junit.jb_p5.airport.producer.FlightProducer;

@RunWith(Arquillian.class)
public class FlightWithPassengerTest {

  @Deployment
  public static JavaArchive createDeployment() {
    return ShrinkWrap.create(JavaArchive.class)
        .addClasses(Passenger.class, Flight.class, FlightProducer.class,
            DistanceManager.class)
        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
  }

  @Inject
  @FlightNumber(number = "AA1234")
  Flight flight;
  
  @Inject
  @FlightNumber(number = "AA1235")
  Flight flight2;
  
  @Inject
  @FlightNumber(number = "AA1236")
  Flight flight3;

  @Inject
  DistanceManager distanceManager;

  @Test
  public void testFlightsDistances() {
//    when(distanceManager.getPassengerDistances())
//        .thenReturn(passengerDistances);

    distanceManager.calculateGivenPoints();

    assertEquals(210, distanceManager.getPassengerPoints()
        .get(new Passenger("940207-6459423", "Susan Todd", "GB")).longValue());
    assertEquals(420,
        distanceManager.getPassengerPoints()
            .get(new Passenger("860602-6749821", "Harry Christensen", "GB"))
            .longValue());
    assertEquals(630, distanceManager.getPassengerPoints()
        .get(new Passenger("850205-3917188", "정성민", "KR")).longValue());
  }

  @Test(expected = RuntimeException.class)
  public void testNumberOfSeatsCannotBeExceeded() throws IOException {
    assertEquals(50, flight.getPassengersNumber());
    flight.addPassenger(new Passenger("850205-3917188", "정성민", "KR"));
  }

  @Test
  public void testAddRemovePassenters() throws IOException {
    flight.setSeats(51);
    Passenger newPassenger = new Passenger("560205-2917188", "정성숙", "KR");
    flight.addPassenger(newPassenger);
    assertEquals(51, flight.getPassengersNumber());
    flight.removePassenger(newPassenger);
    assertEquals(50, flight.getPassengersNumber());
    assertEquals(51, flight.getSeats());
  }

}
