import java.io.*;
import java.util.*;
public class Lab2 {

	public static void main(String[] args) throws FileNotFoundException {
		
		String line = "";
		BufferedReader br = null;
		String cvsSplitBy = ",";
		String[] temp = new String[200];
		int[] counter = new int[200];
		ArrayList<String> sorted = new ArrayList<String>();
		
		//Reading data from CSV file to an array to avoid losing data in transfer process
		//Transferring data from Array to ArrayList to be able to remove duplicate entries
		int x = 0;
		try {
			br = new BufferedReader(new FileReader("spotifyDataLog.csv"));
				while ((line = br.readLine()) != null) {
						String[] name = line.split(cvsSplitBy);
						temp[x] = name[2];
						sorted.add(temp[x]);
						x++;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
						}
					}
				}
		//Removing duplicate entries in the ArrayList
		Set<String> set = new HashSet<>(sorted);
		sorted.clear();
		sorted.addAll(set);
		String[] sortedArr = sorted.toArray(new String[sorted.size()]);
		
		//Counting how many times an artist appears on the Top 200
		for(int i=0; i<133; i++) {
			for(int y=0; y<200; y++) {
					if(temp[y].equals(sortedArr[i])) counter[i]++;
				}
			}
		
		//Writing data to .txt file
	    try {
	        FileWriter Output = new FileWriter("Report.txt");
	        int h=1;
	        Output.write("WEEKLY REPORT OF TOP 200 ARTISTS - 9/03/2020\n--------------------------------------------\n");
	        for(int i=0; i<sortedArr.length; i++) {
	        	Output.write(sortedArr[i] + " appears " + counter[i] + " time(s) on the Top 200\n");
	        }
	        Output.close();
	        System.out.println("Successfully wrote to the file.");
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	    }
	  }
