package me.dogeon.pktool.ui.mbf;

import javax.swing.*;
import java.util.*;
import me.dogeon.pktool.locale.*;
import me.dogeon.pktool.Main;

public class MBFClear {

  static String prefix = "pktool.clear.";

  public static void run(JFrame f) {
    Dictionary ld = Languages.getLangDict();
    int r = JOptionPane.showConfirmDialog(
      f,
      (String)ld.get(prefix + "description"),
      (String)ld.get(prefix + "title"),
      JOptionPane.YES_NO_OPTION,
      JOptionPane.WARNING_MESSAGE
      );
    if (r == JOptionPane.YES_OPTION) {
      f.dispose();
      Main.main(null);
    }
  }
}
