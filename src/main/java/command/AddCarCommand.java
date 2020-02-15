package command;

import car.Car;
import car.CarRepository;
import car.DuplicatedVinException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class AddCarCommand implements UserCommand {
    private final CarRepository carRepository;
    private final String model;
    private final String vin;

    @Override
    public void execute() {
//        if(carRepository.findAll().stream()
//                .map(Car::getVin)
//                .anyMatch(vin::equals)) {
//            System.out.println("Car with given VIN already exists");
//        }

        try {
            Car addedCar = carRepository.add(new Car(model, vin));
            System.out.println("Car with id " + addedCar.getId() + " added");
        } catch (DuplicatedVinException ignored) {
            System.out.println("Car with given VIN already exists");
        }
    }
}
