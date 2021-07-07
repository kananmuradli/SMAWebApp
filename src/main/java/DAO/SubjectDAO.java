package DAO;

import model.Subject;
import java.util.List;

public interface SubjectDAO {

    List<Subject> getAllSubject();

    List<Subject> getAllSubjectWithout(Long id);
}
