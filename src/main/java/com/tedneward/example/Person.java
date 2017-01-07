package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
	  private int age;
	  private String name;
	  private double salary;
	  private String ssn = "";
	  private boolean propertyChangeFired = false;
	  private static int count;
  
  public Person() {
	  this("", 0, 0.0d);
  }

  public Person(String n, int a, double s) {
	  this.name = n;
	  this.age = a;
	  this.salary = s;
	  count++;
  }
  
  public static class AgeComparator implements Comparator<Person> {
	  public int compare(Person p1, Person p2) {
		  return p1.age - p2.age;
	  }
  }
  
  @Override
  public String toString() {
	  return "[Person name:" + this.name + " age:" + this.age + " salary:" + this.salary + "]";
  }
  
  @Override
  public int compareTo(Person other) {
	  double diff = other.salary - this.salary;
	  if (diff < 0) {
		  return -1;
	  } else if (diff == 0) {
		  return 0;
	  } else {
		  return 1;
	  }
  }
  
  public boolean equals(Object other) {
	  if(other instanceof Person) {
		  Person p = (Person)other;
		  return this.age == p.age && this.name == p.name;
	  }
	  return false;
  }
  
  public static List<Person> getNewardFamily() {
	  List<Person> newardFamily = new ArrayList<Person>();
	  newardFamily.add(new Person("Ted", 41, 250000));
	  newardFamily.add(new Person("Charlotte", 43, 150000));
	  newardFamily.add(new Person("Michael", 22, 10000));
	  newardFamily.add(new Person("Matthew", 15, 0));
	  
	  return newardFamily;
  }
  
  public static int count() {
	  return count;
  }
  
  public int getAge() {
	  return this.age;
  }
  
  public String getName() {
	  return this.name;
  }
  
  public double getSalary() {
	  return this.salary;
  }
  
  public void setAge(int age) {
	  if (age < 0) {
		  throw new IllegalArgumentException();
	  }
	  this.age = age;
  }
  
  public void setName(String name) {
	  if (name == null) {
		  throw new IllegalArgumentException();
	  }
	  this.name = name;
  }
  
  public void setSalary(double salary) {
	  this.salary = salary;
  }

  public void setSSN(String value) {
	  String old = ssn;
	  ssn = value;
	
	  this.pcs.firePropertyChange("ssn", old, value);
	  propertyChangeFired = true;
  }
  public boolean getPropertyChangeFired() {
	  return propertyChangeFired;
  }

  public double calculateBonus() {
	  return salary * 1.10;
  }
  
  public String becomeJudge() {
	  return "The Honorable " + name;
  }
  
  public int timeWarp() {
	  return age + 10;
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
