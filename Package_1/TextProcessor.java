package Part_1;
import java.io.*;
import java.util.Scanner;
import java.util.regex.*;

/**
 * 
 * This class prompts the user for a .txt file and a regex pattern and returns all of the instances of that pattern
 * along with a count of its instances.
 * 
 * @author Adam Fischer, Denys Ladden
 * @version 1.0
 * Compiler Project 3
 * CS 322 - Compiler Construction
 * Fall 2021
 * 
 */
public class TextProcessor {
	
	// Regex patterns
	// (\ [Aa][n]?\ )|(\ [Tt](he ))
	// \w+( Transylvania)
	// ( Mina Harker )|( Mrs. Harker )
	//  \ [Tt][o]\ \w+
	// \ (?!Helsing)(?!Godalming)\w+(ing) 
	
	
	/*
	 * In the class Text Processor we create two scanners one for the user input and another to 
	 * iterate through the text file. User is prompted to enter the file they would like to scan
	 * which must be in the directory of the project. The users regex is then scanned using a scanner 
	 * line by line and matches the regex then it out prints. Finally output counts the total occurrences.
	 */
	public static void main(String[] args) throws IOException {
		
		String regexInput =null, file=null;
		Scanner scan = new Scanner(System.in);
		int output=0;
		
		System.out.print("Enter the file you wish to process: ");
		file = scan.nextLine();
		System.out.println();
		System.out.print("Enter your regex pattern: ");
		regexInput = scan.nextLine();
		System.out.println();
		
		Scanner fileScan = new Scanner(new File(file));
		while(fileScan.hasNext()) {
			
			Pattern p = Pattern.compile(regexInput);
			Matcher m = p.matcher(fileScan.nextLine());
			
			if(m.find()) {
				output++;
				System.out.println(m.group());
			}
			
		}// end while loop to go through the text file line by line looking for the pattern given
		System.out.println();
		System.out.println("There were " + output + " occurances of that pattern");

	}// end main

}// end class text processor
