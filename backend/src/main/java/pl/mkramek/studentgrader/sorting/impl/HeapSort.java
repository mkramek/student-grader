package pl.mkramek.studentgrader.sorting.impl;

import lombok.NoArgsConstructor;
import pl.mkramek.studentgrader.api.entity.StudentGrade;
import pl.mkramek.studentgrader.sorting.SortingAlgorithm;

import java.util.Collections;
import java.util.LinkedList;

@NoArgsConstructor
public class HeapSort extends SortingAlgorithm {
    @Override
    public LinkedList<StudentGrade> sort(LinkedList<StudentGrade> source) {
        final int length = source.size();

        for (int i = length / 2 - 1; i >= 0; i--) {
            maxHeap(source, i, length);
        }

        for(int i = length - 1; i >= 0; i--) {
            Collections.swap(source, i, 0);
            maxHeap(source, 0, i);
        }

        return source;
    }

    public void maxHeap(LinkedList<StudentGrade> source, int index, int size) {
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < size && source.get(left).getGrade() < source.get(largest).getGrade()) {
            largest = left;
        }

        if (right < size && source.get(right).getGrade() < source.get(largest).getGrade()) {
            largest = right;
        }

        if (largest != index) {
            Collections.swap(source, index, largest);
            maxHeap(source, largest, size);
        }
    }
}
