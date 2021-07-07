package DAO.impl;

import DAO.SubjectDAO;
import config.DBConfig;
import model.Subject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAOImpl implements SubjectDAO {

    @Override
    public List<Subject> getAllSubject() {
        List<Subject> subjects = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT s.id, s.subject_name FROM studentmanagmenapp.subjectss s WHERE active =1";
        try {
            c = DBConfig.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Subject subject = new Subject();
                    subject.setId(rs.getLong("id"));
                    subject.setSubjectName(rs.getString("subject_name"));
                    subjects.add(subject);

                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                c.close();
                ps.close();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }
        return subjects;
    }

    @Override
    public List<Subject> getAllSubjectWithout(Long id) {
        List<Subject> subjects = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT s.id, s.subject_name FROM studentmanagmenapp.subjectss s WHERE active =1 AND s.id !=" +id;
        try {
            c = DBConfig.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Subject subject = new Subject();
                    subject.setId(rs.getLong("id"));
                    subject.setSubjectName(rs.getString("subject_name"));
                    subjects.add(subject);

                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                c.close();
                ps.close();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }
        return subjects;
    }
}
