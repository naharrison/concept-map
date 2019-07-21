package edu.ung.biol2130k;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Terms {

  public ArrayList<String> terms;

  public Terms(BufferedReader reader) {
    terms = new ArrayList<>();

    try {
      String thisLine = reader.readLine();
      while(thisLine != null) {
        terms.add(thisLine);
        thisLine = reader.readLine();
      }
      reader.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

}
