package command;

import car.CarRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.StringTokenizer;

@RequiredArgsConstructor
public class CommandFactory {
    private final CarRepository carRepository;

    public Optional<UserCommand> commandFrom(StringTokenizer tokenizedInput) {
        switch (tokenizedInput.nextToken()) {
            case "add": return createAddCarCommand(tokenizedInput);
            case "list": return Optional.of(new ListCarsCommand(carRepository));
            case "remove": return createRemoveCarCommand(tokenizedInput);
            case "exit": return Optional.of(() -> System.exit(0));
            default: return Optional.empty();
        }
    }

    /** Command format:
     * 'add Opel-Astra JH4DB1540LS020608'
     */
    private Optional<UserCommand> createAddCarCommand(StringTokenizer tokenizedInput) {
        if(tokenizedInput.countTokens() != 2) {
            return Optional.empty();
        }
        String model = tokenizedInput.nextToken();
        String vin = tokenizedInput.nextToken();
        return Optional.of(new AddCarCommand(carRepository, model, vin));
    }

    /**
     * Command format:
     * 'remove 1'
     * The only command argument is an ID.
     */
    private Optional<UserCommand> createRemoveCarCommand(StringTokenizer tokenizedInput) {
        if(tokenizedInput.countTokens() != 1) {
            return Optional.empty();
        }
        int idToDelete = Integer.parseInt(tokenizedInput.nextToken());
        return Optional.of(() -> carRepository.removeById(idToDelete));
    }

}
