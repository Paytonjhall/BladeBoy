package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import Character.Abilities.AbilityInterface;
import Character.Abilities.Mystic;
import Game.AssetPath;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class LabelCreator {


    private final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    final InputStream is = LabelCreator.class.getResourceAsStream("/fonts/Pixel.ttf");
    final AssetPath ap = new AssetPath();

    ImageIcon[] abilityImages;
    String[] abilityNames;
    Font pixel;
    Font pixelBig;
    Font pixelHuge;

    public LabelCreator() {
    }

    public void registerFont() {
        try {
            File fontFile = new File("src/main/java/view/fonts/Pixel.ttf");
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontFile));
            pixel = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(14f);
            pixelBig = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(20f);
            pixelHuge = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(30f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JLabel createLabel(String path, String hover, int x, int y, int width, int height) {
        if (pixel == null) registerFont();
        JLabel label = new JLabel();
        label.setBounds(x, y, width, height);
        Image dimg = getImage(path).getScaledInstance(label.getWidth(), label.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        label.setIcon(imageIcon);
        Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setFont(pixel); //Sets the font (Pixel.ttf)
        label.setToolTipText(hover); //Sets the tooltip text
        return label;
    }

    public JLabel createLabelWithoutHover(String path, int x, int y, int width, int height) {
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
    public JButton createButton(String path, String hover, int x, int y, int width, int height) {
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

    public JLabel update(String path, JLabel label, String hover) {
        if (pixel == null) registerFont();
        Image dimg = getImage(path).getScaledInstance(label.getWidth(), label.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        label.setFont(pixel);
        label.setToolTipText(hover); //Sets the tooltip text
        label.setIcon(imageIcon);
        return label;
    }

    public JLabel createText(String text, int x, int y, int width, int height) {

        JLabel label = new JLabel();
        label.setBounds(x, y, width, height);
        label.setText(text);
        if (pixel == null) registerFont();
        label.setFont(pixel);
        return label;
    }

    public JLabel createBigText(String text, int x, int y, int width, int height) {

        JLabel label = new JLabel();
        label.setBounds(x, y, width, height);
        label.setText(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        if (pixelBig == null) registerFont();
        label.setFont(pixelBig);
        return label;
    }

    public JLabel createHugeText(String text, int x, int y, int width, int height) {

        JLabel label = new JLabel();
        label.setBounds(x, y, width, height);
        label.setText(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        if (pixelHuge == null) registerFont();
        label.setFont(pixelBig);
        return label;
    }

    public JLabel createMystic(Mystic mystic, int x, int y, int width, int height) {
        if (pixel == null) registerFont();
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

    public JPanel createPanel(String path, int x, int y, int width, int height) {
        JPanel panel = new JPanel();
        panel.setBounds(x, y, width, height);
        Image dimg = getImage(path).getScaledInstance(panel.getWidth(), panel.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        panel.add(new JLabel(imageIcon));
        return panel;
    }

    public JPanel createPanelWithHover(String path, String hover, int x, int y, int width, int height) {
        JPanel panel = new JPanel();
        panel.setBounds(x, y, width, height);
        Image dimg = getImage(path).getScaledInstance(panel.getWidth(), panel.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        panel.add(new JLabel(imageIcon));
        panel.setToolTipText(hover);
        return panel;
    }

    public String getMysticIconPath(Mystic mystic) {
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

    public JProgressBar createProgressBar(int x, int y, int width, int height, int value, int max, int color) {
        if (pixel == null) registerFont();
        JProgressBar progressBar = new JProgressBar();
        progressBar.setBounds(x, y, width, height);
        progressBar.setValue(value);
        progressBar.setMaximum(max);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(color));
        progressBar.setFont(pixel);

        return progressBar;

    }

    public JProgressBar createEnemyProgressBar(int x, int y, int width, int height, int value, int max, int color) {
        if (pixel == null) registerFont();
        JProgressBar progressBar = new JProgressBar();
        progressBar.setBounds(x, y, width, height);
        progressBar.setValue(value);
        progressBar.setMaximum(max);
        progressBar.setForeground(new Color(color));
        progressBar.setFont(pixel);
        return progressBar;

    }


    public Font getPixel() {
        if (pixel == null) registerFont();
        return pixel;
    }

    public ImageIcon getIcon(String iconPath, int height, int width) {
        Image image = getImage(iconPath);
        Image dimg = getImage(iconPath).getScaledInstance(width, height,
                Image.SCALE_SMOOTH);
        return new ImageIcon(dimg);
    }

//    public Icon getIcon(String iconPath) {
//       Image image =  getImage(iconPath);
//        Image dimg = getImage(getMysticIconPath(mystic)).getScaledInstance(label.getWidth(), label.getHeight(),
//                Image.SCALE_SMOOTH);
//    }

    public JComboBox createCustomComboBox(ArrayList<AbilityInterface> abilities, int x, int y, int width, int height, int imageWidth, int imageHeight) {
        Integer[] intArray = new Integer[abilities.size()];
        ImageIcon[] images = new ImageIcon[abilities.size()];
        String[] abilityNames = new String[abilities.size()];
        // JLabel[] labels = new JLabel[abilities.size()];
        for (int i = 0; i < abilities.size(); i++) {
            intArray[i] = i;
            abilityNames[i] = abilities.get(i).getName();
            images[i] = getIcon(ap.getAbility(abilities.get(i).IconName()), imageWidth, imageHeight);
            // labels[i]= createLabel(ap.getAbility(abilities.get(i).IconName()), abilities.get(i).getName(), x, y, width, height);

        }

        abilityImages = images;
        this.abilityNames = abilityNames;
        JComboBox comboBox = new JComboBox(intArray);
        comboBox.setBounds(x, y, width, height);
        ComboBoxRenderer renderer = new ComboBoxRenderer();
        renderer.setPreferredSize(new Dimension(width, height));
        comboBox.setRenderer(renderer);
        return comboBox;
    }


    class ComboBoxRenderer extends JLabel implements ListCellRenderer {

        public ComboBoxRenderer() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            int selectedIndex = ((Integer)value).intValue();
            ImageIcon icon = abilityImages[selectedIndex];
            String name = abilityNames[selectedIndex];
            setIcon(icon);
            setText(name);
            return this;
        }
    }
}
