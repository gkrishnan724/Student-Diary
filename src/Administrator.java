import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Administrator extends User {
	
	static ArrayList<User> totalAdmins = new ArrayList<User>();
	AdminStudentOptions studentOptions;
	AdminBranchOptions branchOptions;
	AdminCourseOptions courseOptions;
	AdminCounsellorOptions counsellorOptions;
	
	Administrator(){
	}

	
	Administrator(String name, String username, String password){
		this.name = name;
		this.username = username;
		this.password = password;
		this.studentOptions = new AdminStudentOptions(this);
		this.branchOptions = new AdminBranchOptions(this);
		this.counsellorOptions = new AdminCounsellorOptions(this);
		this.courseOptions = new AdminCourseOptions(this);
		this.address = new Address();
		totalAdmins.add(this);
		this.save();
	}
    
	public String toString(){
		return "Name: " + this.name + " Username: " + this.username;
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
		while(true){
			Session.clrscr();
			System.out.println("Hi "+Session.sessionUser.name+"!");
			System.out.println("---------------------------------------------------------------------------");
			Scanner input = new Scanner(System.in);
			System.out.println("1. Profile");
			System.out.println("2. Courses");
			System.out.println("3. Students");
			System.out.println("4. Faculty");
			System.out.println("5. Departments");
			System.out.println("6. Admins");
			System.out.println("(default) Back");
			System.out.print(">> ");
			int choice = input.nextInt();
			switch(choice){
				case 1:
					Session.clrscr();
					this.profileView();
					break;
				case 2:
					Session.clrscr();
					Subject.showAllSubjects();
					courseOptions.courseOptions();;
					break;
				case 3:
					Session.clrscr();
					Student.showAllStudents();
					studentOptions.studentOptions();
					break;
				case 4:
					Session.clrscr();
					Counsellor.showAllCounsellors();
					counsellorOptions.CounsellorOptions();
					break;
				case 5:
					Session.clrscr();
					Branch.showAllBranches();
					branchOptions.BranchOptions();
					break;
				case 6:
					Session.clrscr();
					showAllAdmins();
					this.AdminOptions();
				default:
					Session.clrscr();
					return;
				
			}
		}
		
	}
	
	public void createAdmin(){
		System.out.println("--------------------Add new Admin--------------------------");
		Scanner input = new Scanner(System.in);
		System.out.println("Name: ");
		String name = input.next();
		System.out.println("username: ");
		String username = input.next();
		System.out.println("password: ");
		String password = input.next();
		Administrator a = returnAdmin(username);
		if(a == null){
			new Administrator(name,username,password);
		}
		else{
			System.out.println("User already exists..");
		}
		
	}
	
	public void AdminOptions(){
	while(true){
			System.out.println();
			Scanner input = new Scanner(System.in);
			System.out.println("1. Create Admin ");
			System.out.println("(default) Back");
			int choice  = input.nextInt();
			switch(choice){
				case 1:
					Session.clrscr();
					this.createAdmin();
					showAllAdmins();
					break;
				default:
					return;
			}
		}
	}
	
	public void profileView(){
		System.out.println();
		System.out.println("---------------------"+ this.name +"-----------------------------");
		System.out.println(this);
		System.out.println();
		System.out.println(address);
		System.out.println();
		
		System.out.println("1. edit");
		System.out.println("(default) Back");
		System.out.print(">> ");
		Scanner input = new Scanner(System.in);
		int choice  = input.nextInt();
		switch(choice){
		 	case 1:
		 		Session.clrscr();
		 		this.edit();
		 		break;
		 	default:
		 		return;
		}
	}
	
	public void edit(){
		System.out.println("--------------------Edit Profile---------------------");
		System.out.println();
			Scanner input = new Scanner(System.in);
			System.out.print("Change Name?(Y/N): ");
			if(input.next().equals("Y")){
				System.out.println("Name: ");
				this.name = input.hasNextLine()?input.next():this.name;
			}
			System.out.print("Change Address?(Y/N): ");
			if(input.next().equals("Y")){
				System.out.print("Place: ");
				this.address.place = input.hasNextLine()?input.next():this.address.place;
				System.out.print("District: ");
				this.address.district = input.hasNextLine()?input.next():this.address.district;
				System.out.print("State: ");
				this.address.state = input.hasNextLine()?input.next():this.address.state;
				System.out.print("Country: ");
				this.address.country = input.hasNextLine()?input.next():this.address.state;
				System.out.print("ZIP: ");
				this.address.Zip = input.hasNextInt()?input.nextInt():this.address.Zip;		
			}
			System.out.print("Password?(Y/N): ");
			if(input.next().equals("Y")){
				System.out.print("Enter current password: ");
				if(input.next().equals(this.password)){
					System.out.print("Enter new password: ");
					this.password = input.hasNextLine()?input.next():this.password;
				}
				else{
					System.out.println("Wrong Password, Password change failed!");
				}
			}
	}
	
	public static Administrator returnAdmin(String username){
		for(User s: Administrator.totalAdmins){
			if(s.username.equals(username)){
				return (Administrator)s;
			}
		}
		return null;
	}
	
	public static void showAllAdmins(){
		System.out.println("----------------Administrators-----------------------");
		for(User s: Administrator.totalAdmins){
			System.out.println(s.username);
		}
	}
}
