package me.dogeon.pktool.ui;

import java.awt.*;
import java.util.*;
import javax.script.Compilable;
import javax.swing.*;
import me.dogeon.pktool.ui.MainMenuBar;
import me.dogeon.pktool.locale.*;
import me.dogeon.pktool.ui.workplace.*;

public class MainFrame {

    private static JFrame w;
    static Dimension size = new Dimension(400, 450);
    static Image icon = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("assets/pktool/textures/icon.gif")).getImage();

    public MainFrame() {
        initialize();
    }

    void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}
        // initializing main window
        this.w = new JFrame(Languages.getTranslate("pktool.main.title"));
        this.w.setSize(size);
        this.w.setLocationRelativeTo(null);
        this.w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.w.setIconImage(icon);
        // window setups
        this.w.setJMenuBar(new MainMenuBar(this.w).getMenuBar());
        new Bench(this.w);
        // show window
        this.w.setVisible(true);
    }
}
