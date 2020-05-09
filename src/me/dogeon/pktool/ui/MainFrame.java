package me.dogeon.pktool.ui;

import java.awt.*;
import java.util.*;
import javax.script.Compilable;
import javax.swing.*;
import me.dogeon.pktool.ui.MainMenuBar;
import me.dogeon.pktool.locale.*;
import me.dogeon.pktool.ui.workplace.*;

public class MainFrame {

    private static JFrame window;
    static String prefix = "pktool.main.";
    static String frameTitle = prefix + "title";
    static Dimension frameSize = new Dimension(800, 450);
    static Image icon = new ImageIcon("img/icon.gif").getImage();
    static Color fontColor = new Color(100,100,100);

    public MainFrame() {
        initialize();
    }

    void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}
        // initializing main window
        Dictionary langdict = Languages.getLangDict();
        this.window = new JFrame((String)langdict.get(frameTitle));
        this.window.setSize(frameSize);
        this.window.setLocationRelativeTo(null);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setIconImage(icon);
        // window setups
        MainMenuBar toolbar = new MainMenuBar(this.window);
        this.window.setJMenuBar(toolbar.getMenuBar());
        new Bench(this.window);
        // show window
        this.window.setVisible(true);
    }
}
