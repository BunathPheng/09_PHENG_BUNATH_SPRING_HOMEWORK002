package co.kshrd.springhomework.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequest {
    private String studentName;
    private String email;
    private String phoneNumber;
    private List<Integer> coursesId;
}
