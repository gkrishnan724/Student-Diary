import java.util.Scanner;
import java.io.*;

public class StudentCourseBook implements Serializable {
	Student currentStudent;
	
	StudentCourseBook(Student s){
		this.currentStudent = s;
	}
	
	public void displayCourses(){
		System.out.println("Registered Courses: ");
		System.out.println();
		System.out.format("%s%30s%s%30s%s\n","Name","","Course Code","","Credits");
		System.out.println(String.format("%0" + 100 + "d", 0).replace("0","-"));
		try{
			for(Subject s: currentStudent.Subjects){
				System.out.println(s);
			}
		}
		catch(Exception e){
			System.out.format("%57s","No data available");
		}
		System.out.println();
	}
	
	public void addNewCourse(){
		Scanner input = new Scanner(System.in);
		System.out.println("--------------------Add new Course--------------------------");
		System.out.println();
		System.out.print("CourseCode: ");
		String code = input.next();
		Subject s = Subject.getSubject(code);
		if(s != null && !currentStudent.Subjects.contains(s)){
			currentStudent.Subjects.add(s);
		}
		else if(s == null){
			System.out.println("No subject with code: "+code+" Exists!");
		}
		else{
			System.out.println("This subject already registered by the student!");
		}
		currentStudent.save();
	}
	
	public void courseOptions(){
		while(true){
			System.out.println();
			Scanner input = new Scanner(System.in);
			System.out.println("(default) Back");
			int choice;
			if(Session.sessionUser instanceof Student){
				System.out.println("1. Add new Course");
				System.out.println();
				System.out.println(">> ");
				choice = input.nextInt();
				switch(choice){
				case 1:
					this.addNewCourse();
					this.displayCourses();
					break;
				default:
					return;
				}
			}
			else{
				System.out.println();
				System.out.print(">> ");
				choice  = input.nextInt();
				switch(choice){
					default:
						return;
				}
			}
		}
  }
	
}
