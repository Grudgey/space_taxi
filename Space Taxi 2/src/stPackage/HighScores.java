package stPackage;

import java.io.*;

import java.util.StringTokenizer;

public class HighScores {
	
	/**
	 * Name of player
	 */
	private String myName;
	/**
	 * Player's score
	 */
	private int myScore;

	/**
	 * File that holds high scores info
	 */
	private File scoreFile = new File(
			"high_scores.txt");
	
	/**
	 * Array that holds names of highscores
	 */
	private String[] names = new String[10];
	/**
	 * Array that holds highscores
	 */
	private int[] scores = new int[10];

	/**
	 *  default constructor
	 * 
	 */
	public HighScores(){
		getText(scoreFile);
	}
	/**
	 * Checks if new score is good enough and inputs it if it is
	 * 
	 * @param inname players name
	 * @param inscore players score
	 */
	public HighScores(String inname, int inscore) {
		myName = inname;
		myScore = inscore;
		getText(scoreFile);
		check_scores();
	}

	/**
	 * Fetch the entire contents of a text file, and return it in a String. This
	 * style of implementation does not throw Exceptions to the caller.
	 * 
	 * @param aFile
	 *            is a file which already exists and can be read.
	 */
	public void getText(File aFile) {
		// ...checks on aFile are elided
		//StringBuffer contents = new StringBuffer();

		// declared here only to make visible to finally clause
		BufferedReader input = null;
		StringTokenizer st = null;
		int i = 0;
		try {
			// use buffering, reading one line at a time
			// FileReader always assumes default encoding is OK!
			input = new BufferedReader(new FileReader(aFile));
			String line = input.readLine(); // not declared within while loop
			/*
			 * readLine is a bit quirky : it returns the content of a line MINUS
			 * the newline. it returns null only for the END of the stream. it
			 * returns an empty String if two newlines appear in a row.
			 */
			while (line != null) {
				st = new StringTokenizer(line);
				if(st.hasMoreTokens()){
					names[i] = st.nextToken();
					scores[i] = Integer.parseInt(st.nextToken());
				}
				i++;
				line = input.readLine();
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (input != null) {
					// flush and close both "input" and its underlying
					// FileReader
					input.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	//	return contents.toString();
	}

	/**
	 * Change the contents of text file in its entirety, overwriting any
	 * existing text.
	 * 
	 * This style of implementation throws all exceptions to the caller.
	 * 
	 * @param aFile
	 *            is an existing file which can be written to.
	 */
	private void rewrite_text(File aFile) {
		// declared here only to make visible to finally clause; generic
		// reference
		Writer output = null;
		try {
			// use buffering
			// FileWriter always assumes default encoding is OK!
			output = new BufferedWriter(new FileWriter(aFile));
			for (int i = 0; i < 10; i++) {
				output.write(names[i] + " " + scores[i] + "\n");
			}
			output.close();
		} catch (IOException e) {
			System.out.print("no time");
		} finally {
			// flush and close both "output" and its underlying FileWriter
			try {
				if (output != null){
					output.close();
				}
			} catch (IOException e) {
				System.out.print("no time");
			}
		}
	}

	/**
	 * Checks if new score is good enoogh to make it into the highscores
	 *
	 */
	public void check_scores() {
		if (scores[9] > myScore) {
			return;
		} else if (scores[9] < myScore) {
			insert_score(myName, myScore, 9);
		}
		rewrite_text(scoreFile);
	}

	/**
	 * Inserts score
	 * @param newName name 
	 * @param newScore score 
	 * @param place position in high scores
	 */
	public void insert_score(String newName, int newScore, int place) {
		if(place == 0){
			int move_down_one = scores[place];
			scores[place] = newScore;
			
			String move_down_one_name = names[place];
			names[place] = newName;
			
			int tempScore;
			String tempName;
			
			for (int i = place + 1; i < 10; i++) {
				tempScore = scores[i];
				scores[i] = move_down_one;
				move_down_one = tempScore;
				
				tempName = names[i];
				names[i] = move_down_one_name;
				move_down_one_name = tempName;
			}
		}
		else if (newScore > scores[place]) { // higher than the current place
			if (newScore > scores[place - 1]) { // higher than the next place
				insert_score(newName, newScore, place - 1);
			} else if (newScore <= scores[place - 1]) {
				// found the place where the score belongs, update array.
				int move_down_one = scores[place];
				scores[place] = newScore;
				
				String move_down_one_name = names[place];
				names[place] = newName;
				
				int tempScore;
				String tempName;
				
				for (int i = place + 1; i < 10; i++) {
					tempScore = scores[i];
					scores[i] = move_down_one;
					move_down_one = tempScore;
					
					tempName = names[i];
					names[i] = move_down_one_name;
					move_down_one_name = tempName;
				}
			}
		}
	}
	/**
	 * @return Returns the names.
	 */
	public String[] getNames() {
		return names;
	}
	/**
	 * @return Returns the scores.
	 */
	public int[] getScores() {
		return scores;
	}
}
