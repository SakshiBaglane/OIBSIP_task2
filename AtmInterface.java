
//An ATM interface

/**********************************************
*Programmer              	: Sakshi Baglane
*Program name           	: AtmInterface.java
*Task Number                : 03
************************************************/

import java.io.Console;
import java.util.Scanner;

class BankAccount {
	String name;
	String userName;
	String password;
	String Password;
	String accountNo;
	int prevTransaction;
	int balance = 500000;
	int transactions = 0;
	int flag = 0;
	String transactionHistory = "";

	public void register() {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter Name: ");
		name = s.nextLine();
		System.out.print("Enter Username: ");
		this.userName = s.nextLine();
		Console console = System.console();
		if (console == null) {
			System.out.println("Couldn't get Console instance");
			System.exit(0);
		}
		char[] passwordArray = console.readPassword("Enter your password:");
		for (int i = 0; i < passwordArray.length; i++) {
			System.out.print("*");
		}
		password = new String(passwordArray);
		System.out.println("\nEnter the bank account Number: ");
		this.accountNo = s.nextLine();
		System.out.println("Registration successful..You can login To Proceed!!");
	}

	public void checkBalance() {
		System.out.println("Rs. " + balance);
	}

	public void deposit() {
		System.out.print("Enter amount to deposit: ");
		Scanner s = new Scanner(System.in);
		int amount = s.nextInt();
		try {
			if (amount <= 100000) {
				transactions++;
				balance += amount;
				prevTransaction = amount;
				System.out.println("Successfully Deposited!!");
				String str = "Rs." + amount + " deposited\n";
				transactionHistory = transactionHistory.concat(str);
			} else {
				System.out.println("Sorry...Limit is Rs.100000.");
			}
		} catch (Exception e) {
		}
	}

	public void withdraw() {
		System.out.print("Enter amount to withdraw: ");
		Scanner s = new Scanner(System.in);
		int amount = s.nextInt();
		try {
			if (balance >= amount) {
				transactions++;
				balance -= amount;
				prevTransaction -= amount;
				System.out.println("Withdrawl Successful!!");
				String str = "Rs." + amount + " withdrew\n";
				transactionHistory = transactionHistory.concat(str);
			} else {
				System.out.println("Insufficient Balance. Not possible for the withdrawl!");
			}
		} catch (Exception e) {
		}
	}

	public void transfer() {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter Receiver's Name: ");
		String receiver = s.nextLine();
		System.out.print("Enter amount to transfer: ");
		int amount = s.nextInt();
		try {
			if (balance >= amount) {
				if (amount <= 100000) {
					transactions++;
					balance -= amount;
					System.out.println(amount + " Successfully Transferred to " + receiver);
					String str = amount + " Rs. transferred to " + receiver + "\n";
					transactionHistory = transactionHistory.concat(str);
				} else {
					System.out.println("Sorry!! Limit is Rs.100000.");
				}
			} else {
				System.out.println("Transfer failed due to insufficient balance!");
			}
		} catch (Exception e) {
		}
	}

	public void getPrevTransaction() {
		if (prevTransaction > 0) 1{
			System.out.println("Deposited: " + prevTransaction);
		} else if (prevTransaction < 0) {

			System.out.println("Withdraw: " + Math.abs(prevTransaction));
		} else {
			System.out.println("No Transaction Occured!");
		}
	}

	public void transHistory() {
		if (transactions == 0) {
			System.out.println("Empty!!");
		} else {
			System.out.println("\n" + transactionHistory);
		}
	}

	public boolean login() {
		boolean isLogin = false;
		Scanner s = new Scanner(System.in);
		while (!isLogin) {
			System.out.print("Enter Username: ");
			String Username = s.nextLine();
			if (Username.equals(userName)) {
				while (!isLogin) {
					Console console = System.console();
					if (console == null) {
						System.out.println("Couldn't get console instance");
					}
					char[] PasswordArray = console.readPassword("Enter password:");
					for (int i = 0; i < PasswordArray.length; i++) {
						System.out.print("*");
					}
					Password = new String(PasswordArray);
					if (Password.equals(password)) {
						System.out.println("\nLogin Success!!");
						isLogin = true;
					} else {
						System.out.println("Oops!!! Incorrect Password...");
					}
				}
			} else {
				System.out.println("Username not found.");
			}
		}
		return isLogin;
	}
}

public class AtmInterface {
	public static int takeIntegers(int limit) {
		int s = 0;
		boolean flag = false;
		while (!flag) {
			try {
				Scanner sc = new Scanner(System.in);
				s = sc.nextInt();
				flag = true;
				if (flag && s > limit || s < 1) {
					System.out.println("Choose the number between 1 to " + limit + ".");
					flag = false;
				}
			} catch (Exception e) {
				System.out.println("Enter only integer value.");
				flag = false;
			}
		}
		;
		return s;
	}

	public static void main(String[] args) {
		System.out.println("----------- WELCOME TO ATM INTERFACE -----------\n");
		System.out.println("1.Register\n2.Exit");
		System.out.print("Enter your Choice: ");
		int choice = takeIntegers(2);
		if (choice == 1) {
			BankAccount a = new BankAccount();
			a.register();
			while (true) {
				System.out.println("\n1.Login\n2.Exit");
				System.out.print("Enter Your Choice: ");
				int ch = takeIntegers(2);
				if (ch == 1) {
					if (a.login()) {
						System.out.println("\n\n---------- WELCOME " + a.name + " ----------\n");
						boolean isFinished = false;
						while (!isFinished) {
							System.out.println(
									"\n1.Check the Balance\n2.Deposit\n3.Withdraw\n4.Transfer\n5.Get the Last Transaction\n6.Get Full Transaction History\n7.Exit");
							System.out.print("\nEnter Your Choice: ");
							int c = takeIntegers(7);
							switch (c) {
								case 1:
									a.checkBalance();
									break;
								case 2:
									a.deposit();
									break;
								case 3:
									a.withdraw();
									break;
								case 4:
									a.transfer();
									break;
								case 5:
									a.getPrevTransaction();
									break;
								case 6:
									a.transHistory();
									break;
								case 7:
									isFinished = true;
									break;
							}
						}
					}
				} else {
					System.exit(0);
				}
			}
		} else {
			System.exit(0);
		}
	}
}