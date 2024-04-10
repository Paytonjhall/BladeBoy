package Character.Mystics;

import java.util.ArrayList;
import java.util.List;

public class MysticGenerator {
    public MysticGenerator() {}
    // Starter Mystics
    //List<MysticInterface> starterMystics = new ArrayList<>(new BloodAmulet(), new MageTomb(), new HuntingKnife(), new Pavise());
    ArrayList<MysticInterface> starterMystics = new ArrayList<MysticInterface>(List.of(new BloodAmulet(), new MageTomb(), new HuntingKnife(), new Pavise()));

    // Common Mystics
    ArrayList<MysticInterface> commonMystics = new ArrayList<>(List.of(new BloodGem(), new OricalcumChunk(), new UnholySignet(), new VenomOrb()));

    // Rare Mystics
    ArrayList<MysticInterface> rareMystics = new ArrayList<>(List.of(new WeepingMirror(), new Daidem()));

    public MysticInterface getNewMystic(List<MysticInterface> mystics, String rarity) {
    List<MysticInterface> possibleMystics;
    if (rarity == "common") possibleMystics = commonMystics;
    else if (rarity == "rare") possibleMystics = rareMystics;
    else if (rarity == "starter") possibleMystics = starterMystics;
    else return null;
    for (MysticInterface mystic : mystics) {
        if(mystic.rarityString() == rarity) possibleMystics.remove(mystic);
    }
    if (possibleMystics.size() == 0) return null;
    return possibleMystics.get((int) (Math.random() * possibleMystics.size()));
    }
}
