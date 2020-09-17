import org.jfugue.theory.ChordProgression;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import java.util.Scanner;

public class songwrite extends music {
  public static void main(String[] args) {
	  Player player = new Player();
	  Scanner scanner = new Scanner(System.in);

	  
	  // array of notes
	  String[] pitches = new String[] {"A", "B", "C", "D", "E", "F", "G"};
	  // array of durations/beat lengths
	  String[] beats = new String[] {"w","h","q","i"};
	  // array of either major or minor
	  String[] majOrMin = new String[] {"maj", "min"};
	  // major key notes
	  String[] major = new String[] {"C", "G", "D", "A", "E", "B", "Gb", "F#", 
			  						"C#", "Db", "Ab", "Eb", "Bb", "F"};
	  // minor key notes
	  String[] minor = new String[] {"A", "E", "B", "F#", "C#", "G#", "Eb", "D#", 
			  						"Bb", "F", "C", "G", "D"};
	
	
	  
	  // ---------- KEY GENERATOR ---------- //
	  String scale = pickValue(majOrMin);
	  String key = "";
	  String progression = "";
	  if (scale == "maj") {
		  key = "C"; // pickValue(major);
		  progression = "IV V vi V";//"I V vi IV I V vi IV I";
	  }
	  else if (scale == "min") {
		  key = "A"; // pickValue(minor);
		  progression = "i VI III VII i VI III VII i";
	  }
	  key += scale;
	  System.out.println("Generated Key: " + key);
	  
	  
	  
	  
	  // ---------- CHORD PROGRESSION ---------- //
	  ChordProgression cp = new ChordProgression(progression);
	  // chords -> string
      String cpString = cp.setKey(key).toString();
      System.out.println("Generated Chord progression is: " + cpString);
      
      // BASS CHORDS
      String bass = "";
      String[] arrCP = cpString.split(" ", 10); 
      for (int i = 0; i < arrCP.length; i++) {
    	  if (i == arrCP.length - 1) {
    		  bass += arrCP[i] + "w";  
    	  }
    	  else { bass += arrCP[i] + "w | "; }
      }
      // bass -> pattern
      Pattern bassLine = new Pattern ("V0 " + bass);
      
      Pattern melody = new Pattern ("V1 " + makeMelody(measureCount(bass), pitches, beats));
      System.out.println("Randomly Generated Melody: " + melody);
      //player.play(bassLine,melody); // PLAY BASS CHORDS AND MELODY
      
      
      
      // ---------- RANDOM CHORD PROGRESSION C MAJOR WITH RANDOM MELODY IN C ---------- //
      String[] majorChords = {"I", "ii", "iii", "IV", "V", "vi", "vii"};
      String randomStr = "";
      String randomBass = "";
      for (int i = 0; i <=10; i++) {
    	  randomStr += pickValue(majorChords) + " "; 
      }
      String[] arrRan = randomStr.split(" ", 10); 
      for (int i = 0; i < arrRan.length; i++) {
    	  if (i == arrRan.length - 1) {
    		  randomBass += arrRan[i] + "w";  
    	  }
    	  else { randomBass += arrRan[i] + "w | "; }
      }
      ChordProgression randomProgression = new ChordProgression(randomBass);
      String random = randomProgression.setKey("C").toString();
      Pattern randomP = new Pattern ("V0 " + random);
      System.out.println("Randomly Generated Chord Progression: " + random);
      //player.play(randomP,melody);
      
      

      // ---------- 12 BAR BLUES C MAJOR, RANDOM MELODY ---------- //
      System.out.println("\n-------12 BAR BLUES-------\n");
      String bpStr = ("V0 CMAJW | CMAJW | CMAJW | CMAJW | "
      		+ "FMAJW | FMAJW | CMAJW | CMAJW | GMAJW | FMAJW | CMAJW | CMAJW");
      Pattern bluesProgression = new Pattern ("V0 CMAJW | CMAJW | CMAJW | CMAJW | "
        		+ "FMAJW | FMAJW | CMAJW | CMAJW | GMAJW | FMAJW | CMAJW | CMAJW");
      Pattern blues = new Pattern ("V1 " + makeMelody(measureCount(bpStr), pitches, beats));
      System.out.println(bpStr);
      System.out.println(blues);
      player.play(bluesProgression,blues); 
      
  }
}