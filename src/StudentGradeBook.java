import java.io.Serializable;
import java.util.Scanner;

public class StudentGradeBook implements Serializable {
		Student currentStudent;
		
		StudentGradeBook(Student s){
			this.currentStudent = s;
		}
		
		public void editGradeBook(){
			System.out.println("--------------------Edit GradeBook-----------------------------------------------------------");
			System.out.println();
			Scanner input = new Scanner(System.in);
			System.out.print("Enter the subject code: ");
			String code = input.next();
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
				g.Grade = input.next();
			}
			currentStudent.save();
		}
		
		public void gradeBookOptions(){
			while(true){
				System.out.println("(default) Back");
				
				Scanner input = new Scanner(System.in);
				if(Session.sessionUser instanceof Counsellor){
					System.out.println("1. Add new Grade");
					System.out.println("2. Update existing Grade");
					System.out.println();
					System.out.println(">> ");
					int choice  = input.nextInt();
					switch(choice){
						case 1:
							this.addGrade();
							this.displayGradeBook();
							break;
						case 2:
							this.editGradeBook();
							this.displayGradeBook();
							break;
						default:
							return;
					}
				}
				else{
					System.out.println();
					System.out.println(">> ");
					int choice = input.nextInt();
					switch(choice){
						default:
							return;
					}
				}
			}
		}
		
		
		public void addGrade(){
			System.out.println("--------------------Add new Grade-------------------------");
			System.out.println();
			Scanner input = new Scanner(System.in);
			System.out.print("SubjectCode: ");
			String code = input.next();
			System.out.print("Internal Marks: ");
			float internals = input.nextFloat();
			System.out.print("Semester Marks: ");
			float semester  = input.nextFloat();
			System.out.print("Grade: ");
			String grade = input.next();
			if(!this.containsSubjectGrade(code) && currentStudent.hasSubject(code)){
				currentStudent.gradeBook.GradeTable.add(new Grade(code,internals,semester,grade));
			}
			else{
				System.out.println("Unable to add Grade since student is not enrolled for the subject..");
			}
			currentStudent.save();
		}
		
		
		
		
		
		public Grade returnGrade(String code){
			for(Grade g: currentStudent.gradeBook.GradeTable){
				if(g.subject.code.equals(code)){
					return g;
				}
			}
			return null;
		}
		
		
		public void displayGradeBook(){
			System.out.println("------------------------------GradeBook----------------");
			System.out.println();
			System.out.println("GradeBook of " + currentStudent.name + " : ");
			try{
				System.out.println("Current CGPA: " +currentStudent.gradeBook.CGPA);
				System.out.println("Current SGPA: "+ currentStudent.gradeBook.SGPA);
				System.out.println("Attendance: "+currentStudent.attendance + "%");
			
				System.out.println("Grade Table: ");
				System.out.println();
				System.out.format("%s%30s%s%30s%s%30s%s\n", "Subject","","Internals","","Semester","","Grade");
				System.out.println(String.format("%0" + 120 + "d", 0).replace("0","-"));
				for(Grade g: currentStudent.gradeBook.GradeTable){
					System.out.println(g);
				}
				System.out.println();
				System.out.println();
				System.out.println("Arrears Subjects: ");
				System.out.println();
				for(Subject s:currentStudent.gradeBook.arrears){
					System.out.format("%s%30s%s%30s%s%30s%s\n","Subject","","Code","","Credits");
				}
				System.out.println(String.format("%0" + 100 + "d", 0).replace("0","-"));
			}catch(Exception e){
				System.out.println("No data available");
			}
			if(Session.sessionUser instanceof Counsellor || Session.sessionUser instanceof Administrator){
				this.gradeBookOptions();
			}
		}
		
		public  boolean containsSubjectGrade(String code){
			for(Grade g: currentStudent.gradeBook.GradeTable){
				if(g.subject.code.equals(code)){
					return true;
				}
			}
			return false;
		}
}
