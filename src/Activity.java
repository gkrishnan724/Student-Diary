import java.util.Date;
public class Activity {
	String name;
	String type;
	Date date;
	
	public String toString(){
		return this.name + "\t| "+this.type + "\t| "+this.date;
	}
}
