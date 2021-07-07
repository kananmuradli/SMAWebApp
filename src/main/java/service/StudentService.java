package service;

import model.Student;
import java.util.List;

public interface StudentService {

    List<Student> getAllStudentInfoByGroupId(Long id);

    boolean saveStudent(Student student);

    Student getStudentInfoById(Long studentId);
}
