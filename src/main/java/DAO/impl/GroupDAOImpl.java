package DAO.impl;

import DAO.GroupDAO;
import config.DBConfig;
import model.Group;
import model.Subject;
import model.Teacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDAOImpl implements GroupDAO {
    @Override
    public List<Group> getAllGroupInfo() {
        List<Group> groups = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT lg.id group_id, lg.group_name, t.id teacher_id, t.t_name, t.t_surname, s.id subject_id, s.subject_name FROM studentmanagmenapp.lesson_group lg\n" +
                "INNER JOIN studentmanagmenapp.teacher t\n" +
                "ON t.id = lg.teacher_id\n" +
                "INNER JOIN studentmanagmenapp.subjectss s\n" +
                "ON s.id = lg.subject_id WHERE lg.active = 1";
        try {
            c = DBConfig.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Group group = new Group();
                    group.setId(rs.getLong("group_id"));
                    group.setGroupName(rs.getString("group_name"));

                    Subject subject = new Subject();
                    subject.setId(rs.getLong("subject_id"));
                    subject.setSubjectName(rs.getString("subject_name"));

                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("teacher_id"));
                    teacher.setSurname(rs.getString("t_surname"));
                    teacher.setName(rs.getString("t_name"));

                    group.setSubject(subject);
                    group.setTeacher(teacher);
                    groups.add(group);


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
        return groups;
    }

    @Override
    public boolean saveGroup(Group group) {
        boolean isAdded = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "INSERT INTO lesson_group (group_name,teacher_id,subject_id) VALUES(?,?,?)";

        c = DBConfig.getConnection();
        if (c != null) {

            try {
                ps = c.prepareStatement(sql);
                ps.setString(1, group.getGroupName());
                ps.setLong(2, group.getTeacher().getId());
                ps.setLong(3, group.getSubject().getId());
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
    public Group getGroupInfoById(Long groupId) {
        Group group = new Group();

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT lg.id group_id, lg.group_name, t.id teacher_id, t.t_name, t.t_surname, s.id subject_id, s.subject_name FROM studentmanagmenapp.lesson_group lg\n" +
                "INNER JOIN studentmanagmenapp.teacher t\n" +
                "ON t.id = lg.teacher_id\n" +
                "INNER JOIN studentmanagmenapp.subjectss s\n" +
                "ON s.id = lg.subject_id WHERE lg.active = 1 and lg.id=" + groupId;
        try {
            c = DBConfig.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();

                if (rs.next()) {
                    group.setId(rs.getLong("group_id"));
                    group.setGroupName(rs.getString("group_name"));

                    Subject subject = new Subject();
                    subject.setId(rs.getLong("subject_id"));
                    subject.setSubjectName(rs.getString("subject_name"));

                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("teacher_id"));
                    teacher.setSurname(rs.getString("t_surname"));
                    teacher.setName(rs.getString("t_name"));

                    group.setSubject(subject);
                    group.setTeacher(teacher);

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
        return group;
    }

    @Override
    public boolean updateGroup(Group group) {
        boolean isUpdated = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String updateGroup = "UPDATE lesson_group lg SET lg.group_name = ?,lg.subject_id = ?,lg.teacher_id = ? WHERE lg.id = ?";

        c = DBConfig.getConnection();
        if (c != null) {

            try {
                ps = c.prepareStatement(updateGroup);
                ps.setString(1, group.getGroupName());
                ps.setLong(2, group.getSubject().getId());
                ps.setLong(3, group.getTeacher().getId());
                ps.setLong(4, group.getId());
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

    @Override
    public boolean softDeleteGroupById(Long id) {
        boolean isUpdated = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String softDeleteGroup = "UPDATE lesson_group lg SET lg.active = 0 WHERE lg.id = ?";

        c = DBConfig.getConnection();
        if (c != null) {

            try {
                ps = c.prepareStatement(softDeleteGroup);

                ps.setLong(1, id);
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
