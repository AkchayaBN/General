<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.studentapp.model.Student" %>
<%
    List<Student> students = (List<Student>) request.getAttribute("students");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student List</title>
</head>
<body>
    <h2>Student List</h2>

    <p><a href="student?action=new">Add New Student</a></p>

    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Course</th>
            <th>Actions</th>
        </tr>
        <% if (students != null) {
            for (Student s : students) { %>
        <tr>
            <td><%= s.getId() %></td>
            <td><%= s.getName() %></td>
            <td><%= s.getEmail() %></td>
            <td><%= s.getCourse() %></td>
            <td>
                <a href="student?action=edit&id=<%= s.getId() %>">Edit</a>
                <a href="student?action=delete&id=<%= s.getId() %>" onclick="return confirm('Delete this student?');">Delete</a>
            </td>
        </tr>
        <%  }
           } %>
    </table>

    <p><a href="index.jsp">Home</a></p>
</body>
</html>
