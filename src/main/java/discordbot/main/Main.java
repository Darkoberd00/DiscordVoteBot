package discordbot.main;

import discordbot.listner.PrivateChatListener;
import discordbot.listner.ReactionListener;
import discordbot.commands.VoteCMD;
import discordbot.commands.CommandHandler;
import discordbot.commands.CommandListener;
import discordbot.utils.JsonVote;
import discordbot.utils.Vote;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static String PREFIX = "!";
    private static JDA jda;
    private static JDABuilder builder;

    private static ArrayList<Vote> voteList = new ArrayList<>();
    private static HashMap<String, Vote> createVote = new HashMap<>();

    public static void main(String[] args) {
        JsonVote.load();
        try {
            start();
        }catch (LoginException | IllegalArgumentException e){
            System.err.println("Der BOT konnte nicht erstellt werden!");
        }
        shutdown();
    }

    private static void shutdown() {
        new Thread(() -> {
            String line = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                while ((line = reader.readLine()) != null) {
                    if (line.equalsIgnoreCase("exit")) {
                        if (jda != null) {
                            reader.close();
                            jda.shutdown();
                            System.out.println("Der Bot geht Offline");
                        }
                    }
                }
            } catch (IOException e) {
            }
        }).start();
    }

    private static void start() throws LoginException, IllegalArgumentException{
        builder = JDABuilder.createDefault("token");
        builder.setActivity(Activity.listening(PREFIX+"vote"));
        builder.setStatus(OnlineStatus.ONLINE);
        addCommands();
        builder.addEventListeners(new CommandListener());
        builder.addEventListeners(new ReactionListener());
        builder.addEventListeners(new PrivateChatListener());
        jda = builder.build();

    }

    public static HashMap<String, Vote> getCreateVote() {
        return createVote;
    }

    public static void setVoteList(ArrayList<Vote> voteList) {
        Main.voteList = voteList;
    }

    public static void setCreateVote(HashMap<String, Vote> createVote) {
        Main.createVote = createVote;
    }

    private static void addCommands() {
        CommandHandler.commands.put("vote", new VoteCMD());
    }

    public static JDA getJda() {
        return jda;
    }
    public static ArrayList<Vote> getVoteList() {
        return voteList;
    }
}
