package discordbot.commands;

import discordbot.main.Main;
import discordbot.utils.Command;
import discordbot.utils.Utils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class SetChannelCMD implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if(!event.isFromGuild()) {
            event.getAuthor().openPrivateChannel().queue(privateChannel -> {
                privateChannel.sendMessageEmbeds(Utils.MassageNotFromGuild()).queue();
            });
            return;
        }

        if (args.length <= 1) {
            for (String s : args) {
                System.out.println(s);
                s = s.replace("<#", "");
                s = s.replace(">", "");
                if (isChannel(event, s)) {
                    System.out.println("ja");
                }
            }
        } else {

        }

    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public String help(String id) {
        return null;
    }

    private boolean isChannel(MessageReceivedEvent event, String s){
        try {
            Main.getJda().getGuildById(event.getGuild().getId()).getTextChannelById(s).getName();
        }catch (NullPointerException e){
            return false;
        }
        return true;
    }

}
