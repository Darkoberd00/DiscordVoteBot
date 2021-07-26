package discordbot.listner;

import discordbot.main.Main;
import discordbot.utils.Vote;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class PrivateChatListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        if(!Main.getVoteList().isEmpty() && !event.getMessage().isFromGuild() && event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId()){

            for(Vote vote : Main.getVoteList()){

                if(vote.getUserID() == event.getAuthor().getIdLong()){

                    if(!vote.isMessageout()){

                        if (vote.getTitel() != null && vote.getAntwort_a() != null && vote.getAntwort_b() == null){

                            vote.setAntwort_b(event.getMessage().getContentRaw());
                            EmbedBuilder eb = new EmbedBuilder();

                            eb.setTitle("Vote Erstellen:");
                            eb.addField("Erste nachricht:", vote.getTitel(), false);
                            eb.addField("Zweite nachricht:", vote.getAntwort_a(), false);
                            eb.addField("Dritte nachricht:", vote.getAntwort_b(), false);

                            event.getAuthor().openPrivateChannel().queue(privateChannel -> {
                                privateChannel.editMessageEmbedsById(vote.getMassageID(),eb.build()).queue();
                            });

                            Main.getJda().getGuildById(vote.getGuildID()).getTextChannelById(vote.getChannelID()).sendMessageEmbeds(vote.voteEmbed().build()).queue(embedmessage ->{
                                embedmessage.addReaction("🅰").queue();
                                embedmessage.addReaction("🅱").queue();
                                vote.setMessageID(embedmessage.getIdLong());
                            });

                            vote.messageout();

                            return;
                        }else if (vote.getTitel() != null && vote.getAntwort_a() == null){
                            vote.setAntwort_a(event.getMessage().getContentRaw());

                            EmbedBuilder eb = new EmbedBuilder();

                            eb.setTitle("Vote Erstellen:");
                            eb.addField("Erste nachricht:", vote.getTitel(), false);
                            eb.addField("Zweite nachricht:", vote.getAntwort_a(), false);
                            eb.addField("Dritte nachricht:", "<Antword B>", false);

                            event.getAuthor().openPrivateChannel().queue(privateChannel -> {
                                privateChannel.editMessageEmbedsById(vote.getMassageID(),eb.build()).queue();
                            });

                            return;
                        }else if(vote.getTitel() == null){
                            vote.setTitel(event.getMessage().getContentRaw());

                            event.getAuthor().openPrivateChannel().queue(privateChannel -> {
                                EmbedBuilder eb = new EmbedBuilder();

                                eb.setTitle("Vote Erstellen:");
                                eb.addField("Erste nachricht:", vote.getTitel(), false);
                                eb.addField("Zweite nachricht:", "<Antword A>", false);
                                eb.addField("Dritte nachricht:", "<Antword B>", false);

                                privateChannel.editMessageEmbedsById(vote.getMassageID(), eb.build()).queue();

                            });

                            return;
                        }

                    }

                }

            }

        }
    }
}
