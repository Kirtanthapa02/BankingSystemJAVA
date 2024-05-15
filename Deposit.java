
package server;
import java.io.*;
import java.util.*;
public class Deposit{
	//private int currentBalance;
	private String username;
	
	Deposit(String userID){
		username = userID;
	}
	public void addBalance(int depositedAmt){
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
	
				if(currentLine[6].equals(username)){
					int currentBalance = Integer.valueOf(currentLine[8]);
					currentBalance += depositedAmt;
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
