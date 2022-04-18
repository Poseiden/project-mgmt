package project.mgmt.presentation.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.mgmt.base.APIBaseTest;
import project.mgmt.domain.model.project_mgmt.project.ClientProject;
import project.mgmt.domain.model.project_mgmt.project.SubProject;
import project.mgmt.infrastructure.persistence.hibernate.ClientProjectRepoJPA;
import project.mgmt.infrastructure.persistence.hibernate.ProjectRepoJPA;

import java.util.Map;
import java.util.Set;

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

        Map<String, Set<String>> projectIdParams = Maps.newHashMap();
        projectIdParams.put(saved.getId(), Sets.newHashSet(saved.getSubProjects().get(0).getId()));

        //when
        String resultStr = this.mockMvc.perform(get("/projects/invalid-project-ids?projects={projects}",
                JSON.toJSONString(projectIdParams)))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        //then
        assertTrue(JSON.parseObject(resultStr).getJSONObject("notExistsProjectIds").isEmpty());
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

        Map<String, Set<String>> projectIdParams = Maps.newHashMap();
        String notExistSubProjectId = "not exists sub project id";
        projectIdParams.put(saved.getId(), Sets.newHashSet(saved.getSubProjects().get(0).getId(), notExistSubProjectId));

        //when
        String resultStr = this.mockMvc.perform(get("/projects/invalid-project-ids?projects={projects}",
                JSON.toJSONString(projectIdParams)))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        //then
        assertTrue(JSON.parseObject(resultStr).getJSONObject("notExistsProjectIds").containsKey(clientProject.getId()));
        assertEquals(notExistSubProjectId, JSON.parseObject(resultStr).getJSONObject("notExistsProjectIds").getJSONArray(clientProject.getId()).getString(0));
    }
}