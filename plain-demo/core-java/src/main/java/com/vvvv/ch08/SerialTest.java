package com.vvvv.ch08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerialTest {
    public static void main(String args[]) {
        String fileNameString = "em.ser";
        Employee emplpyee = new Employee();
        emplpyee.setAge(99);
        emplpyee.setName("john");
        emplpyee.setGender(true);
        try {
            emplpyee.save(fileNameString);
            outEm(fileNameString);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void outEm(String fileName) throws Exception {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object obj = ois.readObject();/* 反序列化 */
        Employee employee = (Employee) obj;
        System.out.println("From file em info:");
        System.err.println("Name:" + employee.getName());
        System.err.println("Age:" + employee.getAge());
        System.err.println("Gender:"
                + ((employee.getGender() == true) ? "Male" : "Female"));

    }
}

class Employee implements java.io.Serializable {
    private String name;
    private int age;
    private boolean gender;

    public Employee() {
        super();
    }

    public Employee(String name, int age, boolean gender) {
        super();
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String toString() {
        return this.getName() + this.getAge() + this.getGender();
    }

    public void save(String fileName) throws Exception {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
        fos.close();
    }
}
