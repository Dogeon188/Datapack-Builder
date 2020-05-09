package me.dogeon.pktool.locale;

import java.io.*;
import java.util.*;
import me.dogeon.pktool.locale.*;

public class Languages {

  public static final Language EN_US;
  public static final Language ZH_TW;
  public static final Language EN_UD;

  static {
    EN_US = new Language("English (US)", "en_us");
    ZH_TW = new Language("繁體中文（台灣）", "zh_tw");
    EN_UD = new Language("ɥsᴉꞁᵷuƎ (SՈ)", "en_ud");
  }

  private static String defaultLangId = Languages.ZH_TW.id;
  public static String currentLangId = defaultLangId;

  public static Dictionary getLangDict() {
    return Languages.getLangDict(currentLangId);
  }

  public static Dictionary getLangDict(String l) {
    Dictionary<String, String> lang = new Hashtable<>();
    InputStream res = Language.class.getResourceAsStream(String.format("/assets/pktool/lang/%s.yml", l));
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(res, "utf8"));
      int i;
      StringBuffer buf1 = new StringBuffer();
      String buf2 = "";
      while ((i = br.read()) != -1) {
        char c = (char)i;
        if (c == ':') {
          buf2 = buf1.toString();
          buf1 = new StringBuffer();
          br.read();
        } else if (c == '\n') {
          lang.put(buf2, buf1.toString());
          buf1 = new StringBuffer();
          buf2 = "";      
        } else if (c == '\\') {
          c = (char)br.read();
          if (c == 'n') {buf1.append('\n');}
          else if (c == 't') {buf1.append('\t');}
          else if (c == '\\') {buf1.append('\\');}
          else if (c == ':') {buf1.append(':');}
        } else {buf1.append(c);}
      }
    } catch (Exception e) {}
    return lang;
  }
}
