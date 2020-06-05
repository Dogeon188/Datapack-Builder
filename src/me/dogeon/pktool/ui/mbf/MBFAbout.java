package me.dogeon.pktool.ui.mbf;

import java.util.*;
import javax.swing.*;
import me.dogeon.pktool.locale.*;
import me.dogeon.pktool.Main;

public class MBFAbout {

  static String vr = "0.1.1";
  static String pr = "pktool.about.";

  public static void run() {
    Dictionary ld = Languages.getLangDict();
    JOptionPane.showMessageDialog(
      null,
      String.format(
        Languages.getTranslate(pr + "description"),
        Languages.getTranslate(pr + "author"),
        vr
      ),
      Languages.getTranslate(pr + "title"),
      JOptionPane.INFORMATION_MESSAGE,
      new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("assets/pktool/textures/about.png"))
    );
  }
}
