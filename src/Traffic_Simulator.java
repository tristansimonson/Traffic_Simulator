// imports

public class Traffic_Simulator {
	
	// main class
	public static void main (String[] args) {
		Stoplight s1 = new Stoplight();
		s1 = s1.stoplight(1, 2, 5.0, 5.0, 5.0, LightColor.GREEN);
		Address loc = new Address();
		loc = loc.address(1, 1);
		Address dest = new Address();
		dest = dest.address(2, 2);
		Vehicle v1 = new Vehicle();
		v1 = v1.vehicle("Chevy", "Volt", "2015", loc, dest, DrivingStyle.AVERAGE);
		
		
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