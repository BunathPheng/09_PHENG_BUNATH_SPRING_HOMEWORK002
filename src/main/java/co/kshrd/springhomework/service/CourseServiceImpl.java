package co.kshrd.springhomework.service;

import co.kshrd.springhomework.model.Courses;
import co.kshrd.springhomework.model.dto.CourseRequest;
import co.kshrd.springhomework.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Courses getCoursesById(Integer id) {
        return courseRepository.findById(id);
    }

    @Override
    public List<Courses> getAllCoursesPagination(Integer pageNo, Integer pageSize) {
        return courseRepository.findAllCoursesByPagination(pageNo, pageSize);
    }

    @Override
    public Courses updateCourses(CourseRequest courseRequest , Integer id) {
        return courseRepository.updateCourse(courseRequest, id);
    }

    @Override
    public Courses addCourses(CourseRequest courseRequest) {
        return courseRepository.insertCourse(courseRequest);
    }

    @Override
    public Boolean deleteCourses(Integer id) {
        return courseRepository.deleteCourseById(id);
    }
}
