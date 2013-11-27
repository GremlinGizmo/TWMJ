package twmjv3;

import javax.swing.*;
import java.awt.*;
import java.nio.*;
import java.io.*;

public class TwmjFanTable extends JPanel {
    ImageIcon icon = new ImageIcon(getClass().getResource( "/twmjv3/twmjfantable.jpg"));
    JLabel tableImage = new JLabel(icon);
    JScrollPane scroll;

    public TwmjFanTable() {
        setLookAndFeel();
        Color bg = new Color(53, 208, 45);
        setBackground(bg);
        setLayout(new FlowLayout());

        scroll = new JScrollPane(tableImage);
        add(scroll);
        setSize(300, 200);
        setVisible(true);
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            System.err.println("Couldn't use the system "
                    + "look and feel: " + e);
        }
    }
}