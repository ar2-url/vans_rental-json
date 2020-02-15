import car.CarRepository;
import command.CommandExecutor;
import command.CommandFactory;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Application {
    private CommandFactory commandFactory = new CommandFactory(new CarRepository());
    private CommandExecutor commandExecutor = new CommandExecutor();
    private Scanner console = new Scanner(System.in);

    void executeCommand() {
        displayPrompt();
        commandFactory.commandFrom(tokenizedInput())
                .ifPresentOrElse(
                        commandExecutor::execute,
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
