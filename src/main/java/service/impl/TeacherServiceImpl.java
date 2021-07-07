package service.impl;

import DAO.TeacherDAO;
import model.Teacher;
import service.TeacherService;
import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    private TeacherDAO teacherDAO;

    public TeacherServiceImpl(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public List<Teacher> getAllTeacherForComboBox() {
        return teacherDAO.getAllTeacherForComboBox();
    }

    @Override
    public List<Teacher> getAllTeacherForComboBoxWithout(Long id) {
        return teacherDAO.getAllTeacherForComboBoxWithout(id);
    }
}
