//Affine cipher class
public class Affine {
	//Declare key
	static String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	//declare letter maps
	final static String mapa = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
	final static String mapb = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
	final static String mapc = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
	final static String mapd = "ESOVPZJAYQUIRHXLNFTGKDCMWB";
	final static String mape = "VZBRGITYUPSDNHLXAWMJQOFECK";

	//Encryption method
	public static String encrypt(String in, String map) {
		String out = "";
		for(int i = 0; i < in.length(); i++) {
			//System.out.println(in[i].charAt(j) + 1);
			//check for spaces
			if(in.charAt(i) == ' ') {
				out += " ";
			} else {
				//Make sure all characters are upper case letters
				if(in.charAt(i) < 65 || in.charAt(i) > 90) {
					return "Invalid input; message must only contain letters. Type and enter 'help' for help menu.";
				}
				//encrypt character using letter map and add to output string
				out += (map.charAt(letters.indexOf(in.charAt(i))));
			}
		}
		//return encrypted message
		return out.substring(0, out.length());
	}
	//decryption method
	public static String decrypt(String in, String map) {
		String out = "";
		for(int i = 0; i < in.length(); i++) {
			//System.out.println(in[i].charAt(j) + 1);
			//check for spaces
			if(in.charAt(i) == ' ') {
				out += " ";
			} else {
				//Make sure all characters are upper case letters
				if(in.charAt(i) < 65 || in.charAt(i) > 90) {
					return "Invalid input; message must only contain letters. Type and enter 'help' for help menu.";
				}
				//decrypt character using letter map and add to output string
				out += (letters.charAt(map.indexOf(in.charAt(i))));
			}
		}
		//return decrypted message
		return out.substring(0, out.length());
	}
	
}
