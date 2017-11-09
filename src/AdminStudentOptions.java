import java.io.Serializable;
import java.util.Scanner;

public class AdminStudentOptions implements Serializable{
	Administrator currentAdmin;
	
	AdminStudentOptions(Administrator currentAdmin){
		this.currentAdmin = currentAdmin;
	}
	
	public void studentOptions(){
		while(true){
			System.out.println();
			Scanner input = new Scanner(System.in);
			System.out.println("1. Create new Student");
			System.out.println("2. Delete an existing Student");
			System.out.println("(default)Back");
			System.out.println();
			System.out.print(">> ");
			int choice = input.nextInt();
			switch(choice){
				case 1:
					Session.clrscr();
					this.createStudent();
					Student.showAllStudents();
					break;
				case 2:
					Session.clrscr();
					this.deleteStudent();
					Student.showAllStudents();
					break;
				default:
					return;
			}
		}
		
	}
	
	public void deleteStudent(){
		System.out.println("--------------------Delete Student--------------------------");
		Scanner input = new Scanner(System.in);
		System.out.println("Roll no: ");
		String code = input.next();
		Subject s = Subject.returnSubject(code);
		Subject.totalSubjects.remove(s);
		s.save();
	}
	
	public void createStudent(){
		System.out.println("--------------------Create new Student--------------------------");
		Scanner input = new Scanner(System.in);
		System.out.println("Name: ");
		String name = input.next();
		System.out.println("Roll no: ");
		String roll = input.next();
		System.out.println("Branch: ");
		Branch b = Branch.returnBranch(input.next());
		if(b != null){
			if(Student.returnStudent(roll) == null){
				new Student(name,roll,b);
			}
			else{
				System.out.println("Student already exists..");
			}
			
		}
		else{
			System.out.println("No such branch exist");
		}
	}
	
}
