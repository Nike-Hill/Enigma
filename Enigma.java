import java.util.*;
//Main class
public class Enigma {
	public static void main(String[]args){
		//Set up input loop
		Scanner scan = new Scanner(System.in);
		boolean quit = false;
		String in = "";
		System.out.println("Welcome to Enigma!");
		//User input loop
		while(!quit){
			//get input
		    in = scan.nextLine();
		    //process input
		    quit = processComand(in.toUpperCase());
		}
		System.out.println("Thanks for using Enigma!");
	}
	
	public static boolean processComand(String in) {
		//Create rotor array
		Rotor[] rotors = new Rotor[3];
		String temp = "";
		String encrypted = "";

		//System.out.println("In process command method");
		//check for quit
		if(in.equalsIgnoreCase("quit")) {
			return true;
		}
		
		if(!in.contains(" ")) {
			//System.out.println("Input has no spaces");
			return false;
		}

		String[] split = in.toUpperCase().split(" ");

		
		if((Rotor.romanNumerals[0].equals(split[0]) ||
		   Rotor.romanNumerals[1].equals(split[0]) ||
		   Rotor.romanNumerals[2].equals(split[0]) ||
		   Rotor.romanNumerals[3].equals(split[0]) ||
		   Rotor.romanNumerals[4].equals(split[0])) && split.length > 16) {
				
				System.out.println(enigma(split));
		} else{
			if(split[0].length() < 2) {
				return false;
			}
			//Caesar cipher encryption
			if(split[0].substring(0,2).equalsIgnoreCase(">C")) {
				//System.out.println("Identified \">C\" in input");
				//Encrypt
				System.out.println(Caesar.encrypt(split)); 
			//Caesar cipher decryption
			} else if (split[0].substring(0,2).equalsIgnoreCase("<C")) {
				//System.out.println("Identified \"<C\" in input");
				//Decrypt
				System.out.println(Caesar.decrypt(split)); 
			//Affine cipher encryption
			} if(split[0].substring(0,2).equalsIgnoreCase(">A")) {
				//System.out.println("Identified \">A\" in input");
				//Encrypt
				System.out.println(Affine.encrypt(in.substring(in.indexOf(" ")+1), Affine.mapa)); 
			//Affine cipher decryption
			} else if (split[0].substring(0,2).equalsIgnoreCase("<A")) {
				//System.out.println("Identified \"<A\" in input");
				//Decrypt
				System.out.println(Affine.decrypt(in.substring(in.indexOf(" ")+1), Affine.mapa)); 
			//Rotor cipher encryption
			} else  if(split[0].substring(0,2).equalsIgnoreCase(">R")) {
				//System.out.println("Identified \">R\" in input");
				//Encrypt
				System.out.println(Affine.encrypt(Affine.encrypt((Affine.encrypt(in.substring(in.indexOf(" ")+1), Affine.mapa)), Affine.mapb), Affine.mapc));
			//Rotor cipher decryption
			} else if (split[0].substring(0,2).equalsIgnoreCase("<R")) {
				//System.out.println("Identified \"<R\" in input");
				//Decrypt
				System.out.println(Affine.decrypt(Affine.decrypt((Affine.decrypt(in.substring(in.indexOf(" ")+1), Affine.mapc)), Affine.mapb), Affine.mapa));
			} else if(split[0].equalsIgnoreCase("decrypt") && split.length == 3)	{
				Decryptor bombe = new Decryptor(split[1], split[2]);
				bombe.decrypt();
			}
		}
		return false;
	}
	
	public static String enigma(String[] split) {
		Rotor[] rotors = new Rotor[3];
		String temp = "";
		String encrypted = "";
		
		
		rotors[0] = new Rotor(split[2], split[5]);
		rotors[1] = new Rotor(split[1], split[4]);
		rotors[2] = new Rotor(split[0], split[3]);
		
		for(int i = 16; i < split.length; i++) {
			for(int j = 0; j < split[i].length(); j++) {
				
				//System.out.println("\n" + split[i].substring(j, j+1) + "\n--------------------------------------");
				
				for(int k = 6; k < 16; k++) {
					if(split[k].contains(split[i].substring(j, j+1))) {
						split[i] = split[i].substring(0,j) + split[k].charAt(1-split[k].indexOf(split[i].charAt(j))) + split[i].substring(j+1);
						k = 16;
					}
				}
				
				if(rotors[1].atNotch()) {
					rotors[1].shift();
					rotors[2].shift();
				}
				if(rotors[0].shift()) {
						if(rotors[1].shift()) {
							rotors[2].shift();
						}
				} 
				
				
				temp = Caesar.encrypt((">C" + rotors[0].getShift() + " " + split[i].substring(j, j+1)).split(" "));
				temp = Affine.encrypt(temp, rotors[0].getMap());
				temp = Caesar.decrypt(("<C" + rotors[0].getShift() + " " + temp).split(" "));

				
				temp = Caesar.encrypt((">C" + rotors[1].getShift() + " " + temp).split(" "));
				temp = Affine.encrypt(temp, rotors[1].getMap());
				temp = Caesar.decrypt(("<C" + rotors[1].getShift() + " " + temp).split(" "));
				

				temp = Caesar.encrypt((">C" + rotors[2].getShift() + " " + temp).split(" "));
				temp = Affine.encrypt(temp, rotors[2].getMap());
				temp = Caesar.decrypt(("<C" + rotors[2].getShift() + " " + temp).split(" "));

				
				temp = Affine.encrypt(temp, Rotor.reflector);
				
				
				temp = Caesar.encrypt((">C" + rotors[2].getShift() + " " + temp).split(" "));
				temp = Affine.decrypt(temp, rotors[2].getMap());
				temp = Caesar.decrypt(("<C" + rotors[2].getShift() + " " + temp).split(" "));
				
				
				temp = Caesar.encrypt((">C" + rotors[1].getShift() + " " + temp).split(" "));
				temp = Affine.decrypt(temp, rotors[1].getMap());
				temp = Caesar.decrypt(("<C" + rotors[1].getShift() + " " + temp).split(" "));
				
				
				temp = Caesar.encrypt((">C" + rotors[0].getShift() + " " + temp).split(" "));
				temp = Affine.decrypt(temp, rotors[0].getMap());
				temp = Caesar.decrypt(("<C" + rotors[0].getShift() + " " + temp).split(" "));
				
				
				for(int k = 6; k < 16; k++) {
					if(split[k].contains("" + temp.charAt(0))) {
						temp = "" + split[k].charAt(1-split[k].indexOf(temp.charAt(0)));
						k = 16;
					}
				}
				encrypted += temp.charAt(0);
			}
			//encrypted += " ";
		}
		return encrypted;
	}
}
	
