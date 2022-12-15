package Character;
import Character.*;
import java.util.List;

public class Boss extends Enemy {

    public Boss(String name, int health, int damage, double armorRating, int xp, List<ItemInterface> drops, int gold) {
        super(name, health, damage, armorRating, xp, drops, gold);
    }
}
