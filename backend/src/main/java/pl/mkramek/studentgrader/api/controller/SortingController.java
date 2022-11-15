package pl.mkramek.studentgrader.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.mkramek.studentgrader.api.response.MethodsResponse;
import pl.mkramek.studentgrader.api.response.SortingResponse;
import pl.mkramek.studentgrader.service.PerformanceService;

import java.util.List;

@RestController
@CrossOrigin(origins = { "http://localhost:8080" })
@RequestMapping("/api")
public class SortingController {

    @Autowired
    PerformanceService performanceService;

    @PostMapping("/sort")
    public ResponseEntity<SortingResponse> sortFromFile(
            @RequestParam(name = "file") MultipartFile file,
            @RequestParam(name = "sorting") String sortingMethod
            ) {
        return performanceService.handleSorting(file, sortingMethod);
    }

    @GetMapping("/methods")
    public ResponseEntity<MethodsResponse> getSortingMethods() {
        return ResponseEntity.ok(new MethodsResponse(List.of("bubble", "heap", "merge")));
    }
}
