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

public class JsonVote {

    public static void load(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            Reader hashMapReader = Files.newBufferedReader(Paths.get("CreateVote.json"));
            Reader listReader = Files.newBufferedReader(Paths.get("Votes.json"));
            HashMap<String, Vote> map = gson.fromJson(hashMapReader, new TypeToken<HashMap<String, Vote>>(){}.getType());
            if(map != null){
                Main.setCreateVote(map);
            }
            ArrayList<Vote> list = gson.fromJson(listReader, new TypeToken<ArrayList<Vote>>(){}.getType());
            if(list != null){
                Main.setVoteList(list);
            }
            hashMapReader.close();
            listReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            Writer hashMapWriter = Files.newBufferedWriter(Paths.get("CreateVote.json"));
            Writer listWriter = Files.newBufferedWriter(Paths.get("Votes.json"));
            gson.toJson(Main.getCreateVote(), hashMapWriter);
            gson.toJson(Main.getVoteList(), listWriter);
            listWriter.close();
            hashMapWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
