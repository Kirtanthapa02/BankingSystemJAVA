package server;
import java.io.*;
import java.util.*;

public class AccountDetails{
	private String username;
	private String[] AllDetails;
	
	AccountDetails(String userID){
		username = userID;
		setAllDetails();
	}
	public void setAllDetails(){
		try{
			String filePath = "C:\\Users\\HP\\Documents\\java\\MyProject\\server\\CustomerDetails.csv";
			Scanner scanner = new Scanner(new File(filePath));
			scanner.useDelimiter(",");
			while(scanner.hasNextLine()){
				AllDetails = scanner.nextLine().split(",");
				
				if(AllDetails[6].equals(username)){
					break;
				}
				else{
					for(int i =0;i<10;i++){
						AllDetails[i] = "";
					}
				}
			}
			scanner.close();
		}catch(IOException e){
			System.out.println("Error reading:"+e.getMessage());
		}
	}
	public String toString(){
        return 	"Account holder name   :" + AllDetails[3]+" "+AllDetails[4] + "\n" +
               	"Email ID              :" + AllDetails[2] + "\n" +
               	"Mobile number         :" + AllDetails[5]+ "\n" +
		"Account number        :" + AllDetails[1]+"\n"+
		"Current balance       :" + AllDetails[8]+"\n"+
		"Permanent Address     :" + AllDetails[9]+"\n";
    	}
	public int getBalance(){
		int balance = Integer.valueOf(AllDetails[8]);
		return balance;
	}	
}
