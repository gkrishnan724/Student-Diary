import java.io.FileInputStream;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;
//ToDo Removing Entries;
public class Student extends User{
	String rollNo;
	ArrayList<Subject> Subjects;
	float attendance;
	Marks gradeBook;
	Address hostelAddress;
	ArrayList<Achievement> achievements;
	ArrayList<Activity> extraCurricular;
	ArrayList<Memo> memos;
	ArrayList<Fee> penalties;
	Counsellor advisor;
	StudentGradeBook gradeOptions;
	StudentCourseBook courseOptions;
	StudentMemoBook memoOptions;
	StudentActivityOptions activityOptions;
	StudentAchievementBook achievementOptions;
	
	static ArrayList<User> totalStudents = new ArrayList<User>();
	
	Student(){
		
	}
	
	Student(String name, String roll, Branch br){
		this.name = name;
		this.rollNo = roll;
		this.username = this.rollNo;
		this.address = new Address();
		this.hostelAddress = new Address();
		this.Subjects = new ArrayList<Subject>();
		this.password = "root";
		this.department = br;
		this.gradeBook = new Marks();
		this.achievements = new ArrayList<Achievement>();
		this.extraCurricular = new ArrayList<Activity>();
		this.memos = new ArrayList<Memo>();
		this.penalties = new ArrayList<Fee>();
		this.advisor = new Counsellor();
		this.gradeOptions = new StudentGradeBook(this);
		this.courseOptions = new StudentCourseBook(this);
		this.memoOptions = new StudentMemoBook(this);
		this.activityOptions = new StudentActivityOptions(this);
		this.achievementOptions = new StudentAchievementBook(this);
		
		totalStudents.add(this);
		
		this.save();
    }
	
	
	public static ArrayList<User> get(){
		ObjectInputStream in = null;
		
		try{
			in = new ObjectInputStream(new FileInputStream("Users/Students")); 
		}
		catch(Exception e){
			System.out.println("Unable to reach Student database..");
			return null;
		}
		try{
			totalStudents = (ArrayList<User>)in.readObject();
			in.close();
		}
		catch(Exception e){
			System.out.println("Unable to read data from file..");
			return null;
		}
		
		return totalStudents;
	}
	
	public String toString(){
		return "name: " + name + " rollNo: " + rollNo + " Department: "+ department.name;
	}
	
	public void displayMenu(){
		while(true){
			Session.clrscr();
			Scanner input = new Scanner(System.in);
			System.out.println("Hi " + Session.sessionUser.name + "!");
			System.out.println("------------------------------------------------------");
			System.out.println();
			System.out.println("1. Profile");
			System.out.println("2. Courses");
			System.out.println("3. GradeBook");
			System.out.println("4. Achievements");
			System.out.println("5. Activities");
			System.out.println("(default) Logout");
			System.out.println("");
			System.out.print(">>  ");
			int choice = input.nextInt();
			switch(choice){
				case 1:
					Session.clrscr();
					this.profileView();
					break;
				case 2:
					Session.clrscr();
					courseOptions.displayCourses();
					courseOptions.courseOptions();
					break;
				case 3:
					Session.clrscr();
					gradeOptions.displayGradeBook();
					gradeOptions.gradeBookOptions();
					break;
				case 4:
					Session.clrscr();
					achievementOptions.displayAchievements();
					achievementOptions.achievementOptions();
					break;
				case 5:
					Session.clrscr();
					activityOptions.displayActivites();
					activityOptions.activitiesOption();
					break;
				default:
					Session.clrscr();
					return;
				
			}
		}
		
	}
	
	public void profileView(){
		while(true){
			Scanner input = new Scanner(System.in);
			System.out.println("---------------------"+ this.name +"-----------------------------");
			System.out.println(this);
			System.out.println(address);
			try{
				System.out.println("Advisor: " + advisor.name);
				System.out.println("Overall Attendance: " + this.attendance + "%");
				System.out.println("Current CGPA: "+ this.gradeBook.CGPA);
				System.out.println();
				courseOptions.displayCourses();
				System.out.println();
				achievementOptions.displayAchievements();
				System.out.println();
				activityOptions.displayActivites();
				System.out.println();
				memoOptions.displayMemos();
			}
			catch(NullPointerException e){
				System.out.println("Details not available");
			}
			if(Session.sessionUser instanceof Student){
				System.out.println("1. edit");
				System.out.println("2. Register for new Course");
				System.out.println("3. Add new Achievement");
				System.out.println("4. Add new Activity");
				System.out.println("(default) Back");
				System.out.println();
				System.out.print(">> ");
				int choice = input.nextInt();
				switch(choice){
					case 1:
						Session.clrscr();
						this.edit();
						break;
					case 2:
						Session.clrscr();
						courseOptions.addNewCourse();
						break;
					case 3:
						Session.clrscr();
						achievementOptions.addNewAchievement();
						break;
					case 4:
						Session.clrscr();
						activityOptions.addNewActivity();
						break;
					default:
						return;
					
				}
			}
			else{
				System.out.println("1. Add new Memo");
				System.out.println("2. See GradeBook");
				System.out.println("(default) Back");
				System.out.println();
				System.out.print(">> ");
				int choice = input.nextInt();
				switch(choice){
					case 1:
						Session.clrscr();
						this.memoOptions.addNewMemo();
						break;
					case 2:
						Session.clrscr();
						this.gradeOptions.displayGradeBook();
						break;
					default:
						Session.clrscr();
						return;
					
				}
			}
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
				this.address.country = input.hasNextLine()?input.next():this.address.country;
				System.out.print("ZIP: ");
				this.address.Zip = input.hasNextInt()?input.nextInt():this.address.Zip;		
			}
			System.out.print("Change Hostel Address?(Y/N): ");
			if(input.next().equals("Y")){
				System.out.print("Place: ");
				this.hostelAddress.place = input.hasNextLine()?input.next():this.hostelAddress.place;
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
			this.save();

	}

			
	public boolean hasSubject(String code){
		for(Subject s: this.Subjects){
			if(s.code.equals(code)){
				return true;
			}
		}	
		return false;
	}
	
	public static void showAllStudents(){
		System.out.println("----------------Students-----------------------");
		for(User s: Student.totalStudents){
			System.out.println(s.name);
		}
	}
	
	public static Student returnStudent(String roll){
		for(User s: Student.totalStudents){
			if(s.username.equals(roll)){
				return (Student)s;
			}
		}
		return null;
	}
	
		
}
