package service.impl;

import DAO.SubjectDAO;
import model.Subject;
import service.SubjectService;
import java.util.List;

public class SubjectServiceImpl implements SubjectService {

    private SubjectDAO subjectDAO;

    public SubjectServiceImpl(SubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO;
    }

    @Override
    public List<Subject> getAllSubject() {
        return subjectDAO.getAllSubject();
    }

    @Override
    public List<Subject> getAllSubjectWithout(Long id) {
        return subjectDAO.getAllSubjectWithout(id);
    }
}
