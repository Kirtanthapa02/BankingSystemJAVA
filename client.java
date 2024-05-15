package client;
import java.io.*;
import java.net.*;
import java.math.*;
import java.util.Scanner;

public class client {
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter IPaddress:");
			String IPaddress = scanner.next();
			Socket socket = new Socket(IPaddress,1234); // connect to server on localhost:1234
            		System.out.println("Connected to server: " + socket);
            
            
            		DataInputStream in = new DataInputStream(socket.getInputStream());
            		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			boolean isValid = false;
	    		String message = in.readUTF();
            		System.out.println(message);
			while(true){
				System.out.println("Enter 1 to create a new account");
				System.out.println("Enter 2 to login");
				System.out.println("Enter 3 to exit");
				int ch = scanner.nextInt();
				out.writeInt(ch);
				switch(ch){
					case 1:
						System.out.println(in.readUTF());
						System.out.println(in.readUTF());
						System.out.println(in.readUTF());
				                String firstName = System.console().readLine();
						out.writeUTF(firstName);
						
						System.out.println(in.readUTF());
				                String LastName = System.console().readLine();
						out.writeUTF(LastName);
						
						System.out.println(in.readUTF());
				                String emailID = System.console().readLine();
						out.writeUTF(emailID);

						System.out.println(in.readUTF());
				                String address = System.console().readLine();
						out.writeUTF(address);
						
						System.out.println(in.readUTF());
				                String MobileNo = System.console().readLine();
						out.writeUTF(MobileNo);
						
						System.out.println(in.readUTF());
				                	
						System.out.println(in.readUTF());
				                String passID = System.console().readLine();
						out.writeUTF(passID);
						boolean amt = true;
						out.writeBoolean(amt);
						while(amt){
							System.out.println(in.readUTF());
							String amount = System.console().readLine();
							int result = Integer.valueOf(amount);
							if(result < 200000){
								out.writeBoolean(true);
								out.writeUTF(amount);
								break;
							}
							else{
								out.writeBoolean(false);
								System.out.println("cannot deposit more than 200000 online");
							}
						}
						System.out.println(in.readUTF());
	    					break;
					case 2:
						while(!isValid){
	    						System.out.println("Enter the username:");
	      						String username = scanner.next();

              						System.out.println("Enter the password:");
	      						String password = scanner.next();
							
	    						out.writeUTF(username);
							out.flush();
	    						out.writeUTF(password);
							out.flush();
							
	    						String text = in.readUTF();
	    						System.out.println(text);
            						if(text.equals("Login successful!")){
								    isValid = true;
	    						}
	   					}
						break;
					case 3:
						socket.close();
                        			System.out.println("Disconnected from server.");
						System.exit(0);
					default:
						String msg = in.readUTF();
						System.out.println(msg);
                        			break;
				}
			//break;
			//}
            		while (isValid) {
                		System.out.println("Enter 1 to check balance");
                		System.out.println("Enter 2 to deposit");
                		System.out.println("Enter 3 to withdraw");
				System.out.println("Enter 4 to transfer");
				System.out.println("Enter 5 to get Account details");
				System.out.println("Enter 6 to exit");
                		
                		int choice = scanner.nextInt();
                
                		out.writeInt(choice);
                		
                		switch (choice) {
                    			case 1:
                        			int balance = in.readInt();
                        			System.out.println("Your balance is " + balance);
                        			break;
                    			case 2:
                        			System.out.println("Enter amount to deposit:");
                        			int depositAmount = scanner.nextInt();
                        			out.writeInt(depositAmount);
						System.out.println(in.readUTF());
						break;
                    			case 3:
                        			System.out.println("Enter amount to withdraw:");
                        			int withdrawalAmount = scanner.nextInt();
                        			out.writeInt(withdrawalAmount);
                        			String withdrawalStatus = in.readUTF();
                        			System.out.println(withdrawalStatus);
                        			break;
					case 4:
						System.out.println("Enter the amount to transfer:");
						int transferAmount = scanner.nextInt();
						out.writeInt(transferAmount);
						System.out.println("Enter account number of the receipient");
						String accountNumber = scanner.next();
						out.writeUTF(accountNumber);
						String m = in.readUTF();
						System.out.println(m);
						break;
					case 5:
						System.out.println("Your AccountDetails are :");
						System.out.println(in.readUTF());
						break;
                    			case 6:
                        			socket.close();
                        			System.out.println("Disconnected from server.");
						System.exit(0);
                    			default:
                        			String msg = in.readUTF();
						System.out.println(message);
                        			break;
                		}
            		}
		    }
        	}catch (IOException e) {
            		e.printStackTrace();
        	}
	}
}
