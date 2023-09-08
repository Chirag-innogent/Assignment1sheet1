package Assignment1_6;

import java.io.File;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Assignment1 {
    public static void main(String[] args) {
        String studentFilePath="C:\\Users\\chira\\IdeaProjects\\Assignment1-06\\src\\Assignment1_6\\student.csv";
        String addressFilePath="C:\\Users\\chira\\IdeaProjects\\Assignment1-06\\src\\Assignment1_6\\address.csv";
        String classFilePath="C:\\Users\\chira\\IdeaProjects\\Assignment1-06\\src\\Assignment1_6\\class.csv";

        List<Student> students = uploadStudent(studentFilePath);
        List<Address> addresses = uploadAddress(addressFilePath);
        List<Class> classes = uploadClass(classFilePath);
        System.out.println(students);
        System.out.println(addresses);
        System.out.println(classes);
        Services services= new Services();
        //Question 1
        List<Student> students1 = services.findByPincode(students,452002,addresses);
        for(Student s:students1){
            System.out.println(s);
        }
        System.out.println("-----------------");

        //Question2
        List<Student> studentList= services.findByCity(students,"Indore",addresses);
        for(Student s:studentList){
            System.out.println(s);
        }
        System.out.println("-----------------");
        //Question3
        services.reportCard(studentList);
        //Question4
        List<Student> studentPassed= services.listOfStudentPassed(students);
        System.out.println(studentPassed);
        //Question5
        List<Student> studentFailed= services.listOfStudentfaile(students);
        System.out.println(studentFailed);
        //Question6
        List<Student>  studentInSeccion= services.findStudentByClass(students,classes,"A");
        System.out.println("section"+studentInSeccion);
        //Question8
        List<Student> studentFailByAge= services.failByAge(students);
        System.out.println(studentFailByAge);
        //Question 9

    }
    static List<Student> uploadStudent(String filePath){
        List<Student> students= new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            // Skip the header line
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            // Read and process the rest of the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Process the line here (e.g., split it into fields)
                String[] fields = line.split(",");
                Student s1=new Student(Integer.parseInt(fields[0]),fields[1],Integer.parseInt(fields[2]),Integer.parseInt(fields[3]),fields[4],Integer.parseInt(fields[5]));
                students.add(s1);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }
    static List<Address> uploadAddress(String filePath){
        List<Address> address= new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            // Skip the header line
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            // Read and process the rest of the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Process the line here (e.g., split it into fields)
                String[] fields = line.split(",");
                Address a1=new Address(Integer.parseInt(fields[0]),Integer.parseInt(fields[1]),fields[2],Integer.parseInt(fields[3]));
                address.add(a1);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }
    static List<Class> uploadClass(String filePath){
        List<Class> students= new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            // Skip the header line
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            // Read and process the rest of the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Process the line here (e.g., split it into fields)
                String[] fields = line.split(",");
                Class s1=new Class(Integer.parseInt(fields[0]),fields[1]);
                students.add(s1);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

}
