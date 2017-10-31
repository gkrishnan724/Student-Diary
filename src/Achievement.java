import java.util.Date;
public class Achievement {
	String name;
	int rank;
	String type;
	Date date;
	String proof;
	boolean approved;
	
	public String toString(){
		return this.name + "\t| "+this.type + "\t| "+this.date;
	}
}
