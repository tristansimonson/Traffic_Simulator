// stoplights
public class Stoplight {
	String EWStreet;
	String NSStreet;
	double greenDuration;
	double yellowDuration;
	double redDuration;
	LightColor color;
	
	// stoplight constructor
	public Stoplight stoplight(String EW, String NS, double green, double yellow, double red, LightColor color) {
		this.EWStreet = EW;
		this.NSStreet = NS;
		this.greenDuration = green;
		this.yellowDuration = yellow;
		this.redDuration = red;
		this.color = color;
		return this;
	}
	
	// changes the color of the light
	public void changeLight(String color) {
		if (color == "red" || color == "RED" || color == "Red") {
			this.color = LightColor.RED;
		}
		if (color == "yellow" || color == "YELLOW" || color == "Yellow") {
			this.color = LightColor.YELLOW;
		}
		else {
			this.color = LightColor.GREEN;
		}
	}
	
	// change all durations of light
	public void setDurations(double red, double yellow, double green) {
		this.redDuration = red;
		this.yellowDuration = yellow;
		this.greenDuration = green;
	}
	
	// change specified color of light
	public void changeDuration(String color, float duration) {
		if (color == "red" || color == "RED" || color == "Red") {
			this.redDuration = duration;
		}
		if (color == "yellow" || color == "YELLOW" || color == "Yellow") {
			this.yellowDuration = duration;
		}
		else {
			this.greenDuration = duration;
		}
	}
	
	// change address of light incase of possible error
	public void changeAddress(String NS, String EW) {
		this.NSStreet = NS;
		this.EWStreet = EW;
	}
}