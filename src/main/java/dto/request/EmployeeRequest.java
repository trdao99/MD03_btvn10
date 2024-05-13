package dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.servlet.http.Part;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EmployeeRequest {
    private Integer id;
    private String name;
    private Boolean sex;
    private Date birthday;
    private Part file;

}
