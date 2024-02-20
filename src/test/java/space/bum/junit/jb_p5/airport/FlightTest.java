package space.bum.junit.jb_p5.airport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FlightTest {

  @Test
  @DisplayName("탑승객 수 미달 좌석배정 실패 시험")
  public void testSetInvalidSeats() throws FileNotFoundException, IOException {
    Flight flight = FlightBuilderUtil.buildFlightFromCsv("AA1234", 50,
        "src/test/resources/flights_information.csv");
    assertEquals(50, flight.getPassengers().size());
    assertThrows(RuntimeException.class, () -> flight.setSeats(49));
  }

  @Test
  @DisplayName("탑승객 수 초과 좌석배정 성공 시험")
  public void testSetValidSeats() throws FileNotFoundException, IOException {
    Flight flight = FlightBuilderUtil.buildFlightFromCsv("AA1234", 50,
        "src/test/resources/flights_information.csv");
    assertEquals(50, flight.getPassengers().size());
    flight.setSeats(52);
    assertEquals(52, flight.getSeats());
  }

  @Test
  @DisplayName("이륙 직후 상태 변화 및 출발지 변경 시도하면 예외 발생 확인")
  public void testChangeOrigin() {
    Flight flight = new Flight("KK1234", 50);
    flight.setOrigin("동경");
    flight.setDestination("항조우");
    flight.takeOff();
    assertEquals(true, flight.isFlying());
    assertEquals(true, flight.isTakenOff());
    assertEquals(false, flight.isLanded());
    assertThrows(RuntimeException.class, () -> flight.setOrigin("모스크바"));
  }

  @Test
  @DisplayName("이륙 및 착륙 직후 3종 상태 변화 확인")
  public void testLand() {
    Flight flight = new Flight("KK1234", 50);
    flight.setOrigin("동경");
    flight.setDestination("항조우");
    flight.takeOff();
    assertEquals(true, flight.isTakenOff());
    assertEquals(false, flight.isLanded());
    flight.land();
    assertEquals(true, flight.isTakenOff());
    assertEquals(true, flight.isLanded());
    assertEquals(false, flight.isFlying());
  }

  @Test
  @DisplayName("착륙 후 도착지 변경 때 예외 발생 확인")
  public void testChangeDestination() {
    Flight flight = new Flight("AA1234", 50);
    flight.setOrigin("제주");
    flight.setDestination("김포");
    flight.takeOff();
    flight.land();
    assertThrows(RuntimeException.class, () -> flight.setDestination("인천"));
  }

  @Test
  @DisplayName("비운의 대한항공 항공편 번호 정당함 시험")
  public void testFlightCreation() {
    Flight flight = new Flight("KE007", 100);
    assertNotNull(flight);
  }

  @Test
  @DisplayName("항공편 번호 숫자 부분 길이 부적절할 경우 예외 발생 확인")
  public void testInvalidFlightNumber() {
    assertThrows(RuntimeException.class,
        () -> {
          new Flight("AA12", 100);
        });
    assertThrows(RuntimeException.class,
        () -> {
          new Flight("AA12345", 100);
        });
  }

  @Test
  @DisplayName("항공편 번호 두 바른 사례 확인")
  public void testValidFlightNumber() {
    Flight flight = new Flight("AA345", 100);
    assertNotNull(flight);
    flight = new Flight("AA3456", 100);
    assertNotNull(flight);
  }

  @Test
  @DisplayName("만석인 경우 승객 추가하면 예외 발생 확인")
  public void testAddPassengers() throws FileNotFoundException, IOException {
    Flight flight = FlightBuilderUtil.buildFlightFromCsv("AA1234", 50,
        "src/test/resources/flights_information.csv");
    assertEquals(50, flight.getPassengers().size());
    Passenger p = new Passenger("720204-2033444", "한채희", "KR");
    assertThrows(RuntimeException.class, () -> flight.addPassenger(p));
  }
}
