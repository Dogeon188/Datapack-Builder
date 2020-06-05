package me.dogeon.pktool.ui.workplace;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import me.dogeon.pktool.core.*;
import me.dogeon.pktool.locale.*;

public class Bench {

  String pre = "pktool.bench.";
  String[] name;
  Runnable[] acts;
  int gy[];
  ArrayList<JComponent> cpns;

  boolean bool1;

  public Bench(JFrame fr) {
    String n[] = {
      "label.pack_name",
      "label.pack_description",
      null,
      null, // text input
      "config.do_use_json_description",
      "config.do_generate_minecraft",
      "config.do_generate_function_tag",
      "button.generate"
    };
    name = n;
    int a[] = {1, 3, 2, 4, 5, 6, 7, 8, 9};
    gy = a;
    Runnable r[] = {
      null,
      null,
      null,
      null,
      () -> Configurations.jsonDesc = bool1,
      () -> {
        Configurations.mcNamespace = bool1;
        JCheckBox cb1 = (JCheckBox) cpns.get(7);
        cb1.setEnabled(bool1);
        if (!bool1) {
          cb1.setSelected(false);
          Configurations.tickLoadTags = false;
        }
      },
      () -> Configurations.tickLoadTags = bool1
    };
    acts = r;
    cpns = new ArrayList<JComponent>();
    addComponents(fr);
  }

  private void addComponents(JFrame f) {
    JPanel p = new JPanel();
    Border bd = new EmptyBorder(8, 8, 8, 8);
    Font mono = new Font("monospaced", Font.PLAIN, 12);

    p.setLayout(new GridBagLayout());
    p.setBorder(new EmptyBorder(16, 16, 16, 16));

    int i;
    for (i = 0; i < 2; i++) {
      JLabel l = new JLabel(Languages.getTranslate(pre + name[i]));
      l.setBorder(bd);
      cpns.add(l);
    }
    // pack name
      JTextField tf = new JTextField();
      tf.setFont(mono);
      cpns.add(tf);
    // pack description
      JTextArea ta = new JTextArea();
      JScrollPane jp = new JScrollPane(ta);
      jp.setAutoscrolls(true);
      ta.setFont(mono);
      ta.setBorder(bd);
      ta.setTabSize(4);
      ta.setLineWrap(true);
      ta.setWrapStyleWord(true);
      cpns.add(jp);
    // configs
    for (i = 4; i < 7; i++) {
      JCheckBox cb = new JCheckBox(Languages.getTranslate(pre + name[i]));
      cpns.add(cb);
      if (i == 4) cpns.add(new JSeparator());
      Runnable r = acts[i];
      cb.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            bool1 = cb.isSelected();
            r.run();
          }
        });
    }
    // submit
    JButton b = new JButton(Languages.getTranslate(pre + name[7]));
    b.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          GeneratePack.generate(f);
        }
      });
    cpns.add(b);

    for (i = 0; i < cpns.size(); i++) {
      GridBagConstraints c = new GridBagConstraints();
      c.gridx = 0;
      c.gridy = gy[i];
      c.gridwidth = 1;
      c.gridheight = 1;
      c.weightx = (i==3) ? 1 : 0;
      c.weighty = (i==3) ? 1 : 0;
      c.fill = GridBagConstraints.BOTH;
      c.anchor = GridBagConstraints.CENTER;
      p.add(cpns.get(i), c);
    }
    cpns.get(7).setEnabled(false);
    f.add(p);
  }
}
