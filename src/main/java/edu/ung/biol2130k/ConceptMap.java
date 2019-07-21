package edu.ung.biol2130k;

import processing.core.PApplet;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author naharrison
 */
public class ConceptMap extends PApplet {

  public static void main(String[] args) {
    PApplet.main("edu.ung.biol2130k.ConceptMap");
  }


  public Connections connections;
  public Terms terms;
  public Integer winw, winh; // window width, height
  public float leftSideFrac;


  public void settings() {
    leftSideFrac = (float) 0.65;

    winw = 400; // default values in case windowSize.txt DNE
    winh = 400;

    // read the window size from file:
    BufferedReader reader = createReader("windowSize.txt");
    try {
      winw = Integer.parseInt(reader.readLine());
      winh = Integer.parseInt(reader.readLine());
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    size(winw, winh);
  }


  public void setup() {
    frameRate(10);
    textSize(14);

    BufferedReader dataReader1 = createReader("terms.txt");
    terms = new Terms(dataReader1);
    BufferedReader dataReader2 = createReader("connections.txt");
    connections = new Connections(dataReader2);
  }


  public void draw() {
    background(255);

    int nterms = terms.terms.size();
    for(int k = 0; k < nterms; k++) {
      int x = (int) (0.5*0.85*winw*leftSideFrac*Math.cos(k*2.0*Math.PI/nterms) + 0.5*leftSideFrac*winw);
      int y = (int) (0.5*0.85*winw*leftSideFrac*Math.sin(k*2.0*Math.PI/nterms) + 0.5*winh);
      fill(0);
      stroke(0);
      strokeWeight(1);
      ellipse(x, y, 6, 6);
      String termAndIndex = terms.terms.get(k) + " (" + k + ")";
      text(termAndIndex, x+5, y+5);
    }

    int nconnections = connections.indexA.size();
    for(int k = 0; k < nconnections; k++) {
      int xA = (int) (0.5*0.85*winw*leftSideFrac*Math.cos(connections.indexA.get(k)*2.0*Math.PI/nterms) + 0.5*leftSideFrac*winw);
      int yA = (int) (0.5*0.85*winw*leftSideFrac*Math.sin(connections.indexA.get(k)*2.0*Math.PI/nterms) + 0.5*winh);
      int xB = (int) (0.5*0.85*winw*leftSideFrac*Math.cos(connections.indexB.get(k)*2.0*Math.PI/nterms) + 0.5*leftSideFrac*winw);
      int yB = (int) (0.5*0.85*winw*leftSideFrac*Math.sin(connections.indexB.get(k)*2.0*Math.PI/nterms) + 0.5*winh);
      fill(0);
      stroke(0);
      strokeWeight(1);
      line(xA, yA, xB, yB);
    }

    int sci = getConnectionIndex(); // sci = selected connection index
    if(sci >=0) {
      int xA = (int) (0.5*0.85*winw*leftSideFrac*Math.cos(connections.indexA.get(sci)*2.0*Math.PI/nterms) + 0.5*leftSideFrac*winw);
      int yA = (int) (0.5*0.85*winw*leftSideFrac*Math.sin(connections.indexA.get(sci)*2.0*Math.PI/nterms) + 0.5*winh);
      int xB = (int) (0.5*0.85*winw*leftSideFrac*Math.cos(connections.indexB.get(sci)*2.0*Math.PI/nterms) + 0.5*leftSideFrac*winw);
      int yB = (int) (0.5*0.85*winw*leftSideFrac*Math.sin(connections.indexB.get(sci)*2.0*Math.PI/nterms) + 0.5*winh);
      fill(255, 0, 0);
      stroke(255, 0, 0);
      strokeWeight(2);
      line(xA, yA, xB, yB);
      fill(0);
      stroke(0);
      strokeWeight(1);
      text(connections.info.get(sci), (int) (leftSideFrac*winw)+10, 10, (int) (((1.0-leftSideFrac)*winw)-10), winh);
    }

  }


  public int getConnectionIndex() {
    int nconnections = connections.indexA.size();
    int nterms = terms.terms.size();
    for(int k = 0; k < nconnections; k++) {
      int xA = (int) (0.5*0.85*winw*leftSideFrac*Math.cos(connections.indexA.get(k)*2.0*Math.PI/nterms) + 0.5*leftSideFrac*winw);
      int yA = (int) (0.5*0.85*winw*leftSideFrac*Math.sin(connections.indexA.get(k)*2.0*Math.PI/nterms) + 0.5*winh);
      int xB = (int) (0.5*0.85*winw*leftSideFrac*Math.cos(connections.indexB.get(k)*2.0*Math.PI/nterms) + 0.5*leftSideFrac*winw);
      int yB = (int) (0.5*0.85*winw*leftSideFrac*Math.sin(connections.indexB.get(k)*2.0*Math.PI/nterms) + 0.5*winh);
      double distAB = Math.sqrt((xB-xA)*(xB-xA) + (yB-yA)*(yB-yA));
      double distAMB = Math.sqrt((mouseX-xA)*(mouseX-xA) + (mouseY-yA)*(mouseY-yA)) + Math.sqrt((xB-mouseX)*(xB-mouseX) + (yB-mouseY)*(yB-mouseY)); // M = Mouse
      if(distAMB - distAB < 0.175) return k;
    }
    return -1;
  }


}
