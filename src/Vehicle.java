// used to create vehicles with characteristics and agendas to simulate traffic
public class Vehicle {
	String make;
	String model;
	String year;
	Address location;
	Address destination;
	DrivingStyle style;
	
	// constructor for new vehicles
	public Vehicle vehicle(String make, String model, String year, Address loc, Address dest, DrivingStyle style) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.location = loc;
		this.destination = dest;
		this.style = style;
		return this;
	}
	
	// changes driving style of the vehicle
	public void changeStyle(DrivingStyle style) {
		this.style = style;
	}
	
	// updates destination of vehicle
	public void changeDest(Address newDest) {
		this.destination = newDest;
	}
	
	// prints details of vehicle
	public String toString() {
		return (this.year + " " + this.make + " " + this.model);
	}
}