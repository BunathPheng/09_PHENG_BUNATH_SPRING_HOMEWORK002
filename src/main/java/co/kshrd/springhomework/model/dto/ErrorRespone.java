package co.kshrd.springhomework.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorRespone  {
    private String type;
    private String title;
    private HttpStatus status;
    private String detail;
    private String instance;
    private String timestamp;
}
