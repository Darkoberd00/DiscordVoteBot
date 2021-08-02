package discordbot.utils;

import discordbot.main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

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

    public static MessageEmbed MassageEmbedWrongLangthDeleteVoteCMD(){
        eb = new EmbedBuilder();

        eb.setColor(Color.RED);
        eb.setTitle("Bei deinem Command stümmt etwas nicht!");
        eb.addField("Woran kann es liegen?","Hast du die syntax eingehalten: !VoteDelete <UUID>",true);
        eb.addField("Was ist die UUID?", "Die UUID wird jedem Vote zugeteilt. So das der Ersteller sie besser Zuordenen,Editieren und Löschen kann.", false);
        eb.addField("Wo ist die UUID?", "Die UUID ist immer im Footer der Nachricht. Sie ist direkt mit \"UUID: <UUID>\" makiert." ,false);

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

    public static MessageEmbed MassageEmbedDeletedVote(Vote vote){
        eb = new EmbedBuilder();

        Guild guild = Main.getJda().getGuildById(vote.getGuildID());
        TextChannel textChannel = guild.getTextChannelById(vote.getChannelID());

        eb.setColor(Color.GREEN);
        eb.setTitle("Dein Vote mit der UUID: " + vote.getUUID() + " wurde erfolgreich Gelöscht!");
        eb.addField("Server:", guild.getName(), false);
        eb.addField("Channelname:", textChannel.getName(), false);
        eb.addField("Titel:", vote.getTitel(), false);
        eb.addField("Antword A:", vote.getAntwort_a(), false);
        eb.addField("Antword B:", vote.getAntwort_b(), false);
        eb.addField("Anzahl Votes:",vote.getVotetuser().size() + " Votes",false);

        return eb.build();
    }

    public static MessageEmbed MassageNotFromGuild(){
        eb = new EmbedBuilder();

        eb.setColor(Color.red);
        eb.setThumbnail(Main.getJda().getSelfUser().getAvatarUrl());
        eb.setAuthor("Dein Command muss über einem Discord Server Geschrieben werden", "https://gist.github.com/7743dc8f4f11a85cbeb8f6d6ec05f9b3.git", Main.getJda().getSelfUser().getAvatarUrl());
        eb.setTitle("Dein Command muss über einem Discord Server Geschrieben werden");
        eb.addField("Dein Command muss über einem Discord Server Geschrieben werden", "Dein Command muss über einem Discord Server Geschrieben werden" , true);
        eb.addField("Dein Command muss über einem Discord Server Geschrieben werden", "Dein Command muss über einem Discord Server Geschrieben werden" , true);
        eb.addField("Dein Command muss über einem Discord Server Geschrieben werden", "Dein Command muss über einem Discord Server Geschrieben werden" , true);
        eb.addField("Dein Command muss über einem Discord Server Geschrieben werden", "Dein Command muss über einem Discord Server Geschrieben werden" , false);
        eb.addField("Dein Command muss über einem Discord Server Geschrieben werden", "Dein Command muss über einem Discord Server Geschrieben werden" , false);
        eb.addField("Dein Command muss über einem Discord Server Geschrieben werden", "Dein Command muss über einem Discord Server Geschrieben werden" , false);
        eb.addField("Dein Command muss über einem Discord Server Geschrieben werden", "Dein Command muss über einem Discord Server Geschrieben werden" , true);
        eb.addField("Dein Command muss über einem Discord Server Geschrieben werden", "Dein Command muss über einem Discord Server Geschrieben werden" , true);
        eb.addField("Dein Command muss über einem Discord Server Geschrieben werden", "Dein Command muss über einem Discord Server Geschrieben werden" , true);

        eb.setFooter("Wustest du das Dein Command nur auf einem Discord Server Funktioniert, Fun Fact!");

        return eb.build();
    }



}
