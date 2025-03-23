package co.kshrd.springhomework.repository;
import co.kshrd.springhomework.model.Instructor;
import co.kshrd.springhomework.model.dto.InstructorRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InstructorRepository
    {
        @Select("""
            SELECT *
            FROM instructors 
            WHERE instructor_id = #{id}
        """)
        @Results(id = "instructorResultMap", value = {
                @Result(property = "instructorId", column = "instructor_id"),
                @Result(property = "instructorName", column = "instructor_name"),

        })
        Instructor findInstructorById(Integer id);

        ///
        @ResultMap("instructorResultMap")
        @Select("""
            SELECT *  from instructors
            offset #{pageSize} * (#{pageNo}-1) 
            limit #{pageSize}

        """)
        List<Instructor> findAllInstructorsPagination(Integer pageNo, Integer pageSize);
        ///
        @Select(
                """
            INSERT INTO instructors values(default, #{instructorName}, #{email})
            returning *;
            """
        )
        @ResultMap("instructorResultMap")
        Instructor insertInstructor(InstructorRequest instructorRequest);

        @Select("""
        DELETE FROM instructors
        WHERE instructor_id = #{id}
        returning * 
        """)
        @ResultMap("instructorResultMap")
        Instructor deleteInstructorById(Integer id);

        @Select(
        """
            update instructors
            set instructor_name = #{Instructor.instructorName} , email = #{Instructor.email} where instructor_id = #{id}
            returning *
        """
        )
        @ResultMap("instructorResultMap")
        Instructor updateInstructor(@Param("Instructor") InstructorRequest instructorRequest , Integer id);
    }
