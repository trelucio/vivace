import java.util.Random;
import org.jfugue.theory.Chord;
import org.jfugue.theory.Note; 

public class music {		
	// chooses a random number from 0 to x
	public static int randomize(int x){
		Random rand = new Random(); 
		int random = rand.nextInt(x); 
		return random;
	}
	
	// makes a key (chooses value from array a)
	public static String pickValue(String[] a) {
		int x = randomize(a.length); 
		String note = a[x];
		return note;
	}
	
	// extracts the individual notes from chords used
	public static String notify(Chord[] c) {
		String x = "";
	    for (Chord chord : c) {
	      Note[] notes = chord.getNotes();
	      for (Note note : notes) {
	    	  x += note + " ";
	      }
	      x += "\n";
	    }    
	    return(x);
	}
	
	
	// generates a random melody
	public static String makeRandomMelody(String[] pitches, String[] beats) {
	String melody = "";
	  for (int i = 0; i <= 10; i++) {
		melody += pickValue(pitches) + pickValue(beats) + " ";
	  }
	 return("melody is: " + melody);
	}
	
	/* make a measure, returns string of melody, input pitches[] and beats[]
	 * public static String makeMeasure(String[] pitch, String[] beat) {
	 * pick a pitch in the key, beat depends
	 * current beats = 0;
	 * if measure = 4, end, build next measure
	 * if melody contains w, beats + 4, h + 2, q + 1, i + 1/8, s + 1/16, t + 1/32  
	 */
	 
	public static String makeMelody(int m, String[] pitches, String[] beats) {
		String melody = "";
		String beatPicked = "";
		double beatsTotal = 0.0;
		boolean beatIt = false;
		while (beatIt == false) {
			melody += pickValue(pitches);
			beatPicked = pickValue(beats);
			beatsTotal += getNoteValue(beatPicked);
			if (beatsTotal >= m - 10) {
				beatIt = true;
			}
			melody += beatPicked + " ";
		}
		return melody;
	}
	
	
	// sets note values
	public static double getNoteValue(String n) {
		double noteVal = 0.0;
		if (n == "w") {
			noteVal = 4;
		}
		else if (n == "h") {
			noteVal = 2;
		}
		else if (n == "q") {
			noteVal = (0.25);
		}
		else if (n == "i") {
			noteVal = (0.125);
		}
		return noteVal;
	}
	
	// counts measures
	public static int measureCount(String bass) {
		 int mCount = 0;
	     for (int i = 0; i < bass.length(); i++) {
	   	  if (bass.charAt(i) == '|') {
	   		  mCount++;
	   	  }
	     }
	     mCount++;
	     int bpm = 4;
	     int beatsTotal = bpm * mCount;
	     return beatsTotal;
	}
}