package server;
import java.io.*;
import java.util.*;

public class Verification{
	private String accountNumber="";
	public boolean getAllAccountNumbers(String Anumber){
		try{
			String filePath = "C:\\Users\\HP\\Documents\\java\\MyProject\\server\\CustomerDetails.csv";
			Scanner scan = new Scanner(new File(filePath));
			scan.useDelimiter(",");
			
			while(scan.hasNextLine()){
				String[] details = scan.nextLine().split(",");
				accountNumber = details[1];
				if(accountNumber.equals(Anumber)){
					return true;
				}
			}
		}catch(Exception e){
			System.out.println("Exception thrown:"+e);
		}
	return	false;
	}	
	public boolean Search(String username,String password){
		try{
			String filePath = "C:\\Users\\HP\\Documents\\java\\MyProject\\server\\CustomerDetails.csv";
			Scanner scan = new Scanner(new File(filePath));
			scan.useDelimiter(",");
			
			while(scan.hasNextLine()){
				String[] details = scan.nextLine().split(",");
				String userID = details[6];
				System.out.println(userID);
				String passID = details[7];
				System.out.println(passID);
				if(userID.equals(username)&&passID.equals(password)){
					return true;
				}
			}
			
		}catch(Exception e){
			System.out.println("Exception thrown:"+e);
		}
	return	false;
	}
}

