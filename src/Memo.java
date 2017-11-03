
public class Memo {
	String name;
	Counsellor givenBy;
	String reason;
	Student givenTo;
	
	Memo(String name, Counsellor givenBy, Student givenTo, String reason){
		this.name = name;
		this.givenBy = givenBy;
		this.givenTo = givenTo;
		this.reason = reason;
	}
	
	public String toString(){
		return String.format("%s%29s|%s%29s|%s",this.name,this.givenBy,this.reason);
	}
}
