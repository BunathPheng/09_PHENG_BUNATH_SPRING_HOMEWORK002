package co.kshrd.springhomework.repository;
import co.kshrd.springhomework.model.Courses;
import co.kshrd.springhomework.model.Student;
import co.kshrd.springhomework.model.dto.StudentRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentRepository {
    @Select(
            """
            SELECT *
            FROM students s
            INNER JOIN student_course sc ON s.student_id = sc.student_id
            offset #{size} * (#{page} - 1)
            limit #{size}
            """
    )
    @Results ( id = "StudentMapper" , value = {
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "studentName", column = "student_name"),
            @Result(property = "phoneNumber" , column = "phone_number"),
            @Result(property = "courses", column = "student_id",
                    many = @Many(select = "co.kshrd.springhomework.repository.StudentRepository.findCoursesByStudentId")
            )
    }
    )

    List<Student> findStudents(Integer page , Integer size);

    @Results(id = "CourseMapper" , value = {
            @Result(property = "courseId" , column = "course_id"),
            @Result(property = "courseName" , column = "course_name"),
            @Result(property = "instructor" , column = "instructor_id" ,
                    one = @One(select = "co.kshrd.springhomework.repository.InstructorRepository.findInstructorById"))
    })
    @Select("""
        SELECT c.* FROM courses c
        INNER JOIN student_course sc ON c.course_id = sc.course_id
        WHERE sc.student_id = #{studentId}
    """)
    List<Courses> findCoursesByStudentId(Integer studentId);


    @Select("""
     select * from  students s where s.student_id = #{studentId}
""")
    @ResultMap("StudentMapper")
    Student findStudentById(Integer studentId);


    @Select(
       """
    INSERT INTO students (student_name, email, phone_number) values( #{studentRequest.studentName} , #{studentRequest.email} , #{studentRequest.phoneNumber})
     returning student_id;
        """
    )
    Integer insertStudentGetById(@Param("studentRequest") StudentRequest studentRequest);

    //
    @Insert("INSERT INTO student_course (student_id, course_id) VALUES (#{studentId}, #{courseId})")
    void insertStudent(@Param("studentId") Integer studentId , @Param("courseId") Integer courseId);


    @Select(
            """
    update students
    set  student_name = #{studentRequest.studentName} , email = #{studentRequest.email} , phone_number = #{studentRequest.phoneNumber}
    where student_id = #{studentId}
    returning student_id;
    """
    )
    Integer updateStudent(@Param("studentId") Integer studentId , @Param("studentRequest") StudentRequest studentRequest);

    @Insert("""
    insert into student_course(student_id, course_id)
    VALUES (#{studentId}, #{courseId})
    """)
    void updateStudentCourse(@Param("studentId") Integer studentId , @Param("courseId") Integer courseId);

    //
    @Select(
     """
   delete from students where student_id = #{studentId}
    returning true;
    """
    )
    @ResultMap("StudentMapper")
    Boolean  deleteStudentById(Integer studentId);
}
