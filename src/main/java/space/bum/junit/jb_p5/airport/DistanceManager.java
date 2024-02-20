package space.bum.junit.jb_p5.airport;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DistanceManager {
  private static final int DISTANCE_FACTOR = 10;

  private Map<Passenger, Integer> passengerDistances = new HashMap<>();
  private Map<Passenger, Integer> passengerPoints = new HashMap<>();

  public Map<Passenger, Integer> getPassengerDistances() {
    return Collections.unmodifiableMap(passengerDistances);
  }

  public Map<Passenger, Integer> getPassengerPoints() {
    return Collections.unmodifiableMap(passengerPoints);
  }

  public void addDistance(Passenger passenger, int distance) {
    if (passengerDistances.containsKey(passenger)) {
      distance += passengerDistances.get(passenger);
    }
    passengerDistances.put(passenger,  distance);
  }

  public void calculateGivenPoints() {
    getPassengerDistances().keySet().stream().forEach(p -> {
      passengerPoints.put(p,
          getPassengerDistances().get(p) / DISTANCE_FACTOR);
    });
  }
}
