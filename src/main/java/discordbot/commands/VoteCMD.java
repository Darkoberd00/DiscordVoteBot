package discordbot.commands;

import discordbot.main.Main;
import discordbot.utils.Command;
import discordbot.utils.JsonVote;
import discordbot.utils.Utils;
import discordbot.utils.Vote;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * Erstellung der Votes via Command.
 * Weiterleitung ins PrivateChannel.
 */
public class VoteCMD implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

        if(event.getChannelType().isGuild()) {
            Message massage = event.getMessage();
            User author = massage.getAuthor();

            if(Main.getCreateVote().get(author.getId()) == null) {
                Vote vote = new Vote(massage.getGuild().getIdLong(), massage.getTextChannel().getIdLong(), author.getIdLong());

                author.openPrivateChannel().queue(privateChannel -> {
                    privateChannel.sendMessageEmbeds(Utils.MassageEmbedVote(vote)).queue(ebmessage -> {
                        vote.setMessageID(ebmessage.getIdLong());
                        Main.getCreateVote().put(author.getId(), vote);
                        JsonVote.save();
                    }, e->{
                        event.getMessage().getTextChannel().sendMessageEmbeds(Utils.MassageEmbedErrorPrivateMassage()).queue();
                    });
                });
            }else{
                event.getMessage().getTextChannel().sendMessageEmbeds(Utils.MassageEmbedErrorCreateVote()).queue();
            }

            massage.delete().queue();
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
}
