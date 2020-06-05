package me.dogeon.pktool.ui;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.util.*;
import javax.swing.*;
import me.dogeon.pktool.ui.mbf.*;
import me.dogeon.pktool.locale.*;

public class MainMenuBar {

  JMenuBar tb;
  JFrame pf;
  String prefix = "pktool.menu.";

  String[] name;
  KeyStroke[] key;
  Runnable[] methods;
  int[] pid;
  ArrayList<JComponent> cpn;

  int menuKM = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

  public MainMenuBar(JFrame frame) {
    String n[] = {
      "edit",
      "preferences",
      "help",
      "clear",
      "sep",
      "quit",
      "lang",
      "sss",
      "about",
      "sep",
      "tutorial"
    };
    name = n;

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
    
    int p[] = {0, 0, 0, 1, 1, 2, 2, 2, 3};
    pid = p;

    cpn = new ArrayList<JComponent>();

    pf = frame;

    initialize();
  }

  public JMenuBar getMenuBar() {
    return this.tb;
  }

  private void initialize() {
    tb = new JMenuBar();
    // adding JMenu components
    int i;
    for (i = 0; i < 3; i++) {
      String buf1 = Languages.getTranslate(prefix + name[i]);
      JMenu m = new JMenu(buf1);
      cpn.add(m);
    }
    for (i = 3; i < 11; i++) {
      JMenu pm = (JMenu)cpn.get(pid[i - 3]);
      String buf1 = Languages.getTranslate(prefix + name[i]);
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
      mi.setAccelerator(key[i - 3]);
      Runnable mt = methods[i - 3];
      mi.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {mt.run();}
        });
      cpn.add(mi);
    }
    for (i = 0; i < 3; i++) {
      tb.add(cpn.get(i));
    }
  }

  private void addLangsToMenu(JMenu m) {
    ButtonGroup bg = new ButtonGroup();
    for (Field f : Languages.class.getDeclaredFields()) {
      try {
        Language l = (Language)f.get(null);
        boolean bool1 = (Languages.currentLangId == l.id);
        JRadioButtonMenuItem rb = new JRadioButtonMenuItem(l.display, bool1);
        Runnable r = () -> {MBFLang.changeLang(pf, l.id);};
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
