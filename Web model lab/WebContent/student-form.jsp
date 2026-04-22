<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.studentapp.model.Student" %>
<%
    Student student = (Student) request.getAttribute("student");
    boolean editMode = student != null;
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= editMode ? "Edit Student" : "Add Student" %></title>
</head>
<body>
    <h2><%= editMode ? "Edit Student" : "Add Student" %></h2>

    <form action="student" method="post">
        <input type="hidden" name="action" value="<%= editMode ? "update" : "insert" %>">

        <% if (editMode) { %>
            <input type="hidden" name="id" value="<%= student.getId() %>">
        <% } %>

        <table>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" required value="<%= editMode ? student.getName() : "" %>"></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="email" name="email" required value="<%= editMode ? student.getEmail() : "" %>"></td>
            </tr>
            <tr>
                <td>Course:</td>
                <td><input type="text" name="course" required value="<%= editMode ? student.getCourse() : "" %>"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="submit"><%= editMode ? "Update" : "Save" %></button>
                </td>
            </tr>
        </table>
    </form>

    <p><a href="student?action=list">Back to List</a></p>
</body>
</html>
