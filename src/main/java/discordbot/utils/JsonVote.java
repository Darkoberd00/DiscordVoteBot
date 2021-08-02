package discordbot.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import discordbot.main.Main;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonVote {

    public static void load(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            Reader hashMapReader = Files.newBufferedReader(Paths.get("CreateVote.json"));
            Reader listReader = Files.newBufferedReader(Paths.get("Votes.json"));
            Reader useingReader = Files.newBufferedReader(Paths.get("UseingChannels.json"));
            HashMap<String, Vote> map = gson.fromJson(hashMapReader, new TypeToken<HashMap<String, Vote>>(){}.getType());
            if(map != null){
                Main.setCreateVote(map);
            }
            ArrayList<Vote> list = gson.fromJson(listReader, new TypeToken<ArrayList<Vote>>(){}.getType());
            if(list != null){
                Main.setVoteList(list);
            }
            HashMap<String, List<String>> using = gson.fromJson(useingReader, new TypeToken<HashMap<String, ArrayList<String>>>(){}.getType());
            if(using != null){
                Main.setUseingChannels(using);
            }
            hashMapReader.close();
            listReader.close();
            useingReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            Writer hashMapWriter = Files.newBufferedWriter(Paths.get("CreateVote.json"));
            Writer listWriter = Files.newBufferedWriter(Paths.get("Votes.json"));
            Writer usingWriter = Files.newBufferedWriter(Paths.get("UseingChannels.json"));
            gson.toJson(Main.getCreateVote(), hashMapWriter);
            gson.toJson(Main.getVoteList(), listWriter);
            gson.toJson(Main.getUseingChannels(), usingWriter);
            listWriter.close();
            hashMapWriter.close();
            usingWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
