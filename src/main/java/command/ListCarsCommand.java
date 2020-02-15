package command;

import car.Car;
import car.CarRepository;
import lombok.RequiredArgsConstructor;

import javax.crypto.spec.PSource;
import java.util.List;

@RequiredArgsConstructor
class ListCarsCommand implements UserCommand {
    private static final String ROW_FORMAT = "%-4s|%-20s|%-17s";

    private final CarRepository carRepository;

    @Override
    public void execute() {
        System.out.println(createHeader());

//        List<Car> allCars = carRepository.findAll();
//        allCars.forEach(car ->
//                System.out.println(car.getModel() + car.getVin())
//        );
//
//        for(Car car : allCars) {
//            System.out.println(car.getModel() + car.getVin());
//        }
//
        carRepository.findAll().stream()
                .map(car -> toLine(car))
                .forEach(x -> System.out.println(x));
    }

    private String createHeader() {
        return String.format(ROW_FORMAT, "ID", "Car model", "VIN");
    }

    private String toLine(Car car) {
        return String.format(ROW_FORMAT, car.getId(), car.getModel(), car.getVin());
    }
}
