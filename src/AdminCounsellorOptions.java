import java.io.Serializable;
import java.util.Scanner;

public class AdminCounsellorOptions implements Serializable{
	Administrator currentAdmin;
	
	AdminCounsellorOptions(Administrator currentAdmin){
		this.currentAdmin = currentAdmin;
	}
	
	public void CounsellorOptions(){
		while(true){
			System.out.println();
			Scanner input = new Scanner(System.in);
			System.out.println("1. Create new Faculty");
			System.out.println("2. Delete an existing Faculty");
			System.out.println("(default) Back");
			System.out.println();
			System.out.print(">> ");
			int choice = input.nextInt();
			switch(choice){
				case 1:
					Session.clrscr();
					this.createCounsellor();
					Counsellor.showAllCounsellors();					
					break;
				case 2:
					Session.clrscr();
					this.deleteCounsellor();
					Counsellor.showAllCounsellors();
					break;
				default:
					return;
			}
		}
		
	}
	
	public void deleteCounsellor() {
		System.out.println("--------------------Delete Counsellor--------------------------");
		Scanner input = new Scanner(System.in);
		System.out.println("Enter username: ");
		String code = input.next();
		Counsellor s = Counsellor.returnCounsellor(code);
		Counsellor.totalCounsellors.remove(s);
		new Counsellor().save();
	}


	public void createCounsellor() {
		System.out.println("--------------------Create new Counsellor--------------------------");
		Scanner input = new Scanner(System.in);
		System.out.println("Name: ");
		String name = input.next();
		System.out.println("username: ");
		String username = input.next();
		System.out.println("password: ");
		String pass = input.next();
		System.out.println("Branch");
		String branch = input.next();
		Branch b = Branch.returnBranch(branch);
		if(b != null){
			if(Counsellor.returnCounsellor(username) == null){
				new Counsellor(name,username,pass,b);
			}
			else{
				System.out.println("Counsellor already Exsits..");
			}
		}
		else{
			System.out.println("No such branch exists..");
		}
		
	}
}
