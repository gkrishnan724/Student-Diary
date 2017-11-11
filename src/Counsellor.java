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
	
	public String toString(){
		return "name: "+this.name+" username: "+this.username;
	}
	
	Counsellor(String name, String username, String password,Branch b){
		this.name = name;
		this.username = username;
		this.password = password;
		this.teachingSubjects = new ArrayList<Subject>();
		this.advisorOf = new ArrayList<Student>();
		this.pendingAchievements = new ArrayList<Achievement>();
		this.address  = new Address();
		this.department = b;
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
		while(true){
			Session.clrscr();
			System.out.println("Hi "+Session.sessionUser.name+"!");
			System.out.println("---------------------------------------------------------------------------");
			Scanner input = new Scanner(System.in);
			System.out.println("1. Profile");
			System.out.println("2. Students");
			System.out.println("3. Approval Requests");
			System.out.println("(default) Logout");
			System.out.println();
			System.out.print(">> ");
			int choice = input.nextInt();
			switch(choice){
				case 1:
					Session.clrscr();
					this.profileView();
					break;
				case 2:
					Session.clrscr();
	                this.showStudents();
	                break;
				case 3:
					Session.clrscr();
					this.showApprovalRequests();
					break;
				default:
					return;
			}
		}
			
	}
	
	public void showApprovalRequests() {
		while(true){
			Scanner input = new Scanner(System.in);
			System.out.println("--------------------------Approval Requests for" + this.name + "-----------------------");
			for(Achievement a: this.pendingAchievements){
				System.out.println(a.name + "-->" + a.achiever);
			}
			System.out.println();
			System.out.println("1. Approve All");
			System.out.println("2. Reject All");
			System.out.println("3. Approve one( will also show full details )");
			System.out.println("4. Reject one ( will also show full details )");
			System.out.println("(default) Back");
			System.out.println();
			System.out.println(">> ");
			int choice = input.nextInt();
			switch(choice){
				case 1:
					this.approveAll();
					break;
				case 2:
					this.rejectAll();
					break;
				case 3:
					this.approve();
					break;
				case 4:
					this.reject();
					break;
				default:
					return;
			}
			
		}
	}
	
	public void approveAll(){
		for(Achievement a: this.pendingAchievements){
			a.approved = true;
			a.achiever.save();
		}
	    this.pendingAchievements.removeAll(pendingAchievements);
	    this.save();
	    System.out.println("All Approved..");
	}
	
	public void rejectAll(){
		this.pendingAchievements.removeAll(pendingAchievements);
		this.save();
		System.out.println("All rejected..");
	}
	
	public void approve(){
		System.out.println("---------------Approve Achievement--------------");
		Scanner input = new Scanner(System.in);
		System.out.println("Enter name: ");
		String name = input.next();
		Achievement a = this.returnAchievement(name);
		if(a != null){
			System.out.println("Name: " + a.name);
			System.out.println("Achiever: " + a.achiever);
			System.out.println("Proof: "+ a.proof);
			System.out.println("Rank: "+ a.rank);
			System.out.println();
			System.out.println("Approve(Y/N)?: ");
			if(input.next().equals("Y")){
				a.approved = true;
				a.achiever.save();
				this.pendingAchievements.remove(a);
				this.save();
				a.achiever.save();
				System.out.println("Approved..");
			}
		}
		else{
			System.out.println("No such achievement request exists..");
		}
		
	}
	
	public void reject(){
		System.out.println("---------------Reject Achievement--------------");
		Scanner input = new Scanner(System.in);
		System.out.println("Enter name: ");
		Achievement a = this.returnAchievement(name);
		if(a != null){
			System.out.println("Name: " + a.name);
			System.out.println("Achiever: " + a.achiever);
			System.out.println("Proof: "+ a.proof);
			System.out.println("Rank: "+ a.rank);
			System.out.println();
			System.out.println("Reject(Y/N)?: ");
			if(input.next().equals("Y")){
				a.achiever.achievements.remove(a);
				a.achiever.save();
				this.pendingAchievements.remove(a);
				this.save();
				System.out.println("Rejected..");
			}
		}
		else{
			System.out.println("No such achievement request exists..");
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
	
	
	public void showStudents(){
		while(true){
			Scanner input = new Scanner(System.in);
			System.out.println("--------------------------Students under " + this.name + "-----------------------");
			System.out.println();
			for(Student s: this.advisorOf){
				System.out.println(s.name + "->" + s.rollNo);
			}
			System.out.println();
			System.out.println("-----------------------------------------------");
			System.out.println("1. Assign new Student");
			System.out.println("2. view  a student profile");
			System.out.println("(default) Back");
			System.out.println();
			System.out.println(">> ");
			int choice = input.nextInt();
			switch(choice){
				case 1:
					Session.clrscr();
					this.assignStudent();
					break;
				case 2:
					Session.clrscr();
					this.viewStudentProfile();
					break;
				default:
					return;
			}
			
		}
	}
	
	public void assignStudent(){
		System.out.println("--------------------Assign new Student--------------------");
		Scanner input = new Scanner(System.in);
		System.out.print("Roll No: ");
		String roll = input.next();
		Student s = Student.returnStudent(roll);
		if(s != null && !this.advisorOf.contains(s)){
			this.advisorOf.add(s);
			s.advisor = this;
			this.save();
			s.save();
		}
		else{
			System.out.println("Student does not exist or is already assigned to you...");
		}
		
	}
	
	public void viewStudentProfile(){
		System.out.println("--------------Student Profile-------------------------");
		Scanner input  =  new Scanner(System.in);
		System.out.println("Roll No: ");
		String roll = input.next();
		Student s =  Student.returnStudent(roll);
		if(s != null && this.advisorOf.contains(s)){
			Session.clrscr();
			s.profileView();	
		}
		else{
			System.out.println("Student does not exist or not assigned to you, please make sure the student is assigned to you");
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
	
	public static void showAllCounsellors(){
		for(User s: Counsellor.totalCounsellors){
			System.out.println(s.name);
		}
	}
	
	public static Counsellor returnCounsellor(String username){
		System.out.println("----------------Counsellors-----------------------");
		for(User s:Counsellor.totalCounsellors){
			if(s.username.equals(username)){
				return (Counsellor)s;
			}
		}
		return null;
		
	}
	
	public Achievement returnAchievement(String name){
		for(Achievement a: this.pendingAchievements){
			if(a.name.equals(name)){
				return a;
			}
		}
		return null;
	}
}
