package co.kshrd.springhomework.repository;
import co.kshrd.springhomework.model.Courses;
import co.kshrd.springhomework.model.dto.CourseRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseRepository {
    @Results(id = "CourseMapper" , value = {
            @Result(property = "courseId" , column = "course_id"),
            @Result(property = "courseName" , column = "course_name"),
            @Result(property = "instructor" , column = "instructor_id" ,
            one = @One(select = "co.kshrd.springhomework.repository.InstructorRepository.findInstructorById"))
    })
    @Select("""
        select *  from courses  
        where course_id=#{id}
""")
    Courses findById(int id);
    //
    @Select(
            """
            select * from courses
            offset #{size} * (#{page}-1)
            limit #{size}
"""
    )
    @ResultMap("CourseMapper")
    List<Courses> findAllCoursesByPagination(Integer page , Integer size);
    @Select(
            """
    update courses 
    set course_name = #{Course.courseName} , description = #{Course.description}, instructor_id = #{Course.instructorId} where course_id=#{id}
    returning *
    """
    )
    //update to database
    @ResultMap({"CourseMapper"})
    Courses updateCourse(@Param("Course") CourseRequest courseRequest , Integer id);

    @Select("""
    insert into  courses values (default, #{Course.courseName}, #{Course.description}, #{Course.instructorId})
     returning *
    """)
    @ResultMap("CourseMapper")
    Courses insertCourse(@Param("Course") CourseRequest courseRequest );

    @Select(
            """
            DELETE FROM courses where course_id=#{id} RETURNING TRUE;
            """
    )
    @ResultMap("CourseMapper")
    Boolean  deleteCourseById(int id);




}
