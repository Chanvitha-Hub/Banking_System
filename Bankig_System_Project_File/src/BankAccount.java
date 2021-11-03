import java.util.Scanner;

public class BankAccount {
	
	private String name;
	private int age;
	private int account_number;
	private long balance;  
	
	Scanner scanner = new Scanner(System.in);  
	
	//default constructor
	public BankAccount() { 
		
	}
	
	//parameterize constructor
	public BankAccount(String name, int age, int account_number, long balance) { 
		this.name = name;
		this.age = age;
		this.account_number = account_number;
		this.balance = balance;
	}
	
	//setters and getters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAccount_number() {
		return account_number;
	}
	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}

	//public functions
	public void deposit_money(long amount) {
		balance += amount;
		System.out.println("\tSuccessfully deposited!! Balance after deposit: " + balance);
	}

	public void withdraw_money(long amount) {
        if (balance >= amount) {  
            balance -= amount;  
            System.out.println("\tSuccessfully withdrawed!! Balance after withdrawal: " + balance);  
        } 
        else {  
            System.out.println("\tYour balance is less than " + amount + "\tTransaction failed...!!" );  
        }  
    }  
	
	public Long request_balance() {
		System.out.println("\tBalance: " + balance);  
		return balance;
	}
	
	public void show_account() {  
        System.out.println("\tName of account holder: " + name);  
        System.out.println("\tAccount no.: " + account_number);  
        System.out.println("\tAccount holder's age: " + age);  
        System.out.println("\tBalance: " + balance);  
    }  
	
	public boolean search(int acc_number) {  
		if (this.account_number == acc_number) {  
            return (true);  
        }  
        else
        	return (false); 
    } 
	
}
