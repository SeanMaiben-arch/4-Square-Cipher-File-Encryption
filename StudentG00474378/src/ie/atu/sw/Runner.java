package ie.atu.sw;



public class Runner {	
	

	public static void main(String[] args) throws Exception {
		
	
		//Creates the 4 Square from boot, using method from FourSquare class
		FourSquare fs = new FourSquare();
		fs.shuffle();
		//starts menu from boot; from Menu class
		Menu m = new Menu(fs);
		m.start();
		
		}
}