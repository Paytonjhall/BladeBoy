package view;

import Game.AssetPath;
import Character.Town.Armory;
import Character.Town.Artificiary;
import Game.AssetPath;

import javax.swing.*;
import Character.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class oracle {
  boolean returner =false;
  Hero hero;
  AssetPath ap = new AssetPath();
  JFrame f;
  LabelCreator lc = new LabelCreator();

  public void visitOracle(Hero hero){
    this.hero = hero;
    if (returner) return;
    f = new JFrame("Oracle");
    f.update(f.getGraphics());
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
    f.setSize(500, 800);
    f.setLayout(null);
    Container container = f.getContentPane(); //Gets the content layer

    JLabel oracle = lc.createLabelWithoutHover(ap.oracle, 40, 20, 75, 75);
    JLabel heroSkillpoints = lc.createLabel(ap.skillpoint, "Skill Points", 400, 25, 50, 50);
    JLabel heroSkillpointsText = lc.createText(hero.skillPoints.size() + "", 450, 10, 200, 100);

    if(hero.getUnusedSkillPoints()<1){
      //Hero has no skill points
      JLabel oracleText = lc.createText("You have no skill points to spend", 125, 20, 375, 75);
      container.add(oracleText);
    }else{
      JLabel oracleText = lc.createText("You have " + hero.getUnusedSkillPoints() + " skill points to spend", 125, 20, 375, 75);
      container.add(oracleText);



    }


    container.add(oracle);
    container.add(heroSkillpointsText);
    container.add(heroSkillpoints);
    f.setVisible(true);
  }
}
