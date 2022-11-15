package pl.mkramek.studentgrader.sorting.impl;

import lombok.NoArgsConstructor;
import pl.mkramek.studentgrader.api.entity.StudentGrade;
import pl.mkramek.studentgrader.sorting.SortingAlgorithm;

import java.util.LinkedList;

@NoArgsConstructor
public class MergeSort extends SortingAlgorithm {

    @Override
    public LinkedList<StudentGrade> sort(final LinkedList<StudentGrade> source) {
        if (source.size() < 2) {
            return source;
        }
        int mid = source.size() / 2;
        return merged(
                sort(new LinkedList<>(source.subList(0, mid))),
                sort(new LinkedList<>(source.subList(mid, source.size())))
        );
    }

    private static LinkedList<StudentGrade> merged(LinkedList<StudentGrade> left, LinkedList<StudentGrade> right) {
        int leftIndex = 0;
        int rightIndex = 0;
        LinkedList<StudentGrade> merged = new LinkedList<>();

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).getGrade() > right.get(rightIndex).getGrade()) {
                merged.add(left.get(leftIndex++));
            } else {
                merged.add(right.get(rightIndex++));
            }
        }
        merged.addAll(left.subList(leftIndex, left.size()));
        merged.addAll(right.subList(rightIndex, right.size()));
        return merged;
    }
}
