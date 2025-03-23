package co.kshrd.springhomework.service;

import co.kshrd.springhomework.model.Courses;
import co.kshrd.springhomework.model.dto.ApiRespone;
import co.kshrd.springhomework.model.dto.CourseRequest;

import java.util.List;

public interface CourseService {
     Courses getCoursesById(Integer id);
     List<Courses> getAllCoursesPagination(Integer pageNo, Integer pageSize);
     Courses updateCourses(CourseRequest courseRequest , Integer id );
     Courses addCourses(CourseRequest courseRequest );
     Boolean deleteCourses(Integer id);
}
