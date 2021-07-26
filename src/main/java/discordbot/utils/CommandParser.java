package discordbot.utils;

import java.util.ArrayList;

import discordbot.main.Main;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandParser {
	
	public static commandContainer parser(String raw, MessageReceivedEvent event) {
		
		String beheaded = raw.replaceFirst(Main.PREFIX, "");
		String[] splitBeheaded = beheaded.split(" ");
		String invoke = splitBeheaded[0].toLowerCase();
		ArrayList<String> split = new ArrayList<>();
		for( String s : splitBeheaded) {
			split.add(s);
		}
		
		String[] args = new String[split.size() - 1];
		split.subList(1, split.size()).toArray(args);
		
		return new commandContainer(raw, beheaded, splitBeheaded, invoke, args, event);
	}
	
	public static class commandContainer{
		
		public final String raw;
		public final String beheaded;
		public final String[] splitBeheaded;
		public final String invoke;
		public final String[] args;
		public final MessageReceivedEvent event;
		
		public commandContainer(String raw ,String beheaded ,String[] splitBeheaded ,String invoke ,String[] args ,MessageReceivedEvent event) {
			this.raw = raw;
			this.beheaded = beheaded;
			this.splitBeheaded = splitBeheaded;
			this.invoke = invoke;
			this.args = args;
			this.event = event;
		}
		
		
	}

}
