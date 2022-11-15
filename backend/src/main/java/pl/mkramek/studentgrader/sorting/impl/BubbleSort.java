package pl.mkramek.studentgrader.sorting.impl;

import lombok.NoArgsConstructor;
import pl.mkramek.studentgrader.api.entity.StudentGrade;
import pl.mkramek.studentgrader.sorting.SortingAlgorithm;

import java.util.LinkedList;

@NoArgsConstructor
public class BubbleSort extends SortingAlgorithm {
    @Override
    public LinkedList<StudentGrade> sort(LinkedList<StudentGrade> source) {
        final LinkedList<StudentGrade> target = new LinkedList<>(source);
        for (int i = 0; i < target.size() - 1; i++) {
            for (int j = 0; j < target.size() - i - 1; j++) {
                if (target.get(j).getGrade() < target.get(j + 1).getGrade()) {
                    StudentGrade temp = target.get(j);
                    target.set(j, target.get(j + 1));
                    target.set(j + 1, temp);
                }
            }
        }
        return target;
    }
}
