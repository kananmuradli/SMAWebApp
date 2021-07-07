package service;

import model.Subject;
import java.util.List;

public interface SubjectService {

    List<Subject> getAllSubject();

    List<Subject> getAllSubjectWithout(Long id);
}
