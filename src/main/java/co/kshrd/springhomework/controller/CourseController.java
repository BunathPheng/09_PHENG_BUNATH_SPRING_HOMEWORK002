package co.kshrd.springhomework.controller;

import co.kshrd.springhomework.model.Courses;
import co.kshrd.springhomework.model.dto.ApiRespone;
import co.kshrd.springhomework.model.dto.CourseRequest;
import co.kshrd.springhomework.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")

public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("{courses-id}")
    public ResponseEntity<ApiRespone<Courses>> getCourses(@PathVariable("courses-id") Integer coursesId) {
        Courses courses = courseService.getCoursesById(coursesId);
        ApiRespone<Courses> respone = ApiRespone.<Courses>builder()
                .message("The course has been successfully founded.")
                .payload(courses)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(respone, HttpStatus.OK);
    }
    //
    @GetMapping("")
    public ResponseEntity<ApiRespone<List<Courses>>> getCoursesPagination(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "4") Integer size
    ) {
        List<Courses> courses = courseService.getAllCoursesPagination(page , size);
        ApiRespone<List<Courses>> respone = ApiRespone.<List<Courses>>builder()
                .message("The course has been successfully founded.")
                .payload(courses)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(respone, HttpStatus.OK);
    }
    //
    @PutMapping("{course-id}")
    public ResponseEntity<ApiRespone<Courses>> updateToCourse(@RequestBody CourseRequest courseRequest , @PathVariable("course-id") Integer coursesId)
    {
        Courses courses = courseService.updateCourses(courseRequest , coursesId);
        ApiRespone<Courses> respone = ApiRespone.<Courses>builder()
                .message("The course has been successfully founded.")
                .payload(courses)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(respone, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ApiRespone<Courses>> insertToCourse(@RequestBody CourseRequest courseRequest) {
        Courses courses = courseService.addCourses(courseRequest);
        ApiRespone<Courses> respone = ApiRespone.<Courses>builder()
                .message("The course has been successfully added")
                .payload(courses)
                .status(HttpStatus.CREATED)
                .build();
        return new ResponseEntity<>(respone, HttpStatus.CREATED);
    }

    @DeleteMapping("/{course-id}")
    public ResponseEntity<ApiRespone<Courses>> deleteCourse(@PathVariable("course-id") Integer coursesId) {
        Boolean  deleted  =  courseService.deleteCourses(coursesId);
        ApiRespone<Courses>  respone = ApiRespone.<Courses>builder()
                .message("The course has been successfully deleted")
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(respone , HttpStatus.OK);
    }

    
}
