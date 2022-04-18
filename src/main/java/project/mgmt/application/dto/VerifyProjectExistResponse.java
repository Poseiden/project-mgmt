package project.mgmt.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyProjectExistResponse {
    @NonNull
    private Map<String, Set<String>> notExistsProjectIds;
}
