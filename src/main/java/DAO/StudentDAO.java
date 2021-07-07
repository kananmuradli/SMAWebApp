package DAO;

import model.Student;
import java.util.List;

public interface StudentDAO {

    List<Student> getAllStudentInfoByGroupId();

    boolean saveStudent(Student student);

    Student getStudentInfoById(Long studentId);

    public boolean updateStudentById(Student student);
}

