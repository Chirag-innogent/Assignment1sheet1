package Assignment1_6;

import java.io.File;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        students1.stream().forEach(System.out::println);
        System.out.println("-----------------");

        //Question2
        List<Student> studentList= services.findByCity(students,"Indore",addresses);
        studentList.stream().forEach(System.out::println);
        System.out.println("*************************");
        //Question3
        services.reportCard(students);
        System.out.println("----------Sorted-------");
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
        System.out.println("Question 8");
        List<Student> studentFailByAge= services.failByAge(students);
        System.out.println(studentFailByAge);
        //Question 9
    /*    int studentId=1;
         Student student1 = studentList.stream().filter(e -> e.getId() == studentId).findFirst().get();
        List<Address>addressList = addresses.stream().filter(address -> address.getStudentId()==studentId).collect(Collectors.toList());
        students.remove(student1);
        addresses.removeAll(addressList);
*/
        //Question 10
        if(students.isEmpty())
            students=null;
        if(addresses.isEmpty())
            addresses=null;

        //Question11
        System.out.println("Question 11");
        List<Student> femaleStudentsPage1To9 = services.pagenation(students,1,9,"F");
        femaleStudentsPage1To9.stream().forEach(System.out::println);
        System.out.println("-------------------------");

        services.pagenation(students,7,8,"F").forEach(System.out::println);
        System.out.println("-------------------------");
        List<Student> list1=services.pagenation(students,1,5,"F");
        services.orderByMarks(list1).forEach(System.out::println);
        System.out.println("-------------------------");
        List<Student> list2 = services.pagenation(students, 9, 50, "F");
        services.orderByMarks(list2).forEach(System.out::println);

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
