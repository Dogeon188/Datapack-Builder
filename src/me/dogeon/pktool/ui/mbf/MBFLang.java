package me.dogeon.pktool.ui.mbf;

import java.util.*;
import javax.swing.*;
import me.dogeon.pktool.Main;
import me.dogeon.pktool.locale.*;;

public class MBFLang {

  static String prefix = "pktool.changelang.";

  public static void changeLang(JFrame f, String l) {
    int r = JOptionPane.showConfirmDialog(
      f,
      Languages.getTranslate(prefix + "description"),
      Languages.getTranslate(prefix + "title"),
      JOptionPane.YES_NO_OPTION,
      JOptionPane.WARNING_MESSAGE,
      new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("assets/pktool/textures/warn.png"))
      );
    if (r == JOptionPane.YES_OPTION) {
      Languages.currentLangId = l;
      f.dispose();
      Main.main(null);
    }
  }
}
