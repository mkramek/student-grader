package pl.mkramek.studentgrader.api.response;

import lombok.*;
import pl.mkramek.studentgrader.api.entity.StudentGrade;

import java.util.LinkedList;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SortingResponse {
    private String message = "";
    private LinkedList<StudentGrade> grades;
    private Long timeElapsed = -1L;
}
