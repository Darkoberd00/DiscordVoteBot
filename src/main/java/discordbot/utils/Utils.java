package discordbot.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class Utils {

    private static EmbedBuilder eb;

    public static MessageEmbed MassageEmbedVote(Vote vote){

        eb = new EmbedBuilder();
        String value_titel = "<Deine Frage>";
        String value_a = "<Antword A>";
        String value_b = "<Antword B>";

        if(!vote.getTitel().isEmpty()){
            value_titel = vote.getTitel();
        }
        if(!vote.getAntwort_a().isEmpty()){
            value_a = vote.getAntwort_a();
        }
        if(!vote.getAntwort_b().isEmpty()){
            value_b = vote.getAntwort_b();
        }

        eb.setTitle("Vote Erstellen:");
        eb.addField("Erste nachricht:",  value_titel , false);
        eb.addField("Zweite nachricht:", value_a, false);
        eb.addField("Dritte nachricht:", value_b, false);


        JsonVote.save();
        return eb.build();
    }

    public static MessageEmbed MassageEmbedErrorPrivateMassage(){

        eb = new EmbedBuilder();
        eb.setColor(Color.RED);
        eb.setTitle("Ich kann dir keine Private nachricht schreiben!");
        eb.addField("Was kann ich tun?", "Ganz simpel! Gehe unter \"Benutzereinstellung\" > \"Privatspähre & Sicherheit\" und aktiviere \"Direktnachtrichten von Servermitglieder erlauben\"!", false);

        return eb.build();
    }

    public static MessageEmbed MassageEmbedErrorCreateVote(){

        eb = new EmbedBuilder();
        eb.setColor(Color.RED);
        eb.setTitle("Du Erstellst gerade schon ein Vote!");
        eb.addField("Was kann ich tun?", "Ganz simpel! Geh unter deine DMs und schau ob der Bot dir eine nachricht geschrieben hat.", false);
        eb.addField("Wenn nein?", "Kontaktiere den Besitzer des Bots", false);

        return eb.build();
    }



}
