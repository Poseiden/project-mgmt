package project.mgmt.application.service;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;
import project.mgmt.base.UnitBaseTest;

import java.util.Map;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;

public class ProjectApplicationServiceTest extends UnitBaseTest {
    @Test
    public void should_return_empty_collection_when_project_id_exists() {
        //given
        ProjectApplicationService projectApplicationService = new ProjectApplicationService();

        Map<String, Set<String>> projectIds = Maps.newHashMap();
        projectIds.put("projectId", Sets.newHashSet("subproject_id"));

        //when
        Map<String, Set<String>> actual = projectApplicationService.checkProjectExists(projectIds);

        //then
        assertTrue(actual.entrySet().isEmpty());
    }
}