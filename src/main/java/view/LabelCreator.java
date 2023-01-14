package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import Character.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LabelCreator {



    public JLabel createLabel(String path, String hover , int x, int y, int width, int height){
        JLabel label = new JLabel();
        label.setBounds(x, y, width, height);
        Image dimg = getImage(path).getScaledInstance(label.getWidth(), label.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        label.setIcon(imageIcon);
        Dimension size = label.getPreferredSize(); //Gets the size of the image
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
        Image dimg = getImage(path).getScaledInstance(label.getWidth(), label.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        label.setToolTipText(hover);
        label.setIcon(imageIcon);
        return label;
    }

    private Image getImage(String iconPath) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(iconPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
