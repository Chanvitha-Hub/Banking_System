import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.lang.Math;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;

public class main {

	//String filepath = "file.txt";
	
	public static void main(String[] args) throws IOException {

		char repeat = 'n';
		ArrayList<BankAccount> bank_acc_list = new ArrayList<>();//BankAccount list
		main main_object = new main();
		
		ArrayList<BankAccount> acc_list = new ArrayList<>();
		acc_list = read_from_file("account_details.txt");
		for(int i=0; i<acc_list.size(); i++) {
			bank_acc_list.add(acc_list.get(i));
			System.out.println(bank_acc_list.get(i).getAccount_number());
		}
			
		do {
			System.out.println("\n\n\t\t****************BANKING SYSTEM****************\n");
			System.out.println("\tChoose what you want: \n");
			System.out.println("\t1. Create a bank account");
			System.out.println("\t2. Close a bank account");
			System.out.println("\t3. Withdraw money");
			System.out.println("\t4. Deposit money");
			System.out.println("\t5. Request balance and account details");
			System.out.println("\t6. Transfer money");
			
			System.out.print("\n\tEnter your choice: ");//user choice input
			Scanner scanner = new Scanner(System.in);
			int choice = scanner.nextInt();
			
			//choice input validation
			while(choice < 1 || choice > 6) {
				System.out.println("\tChoice is not a valid Input");
				System.out.print("\n\tEnter your choice: ");
				choice = scanner.nextInt();
			}
			//System.out.println();
			
			BankAccount bank_account = new BankAccount();//new object of BankAccout class
			int random_number = (int)Math.round(Math.random()*100000);//random number for account number
					
			ArrayList<BankAccount> temp_array_1 = new ArrayList<BankAccount>();
			ArrayList<BankAccount> temp_array_2 = new ArrayList<BankAccount>();
			ArrayList<BankAccount> temp_array_3 = new ArrayList<BankAccount>();
			//switch condition for 6 functionalities of a bank account
			switch(choice) { 
			
				//Create a bank account
				case 1: { 
					System.out.print("\tEnter your name: ");
					String name = scanner.next();
					bank_account.setName(name);
					System.out.print("\tEnter your age: ");
					int age = scanner.nextInt();
					bank_account.setAge(age);
					System.out.print("\tEnter your account number: ");
					int account_number = random_number;
					System.out.println(account_number);
					bank_account.setAccount_number(account_number);
					System.out.print("\tEnter your starting balance: ");
					long balance = scanner.nextLong();
					bank_account.setBalance(balance);
					
					bank_acc_list.add(bank_account); //add the created object into the list
					//main_object.WriteObjectToFile(bank_account);
					System.out.println("\tYou created a bank account successfully!!");
					
					for(int i=bank_acc_list.size()-1; i<bank_acc_list.size(); i++) {
						String output_text = bank_acc_list.get(i).getName() + "|" + 
								bank_acc_list.get(i).getAge() + "|" + 
								bank_acc_list.get(i).getAccount_number() + "|" + 
								bank_acc_list.get(i).getBalance();
						save_to_file("account_details.txt", output_text, true);
					}
					
					break;
				}
				
				//Close a bank account
				case 2: {
					System.out.print("\tEnter the account number: ");
					int acc_number = scanner.nextInt();
					
					for(int i=0; i<bank_acc_list.size(); i++) {
						if(bank_acc_list.get(i).getAccount_number() == acc_number) {
							bank_acc_list.remove(i); //add the created object into the list
							System.out.println("\tYou closed a bank account successfully!!");
							//break;
							
						}
						
						temp_array_1.add(bank_acc_list.get(i));
					}
					
					clearFile();
					
					for(int j=0; j<temp_array_1.size(); j++) {
						String output_text = temp_array_1.get(j).getName() + "|" + 
							bank_acc_list.get(j).getAge() + "|" + 
							bank_acc_list.get(j).getAccount_number() + "|" + 
							bank_acc_list.get(j).getBalance();
						
						save_to_file("account_details.txt", output_text, true);
					}
					break;
				}
				
				//Withdraw money
				case 3: {
					System.out.print("\tEnter the account number: ");
					int acc_number = scanner.nextInt();
					for(int i=0; i<bank_acc_list.size(); i++) {
						if(bank_acc_list.get(i).search(acc_number)) {
							long amount;  
					        System.out.print("\tEnter the amount you want to withdraw: ");  
					        amount = scanner.nextLong();
					        String previous_balance = parseString(bank_acc_list.get(i).getBalance());
					        bank_acc_list.get(i).withdraw_money(amount);
					        String last_balance = parseString(bank_acc_list.get(i).getBalance()-amount);
					        //replaceSelected(last_balance, previous_balance); 
					        break;
						}
					}
					break;
				}
				
				//Deposit money
				case 4: {
					System.out.print("\tEnter the account number: ");
					int acc_number = scanner.nextInt();
					for(int i=0; i<bank_acc_list.size(); i++) {
						if(bank_acc_list.get(i).search(acc_number)) {
							long amount;
							System.out.print("\tEnter the amount you want to deposit: ");
							amount = scanner.nextLong();
					        bank_acc_list.get(i).deposit_money(amount);
					        break;
						}
					}
					break;
				}
				
				//Request balance and account details
				case 5: {
					System.out.print("\tEnter the account number: ");
					int acc_number = scanner.nextInt();
					for(int i=0; i<bank_acc_list.size(); i++) {
						if(bank_acc_list.get(i).search(acc_number)) {
							bank_acc_list.get(i).show_account();
							break;
						}
//						else {
//							System.out.println("\tSorry!! There is no such bank account!!");
//							break;
//						}
					}
					break;
				}

				//transfer money
				case 6: {
					System.out.print("\tEnter the account number send from: ");
					int from_acc_number = scanner.nextInt();
					System.out.print("\tEnter the account number send To: ");
					int to_acc_number = scanner.nextInt();
					for(int i=0; i<bank_acc_list.size(); i++) {
						for(int j=0; j<bank_acc_list.size(); j++) {
							if(bank_acc_list.get(i).search(from_acc_number) && bank_acc_list.get(j).search(to_acc_number)) {
								long amount;
								System.out.print("\tEnter the amount you want to transfer: ");
								amount = scanner.nextLong();
								bank_acc_list.get(i).withdraw_money(amount);
								bank_acc_list.get(j).deposit_money(amount);
								break;
							}
						}
					}
					break;
				}
				
			}
			System.out.print("\n\tDo you want to continue (Y/N)");
			char letter = scanner.next().charAt(0);
			repeat = letter;
		}while(repeat == 'y' || repeat == 'Y');
					
	}//main function
	
