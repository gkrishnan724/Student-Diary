import java.util.Date;
import java.io.*;
public class Achievement implements Serializable{
	String name;
	int rank;
	String type;
	Date date;
	String proof;
	Student achiever;
	boolean approved;
	
	Achievement(String name, int rank, String type, String proof,Date date,Student currentStudent){
		this.name = name;
		this.rank = rank;
		this.type = type;
		this.proof = proof;
		this.date = date;
		this.approved = false;
		this.achiever = currentStudent;
	}
	
	public String toString(){
		return String.format("%s%29s%s%29s%s%29s",this.name,"",this.type,"",this.date);
	}
	
}

