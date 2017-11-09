import java.io.Serializable;

public class Address implements Serializable{
	String place;
	String district;
	String state;
	String country;
	int Zip;
	
	Address(){
		
	}
	Address(String place, String district , String state , String country, int Zip){
		this.place = place;
		this.district = district;
		this.state = state;
		this.country = country;
		this.Zip = Zip;
	}
	
	public String toString(){
		return "Place: "+place+" District: "+district+" State: "+" Country: "+country;
	}
}
