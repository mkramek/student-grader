package pl.mkramek.studentgrader.api.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MethodsResponse {
    private List<String> methods;
}
