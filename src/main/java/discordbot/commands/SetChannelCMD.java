package discordbot.commands;

import discordbot.main.Main;
import discordbot.utils.Command;
import discordbot.utils.JsonVote;
import discordbot.utils.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SetChannelCMD implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if(!event.isFromGuild()) {
            event.getAuthor().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessageEmbeds(Utils.MassageNotFromGuild()).queue());
            return;
        }

        String serverID = event.getGuild().getId();
        List<String> listChannels = !Main.getUseingChannels().containsKey(serverID) ? new ArrayList<>() : Main.getUseingChannels().get(serverID);
        List<String> listnotaddedChannel = new ArrayList<>();
        List<String> alsowaddedChannel = new ArrayList<>();

        if (args.length >= 1) {
            for (String id : args) {
                id = id.replace("<#", "");
                id = id.replace(">", "");
                if (isChannel(event, id)) {
                    System.out.println("ja");
                    if(!listChannels.contains(id)){
                        listChannels.add(id);
                    } else {
                        alsowaddedChannel.add(id);
                    }
                } else {
                    listnotaddedChannel.add(id);
                }
            }
        } else {
            String id = event.getChannel().getId();
            if(!listChannels.contains(id)){
                listChannels.add(id);
            } else {
                alsowaddedChannel.add(id);
            }
        }

        EmbedBuilder eb = new EmbedBuilder();

        eb.setAuthor(Main.getJda().getSelfUser().getName(), "https://github.com/Darkoberd00/DiscordVoteBot", Main.getJda().getSelfUser().getAvatarUrl());
        eb.setTitle(listChannels.isEmpty() ? "Es wurden keine Channels gefunden!" : "Es wurden Channels gefunden!");

        eb.setColor(listChannels.isEmpty() ? Color.RED : Color.GREEN);

        if(!listChannels.isEmpty()) {
            String s = "";
            for(Iterator<String> iter = listChannels.iterator(); iter.hasNext(); s = s + (s.isEmpty() ? "" : ", ") + "<#" + (String)iter.next() + ">"){}
            eb.addField("Channel die hinzugefügt wurden:", s, false);
        }

        if(!alsowaddedChannel.isEmpty()){
            String s = "";
            for(Iterator<String> iter = alsowaddedChannel.iterator(); iter.hasNext(); s = s + (s.isEmpty() ? "" : ", ") + "<#" + (String)iter.next() + ">"){}
            eb.addField("Channel die schon hinzugefügt wurden:", s, false);
        }

        if(!listnotaddedChannel.isEmpty()){
            String s = "";
            for(Iterator<String> iter = listnotaddedChannel.iterator(); iter.hasNext(); s = s + (s.isEmpty() ? "" : ", ") + "<#" + (String)iter.next() + ">"){}
            eb.addField("Channel die nicht hinzugefügt wurden:", s, false);
        }

        event.getChannel().sendMessageEmbeds(eb.build()).queue();

        Main.getUseingChannels().put(event.getGuild().getId(), listChannels);
        JsonVote.save();
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
        }catch (NullPointerException | NumberFormatException e){
            return false;
        }
        return true;
    }



}
