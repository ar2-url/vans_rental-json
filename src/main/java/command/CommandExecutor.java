package command;

public class CommandExecutor {
    public void execute(UserCommand userCommand) {
        userCommand.execute();
    }
}
