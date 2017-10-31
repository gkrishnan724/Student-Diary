import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Administrator extends User {
	
	static ArrayList<User> totalAdmins = new ArrayList<User>();

	
	Administrator(String name, String username, String password){
		this.name = name;
		this.username = username;
		this.password = password;
		totalAdmins.add(this);
		this.save();
	}
    
	
	public static ArrayList<User> get(){
		ObjectInputStream in = null;
		
		try{
			in = new ObjectInputStream(new FileInputStream("Users/Admins")); 
		}
		catch(Exception e){
			System.out.println("Unable to reach Admin database..");
			return null;
		}
		try{
			totalAdmins = (ArrayList<User>)in.readObject();
			in.close();
		}
		catch(Exception e){
			System.out.println("Unable to read data from file..");
			return null;
		}
		
		return totalAdmins;
	}
	public void displayMenu(){
		Scanner input = new Scanner(System.in);
		System.out.println("1. Profile");
		System.out.println("2. Courses");
		System.out.println("3. Students");
		System.out.println("4. Faculty");
		
	}
	
	public void profileView(){
		
	}
	
	public void displayProfileOptions(){
		
	}
	
	public void edit(){
		
	}
}
