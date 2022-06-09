# Expense-Reimbursement-System

## Project Description

The Expense Reimbursement System manages the process of reimbursing employees for expenses incurred.

## Technologies Used


- Javalin, version 4.1.1
- Java, version 1.8.0
- slf4j version 1.7.32
- Gradle
- Javascript
- HTML
- CSS
- React

## Features


- Employees are given the option to be registered into the system with a chosen unique username and a(n) auto generated account Id that will be associated with the account.

- An employee will be able to login to the account with the corresponding username and password, and submit a reimbursement request for later approval .

- The employee may display previous request that have been resolved and filter the result by type of reimbursement.

- Pending requests may also be viewed and its results can also be filtered by type.

- Managers are given the same functionality as employees, in additions to added features

- View all pending reimbursement requests made by the employee, and filter the request by type, author, and reimbursement id (as indvidual filters or cumulative)

- View all resolved requests and filter the results by reimbursement type, id, and author.

- Managers may choose to deny or approve pending requests (by clicking on react-icons) after filtering the results based on type, author, or reimbursement id.

- Employees and Managers are able to easily navigate through the page with the nav bar present on each screen

## Getting Started

1.Clone repository into the a working directory with the following command: git clone https://github.com/swathij943/ERS-Project-1.git

2.Run the Javalin server in the root directory 

3.Run the React front-end components by changing directory into ers-frontend folder from the root director: cd ers-frontend/ npm install (to install all the node modules used within the system) npm start

4.Setup the database using the script file provided in the resource folder located in app/src/main/resources/ers script.sql


## Usage

# As an Employee

1.Register a new user as an employee

2.Login using the same credential you used to register your account

3.Use the navigation bar at the top of the page to submit a reimbursement form

4.After navigating to the reimbursement form, fill out the form to submit a request

5.You may view any pending and resolved request by navigating to the corresponsing page

# As a Manager

1.After logging in, the navigation bar will include an extended features list

2.Viewing all pending requests will give you the option to filter and resolve the pending requests

3.Viewing all resolved request

4.Viewing all employees

# Contributors

Swathi Jayasree - Github: https://github.com/swathij943

Milan Verma - Github: https://github.com/MillanAir



