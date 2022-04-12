package project.mgmt.presentation.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.assertj.core.util.Lists;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.mgmt.base.APIBaseTest;
import project.mgmt.domain.model.project_mgmt.project.ClientProject;
import project.mgmt.domain.model.project_mgmt.project.SubProject;
import project.mgmt.infrastructure.persistence.hibernate.ProjectRepoJPA;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static project.mgmt.domain.model.project_mgmt.location.Location.CN;

public class ProjectControllerTest extends APIBaseTest {

    @Autowired
    private ProjectRepoJPA projectRepoJPA;

    @Test
    @Ignore
    public void should_return_empty_list_when_project_id_list_exists() throws Exception {
        //given
        ClientProject clientProject = new ClientProject();
        clientProject.setName("project name");
        clientProject.setLocation(CN);
        clientProject.setProjectManager("manager");
        SubProject subProject = new SubProject();
        subProject.setName("this is sub project");
        clientProject.setSubProjects(Lists.newArrayList(subProject));

        ClientProject saved = this.projectRepoJPA.save(clientProject);

        Map<String, Set<String>> projectIdParams = Maps.newHashMap();
        projectIdParams.put(saved.getId(), Sets.newHashSet(saved.getSubProjects().get(0).getId()));

        //when
        String resultStr = this.mockMvc.perform(get("/projects/batch")
                .contentType(APPLICATION_JSON)
                .content(JSON.toJSONString(projectIdParams)))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        //then
        assertTrue(JSON.parseObject(resultStr).isEmpty());
    }
}