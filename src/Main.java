package me.dogeon.pktool;

import javax.swing.SwingUtilities;
import java.io.File;
import me.dogeon.pktool.ui.MainFrame;

public class Main {

    public static void main(String[] args) {
        Runnable run = () -> {
            try {
                new File("datapacks").mkdir();
                MainFrame mf = new MainFrame();
            } catch (Exception e) {}
        };
        SwingUtilities.invokeLater(run);
    }
}
