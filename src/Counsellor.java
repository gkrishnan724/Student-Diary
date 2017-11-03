import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;
public class Counsellor extends User {
	ArrayList<Student> advisorOf;
	ArrayList<Subject> teachingSubjects;
	Branch department;
	ArrayList<Achievement> pendingAchievements;
	static ArrayList<User> totalCounsellors = new ArrayList<User>();
	
	Counsellor(){
		
	}
	
	Counsellor(String name, String username, String password){
		this.name = name;
		this.username = username;
		this.password = password;
		this.teachingSubjects = new ArrayList<Subject>();
		this.advisorOf = new ArrayList<Student>();
		this.pendingAchievements = new ArrayList<Achievement>();
		totalCounsellors.add(this);
		this.save();
	}
	
	public static ArrayList<User> get(){
		ObjectInputStream in = null;
		
		try{
			in = new ObjectInputStream(new FileInputStream("Users/Counsellors")); 
		}
		catch(Exception e){
			System.out.println("Unable to reach Counsellor database..");
			return null;
		}
		try{
			totalCounsellors = (ArrayList<User>)in.readObject();
			in.close();
		}
		catch(Exception e){
			System.out.println("Unable to read data from file..");
			return null;
		}
		
		return totalCounsellors;
	}
	
	public void displayMenu(){
		Scanner input = new Scanner(System.in);
		System.out.println("1. Profile");
		System.out.println("2. Students");
		System.out.println("3. Approval Requests");
		
	}
	
	public void profileView(){
		
	}
	

	
	public void displayProfileOptions(){
		
	}
	
	public void edit(){
		
	}
}
