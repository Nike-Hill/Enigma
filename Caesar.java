//Caesar cipher class
public class Caesar{
	public static String encrypt(String[] in) {
		int shift = 1;
		String out = "";
		//System.out.println("In Caesar encryption method");
		if(in[0].length()>2) {
		    try {
		    	shift = (int) Double.parseDouble(in[0].substring(2));
		    } catch (NumberFormatException exc) {
			    return "Invalid shift; shift amount must be a number. Type and enter 'help' for help menu.";
		    }
		}
		for(int i = 1; i < in.length; i++) {
			for(int j = 0; j < in[i].length(); j++) {
				//System.out.println(in[i].charAt(j) + 1);
				if(in[i].charAt(j)!= ' ') {
					if(in[i].charAt(j) < 65 || in[i].charAt(j) > 90) {
						return "Invalid input; message must only contain letters. Type and enter 'help' for help menu.";
					}
					out += (char)((in[i].charAt(j) - 65 + shift)%26 +65);
				}

			}
			out += " ";
		}
		return out.substring(0, out.length()-1);
	}

	public static String decrypt(String[] in) {
		int shift = 1;
		String out = "";
		//System.out.println("In Caesar decryption method");
		if(in[0].length()>2) {
		    try {
		    	shift = (int) Double.parseDouble(in[0].substring(2));
		    } catch (NumberFormatException exc) {
			    return "Invalid shift; shift amount must be a number. Type and enter 'help' for help menu.";
		    }
		}
		for(int i = 1; i < in.length; i++) {
			for(int j = 0; j < in[i].length(); j++) {
				//System.out.println(in[i].charAt(j) -1);
				if(in[i].charAt(j) < 65 || in[i].charAt(j) > 90) {
					return "Invalid input; message must only contain letters. Type and enter 'help' for help menu.";
				}
				while((in[i].charAt(j) - 39 - shift) < 0) {
					shift -= 26;
				}
				out += (char)((in[i].charAt(j) - 39 - shift)%26 + 65);
			}
			out += " ";
		}
		return out.substring(0, out.length());
	}
	
}
