package discordbot.commands;

import discordbot.main.Main;
import discordbot.utils.CommandParser;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		
		if(event.getMessage().getContentRaw().startsWith(Main.PREFIX) && event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId()) {
			
			CommandHandler.handleCommand(CommandParser.parser(event.getMessage().getContentRaw(), event));
			
		}
		
	}

}