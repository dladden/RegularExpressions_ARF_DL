package Part2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
* ProcessSchedule class which is used in Exact Text to find regex patterns
* and out-put them in a text file.
* @author <Adam Fischer, Denys Ladden>
* @version 1.0
* Compiler Project 3
* CS322 - Compiler Construction * Fall 2021
*/
public class ProcessSchedule {
	
	String fileName;
	
	
	public ProcessSchedule(String file) {
		
		fileName = file;
		
	}// end constructor
	
	/*
	 * Extract the course information and title for each course in the file and save just that information to a file.
	*/
	public void CourseInfo() throws IOException {
		
		String currentLine=null;
		BufferedReader txtReader = new BufferedReader(new FileReader(fileName));
		Scanner fileScan = new Scanner(new File(fileName));
		PrintWriter out = new PrintWriter("CourseInfo.txt");
		 
		while((currentLine = txtReader.readLine())!= null) {
	    	
			Pattern p = Pattern.compile("\\w{2,4}\\-\\d{3}(\\w)?\\-(((\\w{2})?\\d+)|(\\w{2})|)\\D+");
			Matcher m = p.matcher(currentLine);
			
			if(m.find()) {
				out.println(m.group());
			}// end if to print match to the txt file if there's a match in the line
			
		
	    }// end while to loop through all of the lines, comparing each one with the regex expression
		
	   
		out.close();
	    
	}// end courseinfo
	
	 
	
	/*
	 * Extract the section of the schedule that shows the open and closed courses based on 
	 * the seats filled and the seats available. For each course write to a file the status of the 
	 * class (CLOSED/open and the number of seats filled/seats available).
	 */
	public void SeatsAvailible() throws FileNotFoundException {
		

		Scanner fileScan = new Scanner(new File(fileName));
		PrintWriter out = new PrintWriter("SeatsAvailible.txt");
		 
		while(fileScan.hasNext()) {
	    	
			Pattern p = Pattern.compile("((Open)|(CLOSED))\\ \\d+\\ \\d+");
			Matcher m = p.matcher(fileScan.nextLine());
			
			if(m.find()) {
				out.println(m.group());
			}// end if to print match to the txt file if there's a match in the line
			
		
			
	    }// end while to loop through all of the lines, comparing each one with the regex expression
		
		out.close();
		
	}// end SeatsAvailible
	
	/*
	 * Count the number of unique classes offered by each program code (e.g. CSCI, RTH, 
	 * ACCT, BIOL, etc.) in Fall 21. Test your program with another academic semester 
	 * (Spring 21, Fall 20) to see if it still works with different input.
	 */
	public void UniqueClassCount() throws FileNotFoundException {
		
		 
		String classType = null;
		
		Scanner fileScan = new Scanner(new File(fileName));
		PrintWriter out = new PrintWriter("UniqueClasses.txt");
		
		HashMap<String, Integer> classCountMap = new HashMap<String, Integer>();
		
		while(fileScan.hasNext()) {
	    	
			Pattern p = Pattern.compile("\\w{2,4}\\-\\d{3}([L,H])?\\-(01)");
			Matcher m = p.matcher(fileScan.nextLine());
			
			if(m.find()) {
				
				if(m.group().charAt(0)=='B'&& m.group().charAt(1)=='U' && m.group().charAt(2)=='-'||
						m.group().charAt(0)=='C'&& m.group().charAt(1)=='S' && m.group().charAt(2)=='-'||
						m.group().charAt(0)=='P'&& m.group().charAt(1)=='S' && m.group().charAt(2)=='-'||
						m.group().charAt(0)=='P'&& m.group().charAt(1)=='T' && m.group().charAt(2)=='-') {
					classType = m.group().substring(0,2);
				}// if to handle the horrible exception case that is BU classes
				else {
					
					classType = m.group().substring(0, 4);
					
					
				}
				
				if(classCountMap.containsKey(classType)){
					
					int x = classCountMap.get(classType)+1;
					classCountMap.replace(classType, x);
					
				}// end if to increment value if the key has already been seen
				else {
					classCountMap.put(classType, 1);
				}
				
				
			}// end if to print match to the txt file if there's a match in the line
		}//out.println(m.group());
		
		for (String i : classCountMap.keySet()) {
			  out.println(i + " Number of Classes: " + classCountMap.get(i));
			}// end modified for loop
	
		
		out.close();
			
	}// end uniqueclasscount
	

	
}// end class process schedule
