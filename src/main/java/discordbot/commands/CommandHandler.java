package discordbot.commands;

import java.util.HashMap;

import discordbot.utils.Command;
import discordbot.utils.CommandParser;

public class CommandHandler {

	public static final CommandParser parse = new CommandParser();
	public static HashMap<String, Command> commands = new HashMap<>();
	
	public static void handleCommand(CommandParser.commandContainer cmd) {
		
		if(commands.containsKey(cmd.invoke)) {
			
			boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);
			
			if(!safe) {
				commands.get(cmd.invoke).action(cmd.args, cmd.event);
				commands.get(cmd.invoke).executed(safe, cmd.event);
			} else {
				commands.get(cmd.invoke).executed(safe, cmd.event);
			}
			
		}
		
	}
	
}
