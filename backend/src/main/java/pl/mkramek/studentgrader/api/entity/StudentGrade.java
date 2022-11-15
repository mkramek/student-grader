package pl.mkramek.studentgrader.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentGrade {
    private String name;
    private double grade;
}
