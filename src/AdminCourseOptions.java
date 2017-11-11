import java.io.Serializable;
import java.util.Scanner;

public class AdminCourseOptions implements Serializable{
	Administrator currentAdmin;
	
	AdminCourseOptions(Administrator currentAdmin){
		this.currentAdmin = currentAdmin;
	}
	
	
	public void courseOptions(){
		while(true){
			System.out.println();
			Scanner input = new Scanner(System.in);
			System.out.println("1. Create new Course");
			System.out.println("2. Delete an existing Course");
			System.out.println("(default) Back");
			System.out.println();
			System.out.print(">> ");
			int choice = input.nextInt();
			switch(choice){
				case 1:
					Session.clrscr();
					this.createCourse();
					Subject.showAllSubjects();
					break;
				case 2:
					Session.clrscr();
					this.deleteCourse();
					Subject.showAllSubjects();
					break;
				default:
					return;
			}
		}
		
	}
	
	public void createCourse(){
		System.out.println("--------------------Create new Course--------------------------");
		Scanner input = new Scanner(System.in);
		System.out.println("Name: ");
		String name = input.next();
		System.out.println("Course Code: ");
		String code = input.next();
		System.out.println("Credits: ");
		int credits = input.nextInt();
		System.out.println("Semester: ");
		int semester = input.nextInt();
		System.out.println("Branch: ");
		Branch b = Branch.returnBranch(input.next());
		if(b != null){
			if(Subject.returnSubject(code) == null){
				new Subject(name,code,credits,semester,b);
			}
			else{
				System.out.println("Subject already exists..");
			}
		}
		else{
			System.out.println("No such branch exist");
		}
	}
	
	
	
	public void deleteCourse(){
		System.out.println("--------------------Delete Course--------------------------");
		Scanner input = new Scanner(System.in);
		System.out.println("Course Code: ");
		String code = input.next();
		Subject s = Subject.returnSubject(code);
		Subject.totalSubjects.remove(s);
		new Subject().save();
	}
}
