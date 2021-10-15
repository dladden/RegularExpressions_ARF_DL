package two;
import java.io.*;
import java.nio.file.Path;
import java.util.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;



/**
* ExtractText class contains the main method which calls on Process Schedule to generate text files with unique.
* @author <Adam Fischer, Denys Ladden>
* @version 1.0
* Compiler Project 3
* CS322 - Compiler Construction * Fall 2021
*/

/*
 * The main class Extract Text extracts the pdf and creates a text file. Which it then sends to the 
 * ProcessSchedule to carry out he tree different searches
 */
public class ExtractText {

	public static void main(String[] args) {
		
		File file = new File("Regular Expressions/2021FA_Class_Schedule_Daily.pdf");
		String fileToSearch = "class_schedule.txt";
		
		/*
		 * This try catch takes in a a pdf sends it to the processSchedule to locate the unique classes 
		 * and seats available. Methods from processSchedule CourseInfo and SeatsAvailible called on in main
		 * which then create two txt files in the projects folder with the all unique. 
		 */
		try {
			
			
			PDDocument document = PDDocument.load(file);
			PDFTextStripper pdfStripper = new PDFTextStripper();
			
			String text = pdfStripper.getText(document);
		    
			
		    PrintWriter out = new PrintWriter(fileToSearch);
		    out.println(text);
		    
		    out.close();
		    document.close();
		    
		    ProcessSchedule prsch = new ProcessSchedule(fileToSearch);
			
			prsch.CourseInfo();
			prsch.SeatsAvailible();
			prsch.UniqueClassCount();
			
			
		} catch (IOException e) {
			System.out.println(" | FILE ERROR | ");
			e.printStackTrace();
		}// end try catch

		
		
		
		
	}// end main

}// end class ExtractText
