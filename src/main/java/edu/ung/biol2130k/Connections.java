package edu.ung.biol2130k;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Connections {

  public ArrayList<Integer> indexA, indexB;
  public ArrayList<String> info;

  public Connections(BufferedReader reader) {
    indexA = new ArrayList<>();
    indexB = new ArrayList<>();
    info = new ArrayList<>();

    Integer connectionLineNo = 0;

    try {
      String thisLine = reader.readLine();
      while(thisLine != null) {
        // connectionLineNo == 0 are the separating "CONNECTION:" lines
        if(connectionLineNo.equals(1)) indexA.add(Integer.parseInt(thisLine));
        else if(connectionLineNo.equals(2)) indexB.add(Integer.parseInt(thisLine));
        else if(connectionLineNo.equals(3)) info.add(thisLine);
        connectionLineNo++;
        connectionLineNo = connectionLineNo % 4;
        thisLine = reader.readLine();
      }
      reader.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

}
