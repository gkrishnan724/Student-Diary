import java.io.FileInputStream;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User{
	String rollNo;
	ArrayList<Subject> Subjects;
	float attendance;
	Marks gradeBook;
	Address hostelAddress;
	ArrayList<Achievement> achievements;
	ArrayList<Activity>  extraCurricular;
	ArrayList<Memo> memos;
	Counsellor advisor;
	
	static ArrayList<User> totalStudents = new ArrayList<User>();
	
	Student(){
		
	}
	
	Student(String name, String roll, Branch br){
		this.name = name;
		this.rollNo = roll;
		this.username = this.rollNo;
		this.password = "root";
		this.department = br;
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
		return "name: " + name + " rollNo: " + rollNo + " Department: "+ department;
	}
	
	public void displayMenu(){
		while(true){
			Scanner input = new Scanner(System.in);
			System.out.println("1. Profile");
			System.out.println("2. Courses");
			System.out.println("3. GradeBook");
			System.out.println("4. Achievements");
			System.out.println("5. Activities");
			System.out.println("(default) Back");
			System.out.println("");
			System.out.print(">>  ");
			int choice = input.nextInt();
			switch(choice){
				case 1:
					this.profileView();
					break;
				case 2:
					this.displayCourses();
					break;
				case 3:
					this.displayGradeBook();
					break;
				case 4:
					this.displayAchievements();
					break;
				case 5:
					this.displayActivites();
					break;
				default:
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
			System.out.println("Advisor: " + advisor.name);
			System.out.println("Overall Attendance: " + this.attendance + "%");
			System.out.println("Current CGPA: "+ this.gradeBook.CGPA);
			System.out.println();
			this.displayCourses();
			System.out.println();
			this.displayAchievements();
			System.out.println();
			this.displayActivites();
			System.out.println();
			this.displayMemos();
			System.out.println("---------------------------------------------------------");
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
					this.edit();
					break;
				case 2:
					this.addNewCourse();
					break;
				case 3:
					this.addNewAchievement();
					break;
				case 4:
					this.addNewActivity();
					break;
				default:
					return;
				
			}
		}
	}
	
	public void addNewCourse(){
		Scanner input = new Scanner(System.in);
		System.out.println("--------------------Add new Course--------------------------");
		System.out.println();
		System.out.print("CourseCode: ");
		String code = input.nextLine();
		Subject s = Subject.getSubject(code);
		if(s != null && !this.Subjects.contains(s)){
			this.Subjects.add(s);
		}
		else if(s == null){
			System.out.println("No subject with code: "+code+" Exists!");
		}
		else{
			System.out.println("This subject already registered by the student!");
		}
		this.save();
	}
	
	public void addNewActivity(){
		Scanner input = new Scanner(System.in);
		System.out.println("--------------------Add new Activity--------------------------");
		System.out.println();
		System.out.println("Enter Activity name: ");
		String name = input.nextLine();
		String format = "dd/mm/yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		System.out.print("Enter Date(dd/mm/yyyy): ");
		Date date = null;
		try{
			 date = formatter.parse(input.nextLine());
		}
		catch(Exception e){
			System.out.println("Input does not match the format (dd/mm/yyyy)");
		}
		System.out.print("Enter type of activity: ");
		String type = input.nextLine();
		Activity a = new Activity(name,type,date);
		this.extraCurricular.add(a);
		System.out.println("Thanks! Added your activity to the activities list.. ");
		this.save();
	}
	
	public void addNewAchievement(){
		Scanner input = new Scanner(System.in);
		System.out.println("--------------------Add new Achievement--------------------------");
		System.out.println();
		System.out.println("Enter Achievement name: ");
		String name = input.nextLine();
		String format = "dd/mm/yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		System.out.print("Enter Date(dd/mm/yyyy): ");
		Date date = null;
		try{
			 date = formatter.parse(input.nextLine());
		}
		catch(Exception e){
			System.out.println("Input does not match the format (dd/mm/yyyy)");
		}
		System.out.print("Enter type of achievement: ");
		String type = input.nextLine();
		System.out.println("Enter proof statement: ");
		String proof  = input.nextLine();
		System.out.println("Enter rank (If no ranks then put 0 ): ");
		int rank = input.nextInt();
		Achievement a = new Achievement(name,rank,type,proof,date);
		this.achievements.add(a);
		this.advisor.pendingAchievements.add(a);
		System.out.println("Thanks! Your achievement request has been sent to your counsellor for approval ");
		new Counsellor().save(); //Created an instance of counsellor to save data
		this.save();
		
	}
	
	public void displayCourses(){
		System.out.println("Registered Courses: ");
		System.out.format("%s%30s%s%30s%s\n","Name","","Course Code","","Credits");
		System.out.println(String.format("%0" + 100 + "d", 0).replace("0","-"));
		try{
			for(Subject s: Subjects){
				System.out.println(s);
			}
		}
		catch(Exception e){
			System.out.format("%60s","No data available");
		}
		System.out.println();
	}
	
	public void displayMemos(){
		System.out.println("Memos of " + this.name + " : " );
		System.out.format("%s%30s%s%30s%s\n","Name","", "given By","","Date");
		System.out.println(String.format("%0" + 100 + "d", 0).replace("0","-"));
		try{
			for(Memo m: memos){
				System.out.println(m);
			}
		}
		catch(Exception e){
			System.out.format("%60s","No data available");
		}
		System.out.println();
	}
	
	public void displayAchievements(){
		System.out.println("Achivements of " + this.name + " : ");
		System.out.format("%s%30s%s%30s%s\n","Name","", "type","","Date");
		System.out.println(String.format("%0" + 100 + "d", 0).replace("0","-"));
		try{
			for(Achievement e: achievements){
				if(e.approved){
					System.out.println(e);
				}
			}
		}catch(Exception e){
			System.out.format("%60s","No data available");
		}
		System.out.println();
	}
	
	public void displayActivites(){
		System.out.println("Activities in which " + this.name + "was involved : ");
		System.out.format("%s%30s%s%30s%s\n","Name","","Type","","Date");
		System.out.println(String.format("%0" + 100 + "d", 0).replace("0","-"));
		try{
			for(Activity e: extraCurricular){
				System.out.println(e);
			}
		}catch(Exception e){
			System.out.format("%60s","No data available");
		}
		System.out.println();
	}
	
	public void displayGradeBook(){
		System.out.println("------------------------------GradeBook----------------");
		System.out.println();
		System.out.println("GradeBook of " + this.name + " : ");
		System.out.println("Current CGPA: " +this.gradeBook.CGPA);
		System.out.println("Current SGPA: "+ this.gradeBook.SGPA);
		System.out.println("Attendance: "+this.attendance + "%");
		
		System.out.println("Grade Table: ");
		System.out.format("%s%30s%s%30s%s%30s%s\n", "Subject","","Internals","","Semester","","Grade");
		System.out.println(String.format("%0" + 100 + "d", 0).replace("0","-"));
		try{
			for(Grade g: this.gradeBook.GradeTable){
				System.out.println(g);
			}
		}catch(Exception e){
			System.out.format("%60s","No data available");
		}
		System.out.println();
		if(Session.sessionUser instanceof Counsellor || Session.sessionUser instanceof Administrator){
			this.gradeBookOptions();
		}
	}
	
	public void edit(){
		System.out.println("--------------------Edit Profile---------------------");
		System.out.println();
			Scanner input = new Scanner(System.in);
			System.out.print("Instructions: Leave the options blank if no change.");
			System.out.print("Name: ");
			this.name = input.hasNextLine()?input.nextLine():name;
			System.out.print("Change Address?(Y/N): ");
			if(input.nextLine().equals("Y")){
				System.out.print("Place: ");
				this.address.place = input.hasNextLine()?input.nextLine():this.address.place;
				System.out.print("District: ");
				this.address.district = input.hasNextLine()?input.nextLine():this.address.district;
				System.out.print("State: ");
				this.address.state = input.hasNextLine()?input.nextLine():this.address.state;
				System.out.print("ZIP: ");
				this.address.Zip = input.hasNextInt()?input.nextInt():this.address.Zip;		
			}
			System.out.print("Change Hostel Address?(Y/N): ");
			if(input.nextLine().equals("Y")){
				System.out.print("Place: ");
				this.hostelAddress.place = input.hasNextLine()?input.nextLine():this.hostelAddress.place;
			}
			System.out.print("Password?(Y/N): ");
			if(input.nextLine().equals("Y")){
				System.out.print("Enter current password: ");
				if(input.nextLine().equals(this.password)){
					System.out.print("Enter new password: ");
					this.password = input.hasNextLine()?input.nextLine():this.password;
				}
				else{
					System.out.println("Wrong Password, Password change failed!");
				}
			}
			this.save();
	}
	
	
	public void editGradeBook(){
		System.out.println("--------------------Edit GradeBook-----------------------------------------------------------");
		System.out.println();
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the subject code: ");
		String code = input.nextLine();
		Grade g = this.returnGrade(code);
		if(g == null){
			System.out.println("There is no Grade Entry for the following subject code..");
			return;
		}
		else{
			System.out.println("Instructions: Leave the input blank if no change. \n");
			System.out.print("Internal Marks: ");
			g.internals = input.nextFloat();
			System.out.print("Semester Marks: ");
			g.semester = input.nextFloat();
			System.out.print("Grade: ");
			g.Grade = input.nextLine();
		}
		this.save();
	}
	
	
	public void gradeBookOptions(){
		while(true){
			System.out.println("1. Add new Grades");
			System.out.println("2. Update Existing Grade Marks");
			System.out.println("(default) Back");
			System.out.println();
			System.out.print(">> ");
			Scanner input = new Scanner(System.in);
			int choice  = input.nextInt();
			switch(choice){
				case 1:
					this.addGrade();
					break;
				case 2:
					this.editGradeBook();
					break;
				default:
					return;
			}
		}
	}
	
	public void addGrade(){
		System.out.println("--------------------Add new Grade-------------------------");
		System.out.println();
		Scanner input = new Scanner(System.in);
		System.out.print("SubjectCode: ");
		String code = input.nextLine();
		System.out.print("Internal Marks: ");
		float internals = input.nextFloat();
		System.out.print("Semester Marks: ");
		float semester  = input.nextFloat();
		System.out.print("Grade: ");
		String grade = input.nextLine();
		if(!this.containsSubjectGrade(code) && this.hasSubject(code)){
			this.gradeBook.GradeTable.add(new Grade(code,internals,semester,grade));
		}
		else{
			System.out.println("Unable to add Grade since student is not enrolled for the subject..");
		}
		this.save();
	}
	
	
	public  boolean containsSubjectGrade(String code){
		for(Grade g: this.gradeBook.GradeTable){
			if(g.subject.code.equals(code)){
				return true;
			}
		}
		return false;
	}
	
	public boolean hasSubject(String code){
		for(Subject s: this.Subjects){
			if(s.code.equals(code)){
				return true;
			}
		}	
		return false;
	}
	
	public Grade returnGrade(String code){
		for(Grade g: this.gradeBook.GradeTable){
			if(g.subject.code.equals(code)){
				return g;
			}
		}
		return null;
	}
	
}
