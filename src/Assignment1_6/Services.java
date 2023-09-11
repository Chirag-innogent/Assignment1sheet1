package Assignment1_6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.util.stream.Collectors;

public class Services {
    List<Student> findByPincode(List<Student> studentList, int pinCode, List<Address> addressList){

        List<Integer> studentIds = addressList.stream().filter(e -> e.getPinCode()==pinCode).map(e->e.getStudentId()).collect(Collectors.toList());
        return    studentList.stream().filter(student->studentIds.contains(student.getId())).collect(Collectors.toList());
    }
    List<Student> findByCity(List<Student> studentList,String cityName,List<Address> addressList){
        List<Integer> ids = addressList.stream().filter(e -> e.getCity().equalsIgnoreCase(cityName)).map(e -> e.getStudentId()).collect(Collectors.toList());
        return studentList.stream().filter(student -> ids.contains(student.getId())).collect(Collectors.toList());
    }
    List<Student> listOfStudentPassed(List<Student> studentList){
        return     studentList.stream().filter(student -> student.getMarks()>=50).collect(Collectors.toList());
    }
    void reportCard(List<Student> studentList){
        List<Student> studentList1 = listOfStudentPassed(studentList);
        Map<Student,String> report=new HashMap<>();
        List<Student> sortedList = studentList1.stream().sorted(Comparator.comparing(Student::getMarks).reversed()).collect(Collectors.toList());

        report.put(sortedList.get(0),"First Rank");
        report.put(sortedList.get(1),"Second Rank");
        report.put(sortedList.get(2),"Third Rank");

        for (Student student : (sortedList.stream().skip(3).filter(e -> e.getMarks() >= 50).collect(Collectors.toList()))) {
            report.put(student,"pass");
        }
        for (Student student : (studentList.stream().filter(e -> e.getMarks() < 50).collect(Collectors.toList()))) {
            report.put(student,"fail");
        }
        report.forEach((k,v)-> System.out.println(k+" ::Status:"+v));
    }
    List<Student> listOfStudentfaile(List<Student> studentList){
        return     studentList.stream().filter(student -> student.getMarks()<50).collect(Collectors.toList());
    }
    List<Student> findStudentByClass(List<Student> studentList,List<Class> classList,String str){
        List<Integer> sectionId = classList.stream().filter(e ->str.equals(e.getName())).map(e -> e.getId()).collect(Collectors.toList());
        return studentList.stream().filter(student -> sectionId.contains(student.getClassId())).collect(Collectors.toList());

    }
    List<Student> failByAge(List<Student> studentList){
        return studentList.stream().filter(student -> student.getAge()>20).collect(Collectors.toList());
    }
    void deleteStudentFEromCsv(String filePathStudent ,String filePathAddress,int studentId,List<Address> addressList){
        // Read the CSV file into memory and exclude the line to remove

        List<String> lines = new ArrayList<>();
        try  {
            BufferedReader reader = new BufferedReader(new FileReader(filePathStudent));
            String line;
            int lineNumber = 0;
            if((line=reader.readLine())!=null) {
                lines.add(line);
            }
            while ((line = reader.readLine()) != null) {

                String [] student=line.split(",");

                if (Integer.parseInt(student[0]) !=studentId ) {
                    lines.add(line);
                }
                lineNumber++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Write the modified data back to the CSV file
        try  {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePathStudent));
            for (String line : lines) {
                writer.write(line);
                writer.newLine(); // Add a newline character to separate lines
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Student removed successfully.");
        try  {
            BufferedReader reader = new BufferedReader(new FileReader(filePathAddress));
            String line;
            int lineNumber = 0;
            if((line=reader.readLine())!=null) {
                lines.add(line);
            }
            while ((line = reader.readLine()) != null) {

                String [] student=line.split(",");

                if (Integer.parseInt(student[3]) !=studentId ) {
                    lines.add(line);
                }
                lineNumber++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Write the modified data back to the CSV file
        try  {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePathAddress));
            for (String line : lines) {
                writer.write(line);
                writer.newLine(); // Add a newline character to separate lines
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    List<Student> pagenation(List<Student> students, long startPoint,long endPoint,String gender ){
      return students.stream().skip(startPoint-1).limit(endPoint-startPoint+1).filter(student -> student.getGender().equals(gender))
                .collect(Collectors.toList());
    }
    List<Student> orderByMarks(List<Student> studentList){
        return studentList.stream().sorted(Comparator.comparing(Student::getMarks).reversed()).collect(Collectors.toList());
    }
    List<Student> filterByClass(List<Student> studentList,List<Class> classList,String section){
        int  classId = classList.stream().filter(cls -> cls.getName().equals(section)).map(c->c.getId()).findFirst().get();

        return studentList.stream().filter(student->student.getClassId()==classId).collect(Collectors.toList());
    }
    List<Student> filterByGender(List<Student> studentList,List<Class> classList,String gender){

        return studentList.stream().filter(student->student.getGender().equals(gender)).collect(Collectors.toList());
    }
}
