package com.studentapp.dao;

import com.studentapp.model.Student;
import com.studentapp.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private static final String INSERT_STUDENT =
            "INSERT INTO student (name, email, course) VALUES (?, ?, ?)";
    private static final String SELECT_STUDENT_BY_ID =
            "SELECT id, name, email, course FROM student WHERE id = ?";
    private static final String SELECT_ALL_STUDENTS =
            "SELECT id, name, email, course FROM student ORDER BY id";
    private static final String UPDATE_STUDENT =
            "UPDATE student SET name = ?, email = ?, course = ? WHERE id = ?";
    private static final String DELETE_STUDENT =
            "DELETE FROM student WHERE id = ?";

    public void insertStudent(Student student) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_STUDENT)) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getCourse());
            statement.executeUpdate();
        }
    }

    public Student selectStudent(int id) throws SQLException {
        Student student = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_STUDENT_BY_ID)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String course = resultSet.getString("course");
                    student = new Student(id, name, email, course);
                }
            }
        }

        return student;
    }

    public List<Student> selectAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_STUDENTS);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String course = resultSet.getString("course");
                students.add(new Student(id, name, email, course));
            }
        }

        return students;
    }

    public boolean updateStudent(Student student) throws SQLException {
        boolean rowUpdated;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT)) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getCourse());
            statement.setInt(4, student.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }

        return rowUpdated;
    }

    public boolean deleteStudent(int id) throws SQLException {
        boolean rowDeleted;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }

        return rowDeleted;
    }
}
