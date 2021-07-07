<%@ page import="model.Group" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Teacher" %>
<%@ page import="model.Subject" %>
<%@ page import="model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Teacher> teachers = (List<Teacher>) request.getAttribute("teachers");
    List<Subject> subjects = (List<Subject>) request.getAttribute("subjects");
    List<Student> students = (List<Student>) request.getAttribute("students");
    Group group = (Group) request.getAttribute("group");
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>StudentManagementApp - Payment</title>
    <link href="WEB-INF/css/style.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js"
            integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js"
            integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">SMApp</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0 myNavBar">
                    <li class="nav-item">
                        <a class="nav-link " aria-current="page" href="#">Home Page</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Students</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Teachers</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Subjects</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Groups</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Payments</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>

<div class="container">
    <div style="margin-top: 1%" class="accordion accordion-flush" id="accordionFlushExample">
        <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingOne">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                    Update Group
                </button>
            </h2>
            <div style="text-align: center" id="flush-collapseOne" class="accordion-collapse collapse"
                 aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                <div style="display: inline-block" class="accordion-body">
                    <form method="post" action="/groups?action=updateGroup&id=<%=group.getId()%>" class="row gy-2 gx-3 align-items-center">
                        <div class="col-auto">
                            <label class="visually-hidden" for="autoSizingInput">Name</label>
                            <input name="groupName" type="text" class="form-control" id="autoSizingInput"
                                   value="<%=group.getGroupName()%>" placeholder="Group Name">
                        </div>

                        <div class="col-auto">
                            <label class="visually-hidden" for="autoSizingSelect">Preference</label>
                            <select name="teacher" class="form-select" id="autoSizingSelect1">
                                <option value="<%=group.getTeacher().getId()%>"
                                        selected><%=group.getTeacher().getName()%> <%=group.getTeacher().getSurname()%>
                                </option>
                                <%for (Teacher teacher:teachers) {%>
                                <option value="<%=teacher.getId()%>">
                                    <%=teacher.getName()%> <%=teacher.getSurname()%>
                                </option>
                                <%}%>
                            </select>
                        </div>

                        <div class="col-auto">
                            <label class="visually-hidden" for="autoSizingSelect">Preference</label>
                            <select name="subject" class="form-select" id="autoSizingSelect">
                                <option value="<%=group.getSubject().getId()%>"
                                        selected><%=group.getSubject().getSubjectName()%>
                                </option>
                                <%for (Subject subject:subjects) {%>
                                <option value="<%=subject.getId()%>">
                                    <%=subject.getSubjectName()%>
                                </option>
                                <%}%>
                            </select>
                        </div>

                        <div class="col-auto">
                            <button type="submit" class="btn btn-success">Save</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingTwo">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
                    Add Student
                </button>
            </h2>
            <div id="flush-collapseTwo" class="accordion-collapse collapse" aria-labelledby="flush-headingTwo"
                 data-bs-parent="#accordionFlushExample">
                <div class="accordion-body">
                    <div class="container-fluid">
                        <form class="row g-3" method="post" action="/students?action=saveStudent">
                            <div class="col-md-6">
                                <label for="name" class="form-label">Name</label>
                                <input type="text" class="form-control" name="name" id="name">
                            </div>
                            <div class="col-md-6">
                                <label for="surname" class="form-label">Surname</label>
                                <input type="text" class="form-control" name="surname" id="surname">
                            </div>
                            <div class="col-md-6">
                                <label for="dob" class="form-label">DOB</label>
                                <input type="date" class="form-control" name="dob" id="dob">
                            </div>
                            <div class="col-md-6">
                                <label for="serialNum" class="form-label">Serial Number</label>
                                <input type="text" class="form-control" name="serialNum" id="serialNum">
                            </div>
                            <div class="col-md-6">
                                <label for="email" class="form-label">Email</label>
                                <input type="email" class="form-control" name="email" id="email">
                            </div>
                            <div class="col-md-6">
                                <label for="phone" class="form-label">Phone</label>
                                <input type="tel" class="form-control" name="phone" id="phone">
                            </div>

                            <div class="col-md-12" style="text-align: center">
                                <div class="form-check" style="display: inline-block; margin: 1%">
                                    <input class="form-check-input" value="F" type="radio" name="genderRadio" id="female">
                                    <label class="form-check-label" for="female">
                                        Female
                                    </label>
                                </div>
                                <div class="form-check" style="display: inline-block; margin: 1%">
                                    <input class="form-check-input" value="M" type="radio" name="genderRadio" id="male">
                                    <label class="form-check-label" for="male">
                                        Male
                                    </label>
                                </div>
                                <div class="form-check" style="display: inline-block; margin: 1%">
                                    <input class="form-check-input" value="N" type="radio" name="genderRadio" id="other" checked>
                                    <label class="form-check-label" for="other">
                                        Other
                                    </label>
                                </div>
                            </div>
                            <div class="col-12">
                            </div>
                            <div style="text-align: center" class="col-12">
                                <button type="submit" class="btn btn-success">Save</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <table style="margin-top: 1%" class="table table-hover">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Ad</th>
            <th scope="col">Soyad</th>
            <th scope="col">Telefon</th>
            <th scope="col">Email</th>
            <th scope="col">Info</th>
            <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <%for (Student student : students) {%>
        <tr>
            <th scope="row"><%=student.getId()%></th>
            <td><%=student.getName()%></td>
            <td><%=student.getSurname()%></td>
            <td><%=student.getPhone()%></td>
            <td><%=student.getEmail()%></td>
            <td>
                <a class="btn btn-info" href="/students?action=infoStudent&id=<%=group.getId()%>">Info</a>
            </td>
            <td>
                <button type="button" class="btn btn-danger">Delete</button>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
</body>
</html>