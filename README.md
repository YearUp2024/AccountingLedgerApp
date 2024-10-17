# Accounting LedgerApp
## YearUp Java Development Capstone 1


This application helps you track your financial transactions, including deposits, payments, and generating reports. It provides a user-friendly interface to manage and view your financial data.le, ensuring data persistence across 
sessions.

## Features

### Home Screen
The main menu offers the following options:

* D) Add Deposit: Enter deposit details to record incoming funds.
* P) Make Payment: Enter payment information to log outgoing expenses.
* L) Ledger: View transaction history with options to filter by type.
* X) Exit: Exit the application.

<img width="1043" alt="Screenshot 2024-10-17 at 6 34 20 PM" src="https://github.com/user-attachments/assets/efc6c4e8-cfa5-436c-bf36-4e85b6a42027">


### Ledger
* A) All: Display all transactions, with the most recent at the top.
* D) Deposits: Show only deposit entries (positive transactions).
* P) Payments: Display only payment entries (negative transactions).

### Reports
Generate predefined or custom reports:

1) Month To Date: Shows all transactions from the current month.
2) Previous Month: Displays transactions from the previous month.
3) Year To Date: Summarizes transactions for the current year.
4) Previous Year: Lists transactions from the last year.
5) Search by Vendor: Filter transactions by vendor name.
0) Exit: Go to the main screen.

### .CSV File Format
Each line in the transactions.csv file corresponds to a transaction, formatted as follows:
```
Date|Time|Description|Vendor|Amount
```

Example Entry: 
```
2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50
```


### Running the Application

1. **Compile the code**:
    ```sh
    git clone https://github.com/YearUp2024/AccountingLedgerApp.git
    ```

2. **Run the application**:
    ```sh
    cd AccountingLedgerApp
    ```
   
