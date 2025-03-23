package co.kshrd.springhomework.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Courses {
    private Integer courseId;
    private String courseName;
    private String description;
    private Instructor instructor;
}
