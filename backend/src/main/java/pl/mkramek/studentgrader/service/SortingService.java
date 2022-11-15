package pl.mkramek.studentgrader.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import pl.mkramek.studentgrader.api.entity.StudentGrade;
import pl.mkramek.studentgrader.api.response.SortingResponse;
import pl.mkramek.studentgrader.sorting.SortingAlgorithm;
import pl.mkramek.studentgrader.sorting.impl.BubbleSort;
import pl.mkramek.studentgrader.sorting.impl.HeapSort;
import pl.mkramek.studentgrader.sorting.impl.MergeSort;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SortingService {

    public SortingResponse sortWithMeasurement(final InputStream inputStream, final String sortingMethod) {
        StopWatch stopWatch = new StopWatch();
        SortingAlgorithm algorithm = selectAlgorithm(sortingMethod);
        List<String[]> fileData = listFromInputStream(inputStream);
        LinkedList<StudentGrade> performances = mapStringArrayToStudentPerformance(fileData);
        try {
            stopWatch.start();
            LinkedList<StudentGrade> target = algorithm.sort(performances);
            stopWatch.stop();
            return new SortingResponse("Sorting finished", target, stopWatch.getLastTaskTimeNanos());
        } catch (Error err) {
            stopWatch.stop();
            return new SortingResponse("Sorting failed: " + err.getMessage(), null, stopWatch.getLastTaskTimeNanos());
        }
    }

    private LinkedList<StudentGrade> mapStringArrayToStudentPerformance(final List<String[]> rawStrings) {
        LinkedList<StudentGrade> result = new LinkedList<>();
        for (String[] rawData : rawStrings) {
            result.add(new StudentGrade(rawData[0], Double.parseDouble(rawData[1])));
        }
        return result;
    }

    private List<String[]> listFromInputStream(InputStream inputStream) {
        List<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines().toList();
        return lines.stream().map(line -> line.split(",")).collect(Collectors.toList());
    }

    private SortingAlgorithm selectAlgorithm(String sortingMethod) {
        return switch (sortingMethod) {
            case "merge" -> new MergeSort();
            case "bubble" -> new BubbleSort();
            case "heap" -> new HeapSort();
            default -> new SortingAlgorithm();
        };
    }
}
