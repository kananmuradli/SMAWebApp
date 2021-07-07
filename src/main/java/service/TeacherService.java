package service;

import model.Teacher;
import java.util.List;

public interface TeacherService {

    List<Teacher> getAllTeacherForComboBox();

    List <Teacher> getAllTeacherForComboBoxWithout(Long id);
}
