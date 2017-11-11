import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class StudentAchievementBook implements Serializable {
	
	Student currentStudent;
	
	StudentAchievementBook(Student s){
		this.currentStudent = s;
	}
	
	public void displayAchievements(){
		System.out.println("Achivements of " + currentStudent.name + " : ");
		System.out.println();
		System.out.format("%s%30s%s%30s%s\n","Name","", "type","","Date");
		System.out.println(String.format("%0" + 100 + "d", 0).replace("0","-"));
		try{
			for(Achievement e: currentStudent.achievements){
				if(e.approved){
					System.out.println(e);
				}
			}
		}catch(Exception e){
			System.out.format("%60s","No data available");
		}
		System.out.println();
	}
	
	
	public void achievementOptions(){
		while(true){
			System.out.println();
			Scanner input = new Scanner(System.in);
			System.out.println("(default) Back");
			int choice;
			if(Session.sessionUser instanceof Student){
				System.out.println("1. Add new Achievement");
				System.out.println();
				System.out.println(">> ");
				choice  =  input.nextInt();
				switch(choice){
				case 1:
					this.addNewAchievement();
					this.displayAchievements();
					break;
				default:
					return;
				}
			}
			else{
				choice = input.nextInt();
				switch(choice){
					default:
						return;
				}
			}
		}
	}
	
	
	public void addNewAchievement(){
		Scanner input = new Scanner(System.in);
		System.out.println("--------------------Add new Achievement--------------------------");
		System.out.println();
		System.out.println("Enter Achievement name: ");
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
		System.out.print("Enter type of achievement: ");
		String type = input.next();
		System.out.println("Enter proof statement: ");
		String proof  = input.next();
		System.out.println("Enter rank (If no ranks then put 0 ): ");
		int rank = input.nextInt();
		Achievement a = new Achievement(name,rank,type,proof,date,currentStudent);
		currentStudent.achievements.add(a);
		System.out.println(currentStudent.advisor.name);
		currentStudent.advisor.pendingAchievements.add(a);
		System.out.println("Thanks! Your achievement request has been sent to " + currentStudent.advisor.name + " for approval ");
		currentStudent.advisor.save(); //Created an instance of counsellor to save data
		currentStudent.save();
		
	}
	
}
