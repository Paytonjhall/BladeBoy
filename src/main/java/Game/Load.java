package Game;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import Character.Hero;
public class Load {

    public Load() {
    }

    public Hero loadGame(String fileName) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader("src/main/java/Game/Saves/" + fileName + ".json")) {
            HeroSaveAdapter heroSaveAdapter = gson.fromJson(reader, HeroSaveAdapter.class);
            System.out.println(heroSaveAdapter.toString());
            return new Hero(heroSaveAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
