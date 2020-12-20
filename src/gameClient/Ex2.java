package gameClient;

/**
 * this class perform game that the player chose scenario and the get all the details from the server 
 * this class has 3 frames. one - ask what the id of the player and the number of the scenario.
 * than open frame that run the game and then open small frame that ask the player if he want 
 * the server will save the result.
 * @author liadn7
 * @author aviel11
 *
 */
public class Ex2{
	
	public static String ID = "";
	public static int scenario = 0;

	public static void main(String[] args) {
		if(args.length > 0) {
			ID = args[0];
			scenario = Integer.parseInt(args[1]);
		}
		Temp temp = new Temp();
		Thread client = new Thread(temp);
		client.start();
	}
}