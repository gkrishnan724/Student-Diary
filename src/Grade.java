import java.io.Serializable;

public class Grade implements Serializable{
	Subject subject;
	String Grade;
	float internals;
	float semester;
	
	Grade(String code, float internals, float semester, String Grade){
		for(Subject s: Subject.totalSubjects){
			if(s.code.equals(code)){
				this.subject = s;
				break;
			}
		}
		this.internals = internals;
		this.semester = semester;
		this.Grade = Grade;
	
	}
	
	public String toString(){
		return String.format("%s%29s|%s%29s|%s%29s|%s",this.subject.name,this.internals,this.semester,this.Grade);
	}
}
