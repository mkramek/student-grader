package pl.mkramek.studentgrader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.mkramek.studentgrader.api.response.SortingResponse;

import java.io.IOException;
import java.io.InputStream;

@Service
public class PerformanceService {

    @Autowired
    SortingService sortingService;

    public ResponseEntity<SortingResponse> handleSorting(MultipartFile file, String sortingMethod) {
        try {
            InputStream fileInputStream = file.getInputStream();
            SortingResponse response = sortingService.sortWithMeasurement(fileInputStream, sortingMethod);
            return ResponseEntity.ok(response);
        } catch (IOException ioe) {
            return ResponseEntity.notFound().build();
        }
    }
}
