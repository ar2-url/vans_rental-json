package command;

import car.Car;
import car.CarRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class ListCarsCommand implements UserCommand {
    private static final String ROW_FORMAT = "%-4s|%-20s|%-17s";

    private final CarRepository carRepository;

    @Override
    public void execute() {
        System.out.println(createHeader());
        carRepository.findAll().stream()
                .map(this::toLine)
                .forEach(System.out::println);
    }

    private String createHeader() {
        return String.format(ROW_FORMAT, "ID", "Car model", "VIN");
    }

    private String toLine(Car car) {
        return String.format(ROW_FORMAT, car.getId(), car.getModel(), car.getVin());
    }
}
