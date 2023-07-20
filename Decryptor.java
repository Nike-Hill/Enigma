import java.util.*;

public class Decryptor implements Runnable {
	private String key;
	public int count = 0;
	private String encrypted;
	private ArrayList<String> deductions;
	private ArrayList<Integer> deducePosition;
	
	public Decryptor(String key, String encrypted) {
		this.key = key;
		this.encrypted = encrypted;
	}
	
	public void decrypt() {
		int count = 1;
		String current = "";
		String[] split = new String[17];
		split[6] = "AA";
		split[7] = "BB";
		split[8] = "CC";
		split[9] = "DD";
		split[10] = "EE";
		split[11] = "FF";
		split[12] = "GG";
		split[13] = "HH";
		split[14] = "II";
		split[15] = "JJ";
		split[16] = encrypted;
		
		deducePosition = new ArrayList<Integer>();
		this.keyPos();
		for(int i : deducePosition) {
			System.out.print(i + " ");
		}
		System.out.println(deducePosition.size() + "/" + encrypted.length());
		for(int i = 0; i < 5; i++) {
			split[0] = Rotor.romanNumerals[i];
			for(int j = 0; j < 5; j++) {
				split[1] = Rotor.romanNumerals[j];
				for(int k = 0; k < 5; k++) {
					split[2] = Rotor.romanNumerals[k];
					for(int l = 0; l < 26; l++) {
						split[3] = "" + ((char)(l+65));
						for(int m = 0; m < 26; m++) {
							split[4] = "" + ((char)(m+65));
							for(int n = 0; n < 26; n++) {
								split[5] = "" + ((char)(n+65));
								current = Enigma.enigma(split);
								System.out.print("Command: ");
								for(String s : split) {
									System.out.print(s + " "); 
								}
								System.out.println(" Message:" + current);
								if(current.toUpperCase().contains(key)) {
									System.out.print(count + ". " + " Command: ");
									for(String s : split) {
										System.out.println(split + " "); 
									}
									System.out.println(" Message:" + current);
									count++;
								}
							}
						}
					}
				}
			}
		}

	}
	
	public void keyPos() {
		for(int i = 0; i <= encrypted.length()-key.length(); i++) {
			for(int j = i; j < i + key.length(); j++) {
				if(encrypted.charAt(j) == key.charAt(j-i)) {
					j = i + key.length();
				} else if(j == (i+key.length()-1)) {
					deducePosition.add(i);
					System.out.println(i);
				}
			}
		}
	}
	
	public String deducePlugboard() {
		char keyChar;
		char phraseChar;
		for(int i : deducePosition) {
			for(int j = i; j < i + key.length(); j++) {
				Deduction d = new Deduction(key.charAt(j-i), encrypted.charAt(j)); 
			}
		}
		return "";
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
