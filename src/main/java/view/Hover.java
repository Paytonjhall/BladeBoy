package view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class Hover extends MouseAdapter {

    private String lblStr = "";

    public Hover( String lblStr ) {
        this.lblStr = lblStr;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel lbl = (JLabel) e.getComponent();
        lbl.setForeground(new Color(255, 127, 80));
        lbl.setText( String.format( "<html><u>%s</u></html>", lblStr) );
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel lbl = (JLabel) e.getComponent();
        lbl.setText( String.format( "<html><u>%s</u><</html>", lblStr ) );
        lbl.setForeground( new Color(245, 245, 245) );
    }
}
