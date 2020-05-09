package me.dogeon.pktool.ui.mbf;

import java.util.*;
import javax.swing.*;
import me.dogeon.pktool.Main;
import me.dogeon.pktool.locale.*;;

public class MBFLang {

  static String prefix = "pktool.changelang.";

  public static void changeLang(JFrame f, String l) {
    Dictionary ld = Languages.getLangDict();
    int r = JOptionPane.showConfirmDialog(
      f,
      (String)ld.get(prefix + "description"),
      (String)ld.get(prefix + "title"),
      JOptionPane.YES_NO_OPTION,
      JOptionPane.WARNING_MESSAGE
      );
    if (r == JOptionPane.YES_OPTION) {
      Languages.currentLangId = l;
      f.dispose();
      Main.main(null);
    }
  }
}
