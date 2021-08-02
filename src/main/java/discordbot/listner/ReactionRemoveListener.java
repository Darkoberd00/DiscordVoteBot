package discordbot.listner;

import discordbot.main.Main;
import discordbot.utils.Vote;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ReactionRemoveListener extends ListenerAdapter {

    @Override
    public void onMessageReactionRemove(@NotNull MessageReactionRemoveEvent event) {

        System.out.println("test");
        if(event.isFromGuild() && event.getMember().getIdLong() != Main.getJda().getSelfUser().getIdLong()){

            for (Vote vote : Main.getVoteList()){
                System.out.println("i am in");
                if(vote.isMessageout()){

                    if(vote.getMassageID() == event.getMessageIdLong()){
                        System.out.println("gleich");
                        if(vote.getVotetuser().contains(event.getUser().getId())){
                            System.out.println("ja");

                            if(!Main.getUserFlag().isEmpty()) {
                                    if(Main.getUserFlag().get(event.getUser().getId()).equals("Remove from Listener")){
                                        Main.getUserFlag().remove(event.getUser().getId());
                                        return;
                                    }
                            }

                            if (event.getReactionEmote().getAsReactionCode().equals("🅰")) {
                                System.out.println("a remove");
                                vote.minusCounter_a();
                                vote.removeVotetUserID(event.getUser().getId());
                                vote.refashVote();

                            } else if (event.getReactionEmote().getAsReactionCode().equals("🅱")) {
                                System.out.println("b remove");
                                vote.minusCounter_b();
                                vote.removeVotetUserID(event.getUser().getId());
                                vote.refashVote();
                            }

                        }
                    }

                }

            }

        }

    }

}
