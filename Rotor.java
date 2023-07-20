
public class Rotor {
	private String map;
	private int shift;
	private char notch;
	final static String reflector = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
	public static final String[] romanNumerals = {"I", "II", "III", "IV", "V"};
	public Rotor(String roman, String shiftLetter) {
		if(roman.equalsIgnoreCase("I")) {
			setMap(Affine.mapa);
			setNotch('Q');
		} else if(roman.equalsIgnoreCase("II")) {
			setMap(Affine.mapb);
			setNotch('E');
		} else if(roman.equalsIgnoreCase("III")) {
			setMap(Affine.mapc);
			setNotch('V');
		} else if(roman.equalsIgnoreCase("IV")) {
			setMap(Affine.mapd);
			setNotch('J');
		} else if(roman.equalsIgnoreCase("V")) {
			setMap(Affine.mape);
			setNotch('Z');
		}
		if(shiftLetter.charAt(0) > 90) {
			shiftLetter = "Z";
		} else if(shiftLetter.charAt(0) < 65) {
			shiftLetter = "A";
		}
		shift = shiftLetter.charAt(0) - 65;
	}
	
	public boolean shift() {
		final boolean atNotch = ((int)notch-65 == shift);		
		shift = Caesar.encrypt((">C" + " " + (char)(shift + 65)).split(" ")).charAt(0)-65;
		return atNotch;
	}
	
	
	public String getMap() {
		return map;
	}
	public void setMap(String map) {	
		this.map = map;
	}
	public int getShift() {
		return shift;
	}
	public char getShift(char c) {
		return (char) (shift+65);
	}
	public boolean atNotch() {
		return ((int)notch-65 == shift);
	}
	public void setNotch(char notch) {
		this.notch = notch;
	}
	
}