package me.dogeon.pktool.ui.mbf;

import java.awt.*;
import java.net.*;

public class MBFOnlineTutorial {

  public static void run() {
    try {
      URI url = new URI("https://dogeon188.github.io/blog/pktool");
      Desktop.getDesktop().browse(url);
    } catch (Exception e) {
      System.out.println("Unable to open online tutorial!");
    }
  }
}
