import java.util.ArrayList;

public class Traffic_Simulator {
	
	// main class
	public static void main (String[] args) {
		// list of vehicles
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		// list of stoplights
		ArrayList<Stoplight> stoplights = new ArrayList<Stoplight>();
		// map dimensions
		int[] map = {5, 5};
		// how many time units simulation runs for
		int timer = 20;
		
		Stoplight s1 = new Stoplight();
		s1 = s1.stoplight(1, 2, 1.0, 1.0, 1.0, LightColor.GREEN);
		Stoplight s2 = new Stoplight();
		s2 = s2.stoplight(2, 1, 5.0, 5.0, 5.0, LightColor.GREEN);
		Stoplight s3 = new Stoplight();
		s3 = s3.stoplight(3, 2, 5.0, 5.0, 5.0, LightColor.GREEN);
		Address loc = new Address();
		loc = loc.address(1, 1);
		Address dest = new Address();
		dest = dest.address(5, 5);
		Vehicle v1 = new Vehicle();
		v1 = v1.vehicle("Chevy", "Volt", "2015", loc, dest, DrivingStyle.AVERAGE);
		stoplights.add(s1);
		stoplights.add(s2);
		stoplights.add(s3);
		vehicles.add(v1);
		
		System.out.println("Running sim...");
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
			boolean canMove = true;
			// check for stoplights at vehicle location
			for(int j = 0; j < s.size(); j++) {
				// car has arrived at stoplight
				if (s.get(j).EWStreet == v.get(i).location.street1 && s.get(j).NSStreet == v.get(i).location.street2) {
					System.out.println("Vehicle has arrived at stoplight: \n" + 
									  	"    " + v.get(i).toString() + " at " + s.get(j).toString() + 
										" with light color " + s.get(j).currentColor.toString());
					// check if vehicle in a queue
					ArrayList queue = s.get(j).queue;
					for(int k = 0; k < queue.size(); k++) {
						if(queue.get(k) == v.get(i)) {
							// check if vehicle not at front of queue
							if(k != 0) {
								canMove = false;
							}
							System.out.println("Vehicle found in queue");
						}
					}
					if(s.get(j).currentColor != LightColor.GREEN) {
						// move car
						if (canMove == true) {
							canMove = false;
							if(v.get(i).move(map) == true) {
								v.remove(i);
								continue;
							}
						}
					}
					else if(s.get(j).currentColor != LightColor.YELLOW && v.get(i).style == DrivingStyle.FAST) {
						// move car
						if (canMove == true) {
							canMove = false;
							if(v.get(i).move(map) == true) {
								v.remove(i);
								continue;
							}
						}
					}
					if(v.isEmpty() || timer == 0) {
						return;
					}
					// if light is red car will be added to queue of light
					else {
						s.get(j).queue.add(v.get(i));
					}
				}
				// no stoplight at intersection so good to go
				else {
					// if car reaches destination then remove from list
					if (canMove == true) {
						canMove = false;
						if(v.get(i).move(map) == true) {
							v.remove(i);
						}
						if(v.isEmpty() || timer == 0) {
							return;
						}
					}
				}
			}
		}
		// update stoplights
		for(int i = 0; i < s.size(); i++) {
			s.get(i).determineLight(timer);
		}
		// pass updated timer
		run(v, s, map, timer - 1);
	}
}