# electric-billing-system-
Electric Billing system is a software based application developed in java programming language. the main objective is to manage the details of electricity,bill,connections ,store record of customers.
# Electric Billing System (Java)

## Description
This Java console application is an Electric Billing System that allows utility companies or administrative users to manage customer data, generate electricity bills based on consumption, and track payment statuses.

## Features
- Add new customers
- Generate electricity bills based on units consumed
- View customer bill history
- View all registered customers
- Mark bills as paid
- input validation and erroe handling
- enter meter reading
- calculate electricity bill

## Project Structure
Here’s a complete example of how your Electric Billing System (Java) repository should be structured, including a well-organized directory and a sample README.md file tailored to your code.


---

Recommended Repository Structure



---

README.md

# Electric Billing System (Java)

## Description
This Java console application is an Electric Billing System that allows utility companies or administrative users to manage customer data, generate electricity bills based on consumption, and track payment statuses.

## Features
- Add new customers
- Generate electricity bills based on units consumed
- View customer bill history
- View all registered customers
- Mark bills as paid

## Project Structure

src/ └── com/ └── billing/ ├── BillingSystem.java         # Main class with the application menu ├── Customer.java              # Class to manage customer information └── ElectricityBill.java       # Class for creating and managing bills

## Requirements
- Java 11 or higher
- IDE (optional): IntelliJ IDEA, Eclipse, or any Java-supported editor

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/electric-billing-system.git
   cd electric-billing-system

2. Compile the Code If using the command line:

javac -d out src/com/billing/*.java
or 
Compile with javac *.java


3. Run the Application

java -cp out com.billing.BillingSystem

Or simply run BillingSystem.java directly from your IDE.
or run with java main



How to Use

1. Launch the application.


2. Use the menu options to:

Add new customers

Generate and view bills

Mark bills as paid



3. Enter customer IDs and bill IDs as prompted.



Sample Bill Output

--- Bill Details ---
Bill ID: 4a9b2f8d3c
Customer Name: John Doe
Units Consumed: 120.50 kWh
Rate per Unit: $7.50
Bill Amount: $903.75
Bill Date: 26-05-2025
Due Date: 10-06-2025
Payment Status: Pending




Your Name – mahekrajput001@gmail.com


