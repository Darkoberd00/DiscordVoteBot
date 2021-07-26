package discordbot.commands;

import discordbot.main.Main;
import discordbot.utils.Command;
import discordbot.utils.Vote;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public class VoteCMD implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    Long messageid = (long) -1;

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

        if(event.getChannelType().isGuild()) {

            Message massage = event.getMessage();
            User author = massage.getAuthor();
            Vote vote = new Vote(massage.getGuild().getIdLong(), massage.getTextChannel().getIdLong(), author.getIdLong());

            EmbedBuilder eb = new EmbedBuilder();

            eb.setTitle("Vote Erstellen:");
            eb.addField("Erste nachricht:", "<Deine Frage>", false);
            eb.addField("Zweite nachricht:", "<Antword A>", false);
            eb.addField("Dritte nachricht:", "<Antword B>", false);

            author.openPrivateChannel().queue(privateChannel -> {
                privateChannel.sendMessageEmbeds(eb.build()).queue(ebmessage -> {
                    vote.setMessageID(ebmessage.getIdLong());
                    Main.getVoteList().add(vote);
                }, e->{
                    EmbedBuilder exEB = new EmbedBuilder();

                    exEB.setColor(Color.RED);
                    exEB.setTitle("Ich kann dir keine Private nachricht schreiben!");
                    exEB.addField("Was kann ich tun?", "Ganz simpel! \"Gehe unter Benutzereinstellung\" > \"Privatspähre & Sicherheit\" und aktiviere \"Direktnachtrichten von Servermitglieder erlauben\"!", false);

                    event.getMessage().getTextChannel().sendMessageEmbeds(exEB.build()).queue();
                });
            });

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
