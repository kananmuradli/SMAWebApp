package service.impl;

import DAO.StudentDAO;
import model.Student;
import service.StudentService;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentDAO studentDAO;

    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public List<Student> getAllStudentInfoByGroupId(Long id) {
        return studentDAO.getAllStudentInfoByGroupId();
    }

    @Override
    public boolean saveStudent(Student student) {
        return studentDAO.saveStudent(student);
    }

    @Override
    public Student getStudentInfoById(Long studentId) {
        return studentDAO.getStudentInfoById(studentId);
    }
}
