package DAO.impl;

import DAO.StudentDAO;
import config.DBConfig;
import model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public List<Student> getAllStudentInfoByGroupId() {
        List<Student> students = new ArrayList<>();

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT \n" +
                "    s.id id,\n" +
                "    s.s_name name,\n" +
                "    s.s_surname surname,\n" +
                "    s.age age,\n" +
                "    s.seria_num seria_num,\n" +
                "    sci.phone phone,\n" +
                "    sci.email email\n" +
                "FROM\n" +
                "    studentmanagmenapp.student s\n" +
                "        LEFT JOIN\n" +
                "    student_contact_info sci ON s.contact_info_id = sci.id WHERE s.active = 1";
        try {
            c = DBConfig.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getLong("id"));
                    student.setName(rs.getString("name"));
                    student.setSurname(rs.getString("surname"));
                    student.setDOB(rs.getString("age"));
                    student.setSeriaNum(rs.getString("seria_num"));
                    student.setPhone(rs.getString("phone"));
                    student.setEmail(rs.getString("email"));
                    students.add(student);

                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                c.close();
                ps.close();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }
        return students;
    }

    @Override
    public boolean saveStudent(Student student) {

        boolean isAdded = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String insertIntoStudentContactInfo = "INSERT INTO studentmanagmenapp.student_contact_info (phone,email) VALUES(?,?)";

        String getLastStudentInfoId = "SELECT MAX(id) id FROM studentmanagmenapp.student_contact_info";

        String insertIntoStudent = "INSERT INTO studentmanagmenapp.student(s_name,s_surname,age,seria_num,gender,contact_info_id) VALUES (?,?,?,?,?,?)";

        String getLastStudentId = "SELECT MAX(id) id FROM studentmanagmenapp.student";

        String insertIntoGroupStudent = "INSERT INTO studentmanagmenapp.group_student (group_id,student_id) VALUES(?,?)";

        c = DBConfig.getConnection();
        if (c != null) {

            try {
                ps = c.prepareStatement(insertIntoStudentContactInfo);
                ps.setString(1, student.getPhone());
                ps.setString(2, student.getEmail());
                ps.execute();

                ps = c.prepareStatement(getLastStudentInfoId);
                rs = ps.executeQuery();
                long lastStudentInfoId = 0;
                if (rs.next()) {
                    lastStudentInfoId = rs.getLong("id");
                }

                ps = c.prepareStatement(insertIntoStudent);
                ps.setString(1, student.getName());
                ps.setString(2, student.getSurname());
                ps.setString(3, student.getDOB());
                ps.setString(4, student.getSeriaNum());
                ps.setString(5, student.getGender());
                ps.setLong(6, lastStudentInfoId);
                ps.execute();

                ps = c.prepareStatement(getLastStudentId);
                rs = ps.executeQuery();
                long lastStudentId = 0;
                if (rs.next()) {
                    lastStudentId = rs.getLong("id");
                }

                ps = c.prepareStatement(insertIntoGroupStudent);
                ps.setLong(1, student.getGroup().getId());
                ps.setLong(2, lastStudentId);
                ps.execute();

                isAdded = true;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());

            } finally {
                try {
                    ps.close();
                    c.close();

                } catch (Exception e) {

                }

            }

        }
        return isAdded;
    }

    @Override
    public Student getStudentInfoById(Long studentId) {
        Student student = new Student();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT \n" +
                "    s.id id,\n" +
                "    s.s_name name,\n" +
                "    s.s_surname surname,\n" +
                "    s.age age,\n" +
                "    s.seria_num seria_num,\n" +
                "    sci.phone phone,\n" +
                "    sci.email email\n" +
                "FROM\n" +
                "    studentmanagmentapp03.student s\n" +
                "        LEFT JOIN\n" +
                "    student_contact_info sci ON s.contact_info_id = sci.id WHERE s.active = 1 and s.id = " + studentId;
        try {
            c = DBConfig.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();

                if (rs.next()) {
                    student.setId(rs.getLong("id"));
                    student.setName(rs.getString("name"));
                    student.setSurname(rs.getString("surname"));
                    student.setDOB(rs.getString("age"));
                    student.setSeriaNum(rs.getString("seria_num"));
                    student.setPhone(rs.getString("phone"));
                    student.setEmail(rs.getString("email"));
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                c.close();
                ps.close();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }
        return student;
    }

    @Override
    public boolean updateStudentById(Student student) {
        boolean isUpdated = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String updateStudent = "UPDATE student SET s_name = ?,s_surname = ?,age = ?,seria_num = ?,gender = ? WHERE id = ?";

        String selectContactInfoIdById = "SELECT contact_info_id from student WHERE id ="+student.getId();

        String updateStudentContactInfo = "UPDATE student_contact_info SET phone = ?,email = ? WHERE id = ?";

        c = DBConfig.getConnection();
        if (c != null) {

            try {
                ps = c.prepareStatement(updateStudent);
                ps.setString(1, student.getName());
                ps.setString(2, student.getSurname());
                ps.setString(3, student.getDOB());
                ps.setString(4, student.getSeriaNum());
                ps.setString(5, student.getGender());
                ps.setLong(6, student.getId());
                ps.execute();

                ps = c.prepareStatement(selectContactInfoIdById);
                rs = ps.executeQuery();
                long contactInfoId = 0;
                if (rs.next()){
                    contactInfoId = rs.getLong("contact_info_id");
                }

                ps = c.prepareStatement(updateStudentContactInfo);
                ps.setString(1, student.getPhone());
                ps.setString(2, student.getEmail());
                ps.setLong(3, contactInfoId);
                ps.execute();

                isUpdated = true;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());

            } finally {
                try {
                    ps.close();
                    c.close();

                } catch (Exception e) {

                }

            }

        }
        return isUpdated;

    }
}



