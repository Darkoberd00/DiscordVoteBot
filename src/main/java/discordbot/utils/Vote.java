package discordbot.utils;

import discordbot.main.Main;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

public class Vote {

    int counter_a = 0;
    int counter_b = 0;
    ArrayList<String> votetuser = new ArrayList<>();

    //wichtig
    UUID uuid;
    String titel;
    String antwort_a;
    String antwort_b;


    long userid;
    long massageid;
    long guildid;
    long channelid;
    boolean messageout = false;

    public Vote(long guildid, long channelid, long userid){
        uuid = UUID.randomUUID();
        this.guildid = guildid;
        this.channelid = channelid;
        this.userid = userid;

    }

    public String getAntwort_a() {
        return antwort_a;
    }

    public String getAntwort_b() {
        return antwort_b;
    }

    public String getTitel() {
        return titel;
    }

    public long getUserID() {
        return userid;
    }

    public long getMassageID() {
        return massageid;
    }

    public long getGuildID() {
        return guildid;
    }

    public long getChannelID() {
        return channelid;
    }

    public String getUUID() {
        return uuid.toString();
    }

    public ArrayList<String> getVotetuser() {
        return votetuser;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setAntwort_a(String antwort_a) {
        this.antwort_a = antwort_a;
    }

    public void setAntwort_b(String antwort_b) {
        this.antwort_b = antwort_b;
    }

    public void setMessageID(long massageid) {
        this.massageid = massageid;
    }

    public boolean isMessageout() {
        return messageout;
    }

    public void addVotetUserID(String user){
        votetuser.add(user);
    }

    public void messageout(){
        messageout = true;
    }

    public void addCounter_a(){ counter_a++; }
    public void addCounter_b(){ counter_b++; }

    public EmbedBuilder voteEmbed(){
        EmbedBuilder eb = new EmbedBuilder();
        String url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSfGidWLwQPfUtQejGvHdItpLhQ_6TuDlQJ2w&usqp=CAU";
        String username = "Anonymous";

        try {
            url = Main.getJda().getUserById(userid).getAvatarUrl();
            username = Main.getJda().getUserById(userid).getName();
        }catch (NullPointerException e){
        }

        eb.setAuthor(username, null, url);
        eb.setThumbnail(Main.getJda().getSelfUser().getAvatarUrl());

        eb.setColor(Color.CYAN);
        eb.setTitle(titel);
        String value_a = "0%";
        String value_b = "0%";
        double max = counter_a + counter_b;

        if(!(counter_a == 0 && counter_b == 0)){

            if(counter_a > 0) {
                double rechnung_a = counter_a / (max / 100);
                value_a = Math.round(rechnung_a)+"%";
            }
            if(counter_b > 0) {
                double rechnung_b = counter_b / (max / 100);
                value_b = Math.round(rechnung_b)+"%";
            }

        }

        eb.addField("🅰 "+ antwort_a + ":", value_a,false);
        eb.addField("🅱 "+ antwort_b + ":", value_b,false);
        eb.setFooter("Wetten: "+ (int)(max) +" UUID: " + uuid);

        return eb;
    }

    public void refashWette(){
        Main.getJda().getGuildById(getGuildID()).getTextChannelById(getChannelID()).editMessageEmbedsById(massageid, voteEmbed().build()).queue();
    }

}
