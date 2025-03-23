package co.kshrd.springhomework.service;

import co.kshrd.springhomework.model.Student;
import co.kshrd.springhomework.model.dto.StudentRequest;
import co.kshrd.springhomework.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
     private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listStudentsPagination(Integer pageNo, Integer pageSize) {
        return studentRepository.findStudents(pageNo, pageSize);
    }

    @Override
    public Student getStudentById(Integer studentId) {
        return studentRepository.findStudentById(studentId);
    }

    @Override
    public Student addStudent(StudentRequest studentRequest) {
        Integer getStudentId  = studentRepository.insertStudentGetById(studentRequest);
        for(Integer courseId : studentRequest.getCoursesId()){
        studentRepository.insertStudent(getStudentId , courseId);
        };
        return studentRepository.findStudentById(getStudentId);
    }

    @Override
    public Student updateStudent(Integer studentId, StudentRequest studentRequest) {
        Integer getStudentId  = studentRepository.updateStudent(studentId , studentRequest);
        for(Integer courseId : studentRequest.getCoursesId()){
            studentRepository.updateStudentCourse(getStudentId , courseId);
        };
        return studentRepository.findStudentById(getStudentId);
    }

    @Override
    public Boolean deleteStudent(Integer studentId) {
        return studentRepository.deleteStudentById(studentId);
    }
}
