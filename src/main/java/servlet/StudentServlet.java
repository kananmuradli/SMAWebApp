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
import model.Student;
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

@WebServlet(urlPatterns = "/students", name = "StudentServlet")
public class StudentServlet extends HttpServlet {

    StudentDAO studentDAO = new StudentDAOImpl();
    StudentService studentService = new StudentServiceImpl(studentDAO);

    GroupDAO groupDao = new GroupDAOImpl();
    GroupService groupService = new GroupServiceImpl(groupDao);

    TeacherDAO teacherDAO = new TeacherDAOImpl();
    TeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    SubjectDAO subjectDAO = new SubjectDAOImpl();
    SubjectService subjectService = new SubjectServiceImpl(subjectDAO);

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
            String address = null;

            if (request.getParameter("action") != null){
                action = request.getParameter("action");
            }
            if (action.equalsIgnoreCase("saveStudent")) {
                Student student = new Student();
                student.setName(request.getParameter("name"));
                student.setSurname(request.getParameter("surname"));
                student.setEmail(request.getParameter("email"));
                student.setPhone(request.getParameter("phone"));
                student.setSeriaNum(request.getParameter("serialNum"));
                student.setGender(request.getParameter("genderRadio"));
                student.setDOB(request.getParameter("dob"));


                Group group = new Group();
                group.setId(Long.parseLong(request.getParameter("groupId")));
                student.setGroup(group);

                studentService.saveStudent(student);
                response.sendRedirect("http://localhost:8080/groups?action=infoGroups&id=" +group.getId());

            }else if (action.equalsIgnoreCase("infoStudent")) {
                Long id = Long.parseLong(request.getParameter("id"));

                Student student = (Student) studentDAO.getAllStudentInfoByGroupId();
                request.setAttribute("student", student);

                address = "/updateStudent.jsp";

            }else if (action.equalsIgnoreCase("updateStudent")) {
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
            }

            if(address != null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                dispatcher.forward(request, response);
            }

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
