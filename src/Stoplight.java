import java.util.ArrayList;

// stoplights
public class Stoplight {
	int EWStreet; // swapped to int from string for simplicity
	int NSStreet; // TODO: add lookup later for conversion
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
	
	// returns location of string stoplight
	public String toString() {
		// TODO: make actual address after adding lookup
		return (Integer.toString(this.EWStreet) + " " + Integer.toString(this.NSStreet));
	}
	
	// determines what color of light should be based on durations of colors and timer
	// TODO: make light colors specific to EW NS
	public void determineLight(int timePassed) {
		double timeReduced = timePassed % (this.greenDuration + this.yellowDuration + this.redDuration);
		LightColor firstColor = this.startingColor;
		LightColor secondColor = this.startingColor;
		LightColor thirdColor = this.startingColor;
		double firstDuration = 0.0;
		double secondDuration = 0.0;
		double thirdDuration = 0.0;
		// need to figure out color and duration order
		switch(firstColor) {
			case GREEN :
				secondColor = LightColor.YELLOW;
				thirdColor = LightColor.RED;
				firstDuration = this.greenDuration;	
				secondDuration = this.yellowDuration;
				thirdDuration = this.redDuration;
			case YELLOW :
				secondColor = LightColor.RED;
				thirdColor = LightColor.GREEN;
				firstDuration = this.yellowDuration;
				secondDuration = this.redDuration;
				thirdDuration = this.greenDuration;
			default :
				secondColor = LightColor.GREEN;
				thirdColor = LightColor.YELLOW;
				firstDuration = this.redDuration;
				secondDuration = this.greenDuration;
				thirdDuration = this.yellowDuration;
		}
		// into second duration
		if(timeReduced > firstDuration && timeReduced <= firstDuration + secondDuration) {
			this.currentColor = secondColor;
		}
		// into third duration
		else if(timeReduced > firstDuration + secondDuration) {
			this.currentColor = thirdColor;
		}
		// in first duration
		else {
			this.currentColor = firstColor;
		}
	}
}