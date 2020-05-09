package me.dogeon.pktool.ui.mbf;

import java.net.*;
import java.awt.*;

public class MBFSwingDoc{

  public static void run() {
    try {
      URI url = new URI("https://docs.oracle.com/javase/10/docs/api/javax/swing/package-summary.html");
      Desktop.getDesktop().browse(url);
    } catch (Exception e) {
      System.out.println("Unable to open swing document!");
    }
  }
}
