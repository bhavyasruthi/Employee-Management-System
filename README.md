# Employee-Management-System_Sample-Hibernate-Project
Employee Management System manages Employee database using Hibernate, ORM tool. Project is developed by performing all CRUD, search operations on database.

### hibernate.cfg.xml 
This is hibernate configuration file.It contains configuration details of Session Factory and resource of hibernate mapping file. It contains database connection details such as connection url, username, password, dialect, Driver class etc. I used Oracle Driver in this project.

### employee.hbm.xml
This is hibernate mapping file which contains mapping details of all java objects which needs to br mapped to database tables. It mainly has :
- class - mapped to database table
- property - mapped to database column
- id - mapped to primary key of table
- meta - optional element used to create the class description
- generator - id element used to generate the primary key values automatically

### Employee.java
This is JavaBean class with setter and getter methods for properties of Employee class.

### EmployeeDataManager.java
It has all methods to access database table, Employee using Hibernate queries. After creating Configuration instance build a SessionFactory. From this SessionFactory, open a session and perform opertaions on database by beginning Transaction. 
1. addEmployee : This method adds new employee record. save() of hibernate pushes addition of record in to queue and once transaction is committed it will be added to database.
2. getById : This method returns employee details based on id. It uses get() of hibernate is used by sending hibernate class and id. 
3. UpdateById : This method updates employee details based on id. It uses update() of hibernate to update saved object. 
4. RemoveById : This method delete employee record based on id. It uses delete() of hibernate to delete saved object.
5. HQL : This method retrieves selected employee records by invoking HQL query using createQuery(). 
6. GetAllEmployees : This method retrieves all employee records by invoking HQL query using createQuery(). 
7. CritrtiaQueries : This method retrieves selected employee records by invoking Criteria using createQuery(). 
