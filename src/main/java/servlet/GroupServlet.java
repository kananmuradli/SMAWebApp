package servlet;

import DAO.GroupDAO;
import DAO.StudentDAO;
import DAO.SubjectDAO;
import DAO.TeacherDAO;
import DAO.impl.GroupDAOImpl;
import DAO.impl.StudentDAOImpl;
import DAO.impl.SubjectDAOImpl;
import DAO.impl.TeacherDAOImpl;
import model.Group;
import model.Subject;
import model.Teacher;
import service.GroupService;
import service.StudentService;
import service.SubjectService;
import service.TeacherService;
import service.impl.GroupServiceImpl;
import service.impl.StudentServiceImpl;
import service.impl.SubjectServiceImpl;
import service.impl.TeacherServiceImpl;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/groups", name = "GroupServlet")
public class GroupServlet extends HttpServlet {

    GroupDAO groupDao = new GroupDAOImpl();
    GroupService groupService = new GroupServiceImpl(groupDao);

    TeacherDAO teacherDAO = new TeacherDAOImpl();
    TeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    SubjectDAO subjectDAO = new SubjectDAOImpl();
    SubjectService subjectService = new SubjectServiceImpl(subjectDAO);

    StudentDAO studentDAO = new StudentDAOImpl();
    StudentService studentService = new StudentServiceImpl(studentDAO);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response){
        try {
            String action = null;
            String address = "";

            if (request.getParameter("action") != null){
                action = request.getParameter("action");
            }

            if (action.equalsIgnoreCase("saveGroup")){

                Group group = new Group();
                group.setGroupName(request.getParameter("groupName"));

                Teacher teacher = new Teacher();
                teacher.setId(Long.parseLong(request.getParameter("teacher")));

                Subject subject = new Subject();
                subject.setId(Long.parseLong(request.getParameter("subject")));

                group.setTeacher(teacher);
                group.setSubject(subject);
                groupService.saveGroup(group);

                response.sendRedirect("http://localhost:8080/groups?action=allGroups");


            }else if (action.equalsIgnoreCase("allGroups")){

                request.setAttribute("subjectList", subjectService.getAllSubject());

                request.setAttribute("teacherList", teacherService.getAllTeacherForComboBox());

                request.setAttribute("groupList", groupService.getAllGroupInfo());

                address = "/groupPage.jsp";


            }else if (action.equalsIgnoreCase("infoGroup")) {
                Long id = Long.parseLong(request.getParameter("id"));

                Group group = groupService.getGroupInfoById(id);
                request.setAttribute("group", group);

                request.setAttribute("subjects", subjectService.getAllSubjectWithout(group.getSubject().getId()));
                request.setAttribute("teachers", teacherService.getAllTeacherForComboBoxWithout(group.getTeacher().getId()));
                request.setAttribute("students", studentService.getAllStudentInfoByGroupId(id));

                address = "/groupInfo.jsp";

            }else if (action.equalsIgnoreCase("updateGroup")) {
                Long id = Long.valueOf(request.getParameter("id"));

                Group group = new Group();
                group.setGroupName(request.getParameter("groupName"));
                group.setId(id);

                Teacher teacher = new Teacher();
                teacher.setId(Long.parseLong(request.getParameter("teacher")));

                Subject subject = new Subject();
                subject.setId(Long.parseLong(request.getParameter("subject")));

                group.setTeacher(teacher);
                group.setSubject(subject);

                groupService.updateGroup(group);
                response.sendRedirect("http://localhost:8080/groups?action=allGroups&id=" +group.getId());

            }else if (action.equalsIgnoreCase("deleteGroup")){
                Long id = Long.valueOf(request.getParameter("id"));
                groupService.softDeleteGroupById(id);

                response.sendRedirect("http://localhost:8080/groups?action=allGroups");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request,response);

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
