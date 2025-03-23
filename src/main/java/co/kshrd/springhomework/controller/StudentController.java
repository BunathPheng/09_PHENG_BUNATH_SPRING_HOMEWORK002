package co.kshrd.springhomework.controller;
import co.kshrd.springhomework.model.Student;
import co.kshrd.springhomework.model.dto.ApiRespone;
import co.kshrd.springhomework.model.dto.StudentRequest;
import co.kshrd.springhomework.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    public ResponseEntity<ApiRespone<List<Student>>> listAllStudentsPagination(
            @RequestParam(defaultValue = "1" ) Integer page  ,
            @RequestParam(defaultValue = "2") Integer size
    ) {
        List<Student> students = studentService.listStudentsPagination(page, size);
        ApiRespone<List<Student>> data =ApiRespone.<List<Student>>builder()
                .message("All students have been successfully fetched")
                .payload(students)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(data , HttpStatus.OK);
    }
    @GetMapping("{course-id}")
    public ResponseEntity<ApiRespone<Student>> getStudentById(@PathVariable("course-id") Integer courseId) {
        Student student = studentService.getStudentById(courseId);
        ApiRespone<Student> respone = ApiRespone.<Student>
                builder()
                .message("The course has been found successfully")
                .payload(student)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(respone , HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<ApiRespone<Student>> createStudent(@RequestBody StudentRequest  studentRequest) {
        Student student  = studentService.addStudent(studentRequest);
        ApiRespone<Student> respone = ApiRespone.<Student>builder()
                .message("The student has been successfully created")
                .payload(student)
                .status(HttpStatus.CREATED)
                .build();
        return new ResponseEntity<>(respone , HttpStatus.CREATED);
    }
    @PutMapping("{student-id}")
    public ResponseEntity<ApiRespone<Student>> updateStudent(@PathVariable("student-id") Integer studentId, @RequestBody StudentRequest studentRequest) {
        Student student  = studentService.updateStudent(studentId , studentRequest);
        ApiRespone<Student> respone = ApiRespone.<Student>builder()
                .message("The student has been successfully updated")
                .payload(student)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(respone , HttpStatus.OK);
    }
    @DeleteMapping("{student-id}")
    public ResponseEntity<ApiRespone<Student>> deleteStudent(@PathVariable("student-id") Integer studentId) {
        Boolean deleted = studentService.deleteStudent(studentId);
        ApiRespone<Student> respone = ApiRespone.<Student>builder()
                .message("The student has been deleted")
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(respone , HttpStatus.OK);
    }
}
