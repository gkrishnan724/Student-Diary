import java.util.ArrayList;
import java.io.*;
public class Subject implements Storeable,Serializable{
	String name;
	String code;
	int credits;
	int semester;
	Branch department;
	static ArrayList<Subject> totalSubjects = new ArrayList<Subject>();
	
	Subject(String name, String code, int credits, int semester, Branch department){
		this.name = name;
		this.code = code;
		this.credits = credits;
		this.semester = semester;
		this.department = department;
		department.subjects.add(this);
		department.save();
		totalSubjects.add(this);
		this.save();
	}
	
	public String toString(){
		return String.format("%s%29s%s%29s%s",this.name,"",this.code,"",this.credits);
	}
	
	public void save(){
		ObjectOutputStream out;
		try{
			out = new ObjectOutputStream(new FileOutputStream("Subjects"));
			
		}catch(Exception e){
			System.out.println("Unable to locate subject database..");
			return;
		}
		try{
			out.writeObject(totalSubjects);
			out.close();
		}
		catch(Exception e){
			System.out.println("Unable to write subject data to database..");
		}
	}
	
	public static ArrayList<Subject> get(){
		ObjectInputStream in;
		try{
			in =  new ObjectInputStream(new FileInputStream("Subjects"));
		}catch(Exception e){
			System.out.println("Unable to locate subject database..");
			return null;
		}
		try{
			totalSubjects = (ArrayList<Subject>)in.readObject();
			in.close();
		}
		catch(Exception e){
			System.out.println("Unable to read subjects from database..");
			return null;
		}
		return totalSubjects;
	}
	
	public static boolean containsCode(String code){
		for(Subject s: totalSubjects){
			if(s.code.equals(code)){
				return true;
			}
		}
		return false;
	}
	
	public static Subject getSubject(String code){
		for(Subject s: totalSubjects){
			if(s.code.equals(code)){
				return s;
			}
		}
		return null;
	}
	
	public static void showAllSubjects(){
		System.out.println("-------------Subjects-------------------");
		for(Subject s: Subject.totalSubjects){
			System.out.println(s.name);
		}
	}
	
	public static Subject returnSubject(String code){
		for(Subject s: Subject.totalSubjects){
				if(s.code.equals(code)){
					return s;
				}
		}
		return null;
	}
}
