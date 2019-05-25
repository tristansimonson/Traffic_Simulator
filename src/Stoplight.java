import java.util.ArrayList;

// stoplights
public class Stoplight {
	int EWStreet; // swapped to int from string for simplicity
	int NSStreet; // can add lookup later for conversion
	double greenDuration;
	double yellowDuration;
	double redDuration;
	ArrayList queue;
	LightColor startingColor;
	LightColor currentColor;
	
	// stoplight constructor
	public Stoplight stoplight(int EW, int NS, double green, double yellow, double red, LightColor color) {
		this.EWStreet = EW;
		this.NSStreet = NS;
		this.greenDuration = green;
		this.yellowDuration = yellow;
		this.redDuration = red;
		this.startingColor = color;
		this.currentColor = color;
		this.queue = new ArrayList<Vehicle>();
		return this;
	}
	
	// changes the color of the light
	public void changeLight(String color) {
		if (color == "red" || color == "RED" || color == "Red") {
			this.currentColor = LightColor.RED;
		}
		if (color == "yellow" || color == "YELLOW" || color == "Yellow") {
			this.currentColor = LightColor.YELLOW;
		}
		else {
			this.currentColor = LightColor.GREEN;
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
	public void changeAddress(int NS, int EW) {
		this.NSStreet = NS;
		this.EWStreet = EW;
	}
	
	// add vehicle to queue
	public void addVehicle(Vehicle v) {
		this.queue.add(v);
	}
	
	// remove vehicle from queue
	public void removeVehicle(Vehicle v) {
		this.queue.remove(v);
	}
}