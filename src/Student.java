import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;
public class Student extends User{
	String rollNo;
	ArrayList<Subject> Subjects;
	float attendance;
	Marks gradeBook;
	Address hostelAddress;
	ArrayList<Achievement> achievements;
	ArrayList<Activity>  extraCurricular;
	ArrayList<Memo> memos;
	Counsellor advisor;
	
	static ArrayList<User> totalStudents = new ArrayList<User>();
	
	Student(String name, String roll, Branch br){
		this.name = name;
		this.rollNo = roll;
		this.username = this.rollNo;
		this.password = "root";
		this.department = br;
		totalStudents.add(this);
		this.save();
    }
	
	
	public static ArrayList<User> get(){
		ObjectInputStream in = null;
		
		try{
			in = new ObjectInputStream(new FileInputStream("Users/Students")); 
		}
		catch(Exception e){
			System.out.println("Unable to reach Student database..");
			return null;
		}
		try{
			totalStudents = (ArrayList<User>)in.readObject();
			in.close();
		}
		catch(Exception e){
			System.out.println("Unable to read data from file..");
			return null;
		}
		
		return totalStudents;
	}
	
	public String toString(){
		return "name: " + name + " rollNo: " + rollNo + " Department: "+ department;
	}
	
	public void displayMenu(){
		Scanner input = new Scanner(System.in);
		System.out.println("1. Profile");
		System.out.println("2. Courses");
		System.out.println("3. GradeBook");
		System.out.println("4. Achievements");
		System.out.println("5. Activities");
		System.out.println("");
		System.out.print(">> ");
		
	}
	
	public void profileView(){
		System.out.println("---------------------"+ this.name +"-----------------------------");
		System.out.println(this);
		System.out.print("Advisor: " + advisor.name);
		System.out.println("Overall Attendance: " + this.attendance + "%");
		System.out.println("Current CGPA: "+ this.gradeBook.CGPA);
		System.out.println();
		this.displayCourses();
		System.out.println();
		this.displayAchievements();
		System.out.println();
		this.displayActivites();
		System.out.println();
		this.displayMemos();
		System.out.println("---------------------------------------------------------");
	}
	
	public void displayCourses(){
		System.out.println("Registered Courses: ");
		System.out.println("Name \t | Course Code \t | Credits ");
		for(Subject s: Subjects){
			System.out.println(s);
		}
	}
	
	public void displayMemos(){
		System.out.println("Memos of " + this.name + " : " );
		System.out.println("Name \t | given By \t | Reason ");
		for(Memo m: memos){
			System.out.println(m);
		}
	}
	
	public void displayAchievements(){
		System.out.println("Achivements of " + this.name + " : ");
		System.out.println("Name \t| type \t| Date");
		for(Achievement e: achievements){
			if(e.approved){
				System.out.println(e);
			}
		}
	}
	
	public void displayActivites(){
		System.out.println("Activities in which " + this.name + "was involved : ");
		System.out.println("Name \t| type \t| Date");
		for(Activity e: extraCurricular){
			System.out.println(e);
		}
	}
	
	
	public void displayProfileOptions(){
		
	}
	
	public void edit(){
		
	}
}
