package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import Character.Abilities.Mystic;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class LabelCreator {


    private final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    final InputStream is = LabelCreator.class.getResourceAsStream("/fonts/Pixel.ttf");
    Font pixel;
    Font pixelBig;
    Font pixelRaw;
    Font pixelBase;
    //final InputStream is2 = LabelCreator.class.getResourceAsStream("fonts/f2.ttf");

    public LabelCreator() {


    }

    public void registerFont() {
        try {

//            pixelBase = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/Pixel.ttf")).deriveFont(12f);
//            pixel = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/Pixel.ttf")).deriveFont(16f);

//            pixelRaw = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Pixel.ttf"));
//            pixelBase = pixelRaw.deriveFont(12f);
//            pixel = new TrueTypeFont(pixelBase, false);
//            pixel = Font.createFont(Font.TRUETYPE_FONT, is);
//            pixel = pixel.deriveFont(12f);
            //ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));

//            InputStream fontStream = LabelCreator.class.getResourceAsStream("Pixel.ttf");
             File fontFile = new File("src/main/java/view/fonts/Pixel.ttf");
             System.out.println(fontFile.getAbsolutePath());
             System.out.println(fontFile.exists());
             System.out.println(fontFile.toString());
//            Font myFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
//            pixel = myFont.deriveFont(Font.PLAIN, 12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontFile));
            pixel = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(14f);
            pixelBig = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(20f);
//            pix = pixel.deriveFont(12f);


            //ge.registerFont(pixel.deriveFont(12f));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JLabel createLabel(String path, String hover , int x, int y, int width, int height){
        if (pixel == null) registerFont();
        JLabel label = new JLabel();
        label.setBounds(x, y, width, height);
        // System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Image dimg = getImage(path).getScaledInstance(label.getWidth(), label.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        label.setIcon(imageIcon);
        Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setFont(pixel); //Sets the font (Pixel.ttf)
        label.setToolTipText(hover); //Sets the tooltip text
        return label;
    }

    public JLabel createLabelWithoutHover(String path, int x, int y, int width, int height){
        //Edit this to only be a string
        JLabel label = new JLabel();
        label.setBounds(x, y, width, height);
        Image dimg = getImage(path).getScaledInstance(label.getWidth(), label.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        label.setIcon(imageIcon);
        Dimension size = label.getPreferredSize(); //Gets the size of the image
        return label;
    }

    // I think this function is broken.
    public JButton createButton(String path, String hover , int x, int y, int width, int height){
        JButton button = new JButton();
        button.setBounds(x, y, width, height);
        Image dimg = getImage(path).getScaledInstance(button.getWidth(), button.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        button.setIcon(imageIcon);
        Dimension size = button.getPreferredSize(); //Gets the size of the image
        button.setToolTipText(hover); //Sets the tooltip text
        return button;
    }

    public JLabel update(String path, JLabel label, String hover){
        if(pixel == null) registerFont();
        Image dimg = getImage(path).getScaledInstance(label.getWidth(), label.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        label.setFont(pixel);
        label.setToolTipText(hover); //Sets the tooltip text
        label.setIcon(imageIcon);
        return label;
    }

    public JLabel createText(String text, int x, int y, int width, int height){

        JLabel label = new JLabel();
        label.setBounds(x, y, width, height);
        label.setText(text);
        if(pixel == null) registerFont();
        label.setFont(pixel);
        return label;
    }

    public JLabel createBigText(String text, int x, int y, int width, int height){

        JLabel label = new JLabel();
        label.setBounds(x, y, width, height);
        label.setText(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        if(pixelBig == null) registerFont();
        label.setFont(pixelBig);
        return label;
    }

    public JLabel createMystic(Mystic mystic, int x, int y, int width, int height){
        if(pixel == null) registerFont();
        JLabel label = new JLabel();
        label.setBounds(x, y, width, height);
        Image dimg = getImage(getMysticIconPath(mystic)).getScaledInstance(label.getWidth(), label.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        label.setIcon(imageIcon);
        label.setFont(pixel); //Sets the font (Pixel.ttf)
        Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setToolTipText(mystic.getBuff()); //Sets the tooltip text
        return label;
    }

    public JPanel createPanel(String path, int x, int y, int width, int height){
        JPanel panel = new JPanel();
        panel.setBounds(x, y, width, height);
        Image dimg = getImage(path).getScaledInstance(panel.getWidth(), panel.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        panel.add(new JLabel(imageIcon));
        return panel;
    }

    public String getMysticIconPath(Mystic mystic){
        switch (mystic.getBuff()) {
            case "Strength":
                return "src/Assets/Mystics/strength.png";
            case "Health":
                return "src/Assets/Mystics/health.png";
            case "Vamperism":
                return "src/Assets/Mystics/vamperism.png";
            case "Critical":
                return "src/Assets/Mystics/critical.png";
            case "Block":
                return "src/Assets/Mystics/block.png";
            case "Crush":
                return "src/Assets/Mystics/crush.png";
        }
        return "src/Assets/Mystics/health.png";
    }

    public Image getImage(String iconPath) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(iconPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public JProgressBar createProgressBar(int x, int y, int width, int height, int value, int max, int color){
        if(pixel == null) registerFont();
        JProgressBar progressBar = new JProgressBar();
        progressBar.setBounds(x, y, width, height);
        progressBar.setValue(value);
        progressBar.setMaximum(max);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(color));
        progressBar.setFont(pixel);

        return progressBar;

    }

    public JProgressBar createEnemyProgressBar(int x, int y, int width, int height, int value, int max, int color){
        if(pixel == null) registerFont();
        JProgressBar progressBar = new JProgressBar();
        progressBar.setBounds(x, y, width, height);
        progressBar.setValue(value);
        progressBar.setMaximum(max);
        progressBar.setForeground(new Color(color));
        progressBar.setFont(pixel);
        return progressBar;

    }

//    public Icon getIcon(String iconPath) {
//       Image image =  getImage(iconPath);
//        Image dimg = getImage(getMysticIconPath(mystic)).getScaledInstance(label.getWidth(), label.getHeight(),
//                Image.SCALE_SMOOTH);
//    }
}
