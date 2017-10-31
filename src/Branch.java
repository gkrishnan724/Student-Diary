import java.util.ArrayList;
import java.io.*;
public class Branch implements Storeable {
	String name;
	ArrayList<Subject> subjects;
	static ArrayList<Branch> totalBranches = new ArrayList<Branch>();
	
	Branch(){
		totalBranches.add(this);
		this.save();
	}
	
	public void save(){
		ObjectOutputStream out = null;
		try{
			out = new ObjectOutputStream(new FileOutputStream("Branches"));
		}
		catch(Exception e){
			System.out.println("Unable to open Branch database..");
			return;
		}
		try{
			out.writeObject(totalBranches);
			out.close();
		}
		catch(Exception e){
			System.out.println("Unable to write new branch data..");
		}
		
	}
	
	public static ArrayList<Branch> get(){
		ObjectInputStream in;
		try{
			in =  new ObjectInputStream(new FileInputStream("Branches"));
		}catch(Exception e){
			System.out.println("Unable to locate Branch database..");
			return null;
		}
		try{
			totalBranches = (ArrayList<Branch>)in.readObject();
			in.close();
		}
		catch(Exception e){
			System.out.println("Unable to read subjects from database..");
			return null;
		}
		return totalBranches;
	}
	
}
