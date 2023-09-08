package Assignment1_6;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
        List<Student> failedstudents = listOfStudentfaile(studentList);
        List<Student> sortStudent = studentList.stream().sorted(Comparator.comparing(Student::getMarks)).collect(Collectors.toList());
        for(Student s:sortStudent){
            System.out.println(sortStudent.getClass()+"-----"+s.getName()+"-------");

        }

        List<Student> passedStudent = listOfStudentPassed(studentList);
        Student max = passedStudent.stream().max(Comparator.comparing(Student::getMarks, (s1, s2) -> s2.compareTo(s1))).get();
        return;
    }
    List<Student> listOfStudentfaile(List<Student> studentList){
        return     studentList.stream().filter(student -> student.getMarks()<50).collect(Collectors.toList());
    }
    List<Student> findStudentByClass(List<Student> studentList,List<Class> classList,String str){
        List<Integer> sectionId = classList.stream().filter(e ->str.equals(e.getName())).map(e -> e.getId()).collect(Collectors.toList());
        System.out.println(sectionId);
        return studentList.stream().filter(student -> sectionId.contains(student.getClassId())).collect(Collectors.toList());

    }
    List<Student> failByAge(List<Student> studentList){
        return studentList.stream().filter(student -> student.getAge()>20).collect(Collectors.toList());
    }
    void deleteStudent(int studentId , List<Student>studentList,List<Address> addressList){
        List<Student> list  = studentList.stream().dropWhile(e -> e.getId() == studentId).collect(Collectors.toList());
        List<Address>addressLIst = addressList.stream().dropWhile(address -> address.getStudentId()==studentId).collect(Collectors.toList());

    }
}
