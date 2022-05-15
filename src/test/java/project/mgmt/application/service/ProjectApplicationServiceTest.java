package project.mgmt.application.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.mockito.Mock;
import project.mgmt.application.dto.VerifyProjectExistDTO;
import project.mgmt.base.UnitBaseTest;
import project.mgmt.domain.model.project_mgmt.project.ClientProject;
import project.mgmt.domain.model.project_mgmt.project.Project;
import project.mgmt.domain.model.project_mgmt.project.ProjectRepository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

public class ProjectApplicationServiceTest extends UnitBaseTest {
    @Mock
    private ProjectRepository projectRepository;

    @Test
    public void should_return_empty_collection_when_project_id_exists() {
        //given
        String projectId = "project_id";
        String subProjectId1 = "sub_project_id1";
        String subProjectId2 = "sub_project_id2";
        List<VerifyProjectExistDTO> projectIds = Lists.newArrayList();
        projectIds.add(new VerifyProjectExistDTO(projectId,
                Sets.newHashSet(subProjectId1, subProjectId2)));

        Project existInDB = new ClientProject();
        existInDB.setId(projectId);

        Map<String, Set<String>> projectSubProjectIdMappingInDB = Maps.newHashMap();
        projectSubProjectIdMappingInDB.put(projectId, Sets.newHashSet(subProjectId1, subProjectId2));
        when(projectRepository.getProjectSubProjectIdMappingByIds(Sets.newHashSet(projectId)))
                .thenReturn(Maps.newHashMap(projectSubProjectIdMappingInDB));
        ProjectApplicationService projectApplicationService = new ProjectApplicationService(projectRepository);

        //when
        List<VerifyProjectExistDTO> actual = projectApplicationService.checkProjectExists(projectIds);

        //then
        assertTrue(actual.isEmpty());
    }

    @Test
    public void should_return_project_id_which_not_exists_with_all_sub_project_id_when_project_not_exists() {
        //given
        String notExistsProjectId = "not_exists_project_id";
        String subProjectId1 = "sub_project_id1";
        String subProjectId2 = "sub_project_id2";

        List<VerifyProjectExistDTO> projectIds = Lists.newArrayList();
        projectIds.add(new VerifyProjectExistDTO(notExistsProjectId,
                Sets.newHashSet(subProjectId1, subProjectId2)));

        when(projectRepository.getProjectSubProjectIdMappingByIds(Sets.newHashSet(notExistsProjectId)))
                .thenReturn(Maps.newHashMap());
        ProjectApplicationService projectApplicationService =
                new ProjectApplicationService(this.projectRepository);

        //when
        List<VerifyProjectExistDTO> actual = projectApplicationService.checkProjectExists(projectIds);

        //then
        assertEquals(notExistsProjectId, actual.get(0).getProjectId());
        assertEquals(1, actual.size());
        assertTrue(actual.get(0).getSubprojectIds().contains(subProjectId1));
        assertTrue(actual.get(0).getSubprojectIds().contains(subProjectId2));
    }

    @Test
    public void should_only_return_sub_project_id_which_not_exists_when_sub_project_not_exists() {
        //given
        String projectId = "project_id";
        String subProjectId = "sub_project_id";
        String notExistsSubProjectId = "not_exists_sub_project_id";

        List<VerifyProjectExistDTO> projectIds = Lists.newArrayList();
        projectIds.add(new VerifyProjectExistDTO(projectId,
                Sets.newHashSet(subProjectId, notExistsSubProjectId)));

        Map<String, Set<String>> existsInDB = Maps.newHashMap();
        existsInDB.put(projectId, Sets.newHashSet(subProjectId));
        when(projectRepository.getProjectSubProjectIdMappingByIds(Sets.newHashSet(projectId)))
                .thenReturn(existsInDB);

        ProjectApplicationService projectApplicationService =
                new ProjectApplicationService(this.projectRepository);

        //when
        List<VerifyProjectExistDTO> actual = projectApplicationService.checkProjectExists(projectIds);

        //then
        assertEquals(1, actual.size());
        assertTrue(actual.get(0).getSubprojectIds().contains(notExistsSubProjectId));
        assertFalse(actual.get(0).getSubprojectIds().contains(subProjectId));
    }
}