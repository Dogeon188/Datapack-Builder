package me.dogeon.pktool.ui.mbf;

import javax.swing.*;
import java.util.*;
import me.dogeon.pktool.locale.*;
import me.dogeon.pktool.Main;

public class MBFClear {

  public static void run(JFrame f) {
    int r = JOptionPane.showConfirmDialog(
      f,
      Languages.getTranslate("pktool.clear.description"),
      Languages.getTranslate("pktool.clear.title"),
      JOptionPane.YES_NO_OPTION,
      JOptionPane.WARNING_MESSAGE,
      new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("assets/pktool/textures/warn.png"))
      );
    if (r == JOptionPane.YES_OPTION) {
      f.dispose();
      Main.main(null);
    }
  }
}
