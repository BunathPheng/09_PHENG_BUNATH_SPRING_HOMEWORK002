package co.kshrd.springhomework.service;

import co.kshrd.springhomework.model.Instructor;
import co.kshrd.springhomework.model.dto.InstructorRequest;

import java.util.List;

public interface InstructorService {
    Instructor findInstructorById(int id);
    List<Instructor> findAllInstructorsPagination(Integer pageNo, Integer pageSize);
    Instructor insertToInstructor(InstructorRequest instructorRequest);
    Instructor deleteFromInstructor(Integer id);
    Instructor updateInstructor( InstructorRequest instructorRequest , Integer id);
}
