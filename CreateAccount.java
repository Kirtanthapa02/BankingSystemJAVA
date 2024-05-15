package server;
import java.io.*;
import java.util.*;

public class CreateAccount{
	private String FirstName,LastName,emailID,username,password,address,balance;
	private String MobileNo;
	private static long RegdNo;
	ArrayList<String> allRegister = new ArrayList<String>();
	private long AccountNo = 0;
	
	CreateAccount(){
		AccountNo = System.currentTimeMillis();
		RegdNo = AccountNo%1000;
	}
	public long getAccountNo(){
		return this.AccountNo;
	}
	public long getRegdNo(){
		return this.RegdNo;
	}
	public void setAll(String e,String f,String l,String m,String u,String p,String amt,String ad){
		FirstName = f;
		LastName = l;
		emailID = e;
		address = ad;
		MobileNo = m;
		username = u;
		password = p;
		balance = amt;
	}
	public void storeData(){
		String RegisterID = Long.toString(RegdNo);
		String AccountNumber = Long.toString(AccountNo);
		String[] customer = {RegisterID,AccountNumber,emailID,FirstName,LastName,MobileNo,username,password,balance,address};
		try{
			FileWriter fileWriter = new FileWriter("C:\\Users\\HP\\Documents\\java\\MyProject\\server\\CustomerDetails.csv",true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			bufferedWriter.append(String.join(",", customer));
			bufferedWriter.append('\n');
            		bufferedWriter.flush();
			bufferedWriter.close();
		}catch(IOException ex){
			System.out.println("Error writing to"+ex.getMessage());
		}
	}
}

