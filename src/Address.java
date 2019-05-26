// class for holding address information
public class Address {
	int street1;
	int street2;
	// String city;
	// String state;
	// String zip;
	
	public Address address(int street1, int street2) { // , String city, String state, String zip) {
		this.street1 = street1;
		this.street2 = street2;
		// this.city = city;
		// this.state = state;
		// this.zip = zip;
		return this;
	}
	
	public String toString() {
		return (Integer.toString(this.street1) + " " + Integer.toString(this.street2));
	}
	
	/*
	 public String addressToString() {
		String result = "";
		String space = "         ";
		result += Integer.toString(this.street1);
		if(Integer.toString(this.street2) != "") {
			result += "\n" + space + this.street2;
		}
		result += "\n" + space + this.city;
		result += ", " + this.state;
		result += " " + this.zip;
		return result;
	}
	*/
}
