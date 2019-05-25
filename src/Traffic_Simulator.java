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
		s1 = s1.stoplight(1, 2, 5.0, 5.0, 5.0, LightColor.GREEN);
		Stoplight s2 = new Stoplight();
		s2 = s2.stoplight(2, 2, 5.0, 5.0, 5.0, LightColor.GREEN);
		Stoplight s3 = new Stoplight();
		s3 = s3.stoplight(3, 2, 5.0, 5.0, 5.0, LightColor.GREEN);
		Address loc = new Address();
		loc = loc.address(1, 1);
		Address dest = new Address();
		dest = dest.address(3, 3);
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
			// check for stoplights at vehicle location
			for(int j = 0; j < s.size(); j++) {
				// update stoplight
				determineLight(s.get(j), timer);
				// car has arrived at stoplight
				if (s.get(j).EWStreet == v.get(i).location.street1 && s.get(j).NSStreet == v.get(i).location.street2) {
					System.out.println("Vehicle has arrived at stoplight");
					// check if vehicle is in a queue
					ArrayList queue = s.get(j).queue;
					for(int k = 0; k < queue.size(); k++) {
						if(queue.get(k) == v.get(i)) {
							System.out.println("Vehicle found in queue");
						}
					}
					// TODO: need to make moves in order of stoplight queue
					if(s.get(j).currentColor != LightColor.GREEN) {
						// if car reaches destination then remove from list
						if(move(v.get(i), map) == true) {
							v.remove(i);
							return;
						}
					}
					else if(s.get(j).currentColor != LightColor.YELLOW && v.get(i).style == DrivingStyle.FAST) {
						// if car reaches destination then remove from list
						if(move(v.get(i), map) == true) {
							v.remove(i);
							return;
						}
					}
					// if light is red car will be added to queue of light
					else {
						s.get(j).queue.add(v.get(i));
					}
				}
				// no stoplight at intersection so good to go
				else {
					// if car reaches destination then remove from list
					if(move(v.get(i), map) == true) {
						v.remove(i);
						return;
					}
				}
			}
		}
		// pass updated timer
		run(v, s, map, timer - 1);
	}
	
	// determines what color of light should be based on durations of colors and timer
	public static void determineLight(Stoplight s, int timePassed) {
		double timeReduced = timePassed % s.greenDuration + s.yellowDuration + s.redDuration;
		LightColor firstColor = s.startingColor;
		LightColor secondColor = s.startingColor;
		LightColor thirdColor = s.startingColor;
		double firstDuration = 0.0;
		double secondDuration = 0.0;
		double thirdDuration = 0.0;
		// need to figure out color and duration order
		if(firstColor == LightColor.GREEN) {
			secondColor = LightColor.YELLOW;
			thirdColor = LightColor.RED;
			firstDuration = s.greenDuration;
			secondDuration = s.yellowDuration;
			thirdDuration = s.redDuration;
		}
		else if (firstColor == LightColor.YELLOW) {
			secondColor = LightColor.RED;
			thirdColor = LightColor.GREEN;
			firstDuration = s.yellowDuration;
			secondDuration = s.redDuration;
			thirdDuration = s.greenDuration;
		}
		else {
			secondColor = LightColor.GREEN;
			thirdColor = LightColor.YELLOW;
			firstDuration = s.redDuration;
			secondDuration = s.greenDuration;
			thirdDuration = s.yellowDuration;
		}
		// into second duration
		if(timeReduced > firstDuration && timeReduced <= firstDuration + secondDuration) {
			s.currentColor = secondColor;
		}
		// into third duration
		else if(timeReduced > firstDuration + secondDuration) {
			s.currentColor = thirdColor;
		}
		// in first duration
		else {
			s.currentColor = firstColor;
		}
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