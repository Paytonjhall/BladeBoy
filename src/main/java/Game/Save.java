package Game;
import Character.*;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
    Gson gson = new Gson();
    public Save() {
    }

    public void saveGame(Hero hero) {
        File saveFile = new File("src/main/java/Game/Saves/"+ hero.getUsername() + ".json");
        try (FileWriter writer = new FileWriter(saveFile);) {
            HeroSaveAdapter heroSaveAdapter = new HeroSaveAdapter(hero);
            gson.toJson(heroSaveAdapter, writer);
            System.out.println("Game saved");
        } catch (IOException e) {
            System.out.println("Error saving game");
            e.printStackTrace();
        }
    }
}
