package discordbot.listner;

import discordbot.main.Main;
import discordbot.utils.Utils;
import discordbot.utils.Vote;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class PrivateChatListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(!Main.getCreateVote().isEmpty() && !event.getMessage().isFromGuild() && event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId()){
            User user = event.getAuthor();
            Vote vote = Main.getCreateVote().get(user.getId());
            if(vote != null){
                if(vote.getUserID() == user.getIdLong()){
                    if(!vote.isMessageout()){
                        String massage = event.getMessage().getContentRaw();
                        if ((!vote.getTitel().isEmpty()) && (!vote.getAntwort_a().isEmpty()) && vote.getAntwort_b().isEmpty()){
                            vote.setAntwort_b(massage);
                            event.getAuthor().openPrivateChannel().queue(privateChannel -> {
                                privateChannel.editMessageEmbedsById(vote.getMassageID(), Utils.MassageEmbedVote(vote)).queue();
                            });
                            Main.getVoteList().add(vote);
                            Main.getCreateVote().remove(user.getId());
                            Main.getJda().getGuildById(vote.getGuildID()).getTextChannelById(vote.getChannelID()).sendMessageEmbeds(vote.voteEmbed().build()).queue(embedmessage ->{
                                embedmessage.addReaction("🅰").queue();
                                embedmessage.addReaction("🅱").queue();
                                vote.setMessageID(embedmessage.getIdLong());
                            });
                            return;
                        }else if (!vote.getTitel().isEmpty() && vote.getAntwort_a().isEmpty()){
                            vote.setAntwort_a(massage);
                            event.getAuthor().openPrivateChannel().queue(privateChannel -> {
                                privateChannel.editMessageEmbedsById(vote.getMassageID(),Utils.MassageEmbedVote(vote)).queue();
                            });
                            return;
                        }else if(vote.getTitel().isEmpty()){
                            vote.setTitel(massage);
                            event.getAuthor().openPrivateChannel().queue(privateChannel -> {
                                privateChannel.editMessageEmbedsById(vote.getMassageID(), Utils.MassageEmbedVote(vote)).queue();
                            });
                            return;
                        }
                    }
                }
            }
        }
    }
}
