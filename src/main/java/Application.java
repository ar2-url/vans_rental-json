import car.CarRepository;
import command.CommandExecutor;
import command.CommandFactory;
import command.UserCommand;

import java.util.Optional;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Application {
    private CommandFactory commandFactory = new CommandFactory(new CarRepository());
    private CommandExecutor commandExecutor = new CommandExecutor();
    private Scanner console = new Scanner(System.in);

    void executeCommand() {
        displayPrompt();
        Optional<UserCommand> optionalCommand = commandFactory.commandFrom(tokenizedInput());

//        optionalCommand.ifPresent(UserCommand::execute);

        optionalCommand
                .ifPresentOrElse(
                        userCommand -> commandExecutor.runUserCommand(userCommand),
                        () -> System.out.println("Command not recognized")
                );
    }

    private void displayPrompt() {
        System.out.print("> ");
    }

    private StringTokenizer tokenizedInput() {
        return new StringTokenizer(console.nextLine());
    }

    public static void main(String[] args) {
        Application application = new Application();

        while (true) {
            application.executeCommand();
        }
    }
}