	private static String parseString(long balance) {
		// TODO Auto-generated method stub
		return null;
	}

	//save to the file function
	public static void save_to_file(String file_name, String text, boolean append) throws IOException {
		File file1 = new File(file_name); //create a file
		FileWriter fw = new FileWriter(file1, append); //create a file writer class
		PrintWriter pw = new PrintWriter(fw); //create a print writer class
		pw.println(text);
		pw.close();
	}
	
	//read from the file function
	public static ArrayList<BankAccount> read_from_file(String file_name) throws FileNotFoundException {
		File file = new File(file_name);
		Scanner sc = new Scanner(file);
		ArrayList<BankAccount> acc_list = new ArrayList<BankAccount>();
		
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] items = line.split("\\|");
			
			String name = items[0];
			int age = Integer.parseInt(items[1]);
			int account_number = Integer.parseInt(items[2]);
			long balance = Long.parseLong(items[3]);
			
			BankAccount acc = new BankAccount(name, age, account_number, balance);
			acc_list.add(acc);
		}
		return acc_list;
	}
	
	//clear content of the file function
	public static void clearFile() { 
	    try{
	    FileWriter fw = new FileWriter("account_details.txt", false);
	    PrintWriter pw = new PrintWriter(fw, false);
	    pw.flush();
	    pw.close();
	    fw.close();
	    }
	    catch(Exception exception){
	        System.out.println("Exception have been caught");
	    }
	}
	
	public static void replaceSelected(String replaceWith, String type) {
	    try {
	        // input the file content to the StringBuffer "input"
	        BufferedReader file = new BufferedReader(new FileReader("account_details.txt"));
	        StringBuffer inputBuffer = new StringBuffer();
	        String line;

	        while ((line = file.readLine()) != null) {
	            inputBuffer.append(line);
	            inputBuffer.append('\n');
	        }
	        file.close();
	        String inputStr = inputBuffer.toString();

	        System.out.println(inputStr); // display the original file for debugging

	        // logic to replace lines in the string (could use regex here to be generic)
	        inputStr = inputStr.replace(replaceWith, type); 

	        // display the new file for debugging
	        System.out.println("----------------------------------\n" + inputStr);

	        // write the new string with the replaced line OVER the same file
	        FileOutputStream fileOut = new FileOutputStream("account_details.txt");
	        fileOut.write(inputStr.getBytes());
	        fileOut.close();

	    } catch (Exception e) {
	        System.out.println("Problem reading file.");
	    }
	}
	
}//main class




