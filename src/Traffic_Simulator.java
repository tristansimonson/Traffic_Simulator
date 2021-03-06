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
		int count = 0;
		
		Stoplight s1 = new Stoplight();
		s1 = s1.stoplight(1, 2, 1.0, 1.0, 1.0, LightColor.GREEN);
		Stoplight s2 = new Stoplight();
		s2 = s2.stoplight(2, 1, 5.0, 5.0, 5.0, LightColor.GREEN);
		Stoplight s3 = new Stoplight();
		s3 = s3.stoplight(3, 2, 5.0, 5.0, 5.0, LightColor.GREEN);
		Stoplight s4 = new Stoplight();
		s4 = s4.stoplight(4, 2, 1.0, 1.0, 20.0, LightColor.RED);
		Vehicle v1 = new Vehicle();
		Address loc = new Address();
		Address dest = new Address();
		loc = loc.address(1, 1);
		dest = dest.address(5, 5);
		v1 = v1.vehicle("Chevy", "Volt", "2015", loc, dest, DrivingStyle.AVERAGE);
		Vehicle v2 = new Vehicle();
		Address loc2 = new Address();
		Address dest2 = new Address();
		loc2 = loc2.address(4, 5);
		dest2 = dest2.address(4, 1);
		v2 = v2.vehicle("Nissan", "350z", "2005", loc2, dest2, DrivingStyle.FAST);
		Vehicle v3 = new Vehicle();
		Address loc3 = new Address();
		Address dest3 = new Address();
		loc3 = loc3.address(4, 5);
		dest3 = dest3.address(4, 1);
		v3 = v3.vehicle("Nissan", "370z", "2012", loc3, dest3, DrivingStyle.FAST);
		stoplights.add(s1);
		stoplights.add(s2);
		stoplights.add(s3);
		stoplights.add(s4);
		vehicles.add(v1);
		vehicles.add(v2);
		vehicles.add(v3);
		
		System.out.println("Running sim...");
		run(vehicles, stoplights, map, count, timer);
	}
	
	// runs simulation
	public static void run(ArrayList<Vehicle> v, ArrayList<Stoplight> s, int[] map, int count, int timer) {
		// if vehicle list empty or timer ran out return
		if(v.isEmpty() || count == timer) {
			return;
		}
		// copy vehicle list to modify and send to next run call
		ArrayList<Vehicle> newV = new ArrayList(v);
		// go through list and execute move for each vehicle
		for(int i = 0; i < v.size(); i++) {
			boolean canMove = true;
			// check for stoplights at vehicle location
			for(int j = 0; j < s.size(); j++) {
				// check if vehicle in stoplight queue
				for(int k = 0; k < s.get(j).queue.size(); k++) {
					if(s.get(j).queue.get(k) == v.get(i) && s.get(j).currentColor == LightColor.RED) {
						canMove = false;
					}
				}
				// car has arrived at stoplight
				if (s.get(j).EWStreet == v.get(i).location.street1 && s.get(j).NSStreet == v.get(i).location.street2) {
					System.out.println("Vehicle has arrived at stoplight: \n" + "    " + v.get(i).toString() + 
							           " at " + s.get(j).toString() + " with light color " + s.get(j).currentColor);
					if(s.get(j).currentColor == LightColor.GREEN) {
						// move car
						if (canMove == true) {
							canMove = false;
							boolean remove = v.get(i).move(map); 
							if(remove == true) {
								newV.remove(v.get(i));
							}
						}
					}
					else if(s.get(j).currentColor == LightColor.YELLOW && v.get(i).style == DrivingStyle.FAST) {
						// move car
						if (canMove == true) {
							canMove = false;
							boolean remove = v.get(i).move(map); 
							if(remove == true) {
								newV.remove(v.get(i));
							}
						}
					}
					// light is red and car will be added to queue of light
					else {
						canMove = false;
						s.get(j).queue.add(v.get(i));
					}
				}
				// no stoplight at intersection
				else {
					// if car reaches destination after move remove from list
					if (canMove == true) {
						canMove = false;
						boolean remove = v.get(i).move(map); 
						if(remove == true) {
							newV.remove(v.get(i));
						}
					}
				}
				if(newV.isEmpty() || count == timer) {
					return;
				}
			}
			System.out.print(v.get(i).toString() + " located at " + v.get(i).location.toString() + "\n");
		}
		// update stoplights
		for(int i = 0; i < s.size(); i++) {
			s.get(i).determineLight(count + 1);
		}
		// pass updated time and vehicle list
		System.out.println("\nTimer: " + (count + 1) + "/" + timer);
		run(newV, s, map, count + 1, timer);
	}
}