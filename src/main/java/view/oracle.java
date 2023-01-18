package view;

import Character.Town.Oracle;
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
  List<Mystic> mystics = new ArrayList<>();
  Oracle ora = new Oracle();
  List<Hero.SkillPoints> unspentSkillPoints = new ArrayList<>();
  public Hero visitOracle(Hero hero){
    this.hero = hero;
    unspentSkillPoints = hero.getUnusedSkillPointsList();
    if (returner){
      for(Mystic mystic : hero.getMystics()) System.out.println(mystic.toString());
      return hero;
    }
    f = new JFrame("Oracle");
    f.update(f.getGraphics());
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
    f.setSize(500, 600);
    f.setLayout(null);
    Container container = f.getContentPane(); //Gets the content layer

    JLabel oracle = lc.createLabelWithoutHover(ap.oracle, 40, 20, 75, 75);
    JLabel heroSkillpoints = lc.createLabel(ap.skillpoint, "Skill Points", 400, 25, 50, 50);
    JLabel heroSkillpointsText = lc.createText(unspentSkillPoints.size() + "", 450, 10, 200, 100);

    if(hero.getUnusedSkillPoints()<1){
      //Hero has no skill points
      JLabel oracleText = lc.createText("You have no skill points to spend", 125, 20, 375, 75);
      container.add(oracleText);

      JButton back = new JButton("Exit Oracle");

      back.setBounds(100, 410, 150, 35);
      back.addActionListener(e -> {
        returner = true;
        f.dispose();
        try {
          visitOracle(hero);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      });
      returner = true;
      container.add(back);
    }else{
      JLabel oracleText = lc.createText("You have " + hero.getUnusedSkillPoints() + " skill points to spend", 125, 20, 375, 75);
      container.add(oracleText);

      mystics = ora.getMystics(unspentSkillPoints.get(0).getLevel());
      int i = 0;
      for(Mystic mystic: mystics){
        mystic.createMystic();
        JLabel mysticLabel = lc.createMystic(mystic, 75, 125 + i * 125, 100, 100);
        JLabel mysticText = lc.createText(mystic.toString(), 200, 155 + i*125, 200, 30);
        i++;
        container.add(mysticLabel);
        container.add(mysticText);

        mysticLabel.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            hero.addMystic(mystic);
            hero.useSkillPoint(unspentSkillPoints.get(0).getLevel());
            f.dispose();
            visitOracle(hero);
          }
        });
      }
    }

    container.add(oracle);
    container.add(heroSkillpointsText);
    container.add(heroSkillpoints);
    f.setVisible(true);
    return hero;
  }
}
