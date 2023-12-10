package PrototypePattern;

public class Student implements Protype{

    int rollNumber;
    String name;

    int age;

    @Override
    public String toString() {
        return "Student{" +
                "rollNumber=" + rollNumber +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


    public Student(int rollNumber, String name, int age){
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
    }
    @Override
    public Protype clone() {
        return new Student(rollNumber, name, age);
    }
}
