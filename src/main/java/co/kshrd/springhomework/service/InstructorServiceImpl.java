package co.kshrd.springhomework.service;

import co.kshrd.springhomework.model.Instructor;
import co.kshrd.springhomework.model.dto.InstructorRequest;
import co.kshrd.springhomework.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;

    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public Instructor findInstructorById(int id) {
        return instructorRepository.findInstructorById(id);
    }

    @Override
    public List<Instructor> findAllInstructorsPagination(Integer pageNo , Integer pageSize) {
        return instructorRepository.findAllInstructorsPagination(pageNo, pageSize);
    }

    @Override
    public Instructor insertToInstructor(InstructorRequest instructorRequest) {
        return instructorRepository.insertInstructor(instructorRequest);
    }

    @Override
    public Instructor deleteFromInstructor(Integer id) {
        return instructorRepository.deleteInstructorById(id);
    }

    @Override
    public Instructor updateInstructor(InstructorRequest instructorRequest , Integer id) {
        return instructorRepository.updateInstructor(instructorRequest , id);
    }

}
