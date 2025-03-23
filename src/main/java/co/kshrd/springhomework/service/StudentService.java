package co.kshrd.springhomework.service;

import co.kshrd.springhomework.model.Student;
import co.kshrd.springhomework.model.dto.StudentRequest;

import java.util.List;

public interface StudentService {
    List<Student> listStudentsPagination(Integer pageNo, Integer pageSize);
    Student getStudentById(Integer studentId);
    Student addStudent(StudentRequest studentRequest);
    Student updateStudent(Integer studentId,StudentRequest studentRequest);
    Boolean deleteStudent(Integer studentId);
}
