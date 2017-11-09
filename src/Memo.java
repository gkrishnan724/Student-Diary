import java.io.Serializable;

public class Memo implements Serializable{
	String name;
	User givenBy;
	String reason;
	Student givenTo;
	
	Memo(String name, User givenBy, Student givenTo, String reason){
		this.name = name;
		this.givenBy = givenBy;
		this.givenTo = givenTo;
		this.reason = reason;
	}
	
	public String toString(){
		return String.format("%s%29s%s%29s%s",this.name,"",this.givenBy.name,"",this.reason);
	}
}
