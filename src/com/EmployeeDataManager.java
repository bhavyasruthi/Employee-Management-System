package com;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;  
import org.hibernate.criterion.Restrictions;
public class EmployeeDataManager {  
	static Scanner s= new Scanner(System.in);
public static void main(String[] args) {
	int choice=0,eid=0;
	boolean repeatOn=true;
	while(repeatOn){
		System.out.println("Enter your choice to perform actions on Employee database : 1-Add/n ,2-Get by id, 3-Update by id, 4-Remove by id, 5-HQL, 6-GetAllEmployees,7-CritrtiaQueries");
		choice = s.nextInt();
	switch(choice){
	case 1: addEmployee();
	        break;
	case 2: System.out.println("Enter id to display Employee Details");
			eid = s.nextInt();
	        getEmployee(eid);
	        break;
	case 3: System.out.println("Enter id to update Employee Details");
			eid = s.nextInt();
			updateEmployee(eid);
			break;
	case 4: System.out.println("Enter id to remove Employee Details");
			eid = s.nextInt();
			removeEmployee(eid);
			break;
	case 5 : invokeHQL();
	 		 break;
	case 6 : getAllEmployees();
			 break;
	case 7 : criteriaQuery();
	 			break;
	default: System.out.println("Exiting....Bye");
			 repeatOn=false;
	}
	}
}
private static void criteriaQuery() {
	SessionFactory sessionfactory =  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	Session session = sessionfactory.openSession();
	Criteria c= session.createCriteria(Employee.class);
	c.add(Restrictions.like("lname","%S%"));
	List<Employee> employees = (List<Employee>)c.list();
	for (Employee temp : employees) {
		System.out.println(temp.getFname()+temp.getLname()+":"+temp.getDesignation());
	}
}
private static void getAllEmployees() {
	SessionFactory sessionfactory =  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	Session session = sessionfactory.openSession();
    try{
	Query q= session.createQuery("from Employee");
	List<Employee> employees = (List<Employee>)q.list();
	for (Employee temp : employees) {
		System.out.println(temp.getFname()+":"+temp.getDesignation());
	}
    }
    catch(Exception e)
	{
		System.out.println(e);
	}
	finally{
		session.close();
		System.out.println("Successfully Retrieved....");
	}
}
private static void invokeHQL() {
	SessionFactory sessionfactory =  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	Session session = sessionfactory.openSession();
    try{
	Query q= session.createQuery("from Employee where fname like '%S%'");
	List<Employee> employees = (List<Employee>)q.list();
	for (Employee temp : employees) {
		System.out.println(temp.getFname()+temp.getLname()+":"+temp.getDesignation());
	}
    }
    catch(Exception e)
	{
		System.out.println(e);
	}
	finally{
		session.close();
		System.out.println("Successfully Retrieved....");
	}
}
    
public static void addEmployee(){
	SessionFactory sessionfactory =  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	Session session = sessionfactory.openSession();
    try{
	Transaction tnx = session.beginTransaction();
	Employee e1=new Employee(); 
	e1.setFname("Bhavya Sruthi");
	e1.setLname("sode");
	e1.setDesignation("Account Lead");
	Date d = java.sql.Date.valueOf("1987-11-15");
	e1.setDob(d);
	e1.setDoj(java.sql.Date.valueOf("2017-08-01"));
	session.save(e1);
	tnx.commit();
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	finally{
		session.close();
		System.out.println("Successfully saved....");
	}
  }
public static void getEmployee(int id){
	SessionFactory sessionfactory =  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	Session session = sessionfactory.openSession();

	try{
	Transaction tnx = session.beginTransaction();
	Employee emp = (Employee) session.get(Employee.class, id);
	if(emp == null)
		System.out.println("No Employee exists with such Id, please re-enter correct id");
	else{
	System.out.println("Employee Details are: ");
	System.out.println("Name - " + emp.getFname() + emp.getLname());
	System.out.println("Designation - " + emp.getDesignation());
	System.out.println("Date of Birth -  " + emp.getDob());
	System.out.println("Date of Joining -  " + emp.getDoj());
	tnx.commit();
	}
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	finally{
		session.close();
		System.out.println("Successfully Retrieved....");
	}
}
public static void updateEmployee(int id){
	SessionFactory sessionfactory =  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	Session session = sessionfactory.openSession();
    try{
	Transaction tnx = session.beginTransaction();
	System.out.println("Enter new designation you want to update");
	s.nextLine();
	String newDesignation = s.nextLine();
	Employee emp = (Employee) session.get(Employee.class, id);
	if(emp == null)
		System.out.println("No Employee exists with such Id, please re-enter correct id");
	else{
	emp.setDesignation(newDesignation);
	session.update(emp);
	System.out.println("Updated Employee Details are: ");
	System.out.println("Name - " + emp.getFname() + emp.getLname());
	System.out.println("Designation - " + emp.getDesignation());
	System.out.println("Date of Birth -  " + emp.getDob());
	System.out.println("Date of Joining -  " + emp.getDoj());
	tnx.commit();
	}
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	finally{
		session.close();
		System.out.println("Successfully Updated....");
	}
}
public static void removeEmployee(int id){
	SessionFactory sessionfactory =  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	Session session = sessionfactory.openSession();
    try{
	Transaction tnx = session.beginTransaction();
	Employee emp = (Employee) session.get(Employee.class, id);
	if(emp == null)
		System.out.println("No Employee exists with such Id, please re-enter correct id");
	else{
	session.delete(emp);
	tnx.commit();
	}
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	finally{
		session.close();
		System.out.println("Successfully Deleted....");
	}
}
}
