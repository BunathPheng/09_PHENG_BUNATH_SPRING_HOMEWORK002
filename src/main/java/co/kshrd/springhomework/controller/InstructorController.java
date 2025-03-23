package co.kshrd.springhomework.controller;

import co.kshrd.springhomework.model.Instructor;
import co.kshrd.springhomework.model.dto.ApiRespone;
import co.kshrd.springhomework.model.dto.InstructorRequest;
import co.kshrd.springhomework.service.InstructorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }


    @GetMapping("/{instructor-id}")
    @Operation(summary = "Get instructor by id")
    public ResponseEntity<ApiRespone<Instructor>> getInstructorById( Integer  instructorId) {
        Instructor  instructor = instructorService.findInstructorById(instructorId);
        ApiRespone<Instructor> response = new ApiRespone<>();
        response.setMessage("Instructor found");
        response.setPayload(instructor);
        response.setStatus(HttpStatus.OK);
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/instructors")
    @Operation(summary = "Get All instructor")
    public ResponseEntity<ApiRespone<List<Instructor>>> getAllInstructors(
            @RequestParam(defaultValue = "1") Integer page ,
            @RequestParam(defaultValue = "4") Integer size
    ) {
        List<Instructor>  instructors = instructorService.findAllInstructorsPagination( page ,  size);
        ApiRespone<List<Instructor>> response = ApiRespone.<List<Instructor>>builder()
                .message("The  instructors has been successfully founded")
                .payload(instructors)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    @PostMapping("/instructors")
    @Operation(summary = "Post Instructor")
    public ResponseEntity<ApiRespone<Instructor>> postInstructor(@RequestBody InstructorRequest instructorRequest) {
        Instructor instructor = instructorService.insertToInstructor(instructorRequest);
        ApiRespone<Instructor> response = ApiRespone.<Instructor>builder()
                .message("The instructors created successfully ")
                .payload(instructor)
                .status(HttpStatus.CREATED)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{instructor-id}")
    public ResponseEntity<ApiRespone<Instructor>> deleteInstructor(@PathVariable("instructor-id") Integer instructorId) {
        Instructor instructor = instructorService.deleteFromInstructor(instructorId);
        ApiRespone<Instructor> response = ApiRespone.<Instructor>builder()
                .message("The instructors deleted successfully ")
                .payload(null)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{instructor-id}")
    public ResponseEntity<ApiRespone<Instructor>> upDateInstructor(@RequestBody InstructorRequest instructorRequest , @PathVariable("instructor-id") Integer instructorId) {
        Instructor instructor = instructorService.updateInstructor( instructorRequest, instructorId);
        ApiRespone<Instructor> respone = ApiRespone.<Instructor>builder()
                .message("The instructor has been successfully updated.")
                .payload(instructor)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(respone, HttpStatus.OK);
    }

}