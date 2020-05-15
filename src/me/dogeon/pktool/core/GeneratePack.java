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
    String packname = null;
    String packdesc = null;

    for (Component c : cs) {
      if (c.getClass() == JTextField.class) {
        JTextField t = (JTextField) c;
        packname = t.getText();
        if (packname.equals("")) {
          showWarn("no_packname");
          return;
        } else if (packname.matches(".*[\\/|?\"*:<>.].*")) {
          showWarn("packname_with_invalid_char");
          return;
        }
      } else if (c.getClass() == JScrollPane.class) {
        JScrollPane s = (JScrollPane) c;
        JViewport v = (JViewport) s.getComponent(0);
        JTextArea t = (JTextArea) v.getComponent(0);
        packdesc = t.getText();
        if (!Configurations.useJsonChatComponent) {
          packdesc = packdesc.replace("\"", "\\\"");
          packdesc = "\"" + packdesc + "\"";
        }
      }
    }

    try {
      String packroot = "datapacks/" + packname;
      JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
      jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      jfc.setDialogTitle("Save datapack to...");
      int res1 = jfc.showSaveDialog(null);
      if (res1 == JFileChooser.CANCEL_OPTION) {
        return;
      }
      File file1 = jfc.getSelectedFile();
      packroot = file1.getAbsolutePath() + "/" + packname;

      new File(packroot).mkdirs();
      FileWriter fw = new FileWriter(packroot + "/pack.mcmeta");
      fw.write(String.format("{\n\t\"pack\":{\n\t\t\"pack_format\": 5,\n\t\t\"description\": %s\n\t}\n}", packdesc));
      fw.close();
      new File(packroot + "/data").mkdirs();

      showSuccess(packname);
    } catch (Exception e) {
      showWarn("unknown");
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
}
