package view;
import Character.*;
import Dungeon.Loot;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Game.*;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class awardModal  {


    private JPanel contentPane;
    private JButton buttonTakeAll;
    private JButton buttonContinue;
    private Hero hero;
    private Loot loot;
    private Hero afterhero;
    private boolean returner = false;


    public awardModal(Hero hero, Loot loot) {
        this.hero = hero;
        this.loot = loot;
//        int xstart = 30;
//        int ystart = 50;
//
//        setSize(500, 500);
//        setLocationRelativeTo(null);
//        setContentPane(contentPane);
//        setModal(true);
//        getRootPane().setDefaultButton(buttonTakeAll);
//
//        buttonTakeAll.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//
//        buttonContinue.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                onCancel();
//            }
//        });
//
//        // call onCancel() when cross is clicked
//        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//        addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                onCancel();
//            }
//        });
//
//        // call onCancel() on ESCAPE
//        contentPane.registerKeyboardAction(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                onCancel();
//            }
//        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private Hero onTakeAll(Hero hero, Loot loot, JDialog jd) {
        // add your code here
        afterhero.addLoot(loot);
        loot = null;
        // Take all Loot
        jd.dispose();
        return afterhero;
    }

    private Hero onCancel() {
        // add your code here if necessary
        //jd.dispose();
        return afterhero;
    }

    public static void main(String[] args) {
        //awardModal dialog = new awardModal();
//        dialog.pack();
//        dialog.setVisible(true);
//        System.exit(0);
    }


    public Hero openLoot(Hero hero, Loot loot, String title) {
        if(returner) return hero;
        afterhero = hero;

        if(loot == null || !loot.isLoot()) {
            // f.dispose();
            // hero.awardingLoot = false;
            return hero;
        }
        JFrame f = new JFrame("Loot");
        f.setLocationRelativeTo(null);
        // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        f.setSize(485, 325);
        f.setLayout(null);
        Container container = f.getContentPane(); //Gets the content layer
        container.setLayout(null);
        LabelCreator lc = new LabelCreator();
        AssetPath ap = new AssetPath();
        this.hero = hero;
        this.loot = loot;
        // awardModal modal = new awardModal(hero, loot);
        int xstart = 53;
        int ystart = 131;
        int itemSize = 50;
        int distBetween = 110;
        JLabel lootLabel = lc.createBigText(title, 100, 23, 300, 50);

        container.add(lootLabel);
        if(loot.getMystic()!=null) {
            JLabel mystic = lc.createLabel(ap.getMystic(loot.getMystic().IconName()), loot.getMystic().hoverTextString(), xstart, ystart, itemSize, itemSize);
            JLabel mysticText = lc.createText(loot.getMystic().nameString(), xstart-5, ystart + 20, 100, 100);
            mystic.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    afterhero.addMystic(loot.getMystic());
                    loot.setMystic(null);
                    f.dispose();
                    openLoot(afterhero, loot, title);
                }
            });
            container.add(mystic);
            container.add(mysticText);

            xstart += distBetween;
        }
        if(loot.getGold() > 0) {
            JLabel gold = lc.createLabel(ap.Gold, loot.getGold() + " gold", xstart, ystart, itemSize, itemSize);
            JLabel goldText = lc.createText(loot.getGold() + " gold", xstart-5, ystart + 20, 100, 100);
            gold.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    afterhero.addGold(loot.getGold());
                    loot.setGold(0);
                    f.dispose();
                    openLoot(afterhero, loot, title);

                }
            });
            container.add(gold);
            container.add(goldText);
            xstart += distBetween;
        }
        if(loot.getWeapon() != null) {
            JLabel weapon = lc.createLabel(loot.getWeapon().getIconPath(), loot.getWeapon().hoverString(), xstart, ystart, itemSize, itemSize);
            JLabel weaponText = lc.createText(loot.getWeapon().getName(), xstart-5, ystart + 20, 100, 100);
            weapon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    afterhero.addToBag(loot.getWeapon());
                    loot.setWeapon(null);
                    f.dispose();
                    openLoot(afterhero, loot, title);
                }
            });
            container.add(weapon);
            container.add(weaponText);
            xstart += distBetween;
        }
        if(loot.getArmor()!=null) {
            JLabel armor = lc.createLabel(loot.getArmor().getIconPath(), loot.getArmor().hoverString(), xstart, ystart, itemSize, itemSize);
            JLabel armorText = lc.createText(loot.getArmor().getName(), xstart-5 , ystart + 20, 100, 100);
            armor.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    afterhero.addToBag(loot.getArmor());
                    loot.setArmor(null);
                    f.dispose();
                    openLoot(afterhero, loot, title);
                }
            });
            container.add(armor);
            container.add(armorText);
            xstart += distBetween;
        }
        if(loot.getArtifact()!=null) {
            JLabel artifact = lc.createLabel(loot.getArtifact().getIconPath(), loot.getArtifact().hoverString(), xstart, ystart, itemSize, itemSize);
            JLabel artifactText = lc.createText(loot.getArtifact().getName(), xstart-5, ystart + 20, 100, 100);
            artifact.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    afterhero.addToBag(loot.getArtifact());
                    loot.setArtifact(null);
                    f.dispose();
                    openLoot(afterhero, loot, title);
                }
            });
            container.add(artifact);
            container.add(artifactText);
            xstart += distBetween;
        }
        if(loot.getPotion()!=null) {
            JLabel potion = lc.createLabel(loot.getPotion().getIconPath(), loot.getPotion().toString(), xstart, ystart, itemSize, itemSize);
            JLabel potionText = lc.createText(loot.getPotion().toString(), xstart-5, ystart + 20, 100, 100);
            potion.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    afterhero.addPotion(loot.getPotion());
                    loot.setPotion(null);
                    f.dispose();
                    openLoot(afterhero, loot, title);
                }
            });
            container.add(potion);
            container.add(potionText);
            xstart += 125;
        }

        JLabel continueButton = lc.createLabelWithoutHover(ap.continueButton, 45, 212, 175, 45);
        continueButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                returner = true;
                f.dispose();
                openLoot(afterhero, loot, title);
            }
        });

        JLabel TakeAll = lc.createLabelWithoutHover(ap.takeAllButton, 260, 212, 175, 45);
        TakeAll.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                afterhero.addLoot(loot);
                returner = true;
                f.dispose();
                openLoot(afterhero, null, title);
            }
        });


        container.add(continueButton);
        container.add(TakeAll);

        JLabel border = lc.createLabelWithoutHover(ap.awardMenu, 5, 5, 475, 300);
        container.add(border);

        f.setVisible(true);


        // call onCancel() when cross is clicked
        // f.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        return afterhero;
    }
}
