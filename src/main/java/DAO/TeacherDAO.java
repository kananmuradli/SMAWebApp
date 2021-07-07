package DAO;

import model.Teacher;

import java.util.List;

public interface TeacherDAO {

    List<Teacher> getAllTeacherForComboBox();

    List<Teacher> getAllTeacherForComboBoxWithout(Long id);
}
