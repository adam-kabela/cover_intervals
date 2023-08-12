import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class maxCoverByMinSet {
	
	public static int[][] readInput(String file) throws IOException{
		List<int[]> intervalsInList = new ArrayList<int[]>();
		BufferedReader bf = new BufferedReader(new FileReader(file));
		String line = bf.readLine();
		int lineNumber = 0;
		while (line != null) {
			lineNumber++;
			intervalsInList.add(processUserInput(line, lineNumber));
			line = bf.readLine();
		}
		bf.close();   
		int[][] intervals = intervalsInList.toArray(new int[0][0]);
		return intervals;  
	}
	  
	public static int[] processUserInput(String line, int lineNumber){
		String errorText = "Check line " + String.valueOf(lineNumber) + " of the input file. ";
		int [] startAndEnd = new int[2];
		String[] splitString = line.split(" ");
        if (splitString.length != 2){
			System.out.println(errorText + "Expected two values but read: " + line); 
			System.exit(1);
		}
		for(int i = 0; i < 2; i++){
			try{
				startAndEnd[i] = Integer.parseInt(splitString[i]);
			}
			catch(NumberFormatException e){
				System.out.println(errorText + "Expected integers separated by space but read: " + line); 
				System.exit(1);
			}
        }			
		if(startAndEnd[0] > startAndEnd[1]){
			System.out.println(errorText + "First number (left end of interval) is greater than second: " + line); 
			System.exit(1);
		}
		return startAndEnd;
	}

	public static void outputCover(String file, String cover) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(cover);
		writer.close();
	}
	
	/*input: list of intervals sorted by left ends
	output: cover maximum number of points and for this choose a minimal set of intervals
	each interval is only considered once, this should be the fastest possible algorithm */
	public static String findBestCover(int[][] intervals) {
		String cover = "";
		int x = intervals[0][0];  // first point to be covered
		boolean xIsCovered = false;
		int [] dummy = new int [0];
		int [] best = dummy; // best interval covering x
		int index = 0;
		int[] I;
		while(index < intervals.length) { // process intervals from left to right sorted by their left ends
			I = intervals[index];
			if (I[0] <= x){  // left end of I <= x
				index++;
				if (I[1] >= x){  // right end of I >= x, so I covers x
					if (!xIsCovered || (I[1] > x)){
						if (best.length == 0){
							best = I;
						}else if (I[1] > best[1]){  // I covers more uncovered points than best
							best = I;
						}
					}
				}
			}else{  // all candidates covering x were considered (since the left end of I > x)
				if (best.length == 0){  // no interval can cover x
					x = I[0];  // move to the closest point x that can be covered (this point is not covered yet)
					xIsCovered = false;
				}else{  // some interval covers x
					cover = cover.concat(Integer.toString(best[0]) + " " + Integer.toString(best[1]) + "\n");
					x = best[1];  // move x to the right end of best (which is the right-most covered point)
					xIsCovered = true;
					best = dummy;
				}
			}
		}
		if (best.length == 2){  // the last point x can be covered but no interval was added yet
			cover = cover.concat(Integer.toString(best[0]) + " " + Integer.toString(best[1]) + "\n");
		}
		return cover;
	}

	public static void main(String[] args) throws IOException {
		int[][] intervals = readInput("input.txt");
		//sort intervals by first value, that is, by the left ends
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[0],b[0]));
		//System.out.println(Arrays.deepToString(intervals));
		String cover = findBestCover(intervals);
		outputCover("output.txt", cover);
	}
}