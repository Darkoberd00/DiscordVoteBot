package discordbot.listner;

import discordbot.main.Main;
import discordbot.utils.Vote;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ReactionListener extends ListenerAdapter {

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
    if(event.isFromGuild() && event.getMember().getIdLong() != Main.getJda().getSelfUser().getIdLong())
        for (Vote vote : Main.getVoteList()){
            if(vote.isMessageout()){
                if(event.getMessageIdLong() == vote.getMassageID()){
                    if(!vote.getVotetuser().contains(event.getUser().getId())){
                        if(event.getReactionEmote().getAsReactionCode().equals("🅰")){
                            vote.addCounter_a();
                            vote.addVotetUserID(event.getUser().getId());
                            vote.refashWette();
                        }else if(event.getReactionEmote().getAsReactionCode().equals("🅱")){
                            vote.addCounter_b();
                            vote.addVotetUserID(event.getUser().getId());
                            vote.refashWette();
                        }else{
                            event.getReaction().removeReaction(event.getUser()).queue();
                        }
                    }else{
                        event.getReaction().removeReaction(event.getUser()).queue();
                    }
                }
            }
        }

    }
}
