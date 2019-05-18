// class for holding address information
public class Address {
	String street1;
	String street2;
	String city;
	String state;
	String zip;
	
	public Address address(String street1, String street2, String city, String state, String zip) {
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		return this;
	}
	
	public String addressToString() {
		String result = "";
		String space = "         ";
		result += this.street1;
		if(this.street2 != "") {
			result += "\n" + space + this.street2;
		}
		result += "\n" + space + this.city;
		result += ", " + this.state;
		result += " " + this.zip;
		return result;
	}
}
