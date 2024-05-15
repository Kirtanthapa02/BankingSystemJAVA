package server;
import java.io.*;
import java.util.*;

public class Transfer{
	private String AccountNo;
	private int transferAmount;
	
	Transfer(String number,int amt){
		AccountNo = number;
		transferAmount = amt;
		addBalance();
	}
	public void addBalance(){
		try{
			String filePath = "C:\\Users\\HP\\Documents\\java\\MyProject\\server\\CustomerDetails.csv";
			Scanner sc = new Scanner(new File(filePath));
			RandomAccessFile file = new RandomAccessFile(filePath,"rw");
			long position = 0;
			FileWriter writer = new FileWriter(file.getFD());
			sc.useDelimiter(",");
			
			while(sc.hasNextLine()){
				file.seek(position);
				String[] currentLine = sc.nextLine().split(",");
	
				if(currentLine[1].equals(AccountNo)){
					int currentBalance = Integer.valueOf(currentLine[8]);
					currentBalance += transferAmount;
					String updatedBalance = String.valueOf(currentBalance);
					currentLine[8] = updatedBalance;
					writer.write(String.join(",", currentLine));
					writer.close();
					sc.close();
					break;
				}
				file.readLine();
				position = file.getFilePointer();
				
			}
			writer.close();
			sc.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
}
