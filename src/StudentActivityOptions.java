import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.io.*;

public class StudentActivityOptions implements Serializable {
	
	Student currentStudent;
	
	StudentActivityOptions(Student s){
		this.currentStudent = s;
	}
	
	public void addNewActivity(){
		Scanner input = new Scanner(System.in);
		System.out.println("--------------------Add new Activity--------------------------");
		System.out.println();
		System.out.println("Enter Activity name: ");
		String name = input.next();
		String format = "dd/mm/yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		System.out.print("Enter Date(dd/mm/yyyy): ");
		Date date = null;
		try{
			 date = formatter.parse(input.next());
		}
		catch(Exception e){
			System.out.println("Input does not match the format (dd/mm/yyyy)");
		}
		System.out.print("Enter type of activity: ");
		String type = input.next();
		Activity a = new Activity(name,type,date);
		currentStudent.extraCurricular.add(a);
		System.out.println("Thanks! Added your activity to the activities list.. ");
		currentStudent.save();
	}
	
	public void displayActivites(){
		System.out.println("Activities in which " + currentStudent.name + " was involved : ");
		System.out.println();
		System.out.format("%s%30s%s%30s%s\n","Name","","Type","","Date");
		System.out.println(String.format("%0" + 100 + "d", 0).replace("0","-"));
		for(Activity e: currentStudent.extraCurricular){
			System.out.println(e);
		}
  }
	
	
	public void activitiesOption(){
		while(true){
			System.out.println();
			Scanner input = new Scanner(System.in);
			System.out.println("(default) Back");
			int choice;
			if(Session.sessionUser instanceof Student){
				System.out.println("1. Add new Activity");
				System.out.println();
				System.out.println(">> ");
				choice = input.nextInt();
				switch(choice){
				case 1:
					this.addNewActivity(); 
					this.displayActivites();
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
