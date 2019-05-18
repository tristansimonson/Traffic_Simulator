// used to create vehicles with characteristics and agendas to simulate traffic
public class Vehicle {
	String make;
	String model;
	String year;
	String destination;
	DrivingStyle style;
	
	// constructor for new vehicles
	public Vehicle vehicle(String make, String model, String year, String dest, DrivingStyle style) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.destination = dest;    // should make destination its own class
		this.style = style;
		return this;
	}
	
	// changes driving style of the vehicle
	public void changeStyle(DrivingStyle style) {
		this.style = style;
	}
	
	// updates destination of vehicle
	public void changeDest(String newDest) {
		this.destination = newDest;
	}
}