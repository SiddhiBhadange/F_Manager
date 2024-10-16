package com;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Fund {

    static Scanner sc = new Scanner(System.in);
    static String user;
    static Long phone;
    static String add;
    static String type;
    static int pin;
    static double bal;
    static double loanAmount = 0; // Loan balance
    static ArrayList<String> tran = new ArrayList<>();

    public static void main(String[] args) {
        for (;;) {
            System.out.println(" ***** WELCOME TO LAXMI CHIT FUND ***** ");
            System.out.println("1. New Account ");
            System.out.println("2. Login");
            System.out.print("Enter an option: ");
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    newAccount();
                    break;
                case 2:
                    loginUser();
                    break;
                default:
                    System.out.println("Wrong option entered");
            }
        }
    }

    public static void newAccount() {
        if (user != null) {
            System.out.println("Account is already created, please login.");
            return;
        }

        System.out.println("Account Creation");
        System.out.print("Username: ");
        sc.nextLine(); // consume the newline
        user = sc.nextLine();

        System.out.print("Phone Number: ");
        phone = sc.nextLong();

        System.out.print("Address: ");
        sc.nextLine(); 
        add = sc.nextLine();

        System.out.print("Pin: ");
        pin = sc.nextInt();

        System.out.print("Account type: ");
        sc.nextLine();
        type = sc.nextLine();

        System.out.print("Amount to be deposited: ");
        bal = sc.nextDouble();

        // Record transaction 
        tran.add("Credited: " + bal + " on " + getCurrentTime());
        System.out.println("Account Created");
    }

    public static void loginUser() {
        if (user == null) {
            System.out.println("CREATE YOUR ACCOUNT FIRST");
            return;
        }

        System.out.println("Login");
        System.out.print("Username: ");
        sc.nextLine(); // consume the newline
        String user1 = sc.nextLine();

        System.out.print("Pin: ");
        int pin1 = sc.nextInt();

        if (user1.equals(user) && pin == pin1) {
            features();
        } else {
            System.out.println("Wrong credentials");
        }
    }

    public static void features() {
        for (;;) {
            System.out.println("Features");
            System.out.println("1. Deposit Amount ");
            System.out.println("2. Debit Amount ");
            System.out.println("3. Check Balance");
            System.out.println("4. Transfer Amount");
            System.out.println("5. Statement ");
            System.out.println("6. Edit Account ");
            System.out.println("7. Loan & More ");
            System.out.println("8. Logout ");
            System.out.print("Enter an option: ");
            int option = sc.nextInt();

            switch (option) {
                case 1:depositAmount();break;
                case 2:debitAmount();break;
                case 3:checkBalance();break;
                case 4:transferAmount();break;
                case 5:statement();break;
                case 6:editAccount();break;
                case 7:loan();break;
                case 8:System.exit(0);
                default:System.out.println("Wrong option");
            }
        }
    }

    public static void statement() {
        System.out.println("Statement:");
        for (String i : tran) {
            System.out.println(i); // Show transaction history with timestamps
        }
    }

    public static void checkBalance() {
        System.out.print("Enter your pin: ");
        int pin1 = sc.nextInt();

        if (pin == pin1) {
            System.out.println("Your account balance is: " + bal + " rs");
        } else {
            System.out.println("Wrong pin entered");
        }
    }

    public static void depositAmount() {
        System.out.println("Deposit Amount");
        System.out.print("Enter an amount: ");
        double deposit = sc.nextDouble();

        bal += deposit;
        // Record transaction with timestamp
        tran.add("Credited: " + deposit + " on " + getCurrentTime());
        System.out.println("Amount Deposited");
    }

    public static void debitAmount() {
        System.out.println("Debit Amount");
        System.out.print("Enter an amount: ");
        double debit = sc.nextDouble();

        System.out.print("Enter your pin: ");
        int pin1 = sc.nextInt();

        if (pin == pin1) {
            if (debit <= bal) {
                bal -= debit;
                // Record transaction with timestamp
                tran.add("Debited: " + debit + " on " + getCurrentTime());
                System.out.println("Amount Debited");
            } else {
                System.out.println("Insufficient Funds");
            }
        } else {
            System.out.println("Wrong pin entered");
        }
    }

    // Transfer Amount feature
    public static void transferAmount() {
        System.out.println("Transfer Amount");
        System.out.print("Enter an amount to transfer: ");
        double transferAmount = sc.nextDouble();

        System.out.print("Enter your pin: ");
        int pin1 = sc.nextInt();

        if (pin == pin1) {
            if (transferAmount <= bal) {
                bal -= transferAmount;
                // Record transaction with timestamp
                tran.add("Transferred: " + transferAmount + " on " + getCurrentTime());
                System.out.println("Amount Transferred");
            } else {
                System.out.println("Insufficient Funds");
            }
        } else {
            System.out.println("Wrong pin entered");
        }
    }

    // Edit Account feature
    public static void editAccount() {
        System.out.println("Edit Account");
        System.out.print("Enter new phone number: ");
        phone = sc.nextLong();

        sc.nextLine(); // consume the newline
        System.out.print("Enter new address: ");
        add = sc.nextLine();

        System.out.println("Account details updated.");
    }

    // Loan feature
    public static void loan() {
        System.out.println("Loan Menu:");
        System.out.println("1. Apply for Loan");
        System.out.println("2. View Loan Balance");
        System.out.println("3. Repay Loan");
        System.out.println("4. Back to Main Menu");
        System.out.print("Enter your choice: ");
        int option = sc.nextInt();

        switch (option) {
            case 1:applyForLoan();break;
            case 2:viewLoanBalance();break;
            case 3:repayLoan();break;
            case 4:return; // Go back to the main menu
            default:System.out.println("Invalid option! Please try again.");
        }
    }

    public static void applyForLoan() {
        System.out.println("Apply for Loan:");
        System.out.print("Enter loan amount: ");
        double loanRequest = sc.nextDouble();

        System.out.print("Enter your CIBIL score: ");
        int cibilScore = sc.nextInt();

        System.out.print("Enter your pin: ");
        int pin1 = sc.nextInt();

        if (pin == pin1) {
            // Check CIBIL score
            if (cibilScore > 700) {
                loanAmount += loanRequest;
                bal += loanRequest;  
                // Record loan application with timestamp
                tran.add("Loan Applied: " + loanRequest + " on " + getCurrentTime());
                System.out.println("Loan of " + loanRequest + " has been granted.");
                System.out.println("Your updated balance is: " + bal + " rs");
            } else {
                System.out.println("Loan request denied due to low CIBIL score.");
            }
        } else {
            System.out.println("Wrong pin entered. Loan request denied.");
        }
    }

    public static void viewLoanBalance() {
        System.out.println("Loan Balance:");
        if (loanAmount > 0) {
            System.out.println("Your current loan balance is: " + loanAmount + " rs");
        } else {
            System.out.println("You have no outstanding loans.");
        }
    }

    public static void repayLoan() {
        System.out.println("Repay Loan:");
        System.out.print("Enter amount to repay: ");
        double repayAmount = sc.nextDouble();

        System.out.print("Enter your pin: ");
        int pin1 = sc.nextInt();

        if (pin == pin1) {
            if (repayAmount <= bal) {
                if (repayAmount <= loanAmount) {
                    loanAmount -= repayAmount;
                    bal -= repayAmount;
                    // Record repayment with timestamp
                    tran.add("Loan Repaid: " + repayAmount + " on " + getCurrentTime());
                    System.out.println("You have successfully repaid " + repayAmount + " rs.");
                    System.out.println("Remaining loan balance: " + loanAmount + " rs");
                } else {
                    System.out.println("You cannot repay more than your loan balance.");
                }
            } else {
                System.out.println("Insufficient funds to repay the loan.");
            }
        } else {
            System.out.println("Wrong pin entered. Repayment failed.");
        }
    }

    // Method to get the current date and time
    public static String getCurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return dtf.format(LocalDateTime.now());
    }
}