package car;

import java.util.*;

public class CarRepository {

    private Map<Integer, Car> cars = new HashMap<>();
    private IdProvider idProvider = new IdProvider();

    public Car add(Car car) throws DuplicatedVinException {
        if (existsByVin(car.getVin())) {
            throw new DuplicatedVinException();
        }

        int id = idProvider.nextId();
        Car carWithId = withId(id, car);
        cars.put(id, carWithId);
        return carWithId;
    }

    public void removeById(int id) {
        cars.remove(id);
    }

    public List<Car> findAll() {
        return new ArrayList<>(cars.values());
    }

    private boolean existsByVin(String vin) {
        return cars.values().stream().map(Car::getVin).anyMatch(vin::equals);
    }

    private Car withId(int id, final Car car) {
        return new Car(id, car.getModel(), car.getVin());
    }

    private static class IdProvider {
        private int nexUniqueId = 0;

        int nextId() {
            return nexUniqueId++;
        }

    }

}
