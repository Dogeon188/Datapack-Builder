package me.dogeon.pktool.core;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import me.dogeon.pktool.locale.*;

public class GeneratePack {

  static String prefix = "pktool.generate.";

  public static void generate(JFrame f) {
    JPanel bench = (JPanel)f.getContentPane().getComponent(0);
    Component[] cs = bench.getComponents();
    String packname = "NOPE";
    String packdesc = "nope";

    for (Component c : cs) {
      if (c.getClass() == JTextField.class) {
        JTextField t = (JTextField) c;
        packname = t.getText();
        if (packname.equals("")) {
          showWarn("no_packname");
          return;
        } else if (doContainInvalidChar(packname)) {
          showWarn("packname_with_invalid_char");
          return;
        }
      } else if (c.getClass() == JScrollPane.class) {
        JScrollPane s = (JScrollPane) c;
        JViewport v = (JViewport) s.getComponent(0);
        JTextArea t = (JTextArea) v.getComponent(0);
        packdesc = t.getText();
        if (true) {
          packdesc = "\"" + packdesc + "\"";
        } else {
          packdesc = packdesc.replace("\n", "\n\t\t");
        }
      }
    }

    try {
      String packroot = "datapacks/" + packname;
      new File(packroot).mkdir();
      FileWriter fw = new FileWriter(packroot + "/pack.mcmeta");
      fw.write(String.format("{\n\t\"pack\":{\n\t\t\"pack_format\": 5,\n\t\t\"description\": %s\n\t}\n}", packdesc));
      fw.close();
      new File(packroot + "/data").mkdir();

      showSuccess(packname);
    } catch (Exception e) {
      System.out.println("Unable to make datapack!");
      e.printStackTrace();
    }
  }

  private static void showWarn(String e) {
    Dictionary ld = Languages.getLangDict();
    JOptionPane.showMessageDialog(
      null,
      (String)ld.get(prefix + "error.description." + e),
      (String)ld.get(prefix + "error.title"),
      JOptionPane.WARNING_MESSAGE
      );
  }

  private static void showSuccess(String pn) {
    Dictionary ld = Languages.getLangDict();
    JOptionPane.showMessageDialog(
      null,
      String.format(
        (String)ld.get(prefix + "success.description"),
        pn
        ),
      (String)ld.get(prefix + "success.title"),
      JOptionPane.INFORMATION_MESSAGE
      );
  }

  private static boolean doContainInvalidChar(String s) {
    return s.contains("\\") || s.contains("/") || s.contains("|") || s.contains("?") || s.contains("\"") || s.contains("*") || s.contains(":") || s.contains("<") || s.contains(">") || s.contains(".");
  }
}
