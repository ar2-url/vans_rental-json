package command;

public class CommandExecutor {
    public void runUserCommand(UserCommand userCommand) {
        userCommand.execute();
    }
}
