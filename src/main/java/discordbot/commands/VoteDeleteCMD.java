package discordbot.commands;

import discordbot.main.Main;
import discordbot.utils.Command;
import discordbot.utils.JsonVote;
import discordbot.utils.Utils;
import discordbot.utils.Vote;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * Löschung des Votes via Command.
 */
public class VoteDeleteCMD implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

        if(!Main.isChannel(event)){
            return;
        }

        if(args.length == 1){

            String uuid = args[0];
            User user = event.getAuthor();

            for (Vote vote: Main.getVoteList()){

                if(vote.getUUID().equals(uuid)){

                    if(vote.getUserID() == user.getIdLong()){
                        Main.getVoteList().remove(vote);
                        Main.getJda().getGuildById(vote.getGuildID()).getTextChannelById(vote.getChannelID()).deleteMessageById(vote.getMassageID()).queue();

                        user.openPrivateChannel().queue(privateChannel -> {
                            privateChannel.sendMessageEmbeds(Utils.MassageEmbedDeletedVote(vote)).queue();
                        });
                        JsonVote.save();

                    }else{

                    }

                }

            }

        }else{
            event.getChannel().sendMessageEmbeds(Utils.MassageEmbedWrongLangthDeleteVoteCMD()).queue();
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
