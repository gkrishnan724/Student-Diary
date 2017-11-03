import java.util.Date;
public class Activity {
	String name;
	String type;
	Date date;
	
	
	Activity(String name, String type, Date date){
		this.name = name;
		this.type = type;
		this.date = date;
	}
	public String toString(){
		return String.format("%s%29s|%s%29s|%s",this.name,this.type,this.date);

	}
}
