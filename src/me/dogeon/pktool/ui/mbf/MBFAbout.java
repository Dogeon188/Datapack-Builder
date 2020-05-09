package me.dogeon.pktool.ui.mbf;

import java.util.*;
import javax.swing.*;
import me.dogeon.pktool.locale.*;

public class MBFAbout {

  static String version = "0.2.0";
  static String prefix = "pktool.about.";

  public static void run() {
    Dictionary ld = Languages.getLangDict();
    ImageIcon icon = new ImageIcon("assets/pktool/textures/about.png", "about");
    JOptionPane.showMessageDialog(
      null,
      String.format(
        (String)ld.get(prefix + "description"),
        (String)ld.get(prefix + "author"),
        version
        ),
      (String)ld.get(prefix + "title"),
      JOptionPane.INFORMATION_MESSAGE,
      icon
      );
  }
}
