package ie.atu.sw;
import java.io.File;
import java.util.Scanner;

import static java.lang.System.*;

public class MenuHandler {
private String lastEncryptedPath;
private final FourSquare fs;

public MenuHandler(FourSquare fs)
{ this.fs = fs;
}
//creates a list display of all the possible files available for encryption
	public void fileList(Scanner s) {
		File dir = new  File("txtfiles");
		if(!dir.exists()|| !dir.isDirectory()) {
			out.println("txtfiles directory not found");
			out.println("press *ENTER* to continue");
			s.nextLine();
			s.nextLine();
			return;
						}
		for (String name : dir.list()) {
			out.println(name);
		}
		out.println("press *ENTER* to continue");
		s.nextLine();
		s.nextLine();
	}
		 
	
		//takes user input for file to encrypt and then attributes that to the variable fileName
		
	public void createFile(Scanner s) throws Exception {
	    out.print("Please specify the file to encrypt (e.g. BibleGod.txt or txtfiles/BibleGod.txt): ");
	    String fileName = s.next().trim();

	    // If user typed only a filename, assume it is inside ./txtfiles
	    String inputPath = (fileName.contains("/") || fileName.contains("\\"))
	            ? fileName
	            : "txtfiles/" + fileName;

	    //auto-add .txt if user omitted it
	    if (!inputPath.toLowerCase().endsWith(".txt")) inputPath += ".txt";

	    out.print("What file would you like the output to go on? ");
	    String name = s.next().trim();

	    ParseFile p = new ParseFile();
	    String outputPath = p.outputFile(name);

	    p.parse(inputPath, outputPath, fs);

	    lastEncryptedPath = outputPath;
	}

	
	//the decryption method uses the outputfile to place the completed decryption inside of' overwriting the current files contents to prevent files that are huge in size
	public void decrypt() throws Exception {
		if (lastEncryptedPath == null) {
			throw new Exception("No Encrypted file available");
		}
		ParseFile p = new ParseFile();
		p.createDecryptedFile(lastEncryptedPath, fs);
	}
	


}


