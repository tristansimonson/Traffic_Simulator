import java.util.ArrayList;

// used to create vehicles with characteristics and agendas to simulate traffic
public class Vehicle {
	String make;
	String model;
	String year;
	Address location;
	Address destination;
	ArrayList<String> routeHistory = new ArrayList<String> ();
	// TODO: add specified route or class to generate routes based on traffic queues
	DrivingStyle style;
	
	// constructor for new vehicles
	public Vehicle vehicle(String make, String model, String year, Address loc, Address dest, DrivingStyle style) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.location = loc;
		this.destination = dest;
		//this.routeHistory.add(loc);
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
	
	// decides direction for car to go
	// # # # #
	// # x # # ex address 2 3 
	// # # # # 
	// # # # #
	public boolean move(int[] map) {
		// check location of vehicle and compare it to destination, move depending on direction
		Address target = this.destination;
		Address loc = this.location;
		boolean ret = true;
		if(loc.street1 != target.street1) {
			if(loc.street1 > target.street1) {
				this.location.street1 -= 1;
				ret = false;
			}
			else {
				this.location.street1 += 1;
				ret = false;
			}
		}
		else if(loc.street2 != target.street2) {
			if(loc.street2 > target.street2) {
				this.location.street2 -= 1;
				ret = false;
			}
		else {
			this.location.street2 += 1;
				ret = false;
			}
		}
		if(this.location.street1 > map[0] || this.location.street2 > map[1] || this.location.street1 < 0) {
			System.out.println("Out of bounds for map");
		}
		if(ret == true) {
			System.out.println("Vehicle has arrived at destination:");
			System.out.println("    " + this.toString() + " with route history:");
			for(int i = 0; i < this.routeHistory.size(); i++) {
				System.out.println("    " + "    " + this.routeHistory.get(i).toString());
			}
			return ret;
		}
		this.routeHistory.add(this.location.toString());
		return ret;
	}
}