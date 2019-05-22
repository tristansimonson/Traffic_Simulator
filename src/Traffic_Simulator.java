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
	public static void run(ArrayList<Vehicle> v, ArrayList<Stoplight> s, int[] map, int timer) {
		// if vehicle list empty or timer ran out return
		if(v.isEmpty() || timer == 0) {
			return;
		}
		// go through list and execute move for each vehicle
		for(int i = 0; i < v.size(); i++) {
			// check if there is a stoplight at the node
			// TODO: check if vehicle is in a queue
			// TODO: should move go here?
			for(int j = 0; j < s.size(); j++) {
				// car has arrived at stoplight
				if (s.get(j).EWStreet == v.get(i).location.street1 && s.get(j).NSStreet == v.get(i).location.street2) {
					// check if light is green or yellow
					// TODO: need to add behavior for driver types later
					// TODO: need to make moves in order of stoplight queue
					if(s.get(j).color != LightColor.GREEN) {
					}
					if(s.get(j).color == LightColor.RED) {
						// if light is red car will be added to queue of light
						s.get(j).queue.add(v.get(i));
					}
				}
				// no stoplight at vehicle location 
				else {
					// TODO: should I do a move here for recursive call?
				}
			}
			// if car reaches destination then remove from list
			if(v.get(i).location == v.get(i).destination) {
				v.remove(i);
			}
		}
		// pass updated timer
		run(v, s, map, timer - 1);
	}
	
	// decide direction for car to go
	// # # # #
	// # x # # ex address 2 3 
	// # # # #
	// # # # #
	public static boolean move(Vehicle v, int[] map) {
		// check location of vehicle and compare it to destination, depending on cardinal direction have them move respectively
		Address target = v.destination;
		Address loc = v.location;
		boolean ret = true;
		if(loc.street1 != target.street1) {
			if(loc.street1 > target.street1) {
				v.location.street1 -= 1;
				ret = false;
			}
			else {
				v.location.street1 += 1;
				ret = false;
			}
		}
		else if(loc.street2 != target.street2) {
			if(loc.street2 > target.street2) {
				v.location.street2 -= 1;
				ret = false;
			}
			else {
				v.location.street2 += 1;
				ret = false;
			}
		}
		if(v.location.street1 > map[0] || v.location.street2 > map[1] || v.location.street1 < 0) {
			System.out.println("Out of bounds for map");
		}
		if(ret == true) {
			System.out.println("Vehicle has arrived at destination");
		}
		return ret;
	}
}