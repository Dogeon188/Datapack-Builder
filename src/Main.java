package me.dogeon.pktool;

import javax.swing.SwingUtilities;
import me.dogeon.pktool.ui.MainFrame;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mf = new MainFrame();
        });
    }
}
