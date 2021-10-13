package Part3;
import java.io.*;
import java.util.*;
import java.util.regex.*;
/**
 * 
 * This class allows a user to input a .csv file containing IP addresses and usernames and returns these addresses and names based
 * on regular expressions
 * 
 * @author Adam Fischer, Denys Ladden
 * @version 1.0
 * Compilers Project 3
 * 322 - Compiler Construction
 * Fall 2021
 *
 */
public class LogFileProcessor {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		
		int lineNumber=0, uniqueIP=0, uniqueUser=0, x=0;// variables for the output
		
		String regexIP = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";// regex expressions for IP addresses + usernames
		String regexUsers = "(user )\\w+";
		
		Scanner input = new Scanner(System.in);
		
		HashMap<String, Integer> users = new HashMap<String, Integer>();// hashmaps for the usernames / IP addresses
		HashMap<String, Integer> IPs = new HashMap<String, Integer>();
		
		System.out.print("Input the log file (must be .csv) that you wish to process:");
		String file = input.next();
		System.out.println();
		
		BufferedReader csvreader = new BufferedReader(new FileReader(file));// begins the process of converting csv to txt
		String line = "";
		
		String[] csvstuff = new String[1000000];
		
		FileWriter writer = new FileWriter("CSVoutput.txt");
		
		while ((line = csvreader.readLine()) != null) {
			csvstuff = line.split(",");
			// User for loop to iterate String Array and write data to text file
			for (String str : csvstuff) {
				writer.write(str + " ");
			}// end for loop to write to the .txt file from that line
			// Write each line of CSV file to multiple lines
			writer.write("\n");
		}// end while to read through the .csv file
		writer.close();
		
		String currentLine = null;
		BufferedReader txtReader ;
		txtReader= new BufferedReader(new FileReader("CSVoutput.txt"));// csv now in txt format, able to be used
		
		
	
		while((currentLine = txtReader.readLine())!= null) {// buffered reader goes through the txt file, takes one line as a string
			
			lineNumber++;
			
			Pattern p1 = Pattern.compile(regexIP);
			Matcher m1 = p1.matcher(currentLine);
			
			if(m1.find()) {// will look for matches for pattern one in the string
				
				String match = m1.group();
				
				if(IPs.containsKey(match)){// increments matches that are already in the hashmap
					int y = IPs.get(match) + 1;
					IPs.replace(match, IPs.get(match), y);
					
				}//end if for repeat entry
				else {
					
					IPs.put(match, 1);// adds new matches to the hashmap
					
				}// end else for new entry
			}// end if for if the regex works (for the IP addresses)
			
			Pattern p2 = Pattern.compile(regexUsers);
			Matcher m2 = p2.matcher(currentLine);
			
			if(m2.find()) {// looks for matches for the second pattern (usernames)
				
				String match = m2.group();
				match = match.substring(5);// the regex expression finds user ____ to get the usernames, so this takes the "user " out
				
				if(users.containsKey(match)){//  increments existing matches
					int y = users.get(match) + 1;
					users.replace(match, users.get(match), y);
					
				}//end if for repeat entry
				else {
					
					users.put(match, 1);// adds new matches
					
				}// end else for new entry
			}// end if for if the regex works (for the usernames)
			
		}// end while loop to scan through log file
		
		
		System.out.println("Press 0 for the number of lines, unique IP addresses, and unique users.");
		System.out.println("Press 1 to list the unique IP addresses and number of visits for each.");
		System.out.println("Press 2 to list the unique users and number of visits for each.");
		System.out.print("Which would you like: ");x = input.nextInt();
		System.out.println();
		
		uniqueIP = IPs.size();
		uniqueUser = users.size();
		
		switch(x) {// takes in the second argument and delivers the correct output
		
			case 0:
				
				System.out.println("\n"+lineNumber +" lines in the log file were parsed.");
				System.out.println("There are "+uniqueIP+" unique IP addresses in the log");
				System.out.println("There are "+uniqueUser+" unique users in the log");
				
				break;
			case 1:
				
				for (String i : IPs.keySet()) {
					  System.out.println(i + ": " + IPs.get(i));// prints all keys and values
				}// end modified for loop
				
				System.out.println("\n"+lineNumber +" lines in the log file were parsed.");
				System.out.println("There are "+uniqueIP+" unique IP addresses in the log");
				System.out.println("There are "+uniqueUser+" unique users in the log");
				
				break;
			case 2:
				
				for (String i : users.keySet()) {
					  System.out.println(i + ": " + users.get(i));// prints all keys and values
				}// end modified for loop
				
				System.out.println("\n"+lineNumber +" lines in the log file were parsed.");
				System.out.println("There are "+uniqueIP+" unique IP addresses in the log");
				System.out.println("There are "+uniqueUser+" unique users in the log");
				
				break;
			default:// same as 0 for any other entries
				
				System.out.println("\n"+lineNumber +" lines in the log file were parsed.");
				System.out.println("There are "+uniqueIP+" unique IP addresses in the log");
				System.out.println("There are "+uniqueUser+" unique users in the log");
				
		}// end switch statement
		

	}// end main

}// end class LogFileProcessor
