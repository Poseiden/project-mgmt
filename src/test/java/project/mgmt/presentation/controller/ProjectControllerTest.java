package project.mgmt.presentation.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.mgmt.application.dto.VerifyProjectExistDTO;
import project.mgmt.base.APIBaseTest;
import project.mgmt.domain.model.project_mgmt.project.ClientProject;
import project.mgmt.domain.model.project_mgmt.project.SubProject;
import project.mgmt.infrastructure.persistence.hibernate.ClientProjectRepoJPA;
import project.mgmt.infrastructure.persistence.hibernate.ProjectRepoJPA;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static project.mgmt.domain.model.project_mgmt.location.Location.CN;

public class ProjectControllerTest extends APIBaseTest {

    @Autowired
    private ProjectRepoJPA projectRepoJPA;
    @Autowired
    private ClientProjectRepoJPA clientProjectRepoJPA;

    @Test
    public void should_return_empty_list_when_project_id_list_exists() throws Exception {
        //given
        ClientProject clientProject = new ClientProject();
        clientProject.setName("project name");
        clientProject.setLocation(CN);
        clientProject.setProjectManagerId("manager");
        SubProject subProject = new SubProject();
        subProject.setName("this is sub project");
        subProject.setProject(clientProject);
        clientProject.setSubProjects(Lists.newArrayList(subProject));

        ClientProject saved = this.projectRepoJPA.save(clientProject);

        List<VerifyProjectExistDTO> projectIdParams = Lists.newArrayList();
        projectIdParams.add(new VerifyProjectExistDTO(saved.getId(),
                Sets.newHashSet(saved.getSubProjects().get(0).getId())));

        //when
        String resultStr = this.mockMvc.perform(get("/projects/invalid-project-ids?projects={projects}",
                JSON.toJSONString(projectIdParams)))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        //then
        assertTrue(JSON.parseArray(resultStr).isEmpty());
    }

    @Test
    public void should_return_not_exists_project_id_when_project_id_not_exists() throws Exception {
        //given
        ClientProject clientProject = new ClientProject();
        clientProject.setName("project name");
        clientProject.setLocation(CN);
        clientProject.setProjectManagerId("manager");
        SubProject subProject = new SubProject();
        subProject.setName("this is sub project");
        clientProject.setSubProjects(Lists.newArrayList(subProject));

        ClientProject saved = this.clientProjectRepoJPA.save(clientProject);

        List<VerifyProjectExistDTO> projectIdParams = Lists.newArrayList();
        projectIdParams.add(new VerifyProjectExistDTO(saved.getId(),
                Sets.newHashSet(saved.getSubProjects().get(0).getId(), "not exists sub project id")));

        //when
        String resultStr = this.mockMvc.perform(get("/projects/invalid-project-ids?projects={projects}",
                JSON.toJSONString(projectIdParams)))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        //then
        assertEquals(clientProject.getId(), JSON.parseArray(resultStr).getJSONObject(0).getString("projectId"));
        assertEquals("not exists sub project id", JSON.parseArray(resultStr).getJSONObject(0).getJSONArray("subprojectIds").getString(0));
    }
}