import java.util.Scanner;
import java.io.*;
public class AdminBranchOptions implements Serializable {
	Administrator currentAdmin;
	
	AdminBranchOptions(Administrator currentAdmin){
		this.currentAdmin = currentAdmin;
	}
	
	public void BranchOptions(){
		while(true){
			System.out.println();
			Scanner input = new Scanner(System.in);
			System.out.println("1. Create new Branch");
			System.out.println("2. Delete an existing Branch");
			System.out.println("(default) Back");
			System.out.println();
			System.out.print(">> ");
			int choice = input.nextInt();
			switch(choice){
				case 1:
					Session.clrscr();
					this.createBranch();
					Branch.showAllBranches();					
					break;
				case 2:
					Session.clrscr();
					this.deleteBranch();
					Branch.showAllBranches();
					break;
				default:
					return;
			}
		}
		
	}
	
	public void createBranch(){
		System.out.println("--------------------Create new Branch--------------------------");
		Scanner input = new Scanner(System.in);
		System.out.println("Enter name: ");
		String name = input.next();
		Branch b = Branch.returnBranch(name);
		if(b == null){
			new Branch(name);
			
		}
		else{
			System.out.println("Branch already exists..");
		}
	}
	
	public void deleteBranch(){
		System.out.println("--------------------Delete Branch--------------------------");
		Scanner input = new Scanner(System.in);
		System.out.println("Enter name: ");
		String name = input.next();
		Branch b = Branch.returnBranch(name);
		if(b != null){
			Branch.totalBranches.remove(b);
			b.save();
		}
		else{
			System.out.println("Branch already exists..");
		}
	}
}
