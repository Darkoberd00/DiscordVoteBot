package discordbot.listner;

import discordbot.main.Main;
import discordbot.utils.Vote;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ReactionRemoveListener extends ListenerAdapter {

    @Override
    public void onMessageReactionRemove(@NotNull MessageReactionRemoveEvent event) {

        if(event.isFromGuild() && event.getMember().getIdLong() != Main.getJda().getSelfUser().getIdLong()){

            for (Vote vote : Main.getVoteList()){

                if(vote.isMessageout()){

                    if(vote.getMassageID() == event.getMessageIdLong()){

                        if(vote.getVotetuser().equals(event.getUser().getId())){

                            if(event.getReactionEmote().getAsReactionCode().equals("🅰")){

                                vote.minusCounter_a();
                                vote.removeVotetUserID(event.getUser().getId());
                                vote.refashVote();

                            }else if(event.getReactionEmote().getAsReactionCode().equals("🅱")){

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
