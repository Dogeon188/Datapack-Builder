package me.dogeon.pktool.ui.mbf;

import java.awt.*;
import java.net.*;
import javax.swing.*;

public class MBFSuperSecretSettings {

  public static void run() {
    byte[] b1 = {
      104, 116, 116, 112, 115, 58, 47, 47,
      121, 111, 117, 116, 117, 46, 98, 101,
      47, 100, 81, 119, 52, 119, 57, 87,
      103, 88, 99, 81
    };
    byte[] b2 = {
      89, 111, 117, 32, 106, 117, 115, 116,
      32, 103, 101, 116, 32, 114, 105, 99,
      107, 114, 111, 108, 108, 101, 100, 32, 59, 41
    };
    byte[] b3 = {
      85, 110, 97, 98, 108, 101, 32, 116,
      111, 32, 114, 105, 99, 107, 114, 111,
      108, 108, 32, 121, 111, 117, 32, 81,
      65, 81
    };
    try {
      URI url = new URI(new String(b1));
      Desktop.getDesktop().browse(url);
      JOptionPane.showMessageDialog(
      null,
      new String(b2),
      "",
      JOptionPane.INFORMATION_MESSAGE
      );
    } catch (Exception e) {
      System.out.println(new String(b3));
    }
  }
}
