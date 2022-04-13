package project.mgmt.application.service;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.mockito.Mock;
import project.mgmt.base.UnitBaseTest;
import project.mgmt.domain.model.project_mgmt.project.ClientProject;
import project.mgmt.domain.model.project_mgmt.project.Project;
import project.mgmt.domain.model.project_mgmt.project.ProjectRepository;

import java.util.Map;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

public class ProjectApplicationServiceTest extends UnitBaseTest {
    @Mock
    private ProjectRepository projectRepository;

    @Test
    public void should_return_empty_collection_when_project_id_exists() {
        //given
        Map<String, Set<String>> projectIds = Maps.newHashMap();
        String projectId = "project_id";
        String subProjectId1 = "sub_project_id1";
        String subProjectId2 = "sub_project_id2";
        projectIds.put(projectId, Sets.newHashSet(subProjectId1, subProjectId2));

        Project existInDB = new ClientProject();
        existInDB.setId(projectId);

        Map<String, Set<String>> projectSubProjectIdMappingInDB = Maps.newHashMap();
        projectSubProjectIdMappingInDB.put(projectId, Sets.newHashSet(subProjectId1, subProjectId2));
        when(projectRepository.getProjectSubProjectIdMappingByIds(Sets.newHashSet(projectId)))
                .thenReturn(Maps.newHashMap(projectSubProjectIdMappingInDB));
        ProjectApplicationService projectApplicationService = new ProjectApplicationService(projectRepository);

        //when
        Map<String, Set<String>> actual = projectApplicationService.checkProjectExists(projectIds);

        //then
        assertTrue(actual.entrySet().isEmpty());
    }

    @Test
    public void should_return_project_id_which_not_exists_with_all_sub_project_id_when_project_not_exists() {
        //given
        Map<String, Set<String>> projectIds = Maps.newHashMap();
        String notExistsProjectId = "not_exists_project_id";
        String subProjectId1 = "sub_project_id1";
        String subProjectId2 = "sub_project_id2";
        projectIds.put(notExistsProjectId, Sets.newHashSet(subProjectId1, subProjectId2));

        when(projectRepository.getProjectSubProjectIdMappingByIds(Sets.newHashSet(notExistsProjectId)))
                .thenReturn(Maps.newHashMap());
        ProjectApplicationService projectApplicationService =
                new ProjectApplicationService(this.projectRepository);

        //when
        Map<String, Set<String>> actual = projectApplicationService.checkProjectExists(projectIds);

        //then
        assertTrue(actual.containsKey(notExistsProjectId));
        assertEquals(1, actual.size());
        assertTrue(actual.get(notExistsProjectId).contains(subProjectId1));
        assertTrue(actual.get(notExistsProjectId).contains(subProjectId2));
    }
}