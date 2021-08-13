# employee-department-management-system

This is a Simple Employee-Department Management System Java Application.<br>It helps you manage employees and departments and link them.<br>
This README will contain Instructions on **How to Run** the project and will go through all the **Functionalities** of the program.

## Description

This application will
* Automatically create database and tables, all you need to do is connect to it using your User Name and Password.
* Add Employees
* Assign/Add Employee Department
* Update Employee Department
* Search Employee (Using Name or Age or ID)
* Delete Employee
* Add Department
* Search Department (Using Name or ID)
* Delete Department
* Sort Table
* Log Every Activity
* Provide Documentation for backEnd() methods

All the data is easily visible in tables alongside each page.

## Getting Started

### Prerequisites

You _**MUST HAVE**_ Postgress installed and set up with User Name and Password.<br>
If you are unsure how to install Postgress a quick [YouTube Search](https://www.youtube.com) will help.<br>
Along with that, you will need Eclipse or some other similar program installed.
If still not sure, [reach out!](https://github.com/Utkarshthgr8)

### Executing program

_Tested On Eclipse_
* Download and extract the zip file linked up.
* Open Folder with Eclipse IDE.
* Run HomePage.java

### How-To

Start on HomePage()

* #### Check/Connect to the Database :<br>
Connect -> Enter User Name -> Enter Password -> Check/Initialise Connection.
>This will create a [Database](https://github.com/Utkarshthgr8/employee-department-management#the-database-) if it does not exist already, or simply connect to it if it exists already. You need to run this every time after starting the program, or the program will make you do it automatically if you don't ;)
* #### Create Employee :<br>
Employees -> Create Employee -> Fill up all the form -> Create
>This will create a new employee with the details entered. You can see the newly added employee in the table after receiving the "Employee added successfully" prompt.
* #### Add Employee Department :<br>
Employees -> Add Employee Department -> Add Department -> Fill up all the form -> Submit
>This will add the specified Department ID to the Employee ID. You can see the changes in the table after receiving the "Employee added to Department successfully" prompt.<br>_WARNING: Make sure to create the department first._
* #### Change Employee Department :<br>
Employees -> Add Employee Department -> Change Department -> Fill up all the form -> Submit
>This will change the Department ID of Employee from Old Department ID to New Department ID. You can see the changes in the table after receiving the "Changed Employee ID successfully" prompt.
* #### Search Employee :<br>
Employees -> Search/Delete -> Fill the details you know in form -> Search
>This will Search and Update the table with all the matching result(s) of your search. You can search with any mix and match of information you have, no need to fill in all the details.
* #### Delete Employee :<br>
Employees -> Search/Delete -> Fill the details you know in form -> Search -> Delete
>This will Delete the employee found using search. You can see the changes in the table after receiving the "Employee Deleted Successfully" prompt.<br>_WARNING: Deletes the last result in case multiple results are shown._
* #### Create Department :<br>
Departments -> Create Department -> Fill up all the form -> Create Department
>This will Create a new Department. You can see the newly added Department in the table.
* #### Search Department :<br>
Departments -> Search/Delete -> Fill the details you know in form -> Search
>This will Search for the Department. You can see the Department(s) found in the table.
* #### Delete Department :<br>
Departments -> Search/Delete -> Fill the details you know in form -> Search -> Delete
>This will Search for the Department. You can see the changes in the table receiving the "Department ID deleted successfully" prompt.<br>_WARNING: Deletes the last result in case multiple results are shown._
* #### Sort :<br>
Click on Column Name of any Table to Sort / Click again to Sort in reverse order
>This will Sort the data on the Table.

### The Database

When the 'Check/Initialise' button is pressed, the program automatically creates a new Database named 'employee_dept_management' in PostgreSQL with User Name and Password entered by the user if it doesn't exist already, if it already exists, the program will simply connect to it with the User Name and Password entered by the user. It will implement many-to-many relationship with the dept_id table and the emp_id table, with the emp_dept table as a bridge linking the two tables.
The Database will be created with the following TABLES:
* dept_db
* emp_db
* emp_dept

#### dept_db
This table stores Department Information and has columns:<br>
* dept_id : Stores the Department ID (Integer, Primary Key (Auto Generated), Foreign Key for emp_dept)
* dept_name : Stores the Departmet Name (text)

#### emp_db
This table stores Employee Information and has columns:<br>
* emp_id : Stores the Employee ID (Integer, Primary Key (Auto Generated), Foreign Key for emp_dept)
* emp_fname : Stores the Employees First Name (Text)
* emp_lname : Stores the Employees Last Name (Text)
* emp_age : Stores the Employee Age (Integer)

#### emp_dept
This table automatically connects the dept_db and emp_db to tell which Employee has which Department(s) or which Department has which Employee(s) and has columns:<br>
* emp_id : Stores the Employee ID (Integer, References emp_id from emp_db)
* dept_id : Stores the Department ID (Integer, References dept_id from dept_db)

### Special Features

* Keeps a log of every transaction with the Database.
* [Documentation](https://github.com/Utkarshthgr8/employee-department-management/blob/master/doc/overview-tree.html) available for major methods driving the Database.
* [Documentation](https://github.com/Utkarshthgr8/employee-department-management#the-database-) available on the implementation of Database.
* Makes you log in instead of throwing errors and stop working.
* Searching made easy with not needing to enter all the fields.

_Let me know if you have something to say about the project!_
