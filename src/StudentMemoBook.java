import java.io.Serializable;
import java.util.Scanner;

public class StudentMemoBook implements Serializable {
	Student currentStudent;
	
	StudentMemoBook(Student s){
		this.currentStudent = s;
	}
	
	public void displayMemos(){
		System.out.println("Memos of " + currentStudent.name + " : " );
		System.out.println();
		System.out.format("%s%30s%s%30s%s\n","Name","", "given By","","Date");
		System.out.println(String.format("%0" + 100 + "d", 0).replace("0","-"));
		try{
			for(Memo m: currentStudent.memos){
				System.out.println(m);
			}
		}
		catch(Exception e){
			System.out.format("%60s","No data available");
		}
		System.out.println();
	}
	
	public void addNewMemo(){
		Scanner input = new Scanner(System.in);
		System.out.println("Enter memo name: ");
		String name = input.next();
		System.out.println("Enter reason for memo: ");
		String reason = input.next();
		currentStudent.memos.add(new Memo(name,Session.sessionUser,currentStudent,reason));
	}
	
	public void MemoOptions(){
		while(true){
			System.out.println();
			Scanner input = new Scanner(System.in);
			System.out.println("(default) Back");
			int choice;
			if(Session.sessionUser instanceof Counsellor){
				System.out.println("1. Add new Memo");
				System.out.println();
				System.out.println(">> ");
				choice = input.nextInt();
				switch(choice){
				case 1:
					this.addNewMemo(); 
					this.displayMemos();
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
