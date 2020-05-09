package me.dogeon.pktool.locale;

import java.io.*;
import java.util.*;

public class Language {

  public String display;
  public String id;
  public boolean isDefault;

  public Language(String display, String id) {
    this.display = display;
    this.id = id;
    this.isDefault = id == "en_us";
  }
}
