\# Student Record Management System



\## About the Project

The Student Record Management System is a simple Java-based mini project that uses MySQL for storing and managing student information. It connects Java with MySQL using JDBC and provides basic operations to manage student records efficiently.



\## Features

\- Add new student records

\- View all student records

\- Search students by ID

\- Update student information

\- Delete student records

\- Store records in a MySQL database



\## Technologies Used

\- \*\*Programming Language:\*\* Java

\- \*\*Database:\*\* MySQL

\- \*\*Database Connectivity:\*\* JDBC

\- \*\*IDE/Editor:\*\* Notepad or any Java-supported editor

\- \*\*Version Control:\*\* Git and GitHub



\## Database Structure

Database name: `student\_db`



Table name: `students`



| Column | Description |

|---|---|

| `id` | Student ID (Primary Key) |

| `name` | Student name |

| `department` | Department name |

| `year` | Academic year as a number |

| `phone` | Student phone number |



\## Prerequisites

\- Java JDK installed

\- MySQL Server installed and running

\- MySQL Connector/J JDBC driver

\- Git installed (optional, for version control)



\## Setup and Installation



\### 1. Clone the Repository

```bash

git clone https://github.com/YOUR-USERNAME/Student-Record-Management-System.git

cd Student-Record-Management-System

```



Replace `YOUR-USERNAME` with your GitHub username.



\### 2. Create the Database

Run the following SQL commands in MySQL Workbench:



```sql

CREATE DATABASE student\_db;

USE student\_db;



CREATE TABLE students (

&#x20;   id INT PRIMARY KEY,

&#x20;   name VARCHAR(100) NOT NULL,

&#x20;   department VARCHAR(50),

&#x20;   year INT,

&#x20;   phone VARCHAR(15)

);

```



\### 3. Configure the Database Password

Set the `STUDENT\_DB\_PASSWORD` environment variable to your MySQL root password.



On Windows CMD:

```cmd

set /p STUDENT\_DB\_PASSWORD=Enter your MySQL password:

```



Enter your actual password when prompted. Do not commit your password to GitHub.



\### 4. Compile the Java Program

Ensure the MySQL Connector/J JAR file is available in the project folder, then run:



```cmd

javac -cp ".;mysql-connector-j-9.5.0.jar" StudentRecordManagement.java

```



\### 5. Run the Program

```cmd

java -cp ".;mysql-connector-j-9.5.0.jar" StudentRecordManagement

```



\## How to Use

1\. Run the program.

2\. Select an option from the menu.

3\. Enter the requested student details.

4\. Use the search, update, and delete options to manage records.

5\. Select \*\*Exit\*\* to close the application.



\*\*Note:\*\* Enter academic years as numbers: `1`, `2`, `3`, or `4`.



\## Learning Outcomes

\- Understanding Java programming and JDBC

\- Connecting Java applications to MySQL

\- Performing CRUD operations

\- Using SQL queries and prepared statements

\- Managing code with Git and GitHub



\## Future Enhancements

\- Add a graphical user interface

\- Implement student login and administrator authentication

\- Add input validation and report generation



\## Author

\*\*Your Name\*\*



\## License

This project is created for educational and learning purposes.



