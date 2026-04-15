package ie.atu.sw;

import static java.lang.System.out;
import java.util.Scanner;
import java.io.*;

//*********the ParseFile class overlooks 3 major components***************
//1. it reads and encrypts the users chosen file
//2. it creates an output file specified by the user for the contents of the encrypted text to go inside of
//3. it then decrypts the text within the users chosen output file and then places the decrypted text into said output file; overwriting the current encrypted text contents of that file
public class ParseFile {
	
	//*************THE PARSE METHOD***********************
	
	//takes in the name of the file the user specified to encrypt
	//also takes in the name of the output file
	public void parse(String inputfile, String outputfile, FourSquare fs) throws Exception {
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(inputfile))));
	         BufferedWriter bw = new BufferedWriter(new FileWriter(outputfile))) {

	        String line;
	        while ((line = br.readLine()) != null) {

	            StringBuilder letters = new StringBuilder();

	            for (int i = 0; i < line.length(); i++) {
	                char ch = Character.toUpperCase(line.charAt(i));

	                // Only buffer letters we can encrypt
	                if (ch >= 'A' && ch <= 'Z') {
	                    if (ch == 'J') ch = 'I';
	                    letters.append(ch);
	                    continue;
	                }

	                // Non-letter encountered: flush buffered letters (encrypt), then write non-letter as-is
	                flushEncryptedPairs(letters, fs, bw);
	                bw.write(line.charAt(i)); // write original char (space/punct/digit) unchanged
	            }

	            // end-of-line flush, then newline
	            flushEncryptedPairs(letters, fs, bw);
	            bw.newLine();
	        }
	    }
	}

	private void flushEncryptedPairs(StringBuilder letters, FourSquare fs, BufferedWriter bw) throws IOException {
	    if (letters.length() == 0) return;

	    if (letters.length() % 2 != 0) letters.append('X');

	    for (int i = 0; i < letters.length(); i += 2) {
	        bw.write(fs.getEncryptedPair(letters.charAt(i), letters.charAt(i + 1)));
	    }
	    letters.setLength(0);
	} 

	
	
//********************CREATE OUTPUT FILE METHOD*************************************	

//this uses the prompt from the createFile method within the MenuHandler class to create an output file with the specified name the user provides
public String outputFile(String name){	
	
	//if the name is left blank, throw an error
			if(name == null) {
			throw new IllegalArgumentException("The filename cannot be left empty");
			}
			
			String filename = (name.trim()+".txt");
		//create a file using the specified name called newFile	
			 try {
				    File newFile = new File(filename); // Create File object
				 
				   //if successful do the following 
				      if (newFile.createNewFile()) { 
				    	  out.println("File has been created :" + newFile.getName());
				    	   
				      }
			//if unsuccessful, do the following
				       else {
				        out.println("File already exists :" + newFile.getName());
				      } 
				      return newFile.getPath();
				  //if file creation fails for an error, output the following and wait for user to press enter to proceed    
			 } catch (Exception e){
				 try (Scanner miniScanner = new Scanner(System.in)) {
					out.println("Error, the file creation failed " + e.getMessage());
					out.println("Press *ENTER* to continue");
					miniScanner.nextLine();
					miniScanner.nextLine();
				 }
					return null;} } 



//****************************DECRYPTION METHOD*********************************************

//in the MenuHandler class, within the CreateFile method; the users specified output file name is given the variable name "filePath" 

public void createDecryptedFile(String filePath, FourSquare fs) throws Exception {
    StringBuilder output = new StringBuilder();
//we read the encrypted file line by line
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
//we then convert each line to uppercase
        while ((line = br.readLine()) != null) {
            StringBuilder letters = new StringBuilder();
            line = line.toUpperCase();

            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);
//as long as the character is an uppercase English letter from A-Z we append it to the previous ith element of the for loop
                if (ch >= 'A' && ch <= 'Z') {
                    letters.append(ch);
//or else; we flush and append to the output StringBuilder created at the top
                } else {
                    // flush decrypted letters, then keep the non-letter
                    flushDecryptedPairs(letters, fs, output);
                    output.append(ch);
                }
            }

            // end-of-line flush + newline
            flushDecryptedPairs(letters, fs, output);
            output.append('\n');
        }
    }
//open the original output file which currently has the encrypted text within it and overwrite it
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))) {
        bw.write(output.toString());
    }
}


 
private void flushDecryptedPairs(StringBuilder letters, FourSquare fs, StringBuilder out) {
    if (letters.length() == 0) return;
//enforces length for the bigrams
    if (letters.length() % 2 != 0) {
        throw new IllegalStateException("CipherText has odd length; decryption has failed");
    }
//reads the ciphertext 2 letters at a time (bigrams)
    //then calls the getDecryptedPair method wthin the FourSquare class on each loop
    	//appends the output from the getDecryptedPair method onto the Stringbuilder 'out'
    for (int i = 0; i < letters.length(); i += 2) {
        out.append(fs.getDecryptedPair(letters.charAt(i), letters.charAt(i + 1)));
    }
//clears the letters buffer to prepare for next run of cipher letters
    letters.setLength(0);
		}
			}

									
							
		
			 
	
