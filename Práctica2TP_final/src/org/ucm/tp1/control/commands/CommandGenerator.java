package org.ucm.tp1.control.commands;

public class CommandGenerator {
    private static Command[] availableCommands = {
            new AddCommand(),
            new HelpCommand(),
            new ResetCommand(),
            new ExitCommand(),
            new ListCommand(),
            new NoneCommand(),
            new PrintModeCommand()
    };

    public static Command parseCommand(String [] commandWords){
        Command command;
        for(Command i: availableCommands){
            command = i.parse(commandWords);
            if(command != null) return command;
        }
        return null;
    }

    public static String commandHelp(){
        String help = "Commands: \n";
        for(Command i: availableCommands){
            help += i.helpText() + "\n";
        }
        return help;
    }

}
