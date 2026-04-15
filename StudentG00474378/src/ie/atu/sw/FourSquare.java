package ie.atu.sw;
import java.util.Random;



public class FourSquare {
	char [] keytext = null;

	
	private static char[][] square = new char[10][10];

	
	
	//method for shuffling algorithm 
	private void fisherYates(char[] arr) {
	    Random r = new Random();
	    for (int i = arr.length - 1; i > 0; i--) {
	        int j = r.nextInt(i + 1);
	        char tmp = arr[i];
	        arr[i] = arr[j];
	        arr[j] = tmp;
	    }
	}
	
//method for shuffling ciphertext arrays	
public void shuffle() {
	
	 char[] alphabet25 = {
		        'A','B','C','D','E','F','G','H','I','K','L',
		        'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
		    };
	 //clones the above array so 2 arrays of 25 chars are created
	    char[] tr = alphabet25.clone();
	    char[] bl = alphabet25.clone();
	//activates the fy algorithm on each array 
	    fisherYates(bl);
	    fisherYates(tr);
	//passes each array to createCipherQ method    
	   createCipherQ(bl, tr);
	    
	

	}

	
	
	//This creates the top right and bottom left quadrants of the four square
	//it uses the output from the shuffle method as the input for the quadrants
	//it then prompts the creation of the other quadrants by calling the createPlainQ() method
	public char[][] createCipherQ(char[] bottomLeft, char[] topRight) {
		
		int index = 0;
		for(int row=5; row < square.length; row++) {
			 	for(int col=0; col < 5; col++) {
			 		square[row][col] = bottomLeft[index];
			 		index++; }
			 	}
		index = 0;
		
		for(int row=0; row < 5; row++) {
			for(int col=5; col < square[5].length; col++) {
				square[row][col] = topRight[index];
				index++;
			}
		}
		createPlainQ();
		return square;
		} 
	
	
		//this creates the top left and bottom right quadrants of the four square
	    //it uses a created char array which holds the letters of the alphabet
		//for loops are used to pull chars from the array and store them 

	public char[][] createPlainQ(){
		char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L'
		        ,'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	int index =0;
		
	for(int row=0; row < 5; row++) {
		for(int col=0; col < 5; col++) {
			square[row][col] = alphabet[index];
			index++;	
		} }
		index =0;
		for(int row=5; row<square.length; row++) {
			for(int col=5; col<square.length; col++) {
				square[row][col] =alphabet[index];
				index++;
		}	}
		return square;
	}
	
	
	
	//method for deciding if plaintext is to be encrypted
	public String encryption(String plaintext) {
		return doCipher(plaintext, true) ;
	}
	//method for deciding if cipherText is to be encrypted
	public String decryption (String cipherText) {
		return doCipher(cipherText, false) ; }
	
	
		//METHOD FOR PREFORMING CIPHER
	private String doCipher (String s, boolean encrypt) {
		if(s==null) return "";
			
			StringBuilder sb = new StringBuilder();
			s = s.toUpperCase();
			
			for (int i = 0; i<s.length(); i++) {
			char ch = s.charAt(i);
		//change all instances of the letter 'J' and make it equivalent to the letter 'I'	
			if(ch == 'J') ch = 'I';
			sb.append(ch);
			//pads the length if a bigram pair is too short
			} if(sb.length() % 2 != 0) sb.append('X');
			//new Stringbuilder called 'out' is created
			StringBuilder out = new StringBuilder(sb.length());
			for(int i=0; i<sb.length(); i+=2) {
				char a = sb.charAt(i);
				char b =sb.charAt(i +1);
				
			String pair = encrypt ? getEncryptedPair(a, b) : getDecryptedPair(a, b);
			//after the encryption or decryption method is ran; the output is appended to the stringbuilder
			out.append(pair);
			} return out.toString();
		}
		

//*****************GET ENCRYPTED BIGRAMS****************************************
	
	//this method takes in a plaintext bigram pair, 2 chars
	public String getEncryptedPair(char plainOne, char plainTwo) {
	//a for loop searches the top left quadrant
		for(int tLrow=0; tLrow < 5; tLrow++)
			for(int tLcol=0; tLcol <5; tLcol++)
	//if the plaintext char is found in the top left quadrant, then we search the bottom right quadrant for the next plaintext char			
		if(square[tLrow][tLcol] == plainOne){
			
			for(int bRrow=5; bRrow < 10; bRrow++)
				for(int bRcol=5; bRcol <10; bRcol++)
		//once we find the bigram pair we then encrypt them to the corresponding ciphertext quadrants			
				if(square[bRrow][bRcol] == plainTwo) {
		//so plain char1 which was found in the topleft quadrant, is then encrypted to the row we found the plain1 at and the col we found plain2 at
				char cipher1 = square[tLrow][bRcol];
		//the same logic is repeated for the second plain char; it is converted to the ciphertext char at the: row we found plain2 and the col we found plain1
				char cipher2 = square[bRrow][tLcol];
					return "" + cipher1 + cipher2;
				}
		}
	throw new IllegalArgumentException("Character was not found" + plainOne + "," + plainTwo);
	}
	
	
//**************************************GET DECRYPTED BIGRAMS*****************************************	
	
	
	public String getDecryptedPair(char ecrypt1, char ecrypt2) {
		for(int tRrow=0; tRrow < 5; tRrow++)
			for(int tRcol=5; tRcol <10; tRcol++)
				
		if(square[tRrow][tRcol] == ecrypt1){
			
			for(int bLrow=5; bLrow < 10; bLrow++)
				for(int bLcol=0; bLcol <5; bLcol++)
					
				if(square[bLrow][bLcol] == ecrypt2) {
				char dcrypt1 = square[tRrow][bLcol];
				char dcrypt2 = square[bLrow][tRcol];
					return "" + dcrypt1 + dcrypt2;
				}
		}
		throw new IllegalArgumentException("Character was not found" + ecrypt1 + "," + ecrypt2);
	} } 
		
		
		
		

	
	

