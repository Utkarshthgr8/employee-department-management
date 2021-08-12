# employee-department-management-system

This is a simple Employee-Department Management System Java Application.<br>It helps you manage employess and departments and link them.<br>
This README will contain Instructions on how to **Run The Application** and will go through all the functionalities of the program.

## Description

This application will
* Automatically create database and tables, all you need to do it connect to it using your User Name and Password.
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

All the data is easily visible in tables alongside each page.

## Getting Started

### Prerequisites

You _**MUST HAVE**_ Postgress installed and set up with User Name and Password.<br>
If you are unsure how to install Postgress a quick [YouTube Search](https://www.youtube.com) will definitely help.<br>
Along with that you will need 
If still not sure, [reach out!](https://github.com/Utkarshthgr8)

### Executing program

_Tested On Eclipse_
* Download and extract the zip file linked up.
* Open Folder with Eclipse IDE.
* Run HomePage.java

### How-To

Start on HomePage()

* ### Connect to Database :<br>
Connect -> Enter User Name -> Enter Password -> Check/Initialise Connection.
>This will create a database named emp_dept_management in PostgreSQL if it does not exist, or simply connect to it if it exists already. Need to run this everytime after starting the program, or the program will make you do it automatically if you don't ;)
* ### Create Employee :<br>
Employees -> Create Employee -> Fill up all the form -> Create
>This will create a new employee with the details entered. You can see the newly added employee in the table after recieving "Employee added successfully" promt.
* ### Add Employee Department :<br>
Employees -> Add Employee Department -> Add Department -> Fill up all the form -> Submit
>This will add the specified Department ID to the Employee ID. You can see the changes in the table after recieving "Employee added to Department successfully" promt.
* ### Change Employee Department :<br>
Employees -> Add Employee Department -> Change Department -> Fill up all the form -> Submit
>This will change Department ID of Employee from Old Department ID to New Department ID. You can see the changes in the table after recieving "Changed Employee ID successfully" promt.
* ### Search Employee :<br>
Employees -> Search/Delete -> Fill the details you know in form -> Search
>This will Search and Update table with all the matching result(s) of your search. You can search with any mix and match of information you have, no need to fill all the details.
* ### Delete Employee :<br>
Employees -> Search/Delete -> Fill the details you know in form -> Search -> Delete
>This will Delete the employee found using search. You can see the changes in the table after recieving "Employee Deleted Successfully" promt.
* ### Create Department :<br>
Departments -> Create Department -> Fill up all the form -> Create Department
>This will Create a new Department. You can see the newly added Department in the table.
* ### Search Department :<br>
Departments -> Search/Delete -> Fill the details you know in form -> Search
>This will Search for the Department. You can see the Department(s) found in the table.
* ### Delete Department :<br>
Departments -> Search/Delete -> Fill the details you know in form -> Search -> Delete
>This will Search for the Department. You can see the chenges in the table recieving "Department ID deleted successfully" prompt.
* ### Sort :<br>
Click on Column Name of any Table to Sort / Click again to Sort in reverse order
>This will Sort the data on the Table.

### Special Features

* Keeps log of every transaction with the Database.

_Let me know if you have something to say about the project!_
