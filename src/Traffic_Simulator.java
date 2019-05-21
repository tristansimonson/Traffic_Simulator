import java.util.ArrayList;
import java.util.List;

public class Traffic_Simulator {
	
	// main class
	public static void main (String[] args) {
		// list of vehicles
		ArrayList vehicles = new ArrayList<Vehicle>();
		// list of stoplights
		ArrayList stoplights = new ArrayList<Stoplight>();
		// map dimensions
		int[] map = {5, 5};
		// how long you want simulation to run for
		int timer = 20;
		
		Stoplight s1 = new Stoplight();
		s1 = s1.stoplight(1, 2, 5.0, 5.0, 5.0, LightColor.GREEN);
		Address loc = new Address();
		loc = loc.address(1, 1);
		Address dest = new Address();
		dest = dest.address(2, 2);
		Vehicle v1 = new Vehicle();
		v1 = v1.vehicle("Chevy", "Volt", "2015", loc, dest, DrivingStyle.AVERAGE);
		
		run(vehicles, stoplights, map, timer);
	}
	
	// runs simulation
	public static void run(ArrayList v, ArrayList s, int[] map, int timer) {
		// go through list and execute move for each vehicle
		// check if there is a stoplight at the node
		// check if light is green or yellow
		// if light is red car will be added to queue of light
		// if car reaches destination then remove from list
		// if vehicle list empty return
	}
	
	// decide direction for car to go
	// # # # #
	// # x # # ex address 2 3 
	// # # # #
	// # # # #
	public static boolean move(Vehicle v) {
		// check location of vehicle and compare it to destination, depending on cardinal direction have them move respectively
		Address target = v.destination;
		Address loc = v.location;
		if(loc.street1 != target.street1) {
			if(loc.street1 > target.street1) {
				v.location.street1 -= 1;
				return false;
			}
			else {
				v.location.street1 += 1;
				return false;
			}
		}
		if(loc.street2 != target.street2) {
			if(loc.street2 > target.street2) {
				v.location.street2 -= 1;
				return false;
			}
			else {
				v.location.street2 += 1;
				return false;
			}
		}
		System.out.println("Vehicle has arrived at destination");
		return true;
	}
}