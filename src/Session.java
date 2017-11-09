import java.util.*;
import java.io.*;
public class Session {
	static User sessionUser;
	
	Session(String user, String pass){
		this.init();
		for(ArrayList<User> arr: User.totalUsers){
			if(arr != null){
				for(User n: arr){
					if(n.username.equals(user) && n.password.equals(pass)){
						Session.sessionUser = n;
						System.out.println(" ");
						return;
					}
				}
			}
		}
		Session.clrscr();
		System.out.println("Session Creation failed, Invalid Login! please try again..");
	}
	
	public static void main(String[] args){
		while(true){
			System.out.println("----------Amrita Student Diary---------");
			Scanner input = new Scanner(System.in);
			System.out.println("Username: ");
			String username = input.nextLine();
			System.out.println("Password: ");
			String password = input.nextLine();
	//		Counsellor s = new Counsellor("Gopal2","gk","root");
//			Student s = new Student("Gopal","u4cse16126",null);
			Session currentSession = new Session(username,password);
			Session.clrscr();
			if(sessionUser!= null){
				Session.sessionUser.displayMenu();
			}
		}
	}
	
	public static void init(){
		User.totalUsers.add(Student.get());
		User.totalUsers.add(Counsellor.get());
		User.totalUsers.add(Administrator.get());
		Branch.get();
		Subject.get();
	}
	
	public static void clrscr(){
		System.out.println(String.format("%0" + 40 + "d", 0).replace("0","\n"));
	}
	
	
}

