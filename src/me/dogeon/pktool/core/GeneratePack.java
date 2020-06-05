package me.dogeon.pktool.core;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import me.dogeon.pktool.locale.*;

public class GeneratePack {

  static String pre = "pktool.generate.";

  public static void generate(JFrame f) {
    JPanel bench = (JPanel)f.getContentPane().getComponent(0);
    Component[] cs = bench.getComponents();
    String pkn = null;
    String pkd = null;

    for (Component c : cs) {
      if (c.getClass() == JTextField.class) {
        JTextField t = (JTextField) c;
        pkn = t.getText();
        if (pkn.equals("")) {
          showWarn("no_packname");
          return;
        } else if (pkn.matches(".*[\\/|?\"*:<>.].*")) {
          showWarn("packname_with_invalid_char");
          return;
        }
      } else if (c.getClass() == JScrollPane.class) {
        JScrollPane s = (JScrollPane) c;
        JViewport v = (JViewport) s.getComponent(0);
        JTextArea t = (JTextArea) v.getComponent(0);
        pkd = t.getText();
        if (!Configurations.jsonDesc) {
          pkd = pkd.replace("\"", "\\\"");
          pkd = "\"" + pkd + "\"";
        }
      }
    }

    try {
      String root = "datapacks/" + pkn;
      JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
      jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      jfc.setDialogTitle(Languages.getTranslate(pre + "file.title"));
      int res1 = jfc.showSaveDialog(null);
      if (res1 == JFileChooser.CANCEL_OPTION) {
        return;
      }
      File file1 = jfc.getSelectedFile();
      root = file1.getAbsolutePath() + "/" + pkn;

      new File(root).mkdirs();
      FileWriter fw = new FileWriter(root + "/pack.mcmeta");
      fw.write(String.format("{\n\t\"pack\":{\n\t\t\"pack_format\": 5,\n\t\t\"description\": %s\n\t}\n}", pkd));
      fw.close();
      new File(root + "/data").mkdirs();
      if (Configurations.mcNamespace) {
        new File(root + "/data/minecraft/").mkdirs();
        if (Configurations.tickLoadTags) {
          new File(root + "/data/minecraft/tags/functions/").mkdirs();
          String[] strs1 = {"tick", "load"};
          for (String str1 : strs1) {
            FileWriter fw1 = new FileWriter(root + String.format("/data/minecraft/tags/functions/%s.json", str1));
            fw1.write("{\n\t\"values\": [\n\t]\n}");
            fw1.close();
          }
        }
      }
      showSuccess(pkn);
    } catch (Exception e) {
      showWarn("unknown");
      e.printStackTrace();
    }
  }

  private static void showWarn(String e) {
    JOptionPane.showMessageDialog(
      null,
      Languages.getTranslate(pre + "error.description." + e),
      Languages.getTranslate(pre + "error.title"),
      JOptionPane.WARNING_MESSAGE,
      new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("assets/pktool/textures/warn.png"))
    );
  }

  private static void showSuccess(String pn) {
    JOptionPane.showMessageDialog(
      null,
      String.format(
        Languages.getTranslate(pre + "success.description"),
        pn
      ),
      Languages.getTranslate(pre + "success.title"),
      JOptionPane.INFORMATION_MESSAGE,
      new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("assets/pktool/textures/saved.png"))
    );
  }
}
