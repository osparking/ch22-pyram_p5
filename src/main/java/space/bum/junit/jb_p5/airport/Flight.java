package space.bum.junit.jb_p5.airport;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Flight {
  private String flightNumber;
  private Set<Passenger> passengers = new HashSet<>();
  private int seats;
  private String origin;
  private String destination;
  private boolean flying;
  private int distance;

  public int getDistance() {
    return distance;
  }

  public void setDistance(int distance) {
    this.distance = distance;
  }

  public boolean isFlying() {
    return flying;
  }

  private boolean takenOff;
  private boolean landed;

  private String flightNumberRegex = "^[A-Z]{2}\\d{3,4}$";
  private Pattern pattern = Pattern.compile(flightNumberRegex);

  public Flight(String flightNumber, int seats) {
    Matcher matcher = pattern.matcher(flightNumber);
    if (!matcher.matches()) {
      throw new RuntimeException("항공편 번호 오류");
    }
    this.flightNumber = flightNumber;
    this.seats = seats;
    this.flying = false;
    this.takenOff = false;
    this.landed = false;
  }

  public Set<Passenger> getPassengers() {
    return Collections.unmodifiableSet(passengers);
  }

  public int getSeats() {
    return seats;
  }

  public boolean isTakenOff() {
    return takenOff;
  }

  public boolean isLanded() {
    return landed;
  }

  public void setSeats(int seats) {
    if (passengers.size() > seats) {
      throw new RuntimeException("기존 승객 수 보다 좌석 수를 줄일 수 없다!");
    }

    this.seats = seats;
  }

  public boolean addPassenger(Passenger passenger) {
    if (passengers.size() >= seats) {
      throw new RuntimeException("만석이므로 탑승이 불가합니다!");
    }
    return passengers.add(passenger);
  }

  public boolean removePassenger(Passenger passenger) {
    return passengers.remove(passenger);
  }

  public int getPassengersNumber() {
    return passengers.size();
  }

  public void setOrigin(String origin) {
    if (takenOff) {
      throw new RuntimeException("이륙한 항공편의 출발지점은 변경될 수 없다!");
    }

    this.origin = origin;
  }

  public void setDestination(String destination) {
    if (landed) {
      throw new RuntimeException("착륙한 항공편의 출발지점은 변경될 수 없다!");
    }
    this.destination = destination;
  }

  @Override
  public String toString() {
    return "항공편 번호: " + flightNumber +
        ", 출발: " + origin + ", 도착: " + destination;
  }

  public void takeOff() {
    System.out.println(this + " 이륙 중");
    flying = true;
    takenOff = true;
  }

  public void land() {
    System.out.println(this + " 착륙 중");
    flying = false;
    landed = true;
  }
}
