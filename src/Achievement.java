import java.util.Date;
public class Achievement {
	String name;
	int rank;
	String type;
	Date date;
	String proof;
	boolean approved;
	
	Achievement(String name, int rank, String type, String proof,Date date){
		this.name = name;
		this.rank = rank;
		this.type = type;
		this.proof = proof;
		this.date = date;
		this.approved = false;
	}
	
	public String toString(){
		return String.format("%29s|%29s|%29s",this.name,this.type,this.date);
	}
}
