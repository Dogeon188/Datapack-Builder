package me.dogeon.pktool.ui;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.util.*;
import javax.swing.*;
import me.dogeon.pktool.ui.mbf.*;
import me.dogeon.pktool.locale.*;

public class MainMenuBar {

    JMenuBar toolbar;
    JFrame pframe;
    String prefix = "pktool.menu.";

    String[] name;
    KeyStroke[] key;
    Runnable[] methods;
    int[] pid;
    ArrayList<JComponent> components;

    int menuKM = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

    int menuL;
    int mitemL;

    public MainMenuBar(JFrame frame) {
        String n[] = {
        "edit", "preferences", "help", // JMenu
        "clear", "sep", "quit", // Edit...
        "lang", "sss", // Preference...
        "about", "sep", "tutorial" // Help...
        };
        name = n;
        menuL = 3;
        mitemL = 8;

        KeyStroke k[] = {
            KeyStroke.getKeyStroke(KeyEvent.VK_W, menuKM), // edit > clear
            null, // sep
            KeyStroke.getKeyStroke(KeyEvent.VK_Q, menuKM), // edit > quit
            null, // prf > lang
            null, // prf > sss
            null, // help > about
            null, // sep
            KeyStroke.getKeyStroke(KeyEvent.VK_T, menuKM), // help > online tutorial
        };
        key = k;

        Runnable m[] = {
            () -> {MBFClear.run(frame);},
            null, // sep
            () -> {System.exit(0);}, // edit > quit
            null, // prf > lang
            () -> {MBFSuperSecretSettings.run();}, // sss
            () -> {MBFAbout.run();},
            null, // sep
            () -> {MBFOnlineTutorial.run();} // tut
        };
        methods = m;
        
        int p[] = {
            0, 0, 0, // Edit...
            1, 1, // Preference...
            2, 2, 2, // Help...
            3 // Devs...
        };
        pid = p;

        components = new ArrayList<JComponent>();

        pframe = frame;

        initialize(frame);
    }

    public JMenuBar getMenuBar() {
        return this.toolbar;
    }

    private void initialize(JFrame frame) {
        toolbar = new JMenuBar();
        int menuKM = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        // adding JMenu components
        int i;
        Dictionary langdict = Languages.getLangDict();
        for (i = 0; i < menuL; i++) {
            String buf1 = (String)langdict.get(prefix + name[i]);
            JMenu m = new JMenu(buf1);
            components.add(m);
        }
        for (i = menuL; i < menuL + mitemL; i++) {
            JMenu pm = (JMenu)components.get(pid[i - menuL]);
            String buf1 = (String)langdict.get(prefix + name[i]);
            if (name[i] == "sep") {
                pm.addSeparator();
                continue;
            }
            if (name[i] == "lang") {
                JMenu m = new JMenu(buf1);
                addLangsToMenu(m);
                pm.add(m);
                continue;
            }
            JMenuItem mi = new JMenuItem(buf1);
            pm.add(mi);
            mi.setAccelerator(key[i - menuL]);
            Runnable mt = methods[i - menuL];
            mi.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {mt.run();}
                });
            components.add(mi);
        }
        for (i = 0; i < menuL; i++) {
            toolbar.add(components.get(i));
        }
    }

    private void addLangsToMenu(JMenu m) {
        ButtonGroup bg = new ButtonGroup();
        for (Field f : Languages.class.getDeclaredFields()) {
            try {
                Language l = (Language)f.get(null);
                boolean bool1 = (Languages.currentLangId == l.id);
                JRadioButtonMenuItem rb = new JRadioButtonMenuItem(l.display, bool1);
                Runnable r = () -> {MBFLang.changeLang(pframe, l.id);};
                rb.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent e) {r.run();}
                });
                bg.add(rb);
                m.add(rb);
            } catch (Exception e) {}
        }
    }
}
