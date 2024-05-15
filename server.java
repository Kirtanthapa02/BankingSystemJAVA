package server;
import java.io.*;
import java.net.*;
import server.Transfer;
import server.Verification;
import server.Deposit;
import server.CreateAccount;
import server.AccountDetails;
import server.Withdraw;

public class server {
	public static void main(String[] args) {
            		
            	while (true) {
			try {
				ServerSocket serverSocket = new ServerSocket(1234);
				System.out.println("Server started. Waiting for clients to connect...");
				boolean isLoggedIn = false;
                		Socket clientSocket = serverSocket.accept();
                		System.out.println("Client connected: " + clientSocket);
                		
                		DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                		DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
				String currentLogin = "";
				out.writeUTF("Welcome to my bank");
				
				while(true){
					
            				int ch = in.readInt();
					
					switch(ch){
						case 1:
							CreateAccount account = new CreateAccount();
							out.writeUTF("Your registration no. is " +account.getRegdNo());
							out.writeUTF("Please fill in the details");
							out.writeUTF("Enter your firstname:");
							String firstName = in.readUTF();
							out.writeUTF("Enter your LastName");
							String LastName = in.readUTF();
							out.writeUTF("Enter your email:");
							String emailID = in.readUTF();
							out.writeUTF("Enter address without comma:");
							String address = in.readUTF();
							out.writeUTF("Enter your Mobile no.");
							String MobileNo = in.readUTF();
							String userID = firstName+account.getRegdNo();
							out.writeUTF("Your username is "+userID);
							out.writeUTF("Enter a difficult login password");
							String passID = in.readUTF();
						        boolean amt = in.readBoolean();
							while(amt){
								out.writeUTF("Enter the amount to deposit(<200000)");
								boolean one = in.readBoolean();
								if(one){
									String amount = in.readUTF();
							account.setAll(emailID,firstName,LastName,MobileNo,userID,passID,amount,address);
								account.storeData();
									break;
								}
								else continue;
							}
							out.writeUTF("Account created successfully with account no:"+account.getAccountNo());
							
							break;
						case 2:
            						while (!isLoggedIn) {
                						String inputUsername = in.readUTF();
								String inputPassword = in.readUTF();
								Verification verify = new Verification();
								boolean yes = verify.Search(inputUsername,inputPassword);
								if (yes) {
                    							out.writeUTF("Login successful!");
									currentLogin = inputUsername;
                    							isLoggedIn = true;
                						} 
								else {
									out.writeUTF("Incorrect username or password.");
                						}
            						}
							break;
						case 3:
							clientSocket.close();
                            				System.out.println("Client disconnected: " + clientSocket);
                            				break;
						default:
							out.writeUTF("Invalid choice. Please try again.");
                            				break;
                			}				
                		while (isLoggedIn) {
					
					int choice = in.readInt();
					
                    			switch (choice) {
                        			case 1:
							AccountDetails getData1 = new AccountDetails(currentLogin);
							int balance1 = getData1.getBalance();
							out.writeInt(balance1);
                            				break;
                        			case 2:
							Deposit deposit = new Deposit(currentLogin);
               						int depositAmount = in.readInt();
							if(depositAmount<=100000){
								deposit.addBalance(depositAmount);
                            					out.writeUTF("Deposit successful.");
							}
							else{
								out.writeUTF("cannot deposit more than 100000 at once");
							}
                            				break;
                        			case 3:
							Withdraw withdraw = new Withdraw(currentLogin);
							int currentBalance = withdraw.getCurrentBalance();
                            				int withdrawalAmount = in.readInt();
                            				if (withdrawalAmount > currentBalance) {
                                				out.writeUTF("Withdrawal failed. Insufficient balance.");
                            				} 
							else {
                                				withdraw.deductBalance(withdrawalAmount);
                                				out.writeUTF("Withdrawal successful.");
                            				}
                            				break;
						case 4:
							int amountReceived = in.readInt();
							String receipient = in.readUTF();
							Verification accountNumber = new Verification();
							boolean present = accountNumber.getAllAccountNumbers(receipient);
							if(present){
								Withdraw deductCurrentLogin = new Withdraw(currentLogin);
								deductCurrentLogin.deductBalance(amountReceived);
								Transfer transfer = new Transfer(receipient,amountReceived);
								out.writeUTF("Amount transfered successfully");
							}
							else {
								out.writeUTF("Account number doesn't exist");
							}
							break;
						case 5:
							AccountDetails acct = new AccountDetails(currentLogin);
							String accountDetails = acct.toString();
							out.writeUTF(accountDetails);
							break;
                        			case 6:
                            				clientSocket.close();
                            				System.out.println("Client disconnected: " + clientSocket);
                            				break;
				
                        			default:
                            				out.writeUTF("Invalid choice. Please try again.");
                            				break;
						}
                		}
            		}
			}catch (IOException e) {
            			e.printStackTrace();
        		}
		}

    	}
}

