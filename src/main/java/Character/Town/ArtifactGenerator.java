package Character.Town;
import Character.Equipment.Artifact;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class ArtifactGenerator {
    String[] artNames = new String[]{"Amulet of ", "Ring of ", "Necklace of ", "Jewel of ", "Heirloom of ", "Boon of ", "Mantle of ", "Orb of "};
    public ArtifactGenerator() {
    }

    //            case "Fortune" -> fortune=amplifier; //Implemented
    //            case "Discount" -> discount=amplifier;
    //            case "Luck" -> luck=amplifier;
    //            case "Strength" -> strength=amplifier;
    //            case "Defense" -> defense=amplifier;
    //            case "Health" -> health=amplifier;
    //            case "Vampire" -> vampire=amplifier;
    //            case "Conqueror" -> conqueror=amplifier;
    //            case "Learner" -> learner=amplifier;

    public List<Artifact> generateArtifact(int rank, int count){
        List<Artifact> artifacts = new ArrayList<Artifact>();
        Random rand = new Random();

        for(int i = 0; i < count; i ++){
            String type = "";
            int num = rand.nextInt(9);
            switch (num) {
                case 0 -> type = "Fortune";
                case 1 -> type = "Discount";
                case 2 -> type = "Luck";
                case 3 -> type = "Strength";
                case 4 -> type = "Defense";
                case 5 -> type = "Health";
                case 6 -> type = "Vampire";
                case 7 -> type = "Conqueror";
                case 8 -> type = "Learner";
            }
            String name = artNames[rand.nextInt(artNames.length)] + type;
            getAmp(type, rank);
            String description = "This artifact is blessed with " + type;
            int value = ((rank/2 * rank) + 1) * (100 + rand.nextInt(10) + 3);
            Artifact artifact = new Artifact(name, value, description, type, getAmp(type, rank));
            int number = rand.nextInt(24)+1;
            artifact.setIconPath("src/Assets/Artifact/" + number + ".png");
            artifacts.add(artifact);
        }
        return artifacts;
    }


    public double getAmp(String type, int rank){
        if(Objects.equals(type, "Strength")){
            return (double)rank/10 + 1;
        } else if (Objects.equals(type, "Health")){
            return rank * 5;
        } else if (Objects.equals(type, "Vampire")){
            return rank * 1.05;
        } else if(Objects.equals(type, "Learner")){
            return rank * 1.04;
        } else if(Objects.equals(type, "Conqueror")){ //Damage multiplier for the more health you have
            return rank * 1.03;
        } else if(Objects.equals(type, "Luck")){ // Drop rates
            return rank * 1.02;
        } else if(Objects.equals(type, "Fortune")){ // Gold drop rates
            return rank * 1.01;
        } else if(Objects.equals(type, "Discount")){ // Discount at shops.
            return 1 - (rank * 0.03);
        } else if(Objects.equals(type, "Defense")){ // Damage reduction
            return (double)rank/10 + 1;
        } else {
            return 1;
        }
    }
}
