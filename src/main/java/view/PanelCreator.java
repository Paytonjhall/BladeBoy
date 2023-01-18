package view;

import Game.AssetPath;

import javax.swing.*;

public class PanelCreator {
    JPanel panel;
    LabelCreator labelCreator = new LabelCreator();
    AssetPath ap = new AssetPath();

    public JPanel getCombatPanel() {
        panel = labelCreator.createPanel(ap.forest,0, 0, 1300, 500);
        return panel;
    }


}
