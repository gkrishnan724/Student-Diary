import java.util.Date;
import java.io.*;
import java.util.ArrayList;
public abstract class User implements Serializable,Storeable {
	String name;
	Date Dob;
	String username;
	String password;
	Address address;
	Branch department;
	ArrayList<User> arr = new ArrayList<User>();
	public static ArrayList<ArrayList<User>> totalUsers = new ArrayList<ArrayList<User>>();
	
	
	public String toString(){
		return "username: " + this.username;
	}
	
	public void save(){
		ObjectOutputStream out = null;
		String file = null;
		ArrayList<User> Users = null;
		if(this instanceof Student){
			file = "Students";
			Users = Student.totalStudents;
		}
		if(this instanceof Counsellor){
			file = "Counsellors";
			Users = Counsellor.totalCounsellors;
		}
		if(this instanceof Administrator){
			file = "Admins";
			Users = Administrator.totalAdmins;
		}
		try{
			out = new ObjectOutputStream(new FileOutputStream("Users/"+file));
		}
		catch(Exception e){
			System.out.println("Unable to locate database..");
			return;
		}
		try{
			out.writeObject(Users);
			out.close();
		}
		catch(Exception e){
			System.out.println("Unable to save users..");
			return;
		}
	}
		
	public static ArrayList<User> get(){
		return null;
	}
	
	public abstract void displayMenu();
	
	public abstract void edit();
	
	public abstract void profileView();
	
	
}
