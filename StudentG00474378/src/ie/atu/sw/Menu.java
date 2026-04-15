package ie.atu.sw;
import static java.lang.System.*;

import java.util.Scanner;


public class Menu {
private final FourSquare fs;
private Scanner s; 
private boolean keepRunning = true;
	
	public Menu(FourSquare fs) {
		this.fs = fs;
		s = new Scanner(System.in);
	}
	

	
	public void start() throws Exception {
		MenuHandler mh = new MenuHandler(fs);
		while(keepRunning) {
			showOptions();
		
			
		int choice = Integer.parseInt(s.next());
		switch (choice) {
		//option which lists all available files for encryption to user
		case 1 -> mh.fileList(s);
		//takes user input for both: File to be encrypted and the file for outputting said encryption
		case 2 -> { try {mh.createFile(s);} catch (Exception e){
						out.println("Error" + e.getMessage());
						out.println("Press *ENTER* to continue");
						s.nextLine();
						s.nextLine();} }
		
		//option to decrypt the currently encrypted output file the user has selected
		case 3 -> mh.decrypt();
		//option for winding down program
		case 4 -> {keepRunning = false;
					out.println("thanks for your time!");}
		
		default-> out.println("[Please select a valid option]");
		}
		}
	}
	
	
	private void showOptions() {
	out.println(ConsoleColour.WHITE);
	out.println("************************************************************");
	out.println("*     ATU - Dept. of Computer Science & Applied Physics    *");
	out.println("*                                                          *");
	out.println("*       Encrypting Files with a Four Square Cipher         *");
	out.println("*                                                          *");
	out.println("************************************************************");
	out.println("(1) List Text Files available for encryption");
	out.println("(2) Specify Text File to Encrypt");
	out.println("(3) Decrypt Text File");
	out.println("(4) Quit");
	
	//Output a menu of options and solicit text from the user
	out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
	out.print("Select Option [1-4]>");
	out.println();
	
	
	
	
} }
