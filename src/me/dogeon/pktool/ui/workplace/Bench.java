package me.dogeon.pktool.ui.workplace;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import me.dogeon.pktool.core.*;
import me.dogeon.pktool.locale.*;

public class Bench {

  String prefix = "pktool.bench.";
  String[] name;
  Runnable[] actions;
  int att[][];
  ArrayList<JComponent> components;

  int lLabel;
  int lCheckBox;
  int lButton;

  boolean bool_tmp;

  public Bench(JFrame fr) {
    int f[] = { // fill
      GridBagConstraints.BOTH,
      GridBagConstraints.VERTICAL,
      GridBagConstraints.HORIZONTAL,
      GridBagConstraints.NONE
    };
    int l[] = { // anchor
      GridBagConstraints.CENTER,
      GridBagConstraints.EAST,
      GridBagConstraints.SOUTHEAST,
      GridBagConstraints.SOUTH,
      GridBagConstraints.SOUTHWEST,
      GridBagConstraints.WEST,
      GridBagConstraints.NORTHWEST,
      GridBagConstraints.NORTH,
      GridBagConstraints.NORTHEAST
    };
    String n[] = {
      "label.pack_name", "label.pack_description", // sep
      null, null, // text input
      "config.do_use_json_description", "config.do_generate_minecraft", "config.do_generate_function_tag", // checkbox
      "button.generate"
    };
    lLabel = 2;
    lCheckBox = 3;
    lButton = 1;
    name = n;
    int a[][] = { // gridx, gridy, gridw, gridh, weightx, weighty, fill, anchor
      {0, 1, 1, 1, 0, 0, f[0], l[0]}, // jlabel pack name
      {0, 3, 1, 1, 0, 0, f[0], l[0]}, // jlabel pack description
      {0, 2, 1, 1, 0, 0, f[0], l[0]}, // jtextfield pack name
      {0, 4, 1, 1, 1, 1, f[0], l[0]}, // jtextarea pack description
      {0, 5, 1, 1, 0, 0, f[0], l[0]}, // sep line
      {0, 6, 1, 1, 0, 0, f[0], l[0]}, // jcheckbox description json
      {0, 7, 1, 1, 0, 0, f[0], l[0]}, // jcheckbox do generate minecraft namespace
      {0, 8, 1, 1, 0, 0, f[0], l[0]}, // jcheckbox do generate minecraft/tags/function/*.json
      {0, 9, 1, 1, 0, 0, f[0], l[0]} // jbutton generate
    };
    att = a;
    Runnable r[] = {
      null,
      null,
      null,
      null,
      () -> Configurations.useJsonChatComponent = bool_tmp,
      () -> {
        Configurations.generateMinecraftNamespace = bool_tmp;
        JCheckBox cb1 = (JCheckBox) components.get(7);
        cb1.setEnabled(bool_tmp);
        if (!bool_tmp) {
          cb1.setSelected(false);
          Configurations.generateFunctionTags = false;
        }
      },
      () -> Configurations.generateFunctionTags = bool_tmp
    };
    actions = r;
    components = new ArrayList<JComponent>();
    addComponents(fr);
  }

  private void addComponents(JFrame f) {
    Dictionary ld = Languages.getLangDict();
    JPanel p = new JPanel();
    Border border = new EmptyBorder(8, 8, 8, 8);
    Font mono = new Font("monospaced", Font.PLAIN, 12);

    p.setLayout(new GridBagLayout());
    p.setBorder(new EmptyBorder(16, 16, 16, 16));

    int i;
    for (i = 0; i < lLabel; i++) {
      JLabel l = new JLabel((String)ld.get(prefix + name[i]));
      l.setBorder(border);
      components.add(l);
    }
    // pack name
      JTextField tf = new JTextField();
      tf.setFont(mono);
      components.add(tf);
    // pack description
      JTextArea ta = new JTextArea();
      JScrollPane jp = new JScrollPane(ta);
      jp.setAutoscrolls(true);
      ta.setFont(mono);
      ta.setBorder(border);
      ta.setTabSize(4);
      ta.setLineWrap(true);
      ta.setWrapStyleWord(true);
      components.add(jp);
    // configs
    for (i = lLabel + 2; i < lLabel + lCheckBox + 2; i++) {
      JCheckBox cb = new JCheckBox((String)ld.get(prefix + name[i]));
      components.add(cb);
      if (i == 4) components.add(new JSeparator());
      Runnable r = actions[i];
      cb.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            bool_tmp = cb.isSelected();
            r.run();
          }
        });
    }
    // submit
      JButton b = new JButton((String)ld.get(prefix + name[i]));
      b.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            GeneratePack.generate(f);
          }
        });
      components.add(b);

    for (i = 0; i < components.size(); i++) {
      GridBagConstraints c = new GridBagConstraints();
      int a[] = att[i];
      c.gridx = a[0];
      c.gridy = a[1];
      c.gridwidth = a[2];
      c.gridheight = a[3];
      c.weightx = (float)a[4]/100;
      c.weighty = (float)a[5]/100;
      c.fill = a[6];
      c.anchor = a[7];
      p.add(components.get(i), c);
    }
    components.get(7).setEnabled(false);
    f.add(p);
  }
}
